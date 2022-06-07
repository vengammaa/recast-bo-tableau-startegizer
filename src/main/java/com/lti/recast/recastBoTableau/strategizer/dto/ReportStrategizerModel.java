package com.lti.recast.recastBoTableau.strategizer.dto;

import java.io.Serializable;
import java.util.List;


public class ReportStrategizerModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private int analysisTaskId;
	
	private String taskName;
	
	private TaskStatusModel taskStatus;
	
	private List<String> selectedReports;

	private String sourceReportType;
	
	private String targetReportType;
	
	private ProjectReportTargetConModel projectReportTargetCon;
	
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



	public TaskStatusModel getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatusModel taskStatus) {
		this.taskStatus = taskStatus;
	}

	public List<String> getSelectedReports() {
		return selectedReports;
	}

	public void setSelectedReports(List<String> selectedReports) {
		this.selectedReports = selectedReports;
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


	public ProjectReportTargetConModel getProjectReportTargetCon() {
		return projectReportTargetCon;
	}

	public void setProjectReportTargetCon(ProjectReportTargetConModel projectReportTargetCon) {
		this.projectReportTargetCon = projectReportTargetCon;
	}

	@Override
	public String toString() {
		return "ReportStrategizerModel [id=" + id + ", analysisTaskId=" + analysisTaskId + ", taskName=" + taskName
				+ ", taskStatus=" + taskStatus + ", selectedReports=" + selectedReports + ", sourceReportType="
				+ sourceReportType + ", targetReportType=" + targetReportType + ", projectReportTargetCon="
				+ projectReportTargetCon + "]";
	}


	
	
}
