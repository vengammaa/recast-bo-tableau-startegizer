package com.lti.recast.recastBoTableau.strategizer.dto;

import java.io.Serializable;

public class StrategizerMetadataColumnModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	    private String reportId;
	    
	    private String reportName;
		
	    private String metadataColumnName;
	    
	    private String datatype;
	    
	    private String semanticsType;
	    
	    private String tableName;
	    
	    private String valueType;

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


		
		public String getMetadataColumnName() {
			return metadataColumnName;
		}

		public void setMetadataColumnName(String metadataColumnName) {
			this.metadataColumnName = metadataColumnName;
		}

		public String getDatatype() {
			return datatype;
		}

		public void setDatatype(String datatype) {
			this.datatype = datatype;
		}

		public String getSemanticsType() {
			return semanticsType;
		}

		public void setSemanticsType(String semanticsType) {
			this.semanticsType = semanticsType;
		}

		
		
		
		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}



		public String getValueType() {
			return valueType;
		}

		public void setValueType(String valueType) {
			this.valueType = valueType;
		}

		@Override
		public String toString() {
			return "StrategizerMetadataColumnModel [reportId=" + reportId + ", reportName=" + reportName
					+ ", metadataColumnName=" + metadataColumnName + ", datatype=" + datatype + ", semanticsType="
					+ semanticsType + ", tableName=" + tableName + ", valueType=" + valueType + "]";
		}

		
	    
	    

}
