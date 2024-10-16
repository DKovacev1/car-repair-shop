package hr.autorepair.shop.domain.workplace.service;

import hr.autorepair.shop.domain.workplace.dto.WorkPlaceResponse;
import hr.autorepair.shop.domain.workplace.repository.WorkplaceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkplaceServiceImpl implements WorkplaceService{

    private final WorkplaceRepository workplaceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<WorkPlaceResponse> getWorkplaces() {
        return workplaceRepository.findAll().stream()
                .map(workplace -> modelMapper.map(workplace, WorkPlaceResponse.class))
                .toList();
    }

}
