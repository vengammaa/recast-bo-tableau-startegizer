package com.lti.recast.recastBoTableau.strategizer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnalysisReportsVisualization {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer taskId;
	private String reportId;
	private String reportName;
	private String reportTabId;
	private String  reportTabName;
	private String elementType;
	private String category;
	private String elementName;
	private String alwaysFlag;
	private String xPosition;
	private String yPosition;
	private String minimalWidth;
	private String minimalHeight;
	private String backgroundColor;
	private String border;
	private String font;
	private String allignment;
	private String formula;
	private String elementId;
	private String backgroundColorAlpha;
	private String chartType;
	private String chartName;
	private String chartTitle;
	private String chartLegend;
	private String chartPlotArea;
	private String chartAxes;
	private String parentId;
	private String maximumWidth;
	private String maximumHeight;
	private String horizontalAnchorType;
	private String horizontalAnchorId;
	private String verticalAnchorType;
	private String verticalAnchorId;
	private String alerterId;
	private String layoutList;
	
	
	public AnalysisReportsVisualization() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AnalysisReportsVisualization(Integer id, Integer taskId, String reportId, String reportName,
			String reportTabId, String reportTabName, String elementType, String category, String elementName,
			String alwaysFlag, String xPosition, String yPosition, String minimalWidth, String minimalHeight,
			String backgroundColor, String border, String font, String allignment, String formula, String elementId,
			String backgroundColorAlpha, String chartType, String chartName, String chartTitle, String chartLegend,
			String chartPlotArea, String chartAxea, String parentId, String maximumWidth, String maximumHeight,
			String horizontalAnchorType, String horizontalAnchorId, String verticalAnchorType, String verticalAnchorId,
			String alerterId, String layoutList) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.reportId = reportId;
		this.reportName = reportName;
		this.reportTabId = reportTabId;
		this.reportTabName = reportTabName;
		this.elementType = elementType;
		this.category = category;
		this.elementName = elementName;
		this.alwaysFlag = alwaysFlag;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.minimalWidth = minimalWidth;
		this.minimalHeight = minimalHeight;
		this.backgroundColor = backgroundColor;
		this.border = border;
		this.font = font;
		this.allignment = allignment;
		this.formula = formula;
		this.elementId = elementId;
		this.backgroundColorAlpha = backgroundColorAlpha;
		this.chartType = chartType;
		this.chartName = chartName;
		this.chartTitle = chartTitle;
		this.chartLegend = chartLegend;
		this.chartPlotArea = chartPlotArea;
		this.chartAxes = chartAxes;
		this.parentId = parentId;
		this.maximumWidth = maximumWidth;
		this.maximumHeight = maximumHeight;
		this.horizontalAnchorType = horizontalAnchorType;
		this.horizontalAnchorId = horizontalAnchorId;
		this.verticalAnchorType = verticalAnchorType;
		this.verticalAnchorId = verticalAnchorId;
		this.alerterId = alerterId;
		this.layoutList = layoutList;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getTaskId() {
		return taskId;
	}


	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}


	public String getReportId() {
		return reportId;
	}


	public void setReportId(String reportId) {
		this.reportId = reportId;
	}


	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
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


	public String getElementType() {
		return elementType;
	}


	public void setElementType(String elementType) {
		this.elementType = elementType;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getElementName() {
		return elementName;
	}


	public void setElementName(String elementName) {
		this.elementName = elementName;
	}


	public String getAlwaysFlag() {
		return alwaysFlag;
	}


	public void setAlwaysFlag(String alwaysFlag) {
		this.alwaysFlag = alwaysFlag;
	}


	public String getxPosition() {
		return xPosition;
	}


	public void setxPosition(String xPosition) {
		this.xPosition = xPosition;
	}


	public String getyPosition() {
		return yPosition;
	}


	public void setyPosition(String yPosition) {
		this.yPosition = yPosition;
	}


	public String getMinimalWidth() {
		return minimalWidth;
	}


	public void setMinimalWidth(String minimalWidth) {
		this.minimalWidth = minimalWidth;
	}


	public String getMinimalHeight() {
		return minimalHeight;
	}


	public void setMinimalHeight(String minimalHeight) {
		this.minimalHeight = minimalHeight;
	}


	public String getBackgroundColor() {
		return backgroundColor;
	}


	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public String getBorder() {
		return border;
	}


	public void setBorder(String border) {
		this.border = border;
	}


	public String getFont() {
		return font;
	}


	public void setFont(String font) {
		this.font = font;
	}


	public String getAllignment() {
		return allignment;
	}


	public void setAllignment(String allignment) {
		this.allignment = allignment;
	}


	public String getFormula() {
		return formula;
	}


	public void setFormula(String formula) {
		this.formula = formula;
	}


	public String getElementId() {
		return elementId;
	}


	public void setElementId(String elementId) {
		this.elementId = elementId;
	}


	public String getBackgroundColorAlpha() {
		return backgroundColorAlpha;
	}


	public void setBackgroundColorAlpha(String backgroundColorAlpha) {
		this.backgroundColorAlpha = backgroundColorAlpha;
	}


	public String getChartType() {
		return chartType;
	}


	public void setChartType(String chartType) {
		this.chartType = chartType;
	}


	public String getChartName() {
		return chartName;
	}


	public void setChartName(String chartName) {
		this.chartName = chartName;
	}


	public String getChartTitle() {
		return chartTitle;
	}


	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}


	public String getChartLegend() {
		return chartLegend;
	}


	public void setChartLegend(String chartLegend) {
		this.chartLegend = chartLegend;
	}


	public String getChartPlotArea() {
		return chartPlotArea;
	}


	public void setChartPlotArea(String chartPlotArea) {
		this.chartPlotArea = chartPlotArea;
	}


	public String getChartAxes() {
		return chartAxes;
	}


	public void setChartAxea(String chartAxes) {
		this.chartAxes = chartAxes;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getMaximumWidth() {
		return maximumWidth;
	}


	public void setMaximumWidth(String maximumWidth) {
		this.maximumWidth = maximumWidth;
	}


	public String getMaximumHeight() {
		return maximumHeight;
	}


	public void setMaximumHeight(String maximumHeight) {
		this.maximumHeight = maximumHeight;
	}


	public String getHorizontalAnchorType() {
		return horizontalAnchorType;
	}


	public void setHorizontalAnchorType(String horizontalAnchorType) {
		this.horizontalAnchorType = horizontalAnchorType;
	}


	public String getHorizontalAnchorId() {
		return horizontalAnchorId;
	}


	public void setHorizontalAnchorId(String horizontalAnchorId) {
		this.horizontalAnchorId = horizontalAnchorId;
	}


	public String getVerticalAnchorType() {
		return verticalAnchorType;
	}


	public void setVerticalAnchorType(String verticalAnchorType) {
		this.verticalAnchorType = verticalAnchorType;
	}


	public String getVerticalAnchorId() {
		return verticalAnchorId;
	}


	public void setVerticalAnchorId(String verticalAnchorId) {
		this.verticalAnchorId = verticalAnchorId;
	}


	public String getAlerterId() {
		return alerterId;
	}


	public void setAlerterId(String alerterId) {
		this.alerterId = alerterId;
	}


	public String getLayoutList() {
		return layoutList;
	}


	public void setLayoutList(String layoutList) {
		this.layoutList = layoutList;
	}


	@Override
	public String toString() {
		return "AnalysisReportsVisualization [id=" + id + ", taskId=" + taskId + ", reportId=" + reportId
				+ ", reportName=" + reportName + ", reportTabId=" + reportTabId + ", reportTabName=" + reportTabName
				+ ", elementType=" + elementType + ", category=" + category + ", elementName=" + elementName
				+ ", alwaysFlag=" + alwaysFlag + ", xPosition=" + xPosition + ", yPosition=" + yPosition
				+ ", minimalWidth=" + minimalWidth + ", minimalHeight=" + minimalHeight + ", backgroundColor="
				+ backgroundColor + ", border=" + border + ", font=" + font + ", allignment=" + allignment
				+ ", formula=" + formula + ", elementId=" + elementId + ", backgroundColorAlpha=" + backgroundColorAlpha
				+ ", chartType=" + chartType + ", chartName=" + chartName + ", chartTitle=" + chartTitle
				+ ", chartLegend=" + chartLegend + ", chartPlotArea=" + chartPlotArea + ", chartAxes=" + chartAxes
				+ ", parentId=" + parentId + ", maximumWidth=" + maximumWidth + ", maximumHeight=" + maximumHeight
				+ ", horizontalAnchorType=" + horizontalAnchorType + ", horizontalAnchorId=" + horizontalAnchorId
				+ ", verticalAnchorType=" + verticalAnchorType + ", verticalAnchorId=" + verticalAnchorId
				+ ", alerterId=" + alerterId + ", layoutList=" + layoutList + "]";
	}
	
	
	
	
	

}
