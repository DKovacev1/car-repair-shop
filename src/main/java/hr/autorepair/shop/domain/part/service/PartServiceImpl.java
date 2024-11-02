package hr.autorepair.shop.domain.part.service;

import hr.autorepair.shop.domain.part.dto.AddPartRequest;
import hr.autorepair.shop.domain.part.dto.PartResponse;
import hr.autorepair.shop.domain.part.dto.UpdatePartRequest;
import hr.autorepair.shop.domain.part.model.Part;
import hr.autorepair.shop.domain.part.repository.PartRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static hr.autorepair.common.constants.MessageConstants.PART_NOT_EXIST;

@Service
@AllArgsConstructor
public class PartServiceImpl implements PartService{

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PartResponse> getParts() {
        return partRepository.findByIsDeletedFalse().stream()
                .map(part -> modelMapper.map(part, PartResponse.class))
                .toList();
    }

    @Override
    public PartResponse getPart(Long idPart) {
        Part part = partRepository.findByIdPartAndIsDeletedFalse(idPart)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(PART_NOT_EXIST, idPart)));
        return modelMapper.map(part, PartResponse.class);
    }

    @Override
    public PartResponse addPart(AddPartRequest request) {
        Part part = modelMapper.map(request, Part.class);
        part.setIsDeleted(false);
        partRepository.save(part);
        return modelMapper.map(part, PartResponse.class);
    }

    @Override
    public PartResponse updatePart(Long idPart, UpdatePartRequest request) {
        Part part = partRepository.findByIdPartAndIsDeletedFalse(idPart)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(PART_NOT_EXIST, idPart)));
        part.setName(request.getName());
        part.setCost(request.getCost());
        partRepository.save(part);
        return modelMapper.map(part, PartResponse.class);
    }

    @Override
    public void deactivatePart(Long idPart) {
        Part part = partRepository.findByIdPartAndIsDeletedFalse(idPart)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(PART_NOT_EXIST, idPart)));
        part.setIsDeleted(true);
        partRepository.save(part);
    }

}
