package hr.autorepair.shop.domain.joborder.service;

import hr.autorepair.shop.domain.car.repository.CarRepository;
import hr.autorepair.shop.domain.joborder.dto.AddJobOrderRequest;
import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.domain.joborder.util.AddJobOrderValidation;
import hr.autorepair.shop.domain.joborderstatus.model.JobOrderStatus;
import hr.autorepair.shop.domain.joborderstatus.repository.JobOrderStatusRepository;
import hr.autorepair.shop.domain.joborderstatus.util.JobOrderStatusEnum;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.repair.repository.RepairRepository;
import hr.autorepair.shop.domain.schedule.service.ScheduleService;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.AppProperties;
import hr.autorepair.shop.util.MailUtility;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static hr.autorepair.common.constants.MailConstants.VEHICLE_SERVICED_MAIL_BODY;
import static hr.autorepair.common.constants.MailConstants.VEHICLE_SERVICED_MAIL_SUBJECT;
import static hr.autorepair.common.constants.MessageConstants.JOB_ORDER_NOT_EXISTS;
import static hr.autorepair.common.constants.MessageConstants.RECEIPT_NOT_EXIST_FOR_JOB_ORDER;

@Service
@AllArgsConstructor
@EqualsAndHashCode
public class JobOrderServiceImpl implements JobOrderService{

    private final JobOrderRepository jobOrderRepository;
    private final CarRepository carRepository;
    private final RepairRepository repairRepository;
    private final JobOrderStatusRepository jobOrderStatusRepository;
    private final ScheduleService scheduleService;
    private final AddJobOrderValidation addJobOrderValidation;
    private final MailUtility mailUtility;
    private final JavaMailSender javaMailSender;
    private final ModelMapper modelMapper;
    private final AppProperties appProperties;

    @Override
    public List<JobOrderResponse> getAllJobOrders() {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser())
            return jobOrderRepository.findByIdAppUser(userPrincipal.getAppUser().getIdAppUser()).stream()
                    .map(this::mapToJobOrderResponse)
                    .toList();
        else
            return jobOrderRepository.findAll().stream()
                    .map(this::mapToJobOrderResponse)
                    .toList();
    }

    @Override
    public JobOrderResponse getJobOrder(Long idJobOrder) {
        JobOrder jobOrder = jobOrderRepository.findByIdJobOrder(idJobOrder)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, idJobOrder)));

        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser() && !jobOrder.getCar().getCarOwner().getIdAppUser().equals(userPrincipal.getAppUser().getIdAppUser()))
            throw new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, idJobOrder));

        return mapToJobOrderResponse(jobOrder);
    }

    @Override
    public JobOrderResponse addJobOrder(AddJobOrderRequest request) {
        JobOrder jobOrder = new JobOrder();
        addJobOrderValidation.validateAndFillJobOrder(jobOrder, request);
        jobOrderRepository.save(jobOrder);
        return mapToJobOrderResponse(jobOrder);
    }

    @Override
    public JobOrderResponse incrementStatus(Long idJobOrder) {
        JobOrder jobOrder = jobOrderRepository.findByIdJobOrder(idJobOrder)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, idJobOrder)));

        JobOrderStatusEnum newStatus;
        if(JobOrderStatusEnum.CREATED.getName().equals(jobOrder.getJobOrderStatus().getName()))
            newStatus = JobOrderStatusEnum.IN_PROGRESS;
        else if(JobOrderStatusEnum.IN_PROGRESS.getName().equals(jobOrder.getJobOrderStatus().getName())){
            newStatus = JobOrderStatusEnum.FINISHED;

            if(Boolean.TRUE.equals(Boolean.parseBoolean(appProperties.getProperty("app.send.mail.car.finished")))) {
                String email = jobOrder.getCar().getCarOwner().getEmail();
                SimpleMailMessage message = mailUtility.getSimpleMailMessage();
                message.setTo(email);
                message.setSubject(VEHICLE_SERVICED_MAIL_SUBJECT);
                message.setText(MessageFormat.format(VEHICLE_SERVICED_MAIL_BODY, email));
                javaMailSender.send(message);
            }
        }
        else
            throw new BadRequestException("Job order has reached its final stage. It is completed!");

        JobOrderStatus jobOrderStatus = jobOrderStatusRepository.findByName(newStatus.getName())
                        .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, newStatus.getName())));
        jobOrder.setJobOrderStatus(jobOrderStatus);
        jobOrderRepository.save(jobOrder);
        return mapToJobOrderResponse(jobOrder);
    }

    @Override
    public void deactivateJobOrder(Long idJobOrder) {
        JobOrder jobOrder = jobOrderRepository.findByIdJobOrder(idJobOrder)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, idJobOrder)));
        jobOrder.setIsDeleted(true);
        jobOrderRepository.save(jobOrder);
    }

    @Override
    public ReceiptResponse getReceiptByIdJobOrder(Long idJobOrder) {
        JobOrder jobOrder = jobOrderRepository.findByIdJobOrder(idJobOrder)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, idJobOrder)));

        List<Receipt> receipts = jobOrder.getReceipts().stream()
                .filter(receipt -> !receipt.getIsDeleted())
                .toList();

        if(receipts.isEmpty())
            throw new BadRequestException(MessageFormat.format(RECEIPT_NOT_EXIST_FOR_JOB_ORDER, idJobOrder));

        return modelMapper.map(receipts.get(0), ReceiptResponse.class);
    }

    private JobOrderResponse mapToJobOrderResponse(JobOrder jobOrder){
        JobOrderResponse jobOrderResponse = modelMapper.map(jobOrder, JobOrderResponse.class);
        boolean receiptIsGiven = jobOrder.getReceipts().stream()
                        .anyMatch(receipt -> !receipt.getIsDeleted());
        jobOrderResponse.setIsReceiptGiven(receiptIsGiven);
        return jobOrderResponse;
    }

}
