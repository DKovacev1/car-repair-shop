package hr.autorepair.shop.domain.part;

import hr.autorepair.shop.domain.part.dto.AddPartRequest;
import hr.autorepair.shop.domain.part.dto.PartResponse;
import hr.autorepair.shop.domain.part.dto.UpdatePartRequest;
import hr.autorepair.shop.domain.part.service.PartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/part")
@AllArgsConstructor
public class PartController {

    private final PartService partService;

    @GetMapping
    public ResponseEntity<List<PartResponse>> getParts(){
        return ResponseEntity.ok(partService.getParts());
    }

    @GetMapping("/{idPart}")
    public ResponseEntity<PartResponse> getPart(@PathVariable Long idPart){
        return ResponseEntity.ok(partService.getPart(idPart));
    }

    @PostMapping
    public ResponseEntity<PartResponse> addPart(@RequestBody @Valid AddPartRequest request){
        return ResponseEntity.ok(partService.addPart(request));
    }

    @PutMapping("/{idPart}")
    public ResponseEntity<PartResponse> updatePart(@PathVariable Long idPart, @RequestBody @Valid UpdatePartRequest request){
        return ResponseEntity.ok(partService.updatePart(idPart, request));
    }

    @DeleteMapping("/{idPart}")
    public ResponseEntity<Void> deactivatePart(@PathVariable Long idPart){
        partService.deactivatePart(idPart);
        return ResponseEntity.noContent().build();
    }

}
