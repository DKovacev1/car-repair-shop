package hr.autorepair.shop.domain.workplace.service;

import hr.autorepair.shop.domain.workplace.dto.AddWorkplaceRequest;
import hr.autorepair.shop.domain.workplace.dto.UpdateWorkplaceRequest;
import hr.autorepair.shop.domain.workplace.dto.WorkplaceResponse;
import hr.autorepair.shop.domain.workplace.model.Workplace;
import hr.autorepair.shop.domain.workplace.repository.WorkplaceRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static hr.autorepair.common.constants.MessageConstants.WORKSHOP_NOT_EXISTS;

@Service
@AllArgsConstructor
public class WorkplaceServiceImpl implements WorkplaceService{

    private final WorkplaceRepository workplaceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<WorkplaceResponse> getWorkplaces() {
        return workplaceRepository.findByIsDeletedFalse().stream()
                .map(workplace -> modelMapper.map(workplace, WorkplaceResponse.class))
                .toList();
    }

    @Override
    public WorkplaceResponse getWorkplace(Long idWorkplace) {
        Workplace workplace = workplaceRepository.findByIdWorkplaceAndIsDeletedFalse(idWorkplace)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(WORKSHOP_NOT_EXISTS, idWorkplace)));
        return modelMapper.map(workplace, WorkplaceResponse.class);
    }

    @Override
    public WorkplaceResponse addWorkplace(AddWorkplaceRequest request) {
        Workplace workplace = modelMapper.map(request, Workplace.class);
        workplace.setIsDeleted(false);
        workplaceRepository.save(workplace);
        return modelMapper.map(workplace, WorkplaceResponse.class);
    }

    @Override
    public WorkplaceResponse updateWorkplace(Long idWorkplace, UpdateWorkplaceRequest request) {
        Workplace workplace = workplaceRepository.findByIdWorkplaceAndIsDeletedFalse(idWorkplace)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(WORKSHOP_NOT_EXISTS, idWorkplace)));
        workplace.setName(request.getName());
        workplaceRepository.save(workplace);
        return modelMapper.map(workplace, WorkplaceResponse.class);
    }

    @Override
    public void deactivateWorkplace(Long idWorkplace) {
        Workplace workplace = workplaceRepository.findByIdWorkplaceAndIsDeletedFalse(idWorkplace)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(WORKSHOP_NOT_EXISTS, idWorkplace)));
        workplace.setIsDeleted(true);
        workplaceRepository.save(workplace);
    }

}
