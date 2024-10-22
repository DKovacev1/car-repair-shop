package hr.autorepair.shop.domain.receipt.pdf;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.receipt.dto.ReceiptResponse;
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
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class ReceiptPDFGeneratorImpl implements ReceiptPDFGenerator {

    private final ReceiptService receiptService;
    private final ResourceLoader resourceLoader;

    private static final String APP_LOGO = "classpath:report/images/logo.png";
    private static final String RECEIPT_COMPILED_JASPER_URL = "/report/compiled/receipt.jasper";
    private static final String SPACE = " ";

    @Override
    public byte[] generatePdf(Long idReceipt) throws JRException, IOException {
        ReceiptResponse receipt = receiptService.getReceipt(idReceipt);

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(receipt.getJobOrder().getRepairs());

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

        return params;
    }

}
