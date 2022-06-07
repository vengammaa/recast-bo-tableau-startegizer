package com.lti.recast.recastBoTableau.strategizer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StrategizerVisualizationConversion implements Serializable{

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
    
    private String reportName;
    
    private String reportTabId;
    
    private String reportTabName;
    
    private String sourceComponentName;
    
    private String targetComponentName;
    
    @Column(name="source_position_x")
    private String sourcePositionX;
    
    @Column(name="source_position_y")
    private String sourcePositionY;
    
    private String sourceMinimalWidth;
    
    private String sourceMinimalHeight;
    
    @Column(name="target_position_x")
    private String targetPositionX;
    
    @Column(name="target_position_y")
    private String targetPositionY;
    
    private String targetMinimalWidth;
    
    private String targetMinimalHeight;
    
    private String formulaName;
    
    private String font;
    
    private String color;
    
    private String valueType;
    
    private String parentElement;
    
    private String calculations;
    
    private String dashboardName;

	public String getDashboardName() {
		return dashboardName;
	}

	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}

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

	public String getReportTabName() {
		return reportTabName;
	}

	public void setReportTabName(String reportTabName) {
		this.reportTabName = reportTabName;
	}

	public String getSourceComponentName() {
		return sourceComponentName;
	}

	public void setSourceComponentName(String sourceComponentName) {
		this.sourceComponentName = sourceComponentName;
	}

	public String getTargetComponentName() {
		return targetComponentName;
	}

	public void setTargetComponentName(String targetComponentName) {
		this.targetComponentName = targetComponentName;
	}

	public String getSourcePositionX() {
		return sourcePositionX;
	}

	public void setSourcePositionX(String sourcePositionX) {
		this.sourcePositionX = sourcePositionX;
	}

	public String getSourcePositionY() {
		return sourcePositionY;
	}

	public void setSourcePositionY(String sourcePositionY) {
		this.sourcePositionY = sourcePositionY;
	}

	public String getSourceMinimalWidth() {
		return sourceMinimalWidth;
	}

	public void setSourceMinimalWidth(String sourceMinimalWidth) {
		this.sourceMinimalWidth = sourceMinimalWidth;
	}

	public String getSourceMinimalHeight() {
		return sourceMinimalHeight;
	}

	public void setSourceMinimalHeight(String sourceMinimalHeight) {
		this.sourceMinimalHeight = sourceMinimalHeight;
	}

	public String getTargetPositionX() {
		return targetPositionX;
	}

	public void setTargetPositionX(String targetPositionX) {
		this.targetPositionX = targetPositionX;
	}

	public String getTargetPositionY() {
		return targetPositionY;
	}

	public void setTargetPositionY(String targetPositionY) {
		this.targetPositionY = targetPositionY;
	}

	public String getTargetMinimalWidth() {
		return targetMinimalWidth;
	}

	public void setTargetMinimalWidth(String targetMinimalWidth) {
		this.targetMinimalWidth = targetMinimalWidth;
	}

	public String getTargetMinimalHeight() {
		return targetMinimalHeight;
	}

	public void setTargetMinimalHeight(String targetMinimalHeight) {
		this.targetMinimalHeight = targetMinimalHeight;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getParentElement() {
		return parentElement;
	}

	public void setParentElement(String parentElement) {
		this.parentElement = parentElement;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCalculations() {
		return calculations;
	}

	public void setCalculations(String calculations) {
		this.calculations = calculations;
	}

	@Override
	public String toString() {
		return "StrategizerVisualizationConversion [id=" + id + ", stratTaskId=" + stratTaskId + ", reportId="
				+ reportId + ", reportName=" + reportName + ", reportTabId=" + reportTabId + ", reportTabName="
				+ reportTabName + ", sourceComponentName=" + sourceComponentName + ", targetComponentName="
				+ targetComponentName + ", sourcePositionX=" + sourcePositionX + ", sourcePositionY=" + sourcePositionY
				+ ", sourceMinimalWidth=" + sourceMinimalWidth + ", sourceMinimalHeight=" + sourceMinimalHeight
				+ ", targetPositionX=" + targetPositionX + ", targetPositionY=" + targetPositionY
				+ ", targetMinimalWidth=" + targetMinimalWidth + ", targetMinimalHeight=" + targetMinimalHeight
				+ ", formulaName=" + formulaName + ", font=" + font + ", color=" + color + ", valueType=" + valueType
				+ ", parentElement=" + parentElement + ", calculations=" + calculations + ", dashboardName="
				+ dashboardName + "]";
	}

	

	

}
