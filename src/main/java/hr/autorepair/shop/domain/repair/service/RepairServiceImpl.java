package hr.autorepair.shop.domain.repair.service;

import hr.autorepair.shop.domain.repair.dto.RepairResponse;
import hr.autorepair.shop.domain.repair.repository.RepairRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService{

    private final RepairRepository repairRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RepairResponse> getRepairs() {
        return repairRepository.findAll().stream()
                .map(repair -> modelMapper.map(repair, RepairResponse.class))
                .toList();
    }

}
