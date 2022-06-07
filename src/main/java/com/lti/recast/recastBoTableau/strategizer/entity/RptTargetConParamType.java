package com.lti.recast.recastBoTableau.strategizer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RptTargetConParamType {
	
	

	@Override
	public String toString() {
		return "RptTargetConParamType [code=" + code + ", name=" + name + ", reportType=" + reportType + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String name;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private ReportType reportType;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
