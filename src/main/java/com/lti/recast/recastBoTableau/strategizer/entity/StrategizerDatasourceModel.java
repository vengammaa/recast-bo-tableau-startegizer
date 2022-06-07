package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StrategizerDatasourceModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    private int stratTaskId;
	
   // @Id
    private String reportId;
    
    private String type;
    
    private String ltable;
    
    private String lcolumn;
    
    private String rtable;
    
    private String rcolumn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getStratTaskId() {
		return stratTaskId;
	}

	public void setStratTaskId(int stratTaskId) {
		this.stratTaskId = stratTaskId;
	}

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
		return "StrategizerDatasourceModel [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId=" + reportId
				+ ", type=" + type + ", ltable=" + ltable + ", lcolumn=" + lcolumn + ", rtable=" + rtable + ", rcolumn="
				+ rcolumn + "]";
	}
    
	
}
