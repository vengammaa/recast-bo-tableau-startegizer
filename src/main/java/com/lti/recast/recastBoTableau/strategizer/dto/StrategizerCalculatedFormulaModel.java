package com.lti.recast.recastBoTableau.strategizer.dto;

import java.io.Serializable;

public class StrategizerCalculatedFormulaModel{



	
	private String reportId;
	
	private String reportName;
	
	private String reportTabId;
	
	private String formula;
	
	private String calculatedFormula;
	
	private String columnQualification;

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

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getCalculatedFormula() {
		return calculatedFormula;
	}

	public void setCalculatedFormula(String calculatedFormula) {
		this.calculatedFormula = calculatedFormula;
	}

	public String getColumnQualification() {
		return columnQualification;
	}

	public void setColumnQualification(String columnQualification) {
		this.columnQualification = columnQualification;
	}
	
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Override
	public String toString() {
		return "StrategizerCalculatedFormulaModel [reportId=" + reportId + ", reportName=" + reportName
				+ ", reportTabId=" + reportTabId + ", formula=" + formula + ", calculatedFormula=" + calculatedFormula
				+ ", columnQualification=" + columnQualification + "]";
	}

}
