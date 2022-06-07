package com.lti.recast.recastBoTableau.strategizer.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project_report_target_con")
public class ProjectReportTargetCon {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "projectReportTargetCon")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private ReportType reportType;

	private String name;
	
	private String status;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "projectReportTargetCon")
	private Set<PrjRptTargetConParams> prjRptTargetConParams = new HashSet<PrjRptTargetConParams>(0);
	
	public ProjectReportTargetCon() {
		
	}
	
	public ProjectReportTargetCon(ReportType reportType, String name) {
		this.reportType = reportType;
		this.name = name;
	}
	
	public ProjectReportTargetCon(ReportType reportType, String name, Set<PrjRptTargetConParams> prjRptTargetConParams) {
		this.reportType = reportType;
		this.name = name;
		this.prjRptTargetConParams = prjRptTargetConParams;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
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

	public Set<PrjRptTargetConParams> getPrjRptTargetConParams() {
		return prjRptTargetConParams;
	}

	public void setPrjRptTargetConParams(Set<PrjRptTargetConParams> prjRptTargetConParams) {
		this.prjRptTargetConParams = prjRptTargetConParams;
	}

	@Override
	public String toString() {
		return "ProjectReportTargetCon [id=" + id + ", reportType=" + reportType + ", name=" + name + ", status="
				+ status + ", prjRptTargetConParams=" + prjRptTargetConParams + "]";
	}
	
	
	
	

}
