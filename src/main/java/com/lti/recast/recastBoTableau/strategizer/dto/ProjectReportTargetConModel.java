package com.lti.recast.recastBoTableau.strategizer.dto;

import java.util.HashSet;
import java.util.Set;

public class ProjectReportTargetConModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private ReportTypeModel reportType;

	private String name;
	
	private String status;
	
	private Set<PrjRptTargetConParamsModel> prjRptTargetConParams = new HashSet<PrjRptTargetConParamsModel>(0);
	
	public ProjectReportTargetConModel() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<PrjRptTargetConParamsModel> getPrjRptTargetConParams() {
		return prjRptTargetConParams;
	}

	public void setPrjRptTargetConParams(Set<PrjRptTargetConParamsModel> prjRptTargetConParams) {
		this.prjRptTargetConParams = prjRptTargetConParams;
	}

	@Override
	public String toString() {
		return "ProjectReportTargetConModel [id=" + id + ", reportType=" + reportType + ", name=" + name + ", status="
				+ status + ", prjRptTargetConParams=" + prjRptTargetConParams + "]";
	}

	
	
	
}
