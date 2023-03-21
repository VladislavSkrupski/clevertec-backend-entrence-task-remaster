package ru.clevertec.backendtest.service.util.pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DoubleBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.clevertec.backendtest.model.product.Goods;
import ru.clevertec.backendtest.model.receipt.Item;
import ru.clevertec.backendtest.model.receipt.Receipt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Component
public class PDFGenerator {
    @Value("classpath:pdfBlank/Clevertec_Template.pdf")
    private Resource blankPDF;

    /**
     * This method create PDF file with receipt on background from template PDF
     * and returns name of created file
     *
     * @param receipt receipt with requested items
     * @return name of PDF file with receipt
     * @throws IOException
     */
    public String savePDF(Receipt receipt) throws IOException {
        String fileName = "receipt-"
                + new SimpleDateFormat("d-MM-yyyy--HH-mm-ss").format(receipt.getDate().getTime())
                + ".pdf";
        PdfDocument templatePdf = blankPDF.exists() ? new PdfDocument(new PdfReader(blankPDF.getFile())) : null;
        PdfDocument document = new PdfDocument(new PdfWriter(fileName));
        document.addNewPage();
        setDataToPDF(document, receipt);
        if (blankPDF.exists()) {
            for (int i = 1; i <= document.getNumberOfPages(); i++) {
                PdfPage page = document.getPage(i);
                PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);
                canvas.addXObjectAt(templatePdf.getPage(1).copyAsFormXObject(document), 0, 0);
            }
            templatePdf.close();
        }
        document.close();
        return fileName;
    }

    /**
     * This method set data from receipt to pdfDocument
     *
     * @param pdfDocument pdfDocument to set receipt
     * @param receipt     receipt with requested items
     * @throws IOException
     */
    private void setDataToPDF(PdfDocument pdfDocument, Receipt receipt) throws IOException {
        Document document = new Document(pdfDocument);
        PdfFont font = PdfFontFactory.createFont("font/arialbd.ttf", PdfEncodings.IDENTITY_H);
        document.setMargins(140, 100, 30, 100);
        document.setFont(font);

        String[] tableHeaders = {"Name", "Unit", "QTY", "Price", "Promo", "Cost"};
        String title = "RECEIPT";
        String store = "Store: " + receipt.getStoreId();
        String address = "Address: " + receipt.getStoreAddress();
        String cashier = "Cashier: " + receipt.getCashierID();
        String date = "Date: " + new SimpleDateFormat("dd/MM/yyyy").format(receipt.getDate().getTime());
        String time = "Time: " + new SimpleDateFormat("HH:mm:ss").format(receipt.getDate().getTime());
        String sum = String.format("%.2f", receipt.getTotalCost());
        String discount = (receipt.getDiscountCard() != null ? receipt.getDiscountCard().getDiscount() : 0) + "%";
        String total = String.format("%.2f", receipt.getTotalCostWithDiscount());

        Paragraph titleParagraph = new Paragraph(title).setTextAlignment(TextAlignment.CENTER);
        Paragraph storeParagraph = new Paragraph(store).setTextAlignment(TextAlignment.CENTER);
        Paragraph addressParagraph = new Paragraph(address).setTextAlignment(TextAlignment.CENTER);
        Paragraph cashierParagraph = new Paragraph(cashier).setTextAlignment(TextAlignment.LEFT);
        Paragraph dateParagraph = new Paragraph(date).setTextAlignment(TextAlignment.RIGHT);
        Paragraph timeParagraph = new Paragraph(time).setTextAlignment(TextAlignment.RIGHT);
        Paragraph sumParagraph = new Paragraph(sum).setTextAlignment(TextAlignment.RIGHT);
        Paragraph discountParagraph = new Paragraph(discount).setTextAlignment(TextAlignment.RIGHT);
        Paragraph totalParagraph = new Paragraph(total).setTextAlignment(TextAlignment.RIGHT);

        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

        Cell titleCell = new Cell(1, 6)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .add(titleParagraph)
                .add(storeParagraph)
                .add(addressParagraph)
                .setBorder(Border.NO_BORDER);
        Cell cashierCell = new Cell(1, 3)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .add(cashierParagraph)
                .setBorder(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, 1));
        Cell dateCell = new Cell(1, 3)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .add(dateParagraph)
                .add(timeParagraph)
                .setBorder(Border.NO_BORDER)
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, 1));
        table
                .setBorder(Border.NO_BORDER)
                .addCell(titleCell)
                .addCell(cashierCell)
                .addCell(dateCell);

        Arrays.stream(tableHeaders)
                .map(header -> new Paragraph(header)
                        .setTextAlignment(TextAlignment.CENTER)
                )
                .map(paragraph -> new Cell()
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .add(paragraph)
                        .setBorder(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(ColorConstants.BLACK, 1))
                )
                .forEach(table::addCell);

        receipt.getItems().stream().forEach(item -> itemsToTable(table, item));

        Cell totalHeadersCell = new Cell(1, 3)
                .setBorder(Border.NO_BORDER)
                .setBorderTop(new DoubleBorder(ColorConstants.BLACK, 4))
                .setVerticalAlignment(VerticalAlignment.TOP)
                .add(new Paragraph("SUM").setTextAlignment(TextAlignment.LEFT))
                .add(new Paragraph("DISCOUNT").setTextAlignment(TextAlignment.LEFT))
                .add(new Paragraph("TOTAL").setTextAlignment(TextAlignment.LEFT));
        Cell totalDataCell = new Cell(1, 3)
                .setBorder(Border.NO_BORDER)
                .setBorderTop(new DoubleBorder(ColorConstants.BLACK, 4))
                .setVerticalAlignment(VerticalAlignment.TOP)
                .add(sumParagraph)
                .add(discountParagraph)
                .add(totalParagraph);
        table
                .addCell(totalHeadersCell)
                .addCell(totalDataCell);
        document.add(table);
    }

    /**
     * This method create row in table with data about item
     *
     * @param table table from pdfDocument
     * @param item  item from receipt
     */
    private void itemsToTable(Table table, Goods item) {
        String name = item.getName();
        String unit = item.getUnit();
        String qty = String.valueOf(((Item) item).getAmount());
        String price = String.format("%.2f", item.getPrice());
        String promo = item.isPromotional() && ((Item) item).getAmount() >= ((Item) item).getPromotionAmount()
                ? ((Item) item).getPromotionDiscount() + "%" : "";
        String cost = String.format("%.2f", ((Item) item).getCost());

        Cell nameCell = new Cell()
                .add(new Paragraph().add(name).setTextAlignment(TextAlignment.LEFT))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.TOP);
        Cell unitCell = new Cell()
                .add(new Paragraph(unit).setTextAlignment(TextAlignment.CENTER))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.TOP);
        Cell qtyCell = new Cell()
                .add(new Paragraph(qty).setTextAlignment(TextAlignment.CENTER))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.TOP);
        Cell priceCell = new Cell()
                .add(new Paragraph(price).setTextAlignment(TextAlignment.RIGHT))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.TOP);
        Cell promoCell = new Cell()
                .add(new Paragraph(promo).setTextAlignment(TextAlignment.CENTER))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.TOP);
        Cell costCell = new Cell()
                .add(new Paragraph(cost).setTextAlignment(TextAlignment.RIGHT))
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.TOP);
        table
                .addCell(nameCell)
                .addCell(unitCell)
                .addCell(qtyCell)
                .addCell(priceCell)
                .addCell(promoCell)
                .addCell(costCell);
    }
}
