package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@Entity
public class MigratorStatus implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    private int stratTaskId;
	
   // @Id
    private String reportId;
    
    private String reportTabId;
    
    private String statusMessage;
    
    private Timestamp startTime;
    
    private Timestamp endTime;
    
    private String macroRuntime;

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

	public String getReportTabId() {
		return reportTabId;
	}

	public void setReportTabId(String reportTabId) {
		this.reportTabId = reportTabId;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}


	public String getMacroRuntime() {
		return macroRuntime;
	}

	public void setMacroRuntime(String macroRuntime) {
		this.macroRuntime = macroRuntime;
	}

	@Override
	public String toString() {
		return "MigratorStatus [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId=" + reportId + ", reportTabId="
				+ reportTabId + ", statusMessage=" + statusMessage + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", macroRuntime=" + macroRuntime + "]";
	}

	
	
	
}
