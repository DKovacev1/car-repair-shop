package hr.autorepair.shop.domain.receipt.pdf;

import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.receipt.repository.ReceiptRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

import static hr.autorepair.common.constants.MessageConstants.RECEIPT_NOT_EXIST;

@Component
@AllArgsConstructor
public class ReceiptPDFGeneratorImpl implements ReceiptPDFGenerator {

    private final ReceiptRepository receiptRepository;

    private static final String COMPILED_JASPER_URL = "/report/compiled/blank.jasper";

    @Override
    public byte[] generatePdf(Long idReceipt) throws JRException {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        Receipt receipt = receiptRepository.findById(idReceipt)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(RECEIPT_NOT_EXIST, idReceipt)));

        if(userPrincipal.isUser()){
            receipt.getJobOrders().stream()
                    .filter(jobOrder -> !jobOrder.getIsDeleted())
                    .findFirst().ifPresent(jobOrder -> {
                        if(jobOrder.getCar().getCarOwner().getIdAppUser().equals(userPrincipal.getAppUser().getIdAppUser()))
                            throw new BadRequestException(MessageFormat.format(RECEIPT_NOT_EXIST, idReceipt));
                    });
        }

        /*File file = new File(Objects.requireNonNull(getClass().getResource(COMPILED_JASPER_URL)).getFile());
        JasperReport jasperDesign = (JasperReport) JRLoader.loadObject(file);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, new HashMap<>(), new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);*/
        return new byte[0];
    }

}
