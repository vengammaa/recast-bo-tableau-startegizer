package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;
import java.util.Objects;

public class StrategizerTaskReportId implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private String taskId;
	
	private String reportId;
	
	public StrategizerTaskReportId() {
		// TODO Auto-generated constructor stub
	}

	public StrategizerTaskReportId(String taskId, String reportId) {
		super();
		this.taskId = taskId;
		this.reportId = reportId;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        StrategizerTaskReportId taskReportId = (StrategizerTaskReportId) o;
	        return taskId.equals(taskReportId.taskId) &&
	        		reportId.equals(taskReportId.reportId);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(taskId, taskId);
	    }
	
	
}
