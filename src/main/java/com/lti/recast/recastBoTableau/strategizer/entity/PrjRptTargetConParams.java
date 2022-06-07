package com.lti.recast.recastBoTableau.strategizer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrjRptTargetConParams {
	
	@Override
	public String toString() {
		return "PrjRptTargetConParams [id=" + id + ", projectReportTargetCon=" + projectReportTargetCon
				+ ", rptTargetConParamType=" + rptTargetConParamType + ", rptTargetConParamValue="
				+ rptTargetConParamValue + "]";
	}
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private ProjectReportTargetCon projectReportTargetCon;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private RptTargetConParamType rptTargetConParamType;
	private String rptTargetConParamValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProjectReportTargetCon getProjectReportTargetCon() {
		return projectReportTargetCon;
	}
	public void setProjectReportTargetCon(ProjectReportTargetCon projectReportTargetCon) {
		this.projectReportTargetCon = projectReportTargetCon;
	}
	public RptTargetConParamType getRptTargetConParamType() {
		return rptTargetConParamType;
	}
	public void setRptTargetConParamType(RptTargetConParamType rptTargetConParamType) {
		this.rptTargetConParamType = rptTargetConParamType;
	}
	public String getRptTargetConParamValue() {
		return rptTargetConParamValue;
	}
	public void setRptTargetConParamValue(String rptTargetConParamValue) {
		this.rptTargetConParamValue = rptTargetConParamValue;
	}
	
	

}
