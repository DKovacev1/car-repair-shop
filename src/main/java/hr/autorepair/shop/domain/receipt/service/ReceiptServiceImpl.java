package hr.autorepair.shop.domain.receipt.service;

import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.domain.joborderstatus.util.JobOrderStatusEnum;
import hr.autorepair.shop.domain.payment.model.Payment;
import hr.autorepair.shop.domain.payment.repository.PaymentRepository;
import hr.autorepair.shop.domain.receipt.dto.AddReceiptRequest;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.receipt.repository.ReceiptRepository;
import hr.autorepair.shop.domain.repair.model.Repair;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static hr.autorepair.common.constants.BusinessConstants.LOYALTY_JOB_ORDER_DISCOUNT;
import static hr.autorepair.common.constants.BusinessConstants.MIN_LOYALTY_JOB_ORDER_COUNT;
import static hr.autorepair.common.constants.MessageConstants.*;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;
    private ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;
    private final JobOrderRepository jobOrderRepository;

    @Override
    public List<ReceiptResponse> getAllReceipts() {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser())
            return receiptRepository.findByIdAppUser(userPrincipal.getAppUser().getIdAppUser()).stream()
                    .map(this::mapToReceiptResponse)
                    .toList();
        else
            return receiptRepository.findByIsDeletedFalse().stream()
                    .map(this::mapToReceiptResponse)
                    .toList();
    }

    @Override
    public ReceiptResponse getReceipt(Long idReceipt) {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        Receipt receipt = receiptRepository.findByIdReceiptAndIsDeletedFalse(idReceipt)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(RECEIPT_NOT_EXIST, idReceipt)));

        if(userPrincipal.isUser()){
            receipt.getJobOrders().stream()
                    .filter(jobOrder -> !jobOrder.getIsDeleted())
                    .findFirst().ifPresent(jobOrder -> {
                        if(!jobOrder.getCar().getCarOwner().getIdAppUser().equals(userPrincipal.getAppUser().getIdAppUser()))
                            throw new BadRequestException(MessageFormat.format(RECEIPT_NOT_EXIST, idReceipt));
                    });
        }

        return mapToReceiptResponse(receipt);
    }

    @Override
    public ReceiptResponse addReceipt(AddReceiptRequest request) {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        BigDecimal additionalDiscount = request.getAdditionalDiscount() != null ? request.getAdditionalDiscount() : BigDecimal.ZERO;
        Payment payment = paymentRepository.findById(request.getIdPayment())
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(PAYMENT_NOT_EXIST, request.getIdPayment())));
        JobOrder jobOrder = jobOrderRepository.findByIdJobOrder(request.getIdJobOrder())
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, request.getIdJobOrder())));

        if(!JobOrderStatusEnum.FINISHED.getName().equals(jobOrder.getJobOrderStatus().getName()))
            throw new BadRequestException(JOB_ORDER_NOT_FINISHED);

        if(jobOrderRepository.exitsActiveReceiptForJobOrder(request.getIdJobOrder()))
            throw new BadRequestException(RECEIPT_ALREADY_CREATED);

        BigDecimal loyaltyDiscount = getLoyaltyDiscountForUser(jobOrder.getCar().getCarOwner().getIdAppUser());
        BigDecimal totalDiscount = additionalDiscount
                .add(payment.getDiscount())
                .add(loyaltyDiscount);

        BigDecimal repairCostSum = jobOrder.getRepairs().stream()
                .map(Repair::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal partsCostSum = jobOrder.getParts().stream()
                .map(jobOrderPart -> jobOrderPart.getPart().getCost()
                        .multiply(BigDecimal.valueOf(jobOrderPart.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCost = repairCostSum.add(partsCostSum)
                .multiply(BigDecimal.ONE.subtract(totalDiscount));

        Receipt receipt = new Receipt();
        receipt.setCreatedAt(LocalDateTime.now());
        receipt.setLoyaltyDiscount(loyaltyDiscount);
        receipt.setAdditionalDiscount(additionalDiscount);
        receipt.setRepairCostSum(repairCostSum);
        receipt.setPartsCostSum(partsCostSum);
        receipt.setTotalCost(totalCost);
        receipt.setIsDeleted(false);
        receipt.setPayment(payment);
        Set<JobOrder> jobOrders = new HashSet<>();//only one!
        jobOrders.add(jobOrder);
        receipt.setJobOrders(jobOrders);
        receipt.setReceiptAppUserEmployee(userPrincipal.getAppUser());

        receiptRepository.save(receipt);
        return mapToReceiptResponse(receipt);
    }

    @Override
    public void deactivateReceipt(Long idReceipt) {
        Receipt receipt = receiptRepository.findByIdReceiptAndIsDeletedFalse(idReceipt)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(RECEIPT_NOT_EXIST, idReceipt)));
        receipt.setIsDeleted(true);
        receiptRepository.save(receipt);
    }

    private BigDecimal getLoyaltyDiscountForUser(Long idAppUser){
        BigDecimal loyaltyDiscount = BigDecimal.ZERO;

        long orderCountForPastYear = jobOrderRepository
                .findJobOrdersForUserAfterDate(idAppUser, LocalDate.now().minusYears(1));

        if(orderCountForPastYear >= MIN_LOYALTY_JOB_ORDER_COUNT)
            loyaltyDiscount = LOYALTY_JOB_ORDER_DISCOUNT;

        return loyaltyDiscount;
    }

    private ReceiptResponse mapToReceiptResponse(Receipt receipt){
        ReceiptResponse response = modelMapper.map(receipt, ReceiptResponse.class);
        Optional<JobOrder> jobOrderOpt = receipt.getJobOrders().stream().findFirst();
        jobOrderOpt.ifPresent(jobOrder -> response.setJobOrder(modelMapper.map(jobOrder, JobOrderResponse.class)));
        return response;
    }

}
