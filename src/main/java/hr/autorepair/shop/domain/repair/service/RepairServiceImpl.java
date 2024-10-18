package hr.autorepair.shop.domain.repair.service;

import hr.autorepair.shop.domain.repair.dto.AddRepairRequest;
import hr.autorepair.shop.domain.repair.dto.RepairResponse;
import hr.autorepair.shop.domain.repair.dto.UpdateRepairRequest;
import hr.autorepair.shop.domain.repair.model.Repair;
import hr.autorepair.shop.domain.repair.repository.RepairRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static hr.autorepair.common.constants.MessageConstants.NO_CHANGES_MADE;
import static hr.autorepair.common.constants.MessageConstants.REPAIR_NOT_EXISTS;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService{

    private final RepairRepository repairRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RepairResponse> getRepairs() {
        return repairRepository.findByIsDeletedFalse().stream()
                .map(repair -> modelMapper.map(repair, RepairResponse.class))
                .toList();
    }

    @Override
    public RepairResponse getRepair(Long idRepair) {
        Repair repair = repairRepository.findByIdRepairAndIsDeletedFalse(idRepair)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(REPAIR_NOT_EXISTS, idRepair)));
        return modelMapper.map(repair, RepairResponse.class);
    }

    @Override
    public RepairResponse addRepair(AddRepairRequest request) {
        Repair repair = modelMapper.map(request, Repair.class);
        repair.setIsDeleted(false);
        repairRepository.save(repair);
        return modelMapper.map(repair, RepairResponse.class);
    }

    @Override
    public RepairResponse updateRepair(Long idRepair, UpdateRepairRequest request) {
        Repair repair = repairRepository.findByIdRepairAndIsDeletedFalse(idRepair)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(REPAIR_NOT_EXISTS, idRepair)));

        if(!request.hasChanges(modelMapper.map(repair, UpdateRepairRequest.class)))
            throw new BadRequestException(NO_CHANGES_MADE);

        repair.setName(request.getName());
        repair.setCost(request.getCost());
        repair.setRepairTime(request.getRepairTime());
        repairRepository.save(repair);
        return modelMapper.map(repair, RepairResponse.class);
    }

    @Override
    public void deactivateRepair(Long idRepair) {
        Repair repair = repairRepository.findByIdRepairAndIsDeletedFalse(idRepair)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(REPAIR_NOT_EXISTS, idRepair)));
        repair.setIsDeleted(true);
        repairRepository.save(repair);
    }

}
