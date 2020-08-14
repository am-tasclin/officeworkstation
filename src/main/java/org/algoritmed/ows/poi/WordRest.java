package org.algoritmed.ows.poi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.algoritmed.ows.amdb.Db1Rest;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordRest {
	protected static final Logger logger = LoggerFactory.getLogger(Db1Rest.class);

	@PostMapping("/r/for_word")
	public @ResponseBody Map<String, Object> url_sql_read_db1(
			@RequestBody Map<String, Object> data
			,HttpServletRequest request
			,Principal principal
		){
		logger.info("\n--35---Post-- "
				+ "/r/for_word"
				+ "\n" + data
				);
				request.getSession().setAttribute("x", 1);
		data.put("Hello", "World!");
		return data;
	}

	@PostMapping("/r/createWord")
	public ResponseEntity<InputStreamResource> createWord_JSON(
			@RequestBody Map<String, Object> data
			)throws IOException{
		logger.info("\n--59---Post-- "
				+ "\n" + data
				);
		List<String> dataK = (List<String>) data.get("k");
		logger.info("\n--63---Post-- "
				+ "\n" + dataK
				);
		ByteArrayInputStream etr;
		try(XWPFDocument doc = new XWPFDocument()){
			XWPFParagraph paragraph = doc.createParagraph();
			XWPFRun run = paragraph.createRun();
//			String []jsonArray = json.split(",");
			for (String paragrafS : dataK) {
				run.setText(paragrafS + "\n");
			}

//			for(int i=0;i<d.length();i++){
//				run.setText(jsonArray[i] + "\n");
//			}

			etr = extracted2in(doc);

		}
		HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=WikiTechnology.docx");
			MediaType mediaType = MediaType.parseMediaType("application/msword");

			return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(mediaType)
					.body(new InputStreamResource(etr));
	}

	
	@PostMapping("/r/helloWord2")
	public ResponseEntity<InputStreamResource> helloWord2() throws IOException{

		ByteArrayInputStream etr;
		try (XWPFDocument doc = new XWPFDocument()) {

			XWPFParagraph p1 = doc.createParagraph();
			XWPFRun r1 = p1.createRun();
			r1.setText("The quick brown fox");
			etr = extracted2in(doc);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=helloWord.docx");
		MediaType mediaType = MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE);// application/octet-stream
		// MediaType mediaType = MediaType.parseMediaType("application/msword");
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(mediaType)
				.body(new InputStreamResource(etr));
				
	}

	@GetMapping("/r/helloWord3")
	public ResponseEntity<InputStreamResource> helloWord3() throws IOException{

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

	@RequestMapping("/r/helloWord")
	public ResponseEntity<InputStreamResource> helloWord() throws IOException{
		ByteArrayInputStream etr;
		try (XWPFDocument doc = new XWPFDocument()) {

			XWPFParagraph p1 = doc.createParagraph();
			p1.setAlignment(ParagraphAlignment.CENTER);
			p1.setBorderBottom(Borders.DOUBLE);
			p1.setBorderTop(Borders.DOUBLE);

			p1.setBorderRight(Borders.DOUBLE);
			p1.setBorderLeft(Borders.DOUBLE);
			p1.setBorderBetween(Borders.SINGLE);

			p1.setVerticalAlignment(TextAlignment.TOP);

			XWPFRun r1 = p1.createRun();
			r1.setBold(true);
			r1.setText("The quick brown fox");
			r1.setBold(true);
			r1.setFontFamily("Courier");
			r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
			r1.setTextPosition(100);

			XWPFParagraph p2 = doc.createParagraph();
			p2.setAlignment(ParagraphAlignment.RIGHT);

			//BORDERS
			p2.setBorderBottom(Borders.DOUBLE);
			p2.setBorderTop(Borders.DOUBLE);
			p2.setBorderRight(Borders.DOUBLE);
			p2.setBorderLeft(Borders.DOUBLE);
			p2.setBorderBetween(Borders.SINGLE);

			XWPFRun r2 = p2.createRun();
			r2.setText("jumped over the lazy dog");
			r2.setStrikeThrough(true);
			r2.setFontSize(20);

			XWPFRun r3 = p2.createRun();
			r3.setText("and went away");
			r3.setStrikeThrough(true);
			r3.setFontSize(20);
			r3.setSubscript(VerticalAlign.SUPERSCRIPT);

			// hyperlink
			//            XWPFHyperlinkRun hyperlink = p2.insertNewHyperlinkRun(0, "http://poi.apache.org/");
			//            hyperlink.setUnderline(UnderlinePatterns.SINGLE);
			//            hyperlink.setColor("0000ff");
			//            hyperlink.setText("Apache POI");

			XWPFParagraph p3 = doc.createParagraph();
			p3.setWordWrapped(true);
			p3.setPageBreak(true);

			//p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
			p3.setAlignment(ParagraphAlignment.BOTH);
			p3.setSpacingBetween(15, LineSpacingRule.EXACT);

			p3.setIndentationFirstLine(600);


			XWPFRun r4 = p3.createRun();
			r4.setTextPosition(20);
			r4.setText("To be, or not to be: that is the question: "
					+ "Whether 'tis nobler in the mind to suffer "
					+ "The slings and arrows of outrageous fortune, "
					+ "Or to take arms against a sea of troubles, "
					+ "And by opposing end them? To die: to sleep; ");
			r4.addBreak(BreakType.PAGE);
			r4.setText("No more; and by a sleep to say we end "
					+ "The heart-ache and the thousand natural shocks "
					+ "That flesh is heir to, 'tis a consummation "
					+ "Devoutly to be wish'd. To die, to sleep; "
					+ "To sleep: perchance to dream: ay, there's the rub; "
					+ ".......");
			r4.setItalic(true);
			//This would imply that this break shall be treated as a simple line break, and break the line after that word:

			XWPFRun r5 = p3.createRun();
			r5.setTextPosition(-10);
			r5.setText("For in that sleep of death what dreams may come");
			r5.addCarriageReturn();
			r5.setText("When we have shuffled off this mortal coil, "
					+ "Must give us pause: there's the respect "
					+ "That makes calamity of so long life;");
			r5.addBreak();
			r5.setText("For who would bear the whips and scorns of time, "
					+ "The oppressor's wrong, the proud man's contumely,");

			r5.addBreak(BreakClear.ALL);
			r5.setText("The pangs of despised love, the law's delay, "
					+ "The insolence of office and the spurns " + ".......");

			etr = extracted2in(doc);

			
			//            try (FileOutputStream out = new FileOutputStream("helloWord.docx")) {
			//                doc.write(out);
			//            } catch (FileNotFoundException e) {
			//                e.printStackTrace();
			//            } catch (IOException e) {
			//                e.printStackTrace();
			//            }

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
