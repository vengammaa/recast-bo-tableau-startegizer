package com.lti.recast.recastBoTableau.strategizer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StrategizerQueryConversion {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private int stratTaskId;

    private String reportId;
	
	private String databaseType;
	
	private String hostname;
	
	private String databaseName;
	
	private String queryStatement;
	
	private String convertedQueryStatement;
    
	private String queryName;
	
	private String convertedQueryName;

	private String reportName;
	
	public Integer getId() {
		return id;
	}


	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getQueryStatement() {
		return queryStatement;
	}

	public void setQueryStatement(String queryStatement) {
		this.queryStatement = queryStatement;
	}

	public String getConvertedQueryStatement() {
		return convertedQueryStatement;
	}

	public void setConvertedQueryStatement(String convertedQueryStatement) {
		this.convertedQueryStatement = convertedQueryStatement;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getConvertedQueryName() {
		return convertedQueryName;
	}

	public void setConvertedQueryName(String convertedQueryName) {
		this.convertedQueryName = convertedQueryName;
	}


	public int getStratTaskId() {
		return stratTaskId;
	}


	public void setStratTaskId(int stratTaskId) {
		this.stratTaskId = stratTaskId;
	}
	
	


	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "StrategizerQueryConversion [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId=" + reportId
				+ ", databaseType=" + databaseType + ", hostname=" + hostname + ", databaseName=" + databaseName
				+ ", queryStatement=" + queryStatement + ", convertedQueryStatement=" + convertedQueryStatement
				+ ", queryName=" + queryName + ", convertedQueryName=" + convertedQueryName + ", reportName="
				+ reportName + "]";
	}




    
}
