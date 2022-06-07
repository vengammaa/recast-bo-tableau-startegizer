package com.lti.recast.recastBoTableau.strategizer.dto;

import java.io.Serializable;
import java.util.List;

public class BOTableauMigratorModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int stategizerId;
	
	private List<StrategizerQueryModel> queryModelList;
	
	private List<StrategizerVisualizationConvertor> visualConvertorList;

	private List<StrategizerCalculatedFormulaModel> calculatedFormulaList;
	
	private List<StrategizerMetadataColumnModel> metadataColumnList;
	
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

	
	
	public List<StrategizerVisualizationConvertor> getVisualConvertorList() {
		return visualConvertorList;
	}

	public void setVisualConvertorList(List<StrategizerVisualizationConvertor> visualConvertorList) {
		this.visualConvertorList = visualConvertorList;
	}

	
	
	public List<StrategizerCalculatedFormulaModel> getCalculatedFormulaList() {
		return calculatedFormulaList;
	}

	public void setCalculatedFormulaList(List<StrategizerCalculatedFormulaModel> calculatedFormulaList) {
		this.calculatedFormulaList = calculatedFormulaList;
	}

	public List<StrategizerMetadataColumnModel> getMetadataColumnList() {
		return metadataColumnList;
	}

	public void setMetadataColumnList(List<StrategizerMetadataColumnModel> metadataColumnList) {
		this.metadataColumnList = metadataColumnList;
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
		return "BOTableauMigratorModel [stategizerId=" + stategizerId + ", queryModelList=" + queryModelList
				+ ", visualConvertorList=" + visualConvertorList + ", calculatedFormulaList=" + calculatedFormulaList
				+ ", metadataColumnList=" + metadataColumnList + ", datasourceModelList=" + datasourceModelList
				+ ", calculationsList=" + calculationsList + "]";
	}



	

}
