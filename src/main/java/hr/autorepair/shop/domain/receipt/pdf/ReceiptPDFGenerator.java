package hr.autorepair.shop.domain.receipt.pdf;

import net.sf.jasperreports.engine.JRException;

public interface ReceiptPDFGenerator {
    byte[] generatePdf(Long idReceipt) throws JRException;
}
