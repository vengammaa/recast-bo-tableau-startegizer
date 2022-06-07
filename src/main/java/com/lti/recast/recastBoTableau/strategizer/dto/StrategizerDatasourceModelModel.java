package com.lti.recast.recastBoTableau.strategizer.dto;

import java.io.Serializable;

public class StrategizerDatasourceModelModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String reportId;
    
    private String type;
    
    private String ltable;
    
    private String lcolumn;
    
    private String rtable;
    
    private String rcolumn;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLtable() {
		return ltable;
	}

	public void setLtable(String ltable) {
		this.ltable = ltable;
	}

	public String getLcolumn() {
		return lcolumn;
	}

	public void setLcolumn(String lcolumn) {
		this.lcolumn = lcolumn;
	}

	public String getRtable() {
		return rtable;
	}

	public void setRtable(String rtable) {
		this.rtable = rtable;
	}

	public String getRcolumn() {
		return rcolumn;
	}

	public void setRcolumn(String rcolumn) {
		this.rcolumn = rcolumn;
	}

	@Override
	public String toString() {
		return "StrategizerDatasourceModelModel [reportId=" + reportId + ", type=" + type + ", ltable=" + ltable
				+ ", lcolumn=" + lcolumn + ", rtable=" + rtable + ", rcolumn=" + rcolumn + "]";
	}
	
    
    
}
