package com.deloitte.jnj.LegalHoldingAccelerator;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class XmlToExcelConverter {

	private DirectoryManager directoryManager = new DirectoryManager();

	private ZipUtility zipUtility = new ZipUtility();

	private MySaxParser saxXmlParser = new MySaxParser();

	public void process() {
		System.out.println(
				"------------------------------------------\nXML to Excel Converter\n------------------------------------------");
		try {
			// Take zip file name from user
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the source zip file name");
			String sourceZipFile = reader.readLine();
			Path sourceZipPath = directoryManager.getJnjSourcePath().resolve(Paths.get(sourceZipFile));
			if (Files.notExists(sourceZipPath)) {
				System.out.println(sourceZipPath + " doesn't exist. Terminating");
			}
			System.out.println("Found source zip file at " + sourceZipPath + ". Unzipping it.");
			String unzippedSourceXmlFilePath = zipUtility.unzip(sourceZipPath, directoryManager.getJnjWorkingPath());
			System.out.println("Unzipped " + sourceZipFile + " successfully");
			Path sourceXmlPath = Paths.get(unzippedSourceXmlFilePath);
			if (Files.notExists(sourceXmlPath)) {
				System.out.println("Unable to locate the unzipped xml file in temporary directory. Terminating");
			}
			// Parse XML start
			System.out.println("Parsing the source XML file");
			List<Map<String, String>> parsedXmlData = saxXmlParser.parse(sourceXmlPath);
			System.out.println("Preparing excel header row");
			Set<String> headerSet = parsedXmlData.stream().map(x -> x.keySet()).flatMap(l -> l.stream())
					.collect(Collectors.toSet());
			List<String> headerList = headerSet.stream().collect(Collectors.toList());
			headerList.add(0, "OPERATION");
			System.out.println("Preparing excel grid data");
			List<List<String>> excelGridData = new ArrayList<>();
			excelGridData.add(headerList);
			for (Map<String, String> map : parsedXmlData) {
				List<String> row = new ArrayList<>();
				for (String column : headerList) {
					row.add(map.getOrDefault(column, ""));
				}
				excelGridData.add(row);
			}
			// Parse XML end
			String xmlFileName = sourceXmlPath.getFileName().toString();
			String xmlFileNameWithoutExtension = xmlFileName.substring(0, xmlFileName.indexOf("."));
			String excelFileName = xmlFileNameWithoutExtension + Constants.timeStampSeparator
					+ StandardUtility.getTimeStamp() + ".xlsx";
			Path targetExcelPath = directoryManager.getJnjIntermediatePath().resolve(Paths.get(excelFileName));
			System.out.println("Writing excel");
			try (SXSSFWorkbook workBook = new SXSSFWorkbook(100);
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(targetExcelPath.toFile()))) {
				SXSSFSheet sheet = workBook.createSheet(Constants.excelSheetName);

				int rowNum = 0;
				for (int i = 0; i < excelGridData.size(); i++) {
					List<String> inputRow = excelGridData.get(i);
					Row row = sheet.createRow(rowNum++);
					int colNum = 0;
					for (String column : inputRow) {
						Cell cell = row.createCell(colNum++);
						if (i == 0) {
							Font font = workBook.createFont();
							font.setFontHeightInPoints((short) 10);
							font.setFontName("Arial");
							font.setColor(IndexedColors.BLUE.getIndex());
							font.setBold(true);
							font.setItalic(false);

							CellStyle style = workBook.createCellStyle();
							style.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
							style.setFont(font);
							cell.setCellStyle(style);
						}
						cell.setCellValue(column);
					}
				}

				workBook.write(bos);
				System.out.println("Test data excel has been created successfully at: " + targetExcelPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
