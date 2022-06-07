package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StrategizerFeatureLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 private int stratTaskId;
		
	 private String reportId;
	    
	 private String reportTabId;
	
	 private String reportTabName;
	 
	 private String logDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStratTaskId() {
		return stratTaskId;
	}

	public void setStratTaskId(int stratTaskId) {
		this.stratTaskId = stratTaskId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportTabId() {
		return reportTabId;
	}

	public void setReportTabId(String reportTabId) {
		this.reportTabId = reportTabId;
	}

	public String getReportTabName() {
		return reportTabName;
	}

	public void setReportTabName(String reportTabName) {
		this.reportTabName = reportTabName;
	}

	public String getLogDescription() {
		return logDescription;
	}

	public void setLogDescription(String logDescription) {
		this.logDescription = logDescription;
	}

	@Override
	public String toString() {
		return "StrategizerFeatureLog [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId=" + reportId
				+ ", reportTabId=" + reportTabId + ", reportTabName=" + reportTabName + ", logDescription="
				+ logDescription + "]";
	}
	 
	 
}
