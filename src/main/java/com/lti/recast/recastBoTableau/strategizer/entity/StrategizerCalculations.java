package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StrategizerCalculations implements Serializable{

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
	
    private String calculationName;
    
    private String columnNames;
    
    private String formula;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCalculationName() {
		return calculationName;
	}

	public void setCalculationName(String calculationName) {
		this.calculationName = calculationName;
	}

	public String getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Override
	public String toString() {
		return "StrategizerCalculations [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId=" + reportId
				+ ", reportName=" + reportName + ", calculationName=" + calculationName + ", columnNames=" + columnNames
				+ ", formula=" + formula + "]";
	}
    
    
    
}
