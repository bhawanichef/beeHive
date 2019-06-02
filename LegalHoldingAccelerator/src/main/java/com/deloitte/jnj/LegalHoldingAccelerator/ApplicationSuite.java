package com.deloitte.jnj.LegalHoldingAccelerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationSuite {

	public static void main(String[] args) {
		System.out.println(
				"------------------------------------------\nWelcome to JnJ Legal Hold\n------------------------------------------");
		DirectoryManager directoryManager = new DirectoryManager();
		directoryManager.process();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter 1 to convert XML to Excel & 2 to convert Excel to XML");
		try {
			String input = reader.readLine();
			switch (input) {
			case "1":
				new XmlToExcelConverter().process();
				break;
			case "2":
				new ExcelToXmlConverter().process();
				break;
			default:
				System.out.println("Not a valid input. Terminating");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
