package hr.autorepair.shop.domain.joborder.service;

import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
