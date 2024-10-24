package hr.autorepair.shop.domain.receipt.pdf;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
import hr.autorepair.shop.domain.receipt.dto.RepairPartResponse;
import hr.autorepair.shop.domain.receipt.service.ReceiptService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ReceiptPDFGeneratorImpl implements ReceiptPDFGenerator {

    private final ReceiptService receiptService;
    private final ResourceLoader resourceLoader;

    private static final String APP_LOGO = "classpath:report/images/logo.png";
    private static final String RECEIPT_COMPILED_JASPER_URL = "/report/compiled/receipt.jasper";
    private static final String SPACE = " ";
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    @Override
    public byte[] generatePdf(Long idReceipt) throws JRException, IOException {
        ReceiptResponse receipt = receiptService.getReceipt(idReceipt);

        List<RepairPartResponse> repairPartResponses = getDataSource(receipt);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(repairPartResponses);

        Map<String, Object> params = fillMap(receipt);

        InputStream jasperStream = getClass().getResourceAsStream(RECEIPT_COMPILED_JASPER_URL);
        JasperReport jasperDesign = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, params, jrBeanCollectionDataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private Map<String, Object> fillMap(ReceiptResponse receipt) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("logo", new FileInputStream(resourceLoader.getResource(APP_LOGO).getFile().getPath()));
        params.put("createdAt", receipt.getCreatedAt().toLocalDate().toString()
                + SPACE + receipt.getCreatedAt().toLocalTime().getHour()
                + ":" + receipt.getCreatedAt().toLocalTime().getMinute());

        AppUserResponse jobOrderAppUser = receipt.getJobOrder().getJobOrderAppUserEmployee();
        params.put("jobOrderAppUser", jobOrderAppUser.getFirstName() + SPACE + jobOrderAppUser.getLastName());
        params.put("workplace", receipt.getJobOrder().getWorkplace().getName());
        params.put("description", receipt.getJobOrder().getDescription());

        AppUserResponse receiptAppUser = receipt.getJobOrder().getJobOrderAppUserEmployee();
        params.put("receiptAppUser", receiptAppUser.getFirstName() + SPACE + receiptAppUser.getLastName());

        AppUserResponse carAppUser = receipt.getJobOrder().getCar().getCarOwner();
        params.put("carAppUser", carAppUser.getFirstName() + SPACE + carAppUser.getLastName());
        params.put("registrationPlate", receipt.getJobOrder().getCar().getRegistrationPlate());
        params.put("carMaker", receipt.getJobOrder().getCar().getMaker());
        params.put("carModel", receipt.getJobOrder().getCar().getModel());
        params.put("yearOfProduction", receipt.getJobOrder().getCar().getYearOfProduction());

        params.put("paymentMethod", receipt.getPayment().getName());

        BigDecimal repairPartTotalCost = receipt.getRepairCostSum().add(receipt.getPartsCostSum());
        params.put("repairPartTotalCost", repairPartTotalCost);

        params.put("loyaltyDiscount", receipt.getLoyaltyDiscount().compareTo(BigDecimal.ZERO) > 0 ?
                "Loyalty discount (" + receipt.getLoyaltyDiscount().multiply(ONE_HUNDRED) + "%):"
                : null);
        params.put("additionalDiscount", receipt.getAdditionalDiscount().compareTo(BigDecimal.ZERO) > 0 ?
                "Additional discount (" + receipt.getAdditionalDiscount().multiply(ONE_HUNDRED) + "%):"
                : null);
        params.put("paymentDiscount", receipt.getPayment().getDiscount().compareTo(BigDecimal.ZERO) > 0 ?
                "Payment discount (" + receipt.getPayment().getDiscount().multiply(ONE_HUNDRED) + "%):"
                : null);

        params.put("loyaltyDiscountValue", receipt.getLoyaltyDiscount().compareTo(BigDecimal.ZERO) > 0 ?
                "-" + receipt.getLoyaltyDiscount().multiply(repairPartTotalCost).setScale(2, RoundingMode.HALF_UP)
                : null);
        params.put("additionalDiscountValue", receipt.getAdditionalDiscount().compareTo(BigDecimal.ZERO) > 0 ?
                "-" + receipt.getAdditionalDiscount().multiply(repairPartTotalCost).setScale(2, RoundingMode.HALF_UP)
                : null);
        params.put("paymentDiscountValue", receipt.getPayment().getDiscount().compareTo(BigDecimal.ZERO) > 0 ?
                "-" + receipt.getPayment().getDiscount().multiply(repairPartTotalCost).setScale(2, RoundingMode.HALF_UP)
                : null);

        params.put("totalCost", receipt.getTotalCost());

        return params;
    }

    private List<RepairPartResponse> getDataSource(ReceiptResponse receipt){
        List<RepairPartResponse> repairPartResponses = new ArrayList<>();

        receipt.getJobOrder().getRepairs().forEach(repairResponse -> {
            RepairPartResponse repairPartResponse = new RepairPartResponse();
            repairPartResponse.setName(repairResponse.getName());
            repairPartResponse.setCost(repairResponse.getCost());
            repairPartResponse.setQuantity("-");
            repairPartResponse.setTotal(repairResponse.getCost());
            repairPartResponses.add(repairPartResponse);
        });

        receipt.getJobOrder().getParts().forEach(partResponse -> {
            RepairPartResponse repairPartResponse = new RepairPartResponse();
            repairPartResponse.setName(partResponse.getPart().getName());
            repairPartResponse.setCost(partResponse.getPart().getCost());
            repairPartResponse.setQuantity(partResponse.getQuantity().toString());
            repairPartResponse.setTotal(partResponse.getPart().getCost().multiply(BigDecimal.valueOf(partResponse.getQuantity())));
            repairPartResponses.add(repairPartResponse);
        });

        return repairPartResponses;
    }

}
