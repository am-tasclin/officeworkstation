package org.algoritmed.ows.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExcelRest {
	@PostMapping("/r/createExcel001")
	public @ResponseBody
	Map<String, Object> createSqlObject(
			@RequestBody Map<String, Object> data
			, HttpServletRequest request
			, Principal principal
	){
		HashMap<String,Object>sqlObjResponse = new HashMap<String, Object>();
		return sqlObjResponse;
	}


	@RequestMapping("/r/helloExcel")
	public ResponseEntity<InputStreamResource> helloExcel() {
		// https://poi.apache.org/components/spreadsheet/quick-guide.html#NewWorkbook
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("new sheet");
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(0);
		// Or do it on one line.
		row.createCell(1).setCellValue(1.2);
		row.createCell(2).setCellValue(
				createHelper.createRichTextString("This is a string"));
		row.createCell(3).setCellValue(true);
		HttpHeaders headers = new HttpHeaders();
		// set filename in header
		headers.add("Content-Disposition", "attachment; filename=helloExcel.xlsx");

		ByteArrayInputStream extracted2in = extracted2in(wb);
		MediaType mediaType = MediaType.parseMediaType("application/vnd.ms-excel");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(mediaType)
				.body(new InputStreamResource(extracted2in));
	}

	private ByteArrayInputStream extracted2in(Workbook wb) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	@RequestMapping("/r/text001")
	public String text() {
		return "Greetings excel from Spring Boot!";
	}

}
