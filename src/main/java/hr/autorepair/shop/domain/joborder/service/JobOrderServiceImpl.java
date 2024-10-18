package hr.autorepair.shop.domain.joborder.service;

import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static hr.autorepair.common.constants.MessageConstants.JOB_ORDER_NOT_EXISTS;

@Service
@AllArgsConstructor
@EqualsAndHashCode
public class JobOrderServiceImpl implements JobOrderService{

    private final JobOrderRepository jobOrderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<JobOrderResponse> getAllJobOrders() {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser())
            return jobOrderRepository.findByIdAppUser(userPrincipal.getIdAppUser()).stream()
                    .map(jobOrder -> modelMapper.map(jobOrder, JobOrderResponse.class))
                    .toList();
        else
            return jobOrderRepository.findAll().stream()
                    .map(jobOrder -> modelMapper.map(jobOrder, JobOrderResponse.class))
                    .toList();
    }

    @Override
    public JobOrderResponse getJobOrder(Long idJobOrder) {
        JobOrder jobOrder = jobOrderRepository.findByIdJobOrder(idJobOrder)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, idJobOrder)));

        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isUser() && !jobOrder.getCar().getCarOwner().getIdAppUser().equals(userPrincipal.getIdAppUser()))
            throw new BadRequestException(MessageFormat.format(JOB_ORDER_NOT_EXISTS, idJobOrder));

        return modelMapper.map(jobOrder, JobOrderResponse.class);
    }

}
