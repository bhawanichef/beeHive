package com.deloitte.jnj.LegalHoldingAccelerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;

public class ExcelToXmlConverter {

	private DirectoryManager directoryManager = new DirectoryManager();

	private ZipUtility zipUtility = new ZipUtility();

	public void process() {
		System.out.println(
				"------------------------------------------\nExcel to XML Converter\n------------------------------------------");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the test data excel file name");
		String sourceExcelFile;
		try {
			sourceExcelFile = reader.readLine();
		} catch (IOException e) {
			System.out.println("Unable to read user's input. Terminating");
			e.printStackTrace();
			return;
		}
		Path sourceExcelFilePath = directoryManager.getJnjIntermediatePath().resolve(Paths.get(sourceExcelFile));
		if (Files.notExists(sourceExcelFilePath)) {
			System.out.println(sourceExcelFilePath + " doesn't exist. Terminating");
		}
		System.out.println("Found test data Excel file at: " + sourceExcelFilePath);
		String excelFileWithExtension = sourceExcelFilePath.getFileName().toString();
		String targetXmlFileNameWithoutExtension = excelFileWithExtension.substring(0,
				excelFileWithExtension.indexOf(Constants.timeStampSeparator));
		String targetXmlFileName = targetXmlFileNameWithoutExtension.concat(".xml");
		Path targetXmlPath = directoryManager.getJnjWorkingPath().resolve(Paths.get(targetXmlFileName));
		System.out.println("Reading the excel");
		try (InputStream is = new FileInputStream(sourceExcelFilePath.toFile());
				Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);) {
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			List<List<String>> excelGrid = new ArrayList<>();
			while (iterator.hasNext()) {
				List<String> row = new ArrayList<>();
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					// currentCell.setCellType(CellType.STRING);
					row.add(currentCell.getStringCellValue());
				}
				excelGrid.add(row);
			}
			excelGrid = excelGrid.stream().filter(l -> !l.get(0).equalsIgnoreCase("DELETE"))
					.collect(Collectors.toList());
			List<String> header = excelGrid.get(0);
			header.remove(0);
			excelGrid.remove(0);
			System.out.println("Converting excel data to xml");
			List<CustomMap> dataList = new ArrayList<>();
			for (List<String> row : excelGrid) {
				row.remove(0);
				CustomMap data = new CustomMap();
				for (int i = 0; i < row.size(); i++) {
					String key = header.get(i);
					String value = row.get(i);
					if (null != value && "" != value) {
						data.addEntry(key, value);
					}
				}
				dataList.add(data);
			}

			Table table = new Table();
			table.setDataList(dataList);

			JAXBContext contextObj = JAXBContext.newInstance(Table.class);
			Marshaller marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshallerObj.marshal(table, new FileOutputStream(targetXmlPath.toFile()));

			String targetZipPathStr = targetXmlPath.getFileName().toString()
					.substring(0, targetXmlPath.getFileName().toString().indexOf(".xml"))
					.concat(StandardUtility.getTimeStamp()).concat(".zip");
			Path targetZipPath = directoryManager.getJnjDestinationPath().resolve(Paths.get(targetZipPathStr));
			System.out.println("Zipping the xml file");
			String zip = zipUtility.zip(targetXmlPath, targetZipPath);
			System.out.println("Zip file successfully generated at: " + zip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
