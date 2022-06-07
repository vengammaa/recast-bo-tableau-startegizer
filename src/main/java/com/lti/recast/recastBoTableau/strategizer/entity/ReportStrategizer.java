package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class ReportStrategizer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int analysisTaskId;
	
	private String taskName;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private ProjectReportTargetCon projectReportTargetCon;
	
	private String sourceReportType;
	
	private String targetReportType;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@Basic(optional = true)
	private TaskStatus taskStatus;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stratTaskId",orphanRemoval = true)
	private List<StrategizerQueryConversion> StrategizerQueryList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stratTaskId",orphanRemoval = true)
	private List<StrategizerCalculatedColumn> StrategizerCalculatedColumnList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAnalysisTaskId() {
		return analysisTaskId;
	}

	public void setAnalysisTaskId(int analysisTaskId) {
		this.analysisTaskId = analysisTaskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public List<StrategizerQueryConversion> getStrategizerQueryList() {
		return StrategizerQueryList;
	}

	public void setStrategizerQueryList(List<StrategizerQueryConversion> strategizerQueryList) {
		StrategizerQueryList = strategizerQueryList;
	}

	public List<StrategizerCalculatedColumn> getStrategizerCalculatedColumnList() {
		return StrategizerCalculatedColumnList;
	}

	public void setStrategizerCalculatedColumnList(List<StrategizerCalculatedColumn> strategizerCalculatedColumnList) {
		StrategizerCalculatedColumnList = strategizerCalculatedColumnList;
	}

	public String getSourceReportType() {
		return sourceReportType;
	}

	public void setSourceReportType(String sourceReportType) {
		this.sourceReportType = sourceReportType;
	}

	public String getTargetReportType() {
		return targetReportType;
	}

	public void setTargetReportType(String targetReportType) {
		this.targetReportType = targetReportType;
	}
	
	
	

	public ProjectReportTargetCon getProjectReportTargetCon() {
		return projectReportTargetCon;
	}

	public void setProjectReportTargetCon(ProjectReportTargetCon projectReportTargetCon) {
		this.projectReportTargetCon = projectReportTargetCon;
	}

	@Override
	public String toString() {
		return "ReportStrategizer [id=" + id + ", analysisTaskId=" + analysisTaskId + ", taskName=" + taskName
				+ ", projectReportTargetCon=" + projectReportTargetCon + ", sourceReportType=" + sourceReportType
				+ ", targetReportType=" + targetReportType + ", taskStatus=" + taskStatus + ", StrategizerQueryList="
				+ StrategizerQueryList + ", StrategizerCalculatedColumnList=" + StrategizerCalculatedColumnList + "]";
	}


}
