package com.lti.recast.recastBoTableau.strategizer.dto;

import java.io.Serializable;

public class StrategizerCalculationsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 	private String reportId;
	    
	    private String reportName;
	    
	    private String calculationName;
	    
	    private String columnNames;
	    
	    private String formula;

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
			return "StrategizerCalculationsModel [reportId=" + reportId + ", reportName=" + reportName
					+ ", calculationName=" + calculationName + ", columnNames=" + columnNames + ", formula=" + formula
					+ "]";
		}

		
}
