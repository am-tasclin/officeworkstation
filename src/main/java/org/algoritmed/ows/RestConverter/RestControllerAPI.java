package org.algoritmed.ows.RestConverter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Text;

@RestController
public class RestControllerAPI {

    protected static final Logger logger = LoggerFactory.getLogger(RestControllerAPI.class);
    protected String tempData;

    @PostMapping("/r/setData")
    public ResponseEntity setData(@RequestBody Map<String, Object> data) throws IOException {
        tempData = data.get("data").toString();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/r/clearData")
    public ResponseEntity clearData() throws IOException {
        tempData = "";
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/r/getDOCX")
    public ResponseEntity<InputStreamResource> returnDocement() throws IOException, Docx4JException, JAXBException {

        ByteArrayInputStream body;
        XHTMLtoDOCX mToDOCX = new XHTMLtoDOCX();
        WordprocessingMLPackage tmp = mToDOCX.Render(tempData);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        body = extracted2in(tmp, out);

        // Get file name
        String fileName = "Document.docx";
        MainDocumentPart mainDocumentPart = tmp.getMainDocumentPart();
        String textNodesXPath = "//w:t";
        List<Object> textNodes = mainDocumentPart.getJAXBNodesViaXPath(textNodesXPath, true);
        for (Object obj : textNodes) {
            Text text = (Text) ((javax.xml.bind.JAXBElement) obj).getValue();
            fileName = text.getValue() + ".docx";
            System.out.println(fileName);
            break;
        }

        byte[] fileNameBytes = fileName.getBytes("utf-8");
        String dispositionFileName = "";
        for (byte b : fileNameBytes) {
            dispositionFileName += (char) (b & 0xff);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + dispositionFileName);
        MediaType mediaType = MediaType
                .parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        return ResponseEntity.ok().headers(headers).contentType(mediaType).body(new InputStreamResource(body));
    }

    @GetMapping("/r/getPDF")
    public ResponseEntity<InputStreamResource> returnPDF() throws IOException, Docx4JException, JAXBException {

        ByteArrayInputStream body;
        XHTMLtoDOCX mToDOCX = new XHTMLtoDOCX();
        WordprocessingMLPackage tmp = mToDOCX.Render(tempData);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Get file name
        String fileName = "Document.pdf";
        MainDocumentPart mainDocumentPart = tmp.getMainDocumentPart();
        String textNodesXPath = "//w:t";
        List<Object> textNodes = mainDocumentPart.getJAXBNodesViaXPath(textNodesXPath, true);
        for (Object obj : textNodes) {
            Text text = (Text) ((javax.xml.bind.JAXBElement) obj).getValue();
            fileName = text.getValue() + ".pdf";
            System.out.println(fileName);
            break;
        }

        byte[] fileNameBytes = fileName.getBytes("utf-8");
        String dispositionFileName = "";
        for (byte b : fileNameBytes) {
            dispositionFileName += (char) (b & 0xff);
        }

        Docx4J.toPDF(tmp, out);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + dispositionFileName);
        MediaType mediaType = MediaType.parseMediaType("application/pdf");
        return ResponseEntity.ok().headers(headers).contentType(mediaType)
                .body(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));
    }

    private ByteArrayInputStream extracted2in(WordprocessingMLPackage template, ByteArrayOutputStream out)
            throws Docx4JException {
        try {
            template.save(out, Docx4J.FLAG_SAVE_ZIP_FILE);
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
