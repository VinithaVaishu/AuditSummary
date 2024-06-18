package com.apll.auditSummary.service;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangedTable implements Serializable {

	@JsonProperty("SchemaName")
	private String schemaName;
	
	@JsonProperty("ChangedTableName")
	private String changedTableName;
	
	@JsonProperty("NumberOfRows")
	private int numberOfRows;
	
	@JsonProperty("Batches")
	private int batches;
	
	@JsonProperty("Lsn")
	private String lsn;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("TranEndTimeUTC")
	private Date tranEndTimeUTC;

	public ChangedTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangedTable(String schemaName, String changedTableName, int numberOfRows, int batches, String lsn,
			Date tranEndTimeUTC) {
		super();
		this.schemaName = schemaName;
		this.changedTableName = changedTableName;
		this.numberOfRows = numberOfRows;
		this.batches = batches;
		this.lsn = lsn;
		this.tranEndTimeUTC = tranEndTimeUTC;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getChangedTableName() {
		return changedTableName;
	}
	public void setChangedTableName(String changedTableName) {
		this.changedTableName = changedTableName;
	}
	public int getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	public int getBatches() {
		return batches;
	}
	public void setBatches(int batches) {
		this.batches = batches;
	}
	public String getLsn() {
		return lsn;
	}
	public void setLsn(String lsn) {
		this.lsn = lsn;
	}
	public Date getTranEndTimeUTC() {
		return tranEndTimeUTC;
	}
	public void setTranEndTimeUTC(Date tranEndTimeUTC) {
		this.tranEndTimeUTC = tranEndTimeUTC;
	}
	@Override
	public String toString() {
		return "ChangedTable [schemaName=" + schemaName + ", changedTableName=" + changedTableName + ", numberOfRows="
				+ numberOfRows + ", batches=" + batches + ", lsn=" + lsn + ", tranEndTimeUTC=" + tranEndTimeUTC + "]";
	}

}
