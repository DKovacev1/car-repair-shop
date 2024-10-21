package hr.autorepair.shop.domain.receipt;

import hr.autorepair.shop.domain.receipt.dto.AddReceiptRequest;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.service.ReceiptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipt")
@AllArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @GetMapping
    public ResponseEntity<List<ReceiptResponse>> getReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @GetMapping(value = "/{idReceipt}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Object> getReceipt(@PathVariable Long idReceipt, HttpServletRequest request) {
        String headerAccept = request.getHeader(HttpHeaders.ACCEPT);
        if(MediaType.APPLICATION_PDF_VALUE.equals(headerAccept)){
            byte[] bytes = new byte[0];
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new ByteArrayResource(bytes));
        }
        else
            return ResponseEntity.ok(receiptService.getReceipt(idReceipt));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<ReceiptResponse> addReceipt(@RequestBody @Valid AddReceiptRequest request){
        return ResponseEntity.ok(receiptService.addReceipt(request));
    }

    @DeleteMapping("/{idReceipt}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Void> deactivateReceipt(@PathVariable Long idReceipt){
        receiptService.deactivateReceipt(idReceipt);
        return ResponseEntity.noContent().build();
    }

}
