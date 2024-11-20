package hr.autorepair.shop.domain.joborderstatus.service;

import hr.autorepair.shop.domain.joborderstatus.dto.JobOrderStatusResponse;
import hr.autorepair.shop.domain.joborderstatus.repository.JobOrderStatusRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobOrderStatusServiceImpl implements JobOrderStatusService{

    private final JobOrderStatusRepository jobOrderStatusRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<JobOrderStatusResponse> getJobOrderStatuses() {
        return jobOrderStatusRepository.findAll().stream()
                .map(jobOrderStatus -> modelMapper.map(jobOrderStatus, JobOrderStatusResponse.class))
                .toList();
    }

}
