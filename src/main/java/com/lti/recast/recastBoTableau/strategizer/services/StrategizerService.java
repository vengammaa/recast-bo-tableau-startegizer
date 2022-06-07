package com.lti.recast.recastBoTableau.strategizer.services;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.lti.recast.recastBoTableau.strategizer.dto.MigratorStatusModel;
import com.lti.recast.recastBoTableau.strategizer.dto.QueryColumnAliasModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ReportStrategizerModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculatedFormulaModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerQueryModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerVisualizationConvertor;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsTable;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsVisualization;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisSemanticColumns;
import com.lti.recast.recastBoTableau.strategizer.entity.ReportStrategizer;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculations;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerQueryConversion;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerVisualizationConversion;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportTableRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportsVisualizationRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisSemanticColumnRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.MigratorStatusRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.ReportStrategizerRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerCalculatedColumnRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerQueryConversionRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerVisualConversionRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.TaskStatusRepository;
import com.lti.recast.recastBoTableau.strategizer.util.EntityBuilder;
import com.lti.recast.recastBoTableau.strategizer.util.ModelBuilder;

import net.sf.jsqlparser.JSQLParserException;

@Service
public class StrategizerService {

	private static final Logger logger = LoggerFactory.getLogger(StrategizerService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired(required = false)
	private AnalysisReportRepository analysisReportRepository;

	@Autowired(required = false)
	private AnalysisReportsVisualizationRepository visualDetailsRepository;

	@Autowired(required = false)
	private AnalysisReportTableRepository analysisReportTableRepository;

	@Autowired(required = false)
	private  StrategizerQueryService strategizerQueryService;

	@Autowired(required = false)
	private  StrategizerQueryConversionRepository strategizerQueryConversionRepository;

	@Autowired(required = false)
	private StrategizerCalculatedColumnRepository strategizerCalculatedColumnRepository;

	@Autowired(required = false)
	private StrategizerCalculatedFormulaService strategizerCalculatedFormulaService;

	@Autowired(required = false)
	private ReportStrategizerRepository reportStrategizerRepository;

	@Autowired(required = false)
	private TaskStatusRepository taskStatusRepository;

	@Autowired(required = false)
	private StrategizerVisualConvertorSerice strategizerVisualConvertorSerice;

	@Autowired(required = false)
	private StrategizerVisualConversionRepository strategizerVisualConvertorRepo;

	@Autowired
	StrategizerColculationsService strategizerCalculationService;
	
	@Autowired(required = false)
	private AnalysisSemanticColumnRepository semanticColumnRepository;
	
	@Autowired
	private AnalysisReportTableRepository analysisReportTableRepo;
	
	@Autowired
	private StrategizerMetadataColumnService strategizerMetadataColumnService;
	
	@Autowired(required = false)
	private MigratorStatusRepository migratorStatusRepository;

	@Transactional
	public ReportStrategizerModel save(ReportStrategizerModel reportTaskModel) {
		// TODO Auto-generated method stub

		ReportStrategizer strategizerTask = EntityBuilder.reportStrategizerBuilder(reportTaskModel);
		strategizerTask = reportStrategizerRepository.save(strategizerTask);

		ReportStrategizerModel model = ModelBuilder.strategizerModelBuilder(strategizerTask);
		model.setSelectedReports(reportTaskModel.getSelectedReports());
		return model;
	}

	public CompletableFuture<String> fetchStrategizerDetails(
			ReportStrategizerModel r) {
		// TODO Auto-generated method stub

		ReportStrategizer p = reportStrategizerRepository.findById(r.getId()).get();
		p.setTaskStatus(taskStatusRepository.findById("INPROG").get());

		reportStrategizerRepository.saveAndFlush(p);

		// Fetch Analysis Report Data
		System.out.println("In Target as Tableau");

		try {
			int analysisTaskId = r.getAnalysisTaskId();
			int strateTaskId= r.getId();

			List<StrategizerQueryConversion> strategizerQueryConversionList = new ArrayList<StrategizerQueryConversion>();

			List<StrategizerCalculatedColumn> strategizerCalculatedColumnList = new ArrayList<StrategizerCalculatedColumn>();


			List<AnalysisReport> analysisReportList = analysisReportRepository
					.findByPrjRptAnalysisId(analysisTaskId).stream().filter(x ->r.getSelectedReports().contains(x.getReportId().toString()))
				      .collect(Collectors.toList());

			// Fetch Analysis Report Table Table Data
			List<AnalysisSemanticColumns> semanticColumns= semanticColumnRepository.findByTaskId(analysisTaskId);
			

			List<AnalysisReportsTable> analysisReportTableLst= analysisReportTableRepo.findByTaskId(analysisTaskId).stream()
				      .filter(x ->r.getSelectedReports().contains(x.getReportId().toString()))
				      .collect(Collectors.toList());;
			// Fetch Visualization Details

			List<AnalysisReportsVisualization> visualDetailsReportList = visualDetailsRepository
					.findByTaskId(analysisTaskId).stream()
					 .filter(x ->r.getSelectedReports().contains(x.getReportId().toString()))
				      .collect(Collectors.toList());

			//Query conversion and save data
			
			List<QueryColumnAliasModel> strQueryColumnAliasModel=saveStrategizerQueryConversionData(analysisReportList,strateTaskId,semanticColumns,analysisReportTableLst);
			 
			// Save Query meta data Column data
			
			strategizerMetadataColumnService.saveStrategizerMetaDataColumnData(strateTaskId,analysisReportTableLst,analysisTaskId);

			// Calculated Formula Conversion Call

			strategizerCalculatedColumnList=saveFunctionalMappingConversionData(visualDetailsReportList, analysisReportList, strategizerQueryConversionList,strateTaskId,strategizerCalculatedColumnList);
		
			// save data in strategizer calculations
			List<StrategizerCalculations> straCalcutionLst=strategizerCalculationService.saveStrategizerCalculations(strategizerCalculatedColumnList,strateTaskId,strQueryColumnAliasModel);
			
			
			// Visualization Conversion Call
			
			
			List<StrategizerVisualizationConversion> strategizerVisualList = strategizerVisualConvertorSerice
					.saveVisualConversionData(visualDetailsReportList, strategizerCalculatedColumnList,analysisReportList,strateTaskId,strQueryColumnAliasModel,straCalcutionLst);

			strategizerVisualConvertorRepo.saveAll(strategizerVisualList);

			/* Set status of task as completed, save and flush the changes */
			p.setTaskStatus(taskStatusRepository.findById("FIN").get());
			reportStrategizerRepository.saveAndFlush(p);
			logger.debug("Status of " + p.getTaskName() + " is now set to " + p.getTaskStatus().getName() + ".");

		} catch (Exception e) {
			p.setTaskStatus(taskStatusRepository.findById("FAIL").get());
			reportStrategizerRepository.saveAndFlush(p);
			logger.debug("Status of " + p.getTaskName() + " is now set to " + p.getTaskStatus().getName() + ".");
			e.printStackTrace();
		}

		String op = "Status of " + p.getTaskName() + " is now set to " + p.getTaskStatus().getName() + ".";
		return CompletableFuture.completedFuture(op);

	}

	@Transactional
	public List<ReportStrategizerModel> getTasks(int projectReportTargetConId) {
		// TODO Auto-generated method stub

		logger.info("inside gettasks");
		return reportStrategizerRepository.findAllByProjectReportTargetConId(projectReportTargetConId).stream()
				.map(ModelBuilder::strategizerModelBuilder).collect(Collectors.toList());

	}

	@Transactional
	public List<StrategizerQueryModel> getQueryStrategizerData(int id) {
		// TODO Auto-generated method stub
		return strategizerQueryConversionRepository.findByStratTaskId(id).stream()
				.map(ModelBuilder::strategizerModelQueryBuider).collect(Collectors.toList());

	}

	@Transactional
	public List<StrategizerVisualizationConvertor> getVisualizationStrategizerData(int id) {
		// TODO Auto-generated method stub
		return strategizerVisualConvertorRepo.findByStratTaskId(id).stream()
				.map(ModelBuilder::strategizerModelVisualizationBuilder).collect(Collectors.toList());
	}

	public void callClientPythonProgram() throws Exception {

		FileReader reader = new FileReader(
				getClass().getClassLoader().getResource("").getPath().substring(1).replaceAll("%20", " ")
						+ "/strategizer/strategizer.properties");
		Properties p = new Properties();
		p.load(reader);
		String migratorPath = p.getProperty("MigratorPath");

		Path path = Paths.get(migratorPath);
		String clientFilename = p.getProperty("ClientPythonProgramName");

		String clientCommand = "cmd /c start cmd.exe /K \"cd /d \"";
		clientCommand += path.toString() + "\"&&python " + clientFilename + "\"";
		// clientCommand+="\"&&python ";
		// clientCommand+=clientFilename+"\"";
		System.out.println(clientCommand);

		Runtime.getRuntime().exec(clientCommand);

	}

	public void callServerPythonProgram() throws Exception {

		FileReader reader = new FileReader(
				getClass().getClassLoader().getResource("").getPath().substring(1).replaceAll("%20", " ")
						+ "/strategizer/strategizer.properties");
		Properties p = new Properties();
		p.load(reader);
		String migratorPath = p.getProperty("MigratorPath");
		Path path = Paths.get(migratorPath);
		String serverFilename = p.getProperty("ServerPythonProgramName");

		String serverCommand = "cmd /c start cmd.exe /K \"cd /d \"";
		serverCommand += path.toString() + "\"&&python " + serverFilename + "\"";

		System.out.println(serverCommand);

		Runtime.getRuntime().exec(serverCommand);

		// Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /d
		// \"C:\\Users\\10673891\\Desktop\\\"&&python server_multiple_reports.py\"");

	}

	public ReportStrategizer findByAnalysisTaskId(Integer taskId) {

		ReportStrategizer request = reportStrategizerRepository.findByAnalysisTaskId(taskId);
		// TODO Auto-generated method stub
		return request;
	}

	
	public  List<QueryColumnAliasModel> saveStrategizerQueryConversionData(List<AnalysisReport> analysisReportList, int strateTaskId,List<AnalysisSemanticColumns> semanticColumns,	List<AnalysisReportsTable> analysisReportTableLst) throws JSQLParserException
	{
		List<QueryColumnAliasModel> strategizerQueryColumnAliasList = strategizerQueryService
				.saveQueryData(analysisReportList,strateTaskId,semanticColumns,analysisReportTableLst);

		return strategizerQueryColumnAliasList;
		
	}
	
	public List<StrategizerCalculatedColumn> saveFunctionalMappingConversionData(List<AnalysisReportsVisualization> filteredVisualDetailsList, List<AnalysisReport> analysisReportList,
			List<StrategizerQueryConversion> strategizerQueryConversionList,int stratTaskId,List<StrategizerCalculatedColumn> strategizerCalculatedColumnList)
	{
		
		List<StrategizerCalculatedColumn> strategizerCalculatedFormulaList = strategizerCalculatedFormulaService
				.saveFormulaData(filteredVisualDetailsList, analysisReportList, strategizerQueryConversionList,stratTaskId);

		List<StrategizerCalculatedColumn>	strategizerCalculatedFormulaLst= restTemplate.postForObject("http://RECAST-BO-TABLEAU-FUNCTION-MAPPING/v1.0/functionmapping",strategizerCalculatedFormulaList,List.class);
		JSONArray arrJsonStrategizerCalculatedFormulaLst = new JSONArray(strategizerCalculatedFormulaLst);
		for(Object jsonStrategizerCalculatedFormulaLst:arrJsonStrategizerCalculatedFormulaLst)
		{
			JSONObject StrategizerCalculatedFormulaLst= (JSONObject) jsonStrategizerCalculatedFormulaLst;
			StrategizerCalculatedColumn strategizerCalculatedColumn = EntityBuilder
					.StrategizerCalculatedColumnBuilder(StrategizerCalculatedFormulaLst,stratTaskId);
			if(!strategizerCalculatedColumn.getCalculatedFormula().contains("NOT AVAILABLE"))
			{

			strategizerCalculatedColumnList.add(strategizerCalculatedColumn);
			}
		}
		
		
		strategizerCalculatedColumnRepository.saveAll(strategizerCalculatedColumnList);
		return strategizerCalculatedColumnList;
	}
	
	@Transactional
	public List<StrategizerCalculatedFormulaModel> getCalculatedColumnStrategizerData(int id) {
		// TODO Auto-generated method stub
		return strategizerCalculatedColumnRepository.findByStratTaskId(id).stream().map(ModelBuilder::strategizerModelCalculatedColumnBuilder).collect(Collectors.toList());
	}
	
	@Transactional
	public List<MigratorStatusModel> getMigratorStatus(int id) {
		return migratorStatusRepository.findByStratTaskId(id).stream().map(ModelBuilder::migratorStatusModelBuilder).collect(Collectors.toList());
	}
	
	
	/*
	 * public void callClientPythonProgram() throws Exception {
	 * 
	 * ProcessBuilder processBuilder = new ProcessBuilder("python",
	 * resolvePythonScriptPath("client_1_multiple_reports.py"));
	 * processBuilder.redirectErrorStream(true);
	 * 
	 * Process process = processBuilder.start(); List<String> results =
	 * readProcessOutput(process.getInputStream());
	 * 
	 * System.out.println(results);
	 * 
	 * 
	 * }
	 * 
	 * public void callServerPythonProgram() throws Exception { ProcessBuilder
	 * processBuilder = new ProcessBuilder("python",
	 * resolvePythonScriptPath("server_multiple_reports.py"));
	 * processBuilder.redirectErrorStream(true);
	 * 
	 * Process process = processBuilder.start(); List<String> results =
	 * readProcessOutput(process.getInputStream());
	 * 
	 * System.out.println(results);
	 * 
	 * 
	 * } private String resolvePythonScriptPath(String filename) {
	 * 
	 * try { FileReader reader=new
	 * FileReader(getClass().getClassLoader().getResource("").getPath().substring(1)
	 * .replaceAll("%20", " ")+"/strategizer/strategizer.properties"); Properties
	 * p=new Properties(); p.load(reader); String migratorPath =
	 * p.getProperty("MigratorPath"); Path path = Paths.get(migratorPath+filename);
	 * 
	 * return path.toString();
	 * 
	 * } catch(Exception e) { System.err.println("Failed to create directory!" +
	 * e.getMessage()); return null; } }
	 * 
	 * private static List<String> readProcessOutput(InputStream inputStream) throws
	 * IOException { try (BufferedReader output = new BufferedReader(new
	 * InputStreamReader(inputStream))) { return output.lines()
	 * .collect(Collectors.toList()); } }
	 */

}
