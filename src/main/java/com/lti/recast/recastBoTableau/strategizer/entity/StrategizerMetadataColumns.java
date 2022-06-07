package com.lti.recast.recastBoTableau.strategizer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StrategizerMetadataColumns{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    private int stratTaskId;
	
    private String reportId;
    
    private String reportName;
	
    private String metadataColumnName;
    
    private String datatype;
    
    private String semanticsType;
    
    private String tableName;
    
    private String valueType;
   


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

	public StrategizerMetadataColumns(Integer id, int stratTaskId, String reportId, String reportName,
			String metadataColumnName, String datatype, String semanticsType, String tableName, String valueType,
			String type) {
		super();
		this.id = id;
		this.stratTaskId = stratTaskId;
		this.reportId = reportId;
		this.reportName = reportName;
		this.metadataColumnName = metadataColumnName;
		this.datatype = datatype;
		this.semanticsType = semanticsType;
		this.tableName = tableName;
		this.valueType = valueType;
		
	}

	public StrategizerMetadataColumns() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StrategizerMetadataColumns [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId=" + reportId
				+ ", reportName=" + reportName + ", metadataColumnName=" + metadataColumnName + ", datatype=" + datatype
				+ ", semanticsType=" + semanticsType + ", tableName=" + tableName + ", valueType=" + valueType
				+ "]";
	}




    
}
