package com.deloitte.jnj.LegalHoldingAccelerator;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySaxParser {

	public List<Map<String, String>> parse(Path sourceXml) {
		List<Map<String, String>> dataList = null;
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			MySaxHandler myHandler = new MySaxHandler();
			saxParser.parse(sourceXml.toFile(), myHandler);
			dataList = myHandler.getDataList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

}

class MySaxHandler extends DefaultHandler {

	private List<Map<String, String>> rowList;

	private Map<String, String> row;

	private StringBuilder value;

	public List<Map<String, String>> getDataList() {
		return rowList;
	}

	boolean isValue = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("table")) {
			// Do nothing
		} else if (qName.equalsIgnoreCase("data")) {
			row = new HashMap<>();
			if (null == rowList) {
				rowList = new ArrayList<>();
			}
			isValue = false;
		} else {
			isValue = true;
		}
		value = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("table")) {
			// Do nothing
		} else if (isValue) {
			row.put(qName, value.toString());
			isValue = false;
		} else {
			rowList.add(row);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		value.append(new String(ch, start, length));
	}
}
