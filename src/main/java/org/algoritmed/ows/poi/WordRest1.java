package org.algoritmed.ows.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordRest1 {
    protected static final Logger logger = LoggerFactory.getLogger(WordRest1.class);

    @PostMapping("/r/set_json_poi_data")
	public @ResponseBody Map<String, Object> set_json_poi_data(
			@RequestBody Map<String, Object> jsonPoiData
			,HttpServletRequest request
			,Principal principal
		){
		logger.info("\n--25---Post-- "
				+ "/r/set_json_poi_data"
				+ "\n" + jsonPoiData
				);
				request.getSession().setAttribute("jsonPoiData", jsonPoiData);
		return jsonPoiData;
    }

    @GetMapping("/r/helloWord4")
	public ResponseEntity<InputStreamResource> helloWord4(HttpServletRequest request) throws IOException{
        logger.info("\n--46---GET-- "
        + "/r/helloWord4"
        + "\n" + request.getSession().getAttribute("jsonPoiData")
        );
		ByteArrayInputStream etr;
		try (XWPFDocument doc = new XWPFDocument()) {

			XWPFParagraph p1 = doc.createParagraph();
			XWPFRun r1 = p1.createRun();
			r1.setText("The quick brown fox");
			etr = extracted2in(doc);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=helloWord.docx");
		MediaType mediaType = MediaType.parseMediaType("application/msword");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(mediaType)
				.body(new InputStreamResource(etr));
    }

    private ByteArrayInputStream extracted2in(XWPFDocument wb) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
    }

}