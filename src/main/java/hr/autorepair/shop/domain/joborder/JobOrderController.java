package hr.autorepair.shop.domain.joborder;

import hr.autorepair.shop.domain.joborder.dto.AddJobOrderRequest;
import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.joborder.service.JobOrderService;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.pdf.ReceiptPDFGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/job-order")
@AllArgsConstructor
public class JobOrderController {

    private final JobOrderService jobOrderService;
    private final ReceiptPDFGenerator receiptPDFGenerator;

    @GetMapping
    public ResponseEntity<List<JobOrderResponse>> getJobOrders() {
        return ResponseEntity.ok(jobOrderService.getAllJobOrders());
    }

    @GetMapping("/{idJobOrder}")
    public ResponseEntity<JobOrderResponse> getJobOrder(@PathVariable Long idJobOrder) {
        return ResponseEntity.ok(jobOrderService.getJobOrder(idJobOrder));
    }

    @GetMapping(value = "/{idJobOrder}/receipt", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Object> getReceiptByIdJobOrder(@PathVariable Long idJobOrder, HttpServletRequest request) throws JRException, IOException {
        String headerAccept = request.getHeader(HttpHeaders.ACCEPT);
        ReceiptResponse receiptResponse = jobOrderService.getReceiptByIdJobOrder(idJobOrder);

        if(MediaType.APPLICATION_PDF_VALUE.equals(headerAccept)){
            byte[] bytes = receiptPDFGenerator.generatePdf(receiptResponse.getIdReceipt());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new ByteArrayResource(bytes));
        }
        else
            return ResponseEntity.ok(receiptResponse);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<JobOrderResponse> addJobOrder(@RequestBody @Valid AddJobOrderRequest request) {
        return ResponseEntity.ok(jobOrderService.addJobOrder(request));
    }

    @PostMapping("/{idJobOrder}/increment-status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<JobOrderResponse> updateJobOrder(@PathVariable Long idJobOrder) {
        return ResponseEntity.ok(jobOrderService.incrementStatus(idJobOrder));
    }

    @DeleteMapping("/{idJobOrder}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Void> deactivateJobOrder(@PathVariable Long idJobOrder) {
        jobOrderService.deactivateJobOrder(idJobOrder);
        return ResponseEntity.noContent().build();
    }

}
