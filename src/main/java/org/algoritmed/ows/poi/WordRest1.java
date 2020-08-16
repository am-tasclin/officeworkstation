package org.algoritmed.ows.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

			List<String> dataArray = new ArrayList<String>();
			HttpSession session = request.getSession();
			
			 Map<String, List<String>> n = (LinkedHashMap) session.getAttribute("jsonPoiData");
			 for(Map.Entry<String,List<String>> en : n.entrySet()){
				List<String> val = en.getValue();

				for(String s : val){
					if(s == null)continue;
					else
						dataArray.add(s);
				}
			 }
			XWPFParagraph p1 = doc.createParagraph();
			XWPFRun r1 = p1.createRun();
			
			for(String data : dataArray){
				String[] splArray = data.split(" ");

				// still not working function of inserting bold style to word which contains '**'
				for(String splDataCorrecting : splArray){
					if(splDataCorrecting.contains("**")){
						// for testing words with '**' symbols
						System.out.println("data " + splDataCorrecting + " has **");
						r1.setBold(true);
						r1.setText(splDataCorrecting + " ");
					}
					else{
						// for testing clear words
						System.out.println("data " + splDataCorrecting + " clear");
						r1.setBold(false);
						r1.setText(splDataCorrecting + " ");
					}

						
						
				}
				r1.addBreak();
				
			}
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