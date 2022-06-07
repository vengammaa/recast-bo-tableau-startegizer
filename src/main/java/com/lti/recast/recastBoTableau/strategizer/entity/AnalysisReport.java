package com.lti.recast.recastBoTableau.strategizer.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
public class AnalysisReport  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer prjRptAnalysisId;
	private String columnNames;
	private String reportType;
	private String reportId;	
	private String reportName;
	private String reportUpdatedDate;
	private String reportCreationDate;
	private String folderPath;
	private Integer numberOfInstances;
	private Integer numberOfRecurringInstances;
	private Integer averageRunTime;
	private Integer totalSize;
	private Integer totalUniverseCount;
	private Integer numberOfBlocks;
	private Integer numberOfFormulas;
	private Integer numberOfTabs;
	private Integer numberOfFilters;
	private Integer numberOfColumns;
	private Integer numberOfQuery;
	private String universeName;
	private String universePath;
	private Boolean isReportScheduled;
	private Integer universeId;
	private Integer numberOfRows;
	private Boolean isActivelyUsed; 
	private Integer numberOfFailures;
	private String reportUser;
	private Boolean isReportPublished;
	private Integer commonalityFactor;
	private String tableColumnMap;
	private String queryList;
	private String tableSet;
	private String chartSet;
	private Integer numberOfReportPages;
	private Integer numberOfVariables;
	private Integer numberOfConditionalBlocks;
	private String pivotTableSet;
	private Double complexity;
	private String tabList;
	private Integer numberOfImages;
	private Integer numberOfEmbeddedElements;
	private String variableList;
	private String exceptionReport;
	private String inputControl;
	private String alerters;
	private String dataFilters;
	private String queryFilters;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrjRptAnalysisId() {
		return prjRptAnalysisId;
	}
	public void setPrjRptAnalysisId(Integer prjRptAnalysisId) {
		this.prjRptAnalysisId = prjRptAnalysisId;
	}
	public String getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
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

	public String getReportUpdatedDate() {
		return reportUpdatedDate;
	}
	public void setReportUpdatedDate(String reportUpdatedDate) {
		this.reportUpdatedDate = reportUpdatedDate;
	}
	public String getReportCreationDate() {
		return reportCreationDate;
	}
	public void setReportCreationDate(String reportCreationDate) {
		this.reportCreationDate = reportCreationDate;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public Integer getNumberOfInstances() {
		return numberOfInstances;
	}
	public void setNumberOfInstances(Integer numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}
	public Integer getNumberOfRecurringInstances() {
		return numberOfRecurringInstances;
	}
	public void setNumberOfRecurringInstances(Integer numberOfRecurringInstances) {
		this.numberOfRecurringInstances = numberOfRecurringInstances;
	}
	public Integer getAverageRunTime() {
		return averageRunTime;
	}
	public void setAverageRunTime(Integer averageRunTime) {
		this.averageRunTime = averageRunTime;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public Integer getTotalUniverseCount() {
		return totalUniverseCount;
	}
	public void setTotalUniverseCount(Integer totalUniverseCount) {
		this.totalUniverseCount = totalUniverseCount;
	}
	public Integer getNumberOfBlocks() {
		return numberOfBlocks;
	}
	public void setNumberOfBlocks(Integer numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}
	public Integer getNumberOfFormulas() {
		return numberOfFormulas;
	}
	public void setNumberOfFormulas(Integer numberOfFormulas) {
		this.numberOfFormulas = numberOfFormulas;
	}
	public Integer getNumberOfTabs() {
		return numberOfTabs;
	}
	public void setNumberOfTabs(Integer numberOfTabs) {
		this.numberOfTabs = numberOfTabs;
	}
	public Integer getNumberOfFilters() {
		return numberOfFilters;
	}
	public void setNumberOfFilters(Integer numberOfFilters) {
		this.numberOfFilters = numberOfFilters;
	}
	public Integer getNumberOfColumns() {
		return numberOfColumns;
	}
	public void setNumberOfColumns(Integer numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	public Integer getNumberOfQuery() {
		return numberOfQuery;
	}
	public void setNumberOfQuery(Integer numberOfQuery) {
		this.numberOfQuery = numberOfQuery;
	}
	public String getUniverseName() {
		return universeName;
	}
	public void setUniverseName(String universeName) {
		this.universeName = universeName;
	}
	public String getUniversePath() {
		return universePath;
	}
	public void setUniversePath(String universePath) {
		this.universePath = universePath;
	}
	public Boolean getIsReportScheduled() {
		return isReportScheduled;
	}
	public void setIsReportScheduled(Boolean isReportScheduled) {
		this.isReportScheduled = isReportScheduled;
	}
	public Integer getUniverseId() {
		return universeId;
	}
	public void setUniverseId(Integer universeId) {
		this.universeId = universeId;
	}
	public Integer getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	public Boolean getIsActivelyUsed() {
		return isActivelyUsed;
	}
	public void setIsActivelyUsed(Boolean isActivelyUsed) {
		this.isActivelyUsed = isActivelyUsed;
	}
	public Integer getNumberOfFailures() {
		return numberOfFailures;
	}
	public void setNumberOfFailures(Integer numberOfFailures) {
		this.numberOfFailures = numberOfFailures;
	}
	public String getReportUser() {
		return reportUser;
	}
	public void setReportUser(String reportUser) {
		this.reportUser = reportUser;
	}
	public Boolean getIsReportPublished() {
		return isReportPublished;
	}
	public void setIsReportPublished(Boolean isReportPublished) {
		this.isReportPublished = isReportPublished;
	}
	public Integer getCommonalityFactor() {
		return commonalityFactor;
	}
	public void setCommonalityFactor(Integer commonalityFactor) {
		this.commonalityFactor = commonalityFactor;
	}
	public String getTableColumnMap() {
		return tableColumnMap;
	}
	public void setTableColumnMap(String tableColumnMap) {
		this.tableColumnMap = tableColumnMap;
	}
	public String getQueryList() {
		return queryList;
	}
	public void setQueryList(String queryList) {
		this.queryList = queryList;
	}
	public String getTableSet() {
		return tableSet;
	}
	public void setTableSet(String tableSet) {
		this.tableSet = tableSet;
	}
	public String getChartSet() {
		return chartSet;
	}
	public void setChartSet(String chartSet) {
		this.chartSet = chartSet;
	}
	public Integer getNumberOfReportPages() {
		return numberOfReportPages;
	}
	public void setNumberOfReportPages(Integer numberOfReportPages) {
		this.numberOfReportPages = numberOfReportPages;
	}
	public Integer getNumberOfVariables() {
		return numberOfVariables;
	}
	public void setNumberOfVariables(Integer numberOfVariables) {
		this.numberOfVariables = numberOfVariables;
	}
	public Integer getNumberOfConditionalBlocks() {
		return numberOfConditionalBlocks;
	}
	public void setNumberOfConditionalBlocks(Integer numberOfConditionalBlocks) {
		this.numberOfConditionalBlocks = numberOfConditionalBlocks;
	}
	public String getPivotTableSet() {
		return pivotTableSet;
	}
	public void setPivotTableSet(String pivotTableSet) {
		this.pivotTableSet = pivotTableSet;
	}
	public Double getComplexity() {
		return complexity;
	}
	public void setComplexity(Double complexity) {
		this.complexity = complexity;
	}
	public String getTabList() {
		return tabList;
	}
	public void setTabList(String tabList) {
		this.tabList = tabList;
	}
	public Integer getNumberOfImages() {
		return numberOfImages;
	}
	public void setNumberOfImages(Integer numberOfImages) {
		this.numberOfImages = numberOfImages;
	}
	public Integer getNumberOfEmbeddedElements() {
		return numberOfEmbeddedElements;
	}
	public void setNumberOfEmbeddedElements(Integer numberOfEmbeddedElements) {
		this.numberOfEmbeddedElements = numberOfEmbeddedElements;
	}
	public String getVariableList() {
		return variableList;
	}
	public void setVariableList(String variableList) {
		this.variableList = variableList;
	}
	public String getExceptionReport() {
		return exceptionReport;
	}
	public void setExceptionReport(String exceptionReport) {
		this.exceptionReport = exceptionReport;
	}
	public String getInputControl() {
		return inputControl;
	}
	public void setInputControl(String inputControl) {
		this.inputControl = inputControl;
	}
	public String getAlerters() {
		return alerters;
	}
	public void setAlerters(String alerters) {
		this.alerters = alerters;
	}
	public String getDataFilters() {
		return dataFilters;
	}
	public void setDataFilters(String dataFilters) {
		this.dataFilters = dataFilters;
	}
	public String getQueryFilters() {
		return queryFilters;
	}
	public void setQueryFilters(String queryFilters) {
		this.queryFilters = queryFilters;
	}
	
	
	public AnalysisReport() {
		super();
	}
	public AnalysisReport(Integer id, Integer prjRptAnalysisId, String columnNames, String reportType, String reportId,
			String reportName, String reportUpdatedDate, String reportCreationDate, String folderPath,
			Integer numberOfInstances, Integer numberOfRecurringInstances, Integer averageRunTime, Integer totalSize,
			Integer totalUniverseCount, Integer numberOfBlocks, Integer numberOfFormulas, Integer numberOfTabs,
			Integer numberOfFilters, Integer numberOfColumns, Integer numberOfQuery, String universeName,
			String universePath, Boolean isReportScheduled, Integer universeId, Integer numberOfRows,
			Boolean isActivelyUsed, Integer numberOfFailures, String reportUser, Boolean isReportPublished,
			Integer commonalityFactor, String tableColumnMap, String queryList, String tableSet, String chartSet,
			Integer numberOfReportPages, Integer numberOfVariables, Integer numberOfConditionalBlocks,
			String pivotTableSet, Double complexity, String tabList, Integer numberOfImages,
			Integer numberOfEmbeddedElements, String variableList, String exceptionReport, String inputControl,
			String alerters, String dataFilters, String queryFilters) {
		super();
		this.id = id;
		this.prjRptAnalysisId = prjRptAnalysisId;
		this.columnNames = columnNames;
		this.reportType = reportType;
		this.reportId = reportId;
		this.reportName = reportName;
		this.reportUpdatedDate = reportUpdatedDate;
		this.reportCreationDate = reportCreationDate;
		this.folderPath = folderPath;
		this.numberOfInstances = numberOfInstances;
		this.numberOfRecurringInstances = numberOfRecurringInstances;
		this.averageRunTime = averageRunTime;
		this.totalSize = totalSize;
		this.totalUniverseCount = totalUniverseCount;
		this.numberOfBlocks = numberOfBlocks;
		this.numberOfFormulas = numberOfFormulas;
		this.numberOfTabs = numberOfTabs;
		this.numberOfFilters = numberOfFilters;
		this.numberOfColumns = numberOfColumns;
		this.numberOfQuery = numberOfQuery;
		this.universeName = universeName;
		this.universePath = universePath;
		this.isReportScheduled = isReportScheduled;
		this.universeId = universeId;
		this.numberOfRows = numberOfRows;
		this.isActivelyUsed = isActivelyUsed;
		this.numberOfFailures = numberOfFailures;
		this.reportUser = reportUser;
		this.isReportPublished = isReportPublished;
		this.commonalityFactor = commonalityFactor;
		this.tableColumnMap = tableColumnMap;
		this.queryList = queryList;
		this.tableSet = tableSet;
		this.chartSet = chartSet;
		this.numberOfReportPages = numberOfReportPages;
		this.numberOfVariables = numberOfVariables;
		this.numberOfConditionalBlocks = numberOfConditionalBlocks;
		this.pivotTableSet = pivotTableSet;
		this.complexity = complexity;
		this.tabList = tabList;
		this.numberOfImages = numberOfImages;
		this.numberOfEmbeddedElements = numberOfEmbeddedElements;
		this.variableList = variableList;
		this.exceptionReport = exceptionReport;
		this.inputControl = inputControl;
		this.alerters = alerters;
		this.dataFilters = dataFilters;
		this.queryFilters = queryFilters;
	}
	@Override
	public String toString() {
		return "AnalysisReport [id=" + id + ", prjRptAnalysisId=" + prjRptAnalysisId + ", columnNames=" + columnNames
				+ ", reportType=" + reportType + ", reportId=" + reportId + ", reportName=" + reportName
				+ ", reportUpdatedDate=" + reportUpdatedDate + ", reportCreationDate=" + reportCreationDate
				+ ", folderPath=" + folderPath + ", numberOfInstances=" + numberOfInstances
				+ ", numberOfRecurringInstances=" + numberOfRecurringInstances + ", averageRunTime=" + averageRunTime
				+ ", totalSize=" + totalSize + ", totalUniverseCount=" + totalUniverseCount + ", numberOfBlocks="
				+ numberOfBlocks + ", numberOfFormulas=" + numberOfFormulas + ", numberOfTabs=" + numberOfTabs
				+ ", numberOfFilters=" + numberOfFilters + ", numberOfColumns=" + numberOfColumns + ", numberOfQuery="
				+ numberOfQuery + ", universeName=" + universeName + ", universePath=" + universePath
				+ ", isReportScheduled=" + isReportScheduled + ", universeId=" + universeId + ", numberOfRows="
				+ numberOfRows + ", isActivelyUsed=" + isActivelyUsed + ", numberOfFailures=" + numberOfFailures
				+ ", reportUser=" + reportUser + ", isReportPublished=" + isReportPublished + ", commonalityFactor="
				+ commonalityFactor + ", tableColumnMap=" + tableColumnMap + ", queryList=" + queryList + ", tableSet="
				+ tableSet + ", chartSet=" + chartSet + ", numberOfReportPages=" + numberOfReportPages
				+ ", numberOfVariables=" + numberOfVariables + ", numberOfConditionalBlocks="
				+ numberOfConditionalBlocks + ", pivotTableSet=" + pivotTableSet + ", complexity=" + complexity
				+ ", tabList=" + tabList + ", numberOfImages=" + numberOfImages + ", numberOfEmbeddedElements="
				+ numberOfEmbeddedElements + ", variableList=" + variableList + ", exceptionReport=" + exceptionReport
				+ ", inputControl=" + inputControl + ", alerters=" + alerters + ", dataFilters=" + dataFilters
				+ ", queryFilters=" + queryFilters + "]";
	}

	

	
	
   

}
