package hr.autorepair.shop.domain.receipt.pdf;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReceiptPDFGenerator {
    byte[] generatePdf(Long idReceipt) throws JRException, IOException;
}
