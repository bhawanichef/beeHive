package com.deloitte.jnj.LegalHoldingAccelerator;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "memotech")
public class Table {
	private List<CustomMap> dataList;
	
	public Table() {
		this.dataList = new ArrayList<>();
	}

	@XmlElement(name = "data")
	public List<CustomMap> getDataList() {
		return dataList;
	}

	public void setDataList(List<CustomMap> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return this.dataList.toString();
	}

}
