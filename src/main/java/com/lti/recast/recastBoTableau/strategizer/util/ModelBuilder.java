package com.lti.recast.recastBoTableau.strategizer.util;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.lti.recast.recastBoTableau.strategizer.dto.BOTableauMigratorModel;
import com.lti.recast.recastBoTableau.strategizer.dto.MigratorModel;
import com.lti.recast.recastBoTableau.strategizer.dto.MigratorStatusModel;
import com.lti.recast.recastBoTableau.strategizer.dto.PrjRptConParamsModel;
import com.lti.recast.recastBoTableau.strategizer.dto.PrjRptTargetConParamsModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ProjectReportTargetConModel;
import com.lti.recast.recastBoTableau.strategizer.dto.QueryColumnModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ReportStrategizerModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ReportTypeModel;
import com.lti.recast.recastBoTableau.strategizer.dto.RptConParamTypeModel;
import com.lti.recast.recastBoTableau.strategizer.dto.RptTargetConParamTypeModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StatusModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculatedFormulaModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculationsModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerDatasourceModelModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerMetadataColumnModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerQueryModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerVisualizationConvertor;
import com.lti.recast.recastBoTableau.strategizer.dto.TaskStatusModel;
import com.lti.recast.recastBoTableau.strategizer.entity.MigratorStatus;
import com.lti.recast.recastBoTableau.strategizer.entity.PrjRptConParams;
import com.lti.recast.recastBoTableau.strategizer.entity.PrjRptTargetConParams;
import com.lti.recast.recastBoTableau.strategizer.entity.ProjectReportTargetCon;
import com.lti.recast.recastBoTableau.strategizer.entity.ReportStrategizer;
import com.lti.recast.recastBoTableau.strategizer.entity.ReportType;
import com.lti.recast.recastBoTableau.strategizer.entity.RptConParamType;
import com.lti.recast.recastBoTableau.strategizer.entity.RptTargetConParamType;
import com.lti.recast.recastBoTableau.strategizer.entity.Status;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculations;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerDatasourceModel;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerMetadataColumns;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerQueryConversion;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerVisualizationConversion;
import com.lti.recast.recastBoTableau.strategizer.entity.TaskStatus;

public class ModelBuilder {

	public static ReportTypeModel reportTypeModelBuilder(ReportType r) {
		ReportTypeModel rm = new ReportTypeModel();
		rm.setName(r.getName());
		rm.setCode(r.getCode());
		rm.setStatus(statusModelBuilder(r.getStatus()));
		return rm;
	}

	public static StatusModel statusModelBuilder(Status e) {
		StatusModel m = new StatusModel();
		m.setCode(e.getCode());
		m.setName(e.getName());
		return m;
	}
	public static RptConParamTypeModel rptConParamTypeModelBuilder(RptConParamType r) {
		RptConParamTypeModel rm = new RptConParamTypeModel();
		rm.setCode(r.getCode());
		rm.setName(r.getName());
		rm.setReportType(reportTypeModelBuilder(r.getReportType()));
		return rm;
	}
	public static MigratorStatusModel migratorStatusModelBuilder(MigratorStatus m) 
	{
		MigratorStatusModel migratorStatusObj = new MigratorStatusModel();
		
		migratorStatusObj.setStratTaskId(m.getStratTaskId());
		migratorStatusObj.setReportId(m.getReportId());
		migratorStatusObj.setReportTabId(m.getReportTabId());
		migratorStatusObj.setStatusMessage(m.getStatusMessage());
		migratorStatusObj.setMacroRuntime(m.getMacroRuntime());
		migratorStatusObj.setStartTime(m.getStartTime().toString());
		migratorStatusObj.setEndTime(m.getEndTime().toString());
		
		return migratorStatusObj;
	}
	public static PrjRptConParamsModel prjRptConParamsModelBuilder(PrjRptConParams p) {
		PrjRptConParamsModel pm = new PrjRptConParamsModel();
		pm.setId(p.getId());
		pm.setProjectReportCon(p.getProjectReportCon().getName());
		pm.setRptConParamType(rptConParamTypeModelBuilder(p.getRptConParamType()));
		pm.setRptConParamValue(p.getRptConParamValue());
		return pm;
	}
	public static ProjectReportTargetConModel projectReportTargetConModelBuilder(ProjectReportTargetCon p) {
		ProjectReportTargetConModel pm = new ProjectReportTargetConModel();
		pm.setId(p.getId());
		pm.setName(p.getName());
		pm.setReportType(reportTypeModelBuilder(p.getReportType()));
		pm.setStatus(p.getStatus());
		pm.setPrjRptTargetConParams(p.getPrjRptTargetConParams().stream().map(ModelBuilder::prjRptTargetConParamsModelBuilder).collect(Collectors.toSet()));
		return pm;
	}
	
	public static PrjRptTargetConParamsModel prjRptTargetConParamsModelBuilder(PrjRptTargetConParams p) {
		PrjRptTargetConParamsModel pm = new PrjRptTargetConParamsModel();
		pm.setId(p.getId());
		pm.setProjectReportTargetCon(p.getProjectReportTargetCon().getName());
		pm.setRptTargetConParamType(rptTargetConParamTypeModelBuilder(p.getRptTargetConParamType()));
		pm.setRptTargetConParamValue(p.getRptTargetConParamValue());
		return pm;
	}
	public static RptTargetConParamTypeModel rptTargetConParamTypeModelBuilder(RptTargetConParamType r) {
		RptTargetConParamTypeModel rm = new RptTargetConParamTypeModel();
		rm.setCode(r.getCode());
		rm.setName(r.getName());
		rm.setReportType(reportTypeModelBuilder(r.getReportType()));
		return rm;
	}

	public static ProjectReportTargetConModel projectReportTargetConModelBuilderCopy(ProjectReportTargetCon p) {
		ProjectReportTargetConModel pm = new ProjectReportTargetConModel();
		pm.setId(p.getId());
		pm.setName(p.getName());
//		pm.setReportType(reportTypeModelBuilder(p.getReportType()));
//		pm.setStatus(p.getStatus());
//		pm.setPrjRptTargetConParams(p.getPrjRptTargetConParams().stream().map(ModelBuilder::prjRptTargetConParamsModelBuilder).collect(Collectors.toSet()));
		return pm;
	}

	public static TaskStatusModel taskStatusModelBuilder(TaskStatus t) {
		TaskStatusModel tm = new TaskStatusModel();
		tm.setName(t.getName());
		tm.setCode(t.getCode());
		return tm;
	}

	public static StrategizerQueryModel StrategizerQueryBuilder(List<String> selectQueryList) {
		// TODO Auto-generated method stub

		StrategizerQueryModel strategizerQueryModel = new StrategizerQueryModel();

		// strategizerQueryModel.setDatabaseType("SQL Server database");
		// strategizerQueryModel.setDatabaseName("testdb");
		// strategizerQueryModel.setHostname("10.200.249.19");
		strategizerQueryModel.setDatabaseType("MySQL database");
		strategizerQueryModel.setDatabaseName("efashion");
		strategizerQueryModel.setHostname("localhost");

		strategizerQueryModel.setQueryStatement(selectQueryList.get(0));
		strategizerQueryModel.setConvertedQueryStatement(selectQueryList.get(1));

		return strategizerQueryModel;
	}

	public static ReportStrategizerModel strategizerModelBuilder(ReportStrategizer strategizerTask) {
		// TODO Auto-generated method stub
		ReportStrategizerModel reportStrategizerModel = new ReportStrategizerModel();
		reportStrategizerModel.setAnalysisTaskId(strategizerTask.getAnalysisTaskId());
		reportStrategizerModel.setTaskName(strategizerTask.getTaskName());
		reportStrategizerModel.setTaskStatus(taskStatusModelBuilder(strategizerTask.getTaskStatus()));
		reportStrategizerModel.setSourceReportType(strategizerTask.getSourceReportType());
		reportStrategizerModel.setTargetReportType(strategizerTask.getTargetReportType());
		reportStrategizerModel.setId(strategizerTask.getId());
		return reportStrategizerModel;

	}

	public static StrategizerQueryModel strategizerModelQueryBuider(StrategizerQueryConversion s) {
		StrategizerQueryModel strategizerQueryModelObj = new StrategizerQueryModel();

		strategizerQueryModelObj.setConvertedQueryName(s.getConvertedQueryName());
		strategizerQueryModelObj.setConvertedQueryStatement(s.getConvertedQueryStatement());
		strategizerQueryModelObj.setDatabaseName(s.getDatabaseName());
		strategizerQueryModelObj.setDatabaseType(s.getDatabaseType());
		strategizerQueryModelObj.setHostname(s.getHostname());
		strategizerQueryModelObj.setQueryName(s.getQueryName());
		strategizerQueryModelObj.setQueryStatement(s.getQueryStatement());
		strategizerQueryModelObj.setReportId(s.getReportId());
		strategizerQueryModelObj.setReportName(s.getReportName());
		return strategizerQueryModelObj;

	}

	public static StrategizerCalculatedFormulaModel strategizerModelCalculatedColumnBuilder(
			StrategizerCalculatedColumn c) {
		StrategizerCalculatedFormulaModel stratFormulaModelObj = new StrategizerCalculatedFormulaModel();

		stratFormulaModelObj.setCalculatedFormula(c.getCalculatedFormula());
		stratFormulaModelObj.setColumnQualification(c.getColumnQualification());
		stratFormulaModelObj.setFormula(c.getFormula());
		stratFormulaModelObj.setReportId(c.getReportId());
		stratFormulaModelObj.setReportTabId(c.getReportTabId());

		return stratFormulaModelObj;

	}

	public static StrategizerVisualizationConvertor strategizerModelVisualizationBuilder(
			StrategizerVisualizationConversion x) {
		StrategizerVisualizationConvertor y = new StrategizerVisualizationConvertor();

		y.setColor(x.getColor());
		y.setFont(x.getFont());
		y.setFormulaName(x.getFormulaName());
		y.setParentElement(x.getParentElement());
		y.setReportId(x.getReportId());
		y.setReportTabId(x.getReportTabId());
		y.setReportTabName(x.getReportTabName());
		y.setSourceComponentName(x.getSourceComponentName());
		y.setSourceMinimalHeight(x.getSourceMinimalHeight());
		y.setSourceMinimalWidth(x.getSourceMinimalWidth());
		y.setSourcePositionX(x.getSourcePositionX());
		y.setSourcePositionY(x.getSourcePositionY());
		y.setTargetComponentName(x.getTargetComponentName());
		y.setTargetMinimalHeight(x.getTargetMinimalHeight());
		y.setTargetMinimalWidth(x.getTargetMinimalWidth());
		y.setTargetPositionX(x.getTargetPositionX());
		y.setTargetPositionY(x.getTargetPositionY());
		y.setValueType(x.getValueType());
		y.setReportName(x.getReportName());
		y.setCalculations(x.getCalculations());
		y.setDashboardName(x.getDashboardName()!=null?x.getDashboardName():"");
		return y;

	}

	public static StrategizerMetadataColumnModel strategizerModelMetadataColumnBuilder(StrategizerMetadataColumns c) {
		StrategizerMetadataColumnModel stratMetadataColumnModelObj = new StrategizerMetadataColumnModel();

		stratMetadataColumnModelObj.setDatatype(c.getDatatype());
		stratMetadataColumnModelObj.setMetadataColumnName(c.getMetadataColumnName());
		stratMetadataColumnModelObj.setReportId(c.getReportId());
		stratMetadataColumnModelObj.setReportName(c.getReportName());
		stratMetadataColumnModelObj.setSemanticsType(c.getSemanticsType());
		stratMetadataColumnModelObj.setTableName(c.getTableName());
		stratMetadataColumnModelObj.setValueType(c.getValueType());
		return stratMetadataColumnModelObj;

	}

	public static StrategizerDatasourceModelModel strategizerModelDataModelBuilder(StrategizerDatasourceModel c) {
		StrategizerDatasourceModelModel stratDatasourceModelObj = new StrategizerDatasourceModelModel();

		stratDatasourceModelObj.setLcolumn(c.getLcolumn());
		stratDatasourceModelObj.setLtable(c.getLtable());
		stratDatasourceModelObj.setRcolumn(c.getRcolumn());
		stratDatasourceModelObj.setReportId(c.getReportId());
		stratDatasourceModelObj.setRtable(c.getRtable());
		stratDatasourceModelObj.setType(c.getType());

		return stratDatasourceModelObj;

	}

	public static StrategizerCalculationsModel strategizerCalculationsModelBuilder(StrategizerCalculations c) {
		StrategizerCalculationsModel stratCalculationsModelObj = new StrategizerCalculationsModel();

		stratCalculationsModelObj.setCalculationName(c.getCalculationName());
		stratCalculationsModelObj.setColumnNames(c.getColumnNames());
		stratCalculationsModelObj.setFormula(c.getFormula());
		stratCalculationsModelObj.setReportId(c.getReportId());
		stratCalculationsModelObj.setReportName(c.getReportName());

		return stratCalculationsModelObj;

	}

	public static MigratorModel BOTableauModelBuilder(BOTableauMigratorModel t) {
		// TODO Auto-generated method stub

		MigratorModel m = new MigratorModel();
		m.setStategizerId(t.getStategizerId());

		List<StrategizerQueryModel> queryModelList = new LinkedList<StrategizerQueryModel>();

		List<StrategizerQueryModel> stratQueryModelList = t.getQueryModelList();

		stratQueryModelList.forEach(x -> {
			StrategizerQueryModel q = new StrategizerQueryModel();
			// q.setConvertedQueryName(s1.ge)
			q.setConvertedQueryName(x.getConvertedQueryName());
			q.setConvertedQueryStatement(x.getConvertedQueryStatement());
			q.setDatabaseName(x.getDatabaseName());
			q.setDatabaseType(x.getDatabaseType());
			q.setHostname(x.getHostname());
			q.setQueryName(x.getQueryName());
			q.setQueryStatement(x.getQueryStatement());
			q.setReportId(x.getReportId());
			q.setReportName(x.getReportName());
			queryModelList.add(q);

		});
		m.setQueryModelList(queryModelList);

		List<StrategizerVisualizationConvertor> visualConvertorList = t.getVisualConvertorList();

		List<StrategizerVisualizationConvertor> visualModelList = new LinkedList<StrategizerVisualizationConvertor>();

		visualConvertorList.forEach(x -> {

			StrategizerVisualizationConvertor v = new StrategizerVisualizationConvertor();
			v.setColor(x.getColor());
			v.setFont(x.getFont());
			v.setFormulaName(x.getFormulaName());
			v.setParentElement(x.getParentElement());
			v.setReportId(x.getReportId());
			v.setReportName(x.getReportName());
			v.setReportTabId(x.getReportTabId());
			v.setReportTabName(x.getReportTabName());
			v.setSourceComponentName(x.getSourceComponentName());
			v.setSourceMinimalHeight(x.getSourceMinimalHeight());
			v.setSourceMinimalWidth(x.getSourceMinimalWidth());
			v.setSourcePositionX(x.getSourcePositionX());
			v.setSourcePositionY(x.getSourcePositionY());
			v.setTargetComponentName(x.getTargetComponentName());
			v.setTargetMinimalHeight(x.getTargetMinimalHeight());
			v.setTargetMinimalWidth(x.getTargetMinimalWidth());
			v.setTargetPositionX(x.getTargetPositionX());
			v.setTargetPositionY(x.getTargetPositionY());
			v.setValueType(x.getValueType());
			v.setCalculations(x.getCalculations());
			v.setDashboardName(x.getDashboardName());
			visualModelList.add(v);
		});

		m.setVisualModelList(visualModelList);

		List<StrategizerCalculatedFormulaModel> calculatedModelList = new LinkedList<StrategizerCalculatedFormulaModel>();

		List<StrategizerCalculatedFormulaModel> strategizerCalculatedFormulaList = t.getCalculatedFormulaList();

		strategizerCalculatedFormulaList.forEach(x -> {

			StrategizerCalculatedFormulaModel c = new StrategizerCalculatedFormulaModel();

			c.setCalculatedFormula(x.getCalculatedFormula());
			c.setColumnQualification(x.getColumnQualification());
			c.setFormula(x.getFormula());
			c.setReportId(x.getReportId());
			c.setReportName(x.getReportName());
			c.setReportTabId(x.getReportTabId());

			calculatedModelList.add(c);
		});
		m.setCalculatedFormualaModelList(calculatedModelList);

		List<StrategizerMetadataColumnModel> metadataColumnList = t.getMetadataColumnList();

		// List<StrategizerMetadataColumnModel> metadataColumnList = new
		// LinkedList<MetadataColumnModel>();

		/*
		 * strategizerMetadataColumnModelList.forEach(x->{
		 * StrategizerMetadataColumnModel c = new MetadataColumnModel();
		 * 
		 * c.setDatatype(x.getDatatype());
		 * c.setMetadataColumnName(x.getMetadataColumnName());
		 * c.setReportId(x.getReportId()); c.setReportName(x.getReportName());
		 * c.setSemanticsType(x.getSemanticsType()); c.setTableName(x.getTableName());
		 * c.setValueType(x.getValueType()); metadataColumnList.add(c);
		 * 
		 * 
		 * });
		 */

		m.setMetadataColumnModelList(metadataColumnList);

		// List<StrategizerDatasourceModelModel> dataSourceModelList = new
		// LinkedList<StrategizerDatasourceModelModel>();

		List<StrategizerDatasourceModelModel> dataSourceModelList = t.getDatasourceModelList();

		/*
		 * stratDataModelList.forEach(x->{
		 * 
		 * DatasourceModel c = new DatasourceModel();
		 * 
		 * c.setLcolumn(x.getLcolumn()); c.setLtable(x.getLtable());
		 * c.setRcolumn(x.getRcolumn()); c.setReportId(x.getReportId());
		 * c.setRtable(x.getRtable()); c.setType(x.getType());
		 * dataSourceModelList.add(c);
		 * 
		 * });
		 */
		m.setDatasourceModelList(dataSourceModelList);

		// List<StrategizerCalculationsModel> calculationsList = new
		// LinkedList<StrategizerCalculationsModel>();

		List<StrategizerCalculationsModel> calculationsList = t.getCalculationsList();

		/*
		 * stratCalculationsList.forEach(x->{ CalculationsModel c = new
		 * CalculationsModel(); c.setCalculationName(x.getCalculationName());
		 * c.setColumnNames(x.getColumnNames()); c.setFormula(x.getFormula());
		 * c.setReportId(x.getReportId()); c.setReportName(x.getReportName());
		 * calculationsList.add(c); });
		 */
		m.setCalculationsList(calculationsList);

		return m;
	}

	public static QueryColumnModel queryColumnModelBuilder(JSONObject querycolumnJSONObj) {
		// TODO Auto-generated method stub
		QueryColumnModel queryColumnModelObj = new QueryColumnModel();

		queryColumnModelObj.setObjectId(querycolumnJSONObj.getString("dataSourceObjectId"));
		// queryColumnModelObj.setColumnQualification(querycolumnJSONObj.getString("columnQualification"));
		queryColumnModelObj.setColumnName(querycolumnJSONObj.getString("dataProviderObjectName"));

		return queryColumnModelObj;

	}

}
