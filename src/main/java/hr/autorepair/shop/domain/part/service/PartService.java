package hr.autorepair.shop.domain.part.service;

import hr.autorepair.shop.domain.part.dto.AddPartRequest;
import hr.autorepair.shop.domain.part.dto.PartResponse;
import hr.autorepair.shop.domain.part.dto.UpdatePartRequest;

import java.util.List;

public interface PartService {
    List<PartResponse> getParts();
    PartResponse getPart(Long idPart);
    PartResponse addPart(AddPartRequest request);
    PartResponse updatePart(Long idPart, UpdatePartRequest request);
    void deactivatePart(Long idPart);
}
