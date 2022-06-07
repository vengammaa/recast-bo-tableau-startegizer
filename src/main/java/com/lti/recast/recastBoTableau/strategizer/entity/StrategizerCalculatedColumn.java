package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@IdClass(StrategizerTaskReportId.class)
public class StrategizerCalculatedColumn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    private int stratTaskId;
	
   // @Id
    private String reportId;
    
    private String reportName;
    
    private String reportTabId;
    
    private String formula;
    
    private String calculatedFormula;
    
    private String columnQualification;


	public Integer getId() {
		return id;
	}


	public int getStratTaskId() {
		return stratTaskId;
	}


	public void setStratTaskId(int stratTaskId) {
		this.stratTaskId = stratTaskId;
	}


	public void setId(Integer id) {
		this.id = id;
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
		return "StrategizerCalculatedColumn [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId=" + reportId
				+ ", reportName=" + reportName + ", reportTabId=" + reportTabId + ", formula=" + formula
				+ ", calculatedFormula=" + calculatedFormula + ", columnQualification=" + columnQualification + "]";
	}




	
	
}
