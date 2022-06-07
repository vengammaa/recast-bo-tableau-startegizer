package com.lti.recast.recastBoTableau.strategizer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UniverseReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String connectionClass;
	private String dataSources;
	private String description;
	private String filters;
	private String items;
	private String joins;
	private String name;
	private Integer prjRptAnalysisId;
	private String tables;
	private String universeSourceId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConnectionClass() {
		return connectionClass;
	}
	public void setConnectionClass(String connectionClass) {
		this.connectionClass = connectionClass;
	}
	public String getDataSources() {
		return dataSources;
	}
	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getJoins() {
		return joins;
	}
	public void setJoins(String joins) {
		this.joins = joins;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPrjRptAnalysisId() {
		return prjRptAnalysisId;
	}
	public void setPrjRptAnalysisId(Integer prjRptAnalysisId) {
		this.prjRptAnalysisId = prjRptAnalysisId;
	}
	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	public String getUniverseSourceId() {
		return universeSourceId;
	}
	public void setUniverseSourceId(String universeSourceId) {
		this.universeSourceId = universeSourceId;
	}
	public UniverseReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UniverseReport(Integer id, String connectionClass, String dataSources, String description, String filters,
			String items, String joins, String name, Integer prjRptAnalysisId, String tables, String universeSourceId) {
		super();
		this.id = id;
		this.connectionClass = connectionClass;
		this.dataSources = dataSources;
		this.description = description;
		this.filters = filters;
		this.items = items;
		this.joins = joins;
		this.name = name;
		this.prjRptAnalysisId=prjRptAnalysisId;
		this.tables = tables;
		this.universeSourceId = universeSourceId;
	}
	
	

}
