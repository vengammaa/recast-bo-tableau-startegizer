package com.lti.recast.recastBoTableau.strategizer.dto;

import java.util.List;


public class MigratorModel {

	private int stategizerId;
	
	private List<StrategizerQueryModel> queryModelList;
	
	private List<StrategizerVisualizationConvertor> visualModelList;
	
	private List<StrategizerCalculatedFormulaModel> calculatedFormualaModelList;
	
	private List<StrategizerMetadataColumnModel> metadataColumnModelList;
	
	private List<StrategizerDatasourceModelModel> datasourceModelList;
	
	private List<StrategizerCalculationsModel> calculationsList;

	public int getStategizerId() {
		return stategizerId;
	}

	public void setStategizerId(int stategizerId) {
		this.stategizerId = stategizerId;
	}

	public List<StrategizerQueryModel> getQueryModelList() {
		return queryModelList;
	}

	public void setQueryModelList(List<StrategizerQueryModel> queryModelList) {
		this.queryModelList = queryModelList;
	}

	

	public List<StrategizerVisualizationConvertor> getVisualModelList() {
		return visualModelList;
	}

	public void setVisualModelList(List<StrategizerVisualizationConvertor> visualModelList) {
		this.visualModelList = visualModelList;
	}

	public List<StrategizerCalculatedFormulaModel> getCalculatedFormualaModelList() {
		return calculatedFormualaModelList;
	}

	public void setCalculatedFormualaModelList(List<StrategizerCalculatedFormulaModel> calculatedFormualaModelList) {
		this.calculatedFormualaModelList = calculatedFormualaModelList;
	}

	public List<StrategizerMetadataColumnModel> getMetadataColumnModelList() {
		return metadataColumnModelList;
	}

	public void setMetadataColumnModelList(List<StrategizerMetadataColumnModel> metadataColumnModelList) {
		this.metadataColumnModelList = metadataColumnModelList;
	}

	public List<StrategizerDatasourceModelModel> getDatasourceModelList() {
		return datasourceModelList;
	}

	public void setDatasourceModelList(List<StrategizerDatasourceModelModel> datasourceModelList) {
		this.datasourceModelList = datasourceModelList;
	}

	public List<StrategizerCalculationsModel> getCalculationsList() {
		return calculationsList;
	}

	public void setCalculationsList(List<StrategizerCalculationsModel> calculationsList) {
		this.calculationsList = calculationsList;
	}

	@Override
	public String toString() {
		return "MigratorModel [stategizerId=" + stategizerId + ", queryModelList=" + queryModelList
				+ ", visualModelList=" + visualModelList + ", calculatedFormualaModelList="
				+ calculatedFormualaModelList + ", metadataColumnModelList=" + metadataColumnModelList
				+ ", datasourceModelList=" + datasourceModelList + ", calculationsList=" + calculationsList + "]";
	}
	



	
	
	
}
