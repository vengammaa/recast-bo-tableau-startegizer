package com.lti.recast.recastBoTableau.strategizer.dto;

public class PrjRptTargetConParamsModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String projectReportTargetCon;
	
	private RptTargetConParamTypeModel rptTargetConParamType;
	private String rptTargetConParamValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectReportTargetCon() {
		return projectReportTargetCon;
	}
	public void setProjectReportTargetCon(String projectReportTargetCon) {
		this.projectReportTargetCon = projectReportTargetCon;
	}
	public RptTargetConParamTypeModel getRptTargetConParamType() {
		return rptTargetConParamType;
	}
	public void setRptTargetConParamType(RptTargetConParamTypeModel rptTargetConParamType) {
		this.rptTargetConParamType = rptTargetConParamType;
	}
	public String getRptTargetConParamValue() {
		return rptTargetConParamValue;
	}
	public void setRptTargetConParamValue(String rptTargetConParamValue) {
		this.rptTargetConParamValue = rptTargetConParamValue;
	}
	@Override
	public String toString() {
		return "PrjRptTargetConParamsModel [id=" + id + ", projectReportTargetCon=" + projectReportTargetCon
				+ ", rptTargetConParamType=" + rptTargetConParamType + ", rptTargetConParamValue="
				+ rptTargetConParamValue + "]";
	}
	
	

}
