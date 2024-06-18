package com.apll.auditSummary.model;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.apll.auditSummary.service.ChangedTable;

@Component
public class CargowiseSummary {
	ChangedTable[] tables;

	public ChangedTable[] getTables() {
		return tables;
	}

	@Override
	public String toString() {
		return "CargowiseSummary [tables=" + Arrays.toString(tables) + "]";
	}

	public void setTables(ChangedTable[] tables) {
		this.tables = tables;
	}

	public CargowiseSummary(ChangedTable[] tables) {
		super();
		this.tables = tables;
	}

	public CargowiseSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
