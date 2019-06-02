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
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XmlToExcelConverter {

	private DirectoryManager directoryManager = new DirectoryManager();

	private ZipUtility zipUtility = new ZipUtility();

	public void process() {
		// TODO System prompt to enter source xml zip file name
		// TODO System reads the zip file in Documents\source folder and unpacks
		// the
		// content in working directory. Assumption:
		// Zip file contains only one .xml file. If file not present, terminate
		// with error message.
		// TODO System unmarshalls the xml file and prepares the excel
		// sheet with data. Then display message to the user with created file
		// name in workbench folder with system timestamp
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
			String xmlFileName = sourceXmlPath.getFileName().toString();
			String xmlFileNameWithoutExtension = xmlFileName.substring(0, xmlFileName.indexOf("."));
			String excelFileName = xmlFileNameWithoutExtension + Constants.timeStampSeparator
					+ StandardUtility.getTimeStamp() + ".xlsx";
			Path targetExcelPath = directoryManager.getJnjIntermediatePath().resolve(Paths.get(excelFileName));
			System.out.println("Parsing the source XML file");
			JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Table table = (Table) jaxbUnmarshaller.unmarshal(sourceXmlPath.toFile());
			System.out.println("Preparing excel header row");
			Set<String> headers = table.getDataList().stream().map(x -> x.toMap().keySet()).flatMap(l -> l.stream())
					.collect(Collectors.toSet());
			List<String> excelHeader = headers.stream().collect(Collectors.toList());
			excelHeader.add(0, "OPERATION");
			List<List<String>> excelGrid = new ArrayList<>();
			excelGrid.add(excelHeader);
			System.out.println("Preparing excel grid data");
			for (CustomMap customMap : table.getDataList()) {
				List<String> row = new ArrayList<>();
				for (String column : excelHeader) {
					row.add(customMap.toMap().getOrDefault(column, ""));
				}
				excelGrid.add(row);
			}
			System.out.println("Writing excel");
			try (XSSFWorkbook workBook = new XSSFWorkbook();
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(targetExcelPath.toFile()))) {
				XSSFSheet sheet = workBook.createSheet(Constants.excelSheetName);

				int rowNum = 0;
				for (int i = 0; i < excelGrid.size(); i++) {
					List<String> inputRow = excelGrid.get(i);
					Row row = sheet.createRow(rowNum++);
					int colNum = 0;
					if (i == 0) {
						XSSFFont font = workBook.createFont();
						font.setFontHeightInPoints((short) 10);
						font.setFontName("Arial");
						font.setColor(IndexedColors.WHITE.getIndex());
						font.setBold(true);
						font.setItalic(false);

						CellStyle style = workBook.createCellStyle();
						style.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
						style.setFont(font);
						row.setRowStyle(style);
					}
					for (String column : inputRow) {
						Cell cell = row.createCell(colNum++);
						// cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(column);
						cell.setCellStyle(row.getRowStyle());
					}
				}

				workBook.write(bos);
				System.out.println("Test data excel has been created successfully at: " + targetExcelPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

	}

}
