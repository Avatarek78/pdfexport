package cz.dragonsoft;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author tomas.langer
 */
public class Main {

    public static void main(String[] args) {
        try {
            generatePDFFromHTML("test.html");
            System.out.println("Successfully generated");
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void generatePDFFromHTML(String filename) throws IOException, DocumentException {
        Document document = new Document();
        try (FileOutputStream outStream = new FileOutputStream("html.pdf");
             FileInputStream inStream = new FileInputStream(filename)) {
            PdfWriter writer = PdfWriter.getInstance(document, outStream);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, inStream);
            document.close();
        }
    }

}
