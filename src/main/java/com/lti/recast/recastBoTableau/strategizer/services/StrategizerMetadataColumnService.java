package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsTable;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerMetadataColumns;
import com.lti.recast.recastBoTableau.strategizer.entity.UniverseReport;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerMetadataColumnRepository;

@Service
public class StrategizerMetadataColumnService {
	
	@Autowired
	private UniverseReportServices universeReportServices;
	
	@Autowired
	private StrategizerMetadataColumnRepository strategizerMetadataColumnRepository;
	
	public void saveStrategizerMetaDataColumnData(int strateTaskId,List<AnalysisReportsTable> analysisReportTableLst,int analysisTaskId)
	{
		List<StrategizerMetadataColumns> lstStrategizerMetadataColumns= prepareStrategizerMetadataColumns(strateTaskId,analysisReportTableLst,analysisTaskId);	
		strategizerMetadataColumnRepository.saveAll(lstStrategizerMetadataColumns);
	}
	
	public List<StrategizerMetadataColumns> prepareStrategizerMetadataColumns(int strateTaskId,List<AnalysisReportsTable> analysisReportTableLst,int analysisTaskId){
		
		List<StrategizerMetadataColumns> strategizerMetadataColumnsLst= new ArrayList<StrategizerMetadataColumns>();
		  UniverseReport univerReportData=  universeReportServices.findUniverseReportDetailsByPrjRptAnalysisId(analysisTaskId);
		  
		  for(AnalysisReportsTable analysisReportTable:analysisReportTableLst)
		  {
			  String tableName= analysisReportTable.getTableNames()!=null?analysisReportTable.getTableNames():"";
			  String universeReportTableData = univerReportData.getTables();
			  JSONArray jsonArrUniverseReportTableData = new JSONArray(universeReportTableData);
			  for(Object objUniverseReportTableData :jsonArrUniverseReportTableData)
			  {
				  JSONObject UniverseReportTableData = (JSONObject) objUniverseReportTableData;
				  if(tableName.equals(UniverseReportTableData.getString("name")))
				  {
					  JSONArray jsonArrayColumnLst = UniverseReportTableData.getJSONArray("columns");
					  
					  for(Object objColumnLst :jsonArrayColumnLst)
					  {
						  JSONObject jsonColumnLst = (JSONObject) objColumnLst;
						  StrategizerMetadataColumns strategizerMetadataColumns= new StrategizerMetadataColumns();
						  
						  strategizerMetadataColumns.setStratTaskId(strateTaskId);
						  strategizerMetadataColumns.setReportId(analysisReportTable.getReportId()!=null?analysisReportTable.getReportId():"");
						  strategizerMetadataColumns.setReportName(analysisReportTable.getReportName()!=null?analysisReportTable.getReportName():"");
						  strategizerMetadataColumns.setMetadataColumnName(jsonColumnLst.getString("name")!=null?jsonColumnLst.getString("name"):"");
						  if(jsonColumnLst.getString("dataType")!=null&&jsonColumnLst.getString("dataType").equals("VAR_CHAR"))
						  {
							  strategizerMetadataColumns.setDatatype("string");
						  }
						  else if(jsonColumnLst.getString("dataType")!=null&&jsonColumnLst.getString("dataType").equals("INTEGER"))
						  {
							  strategizerMetadataColumns.setDatatype("integer"); 
						  }
						  else if(jsonColumnLst.getString("dataType")!=null&&jsonColumnLst.getString("dataType").equals("DOUBLE"))
						  {
						  strategizerMetadataColumns.setDatatype("real");
						  }
						  else if (jsonColumnLst.getString("dataType")!=null&&jsonColumnLst.getString("dataType").equals("TIME_STAMP")||jsonColumnLst.getString("dataType").equalsIgnoreCase("DATE"))
						  {
							  strategizerMetadataColumns.setDatatype("datetime"); 
						  }
							  
						  strategizerMetadataColumns.setTableName(tableName);
						  strategizerMetadataColumns.setSemanticsType("");
						  strategizerMetadataColumns.setValueType("");
						 // strategizerMetadataColumns.setType("");
						  strategizerMetadataColumnsLst.add(strategizerMetadataColumns);
					  }
				  }
			  }
			  
		  }
		  
		return strategizerMetadataColumnsLst;
		  
	}

}
