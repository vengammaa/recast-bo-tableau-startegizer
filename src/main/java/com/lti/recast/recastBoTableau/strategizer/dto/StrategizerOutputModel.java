package com.lti.recast.recastBoTableau.strategizer.dto;

import java.io.Serializable;
import java.util.List;

import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerQueryConversion;

public class StrategizerOutputModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int taskId;
	
	private List<StrategizerQueryConversion> queryConversionList;
	
	private List<StrategizerCalculatedColumn> calculatedColumnList;
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public List<StrategizerQueryConversion> getQueryConversionList() {
		return queryConversionList;
	}

	public void setQueryConversionList(List<StrategizerQueryConversion> queryConversionList) {
		this.queryConversionList = queryConversionList;
	}

	public List<StrategizerCalculatedColumn> getCalculatedColumnList() {
		return calculatedColumnList;
	}

	public void setCalculatedColumnList(List<StrategizerCalculatedColumn> calculatedColumnList) {
		this.calculatedColumnList = calculatedColumnList;
	}

	@Override
	public String toString() {
		return "StrategizerOutputModel [taskId=" + taskId + ", queryConversionList=" + queryConversionList
				+ ", calculatedColumnList=" + calculatedColumnList + "]";
	}
	
	
	
	
}
