package com.lti.recast.recastBoTableau.strategizer.dto;

public class RptTargetConParamTypeModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String code;

	private ReportTypeModel reportType;

	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ReportTypeModel getReportType() {
		return reportType;
	}

	public void setReportType(ReportTypeModel reportType) {
		this.reportType = reportType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RptTargetConParamTypeModel [code=" + code + ", reportType=" + reportType + ", name=" + name + "]";
	}

	
	
	

}
