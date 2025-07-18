package StepDefs.services.Practice;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.font.PdfFont;

import java.io.IOException;

public class PdfTextOverlay {
    public static void main(String[] args) throws IOException {
        String src = "D://WinWire//Price Logic//Automation//BDD_UI//resources//files//Sujith S-releaving Letter.pdf";
        String dest = "D://WinWire//Price Logic//Automation//BDD_UI//resources//files//Sujith S-releaving Letter - Copy.pdf";

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document document = new Document(pdfDoc);

        // Coordinates where "Sep 27, 2024" is located - adjust as needed
        Rectangle rect = new Rectangle(320, 620, 100, 15); // x, y, width, height

        // Clear old text
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        canvas.saveState()
                .setFillColorRgb(1, 1, 1) // white
                .rectangle(rect)
                .fill()
                .restoreState();

        // Overlay new text "Sep 13, 2024"
        PdfFont font = PdfFontFactory.createFont();
        canvas.beginText()
                .setFontAndSize(font, 12)
                .moveText(rect.getX(), rect.getY())
                .showText("Sep 13, 2024")
                .endText();

        document.close();
        System.out.println("PDF updated successfully.");
    }
}
