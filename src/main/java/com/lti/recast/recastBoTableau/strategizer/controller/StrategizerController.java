package com.lti.recast.recastBoTableau.strategizer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.recast.recastBoTableau.strategizer.dto.MigratorStatusModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ReportStrategizerModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculatedFormulaModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerQueryModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerVisualizationConvertor;
import com.lti.recast.recastBoTableau.strategizer.entity.ReportStrategizer;
import com.lti.recast.recastBoTableau.strategizer.services.AnalysisReportService;
import com.lti.recast.recastBoTableau.strategizer.services.AnalysisReportsTableService;
import com.lti.recast.recastBoTableau.strategizer.services.MigrateService;
import com.lti.recast.recastBoTableau.strategizer.services.StrategizerQueryService;
import com.lti.recast.recastBoTableau.strategizer.services.StrategizerService;

import net.sf.jsqlparser.JSQLParserException;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/strategizer")
public class StrategizerController {

	private static Logger logger = LoggerFactory.getLogger(StrategizerController.class);

	@Autowired
	StrategizerService strategizerService;
	
	@Autowired
	MigrateService migrateService;

	@Autowired(required = false)
	StrategizerQueryService strategizerQueryService;

	@Autowired
	AnalysisReportService analisisReportService;

	@Autowired
	AnalysisReportsTableService analysisReportsTableService;
	
	
	@GetMapping("/getTasks/{projectReportTargetConId}")
	public List<ReportStrategizerModel> getTasks(@PathVariable int projectReportTargetConId) {
		
		//System.out.println("task Id Analyzer:"+analysisTaskId);
		logger.debug("Inside get tasks");
		return strategizerService.getTasks(projectReportTargetConId);

	}

	@GetMapping("/FetchReportData/{taskId}")
	public ResponseEntity<ReportStrategizer> fetchReportStrategizer(@PathVariable Integer taskId) {

		ReportStrategizer request = strategizerService.findByAnalysisTaskId(taskId);
		return new ResponseEntity<ReportStrategizer>(request, HttpStatus.OK);
	}

	@PostMapping("/addTask")
	public ResponseEntity<String> getTableauReport(@RequestBody ReportStrategizerModel reportTaskModel) {
		logger.info("Inside BoToTableau Controller to fetch Analyser data.");

		ReportStrategizerModel r = strategizerService.save(reportTaskModel);

		String op;
		try {
			op = strategizerService.fetchStrategizerDetails(r).get();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			op = "Failed";
			return new ResponseEntity<String>(op,HttpStatus.METHOD_FAILURE);
		}

		// JSONObject queryStatements =
		// analisisReportService.fetchListOfQueryStatement(lstAnalysisReport);

		logger.info("Insert Query data to Straitegizer.");

		return new ResponseEntity<String>(op,HttpStatus.OK);
	}
	
	@GetMapping("/query/{id}")
	public List<StrategizerQueryModel> getConvertedQueryData(@PathVariable int id) {
		logger.info("Inside get Query Strategizer Analysis for: " + id); 
		return strategizerService.getQueryStrategizerData(id);
	}
	
	@GetMapping("/calculatedColumn/{id}")
	public List<StrategizerCalculatedFormulaModel> getConvertedCalculatedColumnData(@PathVariable int id) {
		logger.info("Inside get Query Strategizer Analysis for: " + id); 
		return strategizerService.getCalculatedColumnStrategizerData(id);
	}
	
	@GetMapping("/visualization/{id}")
	public List<StrategizerVisualizationConvertor> getVisualizationData(@PathVariable int id) {
		logger.info("Inside get Visualization Strategizer Analysis for: " + id); 
		return strategizerService.getVisualizationStrategizerData(id);
	}

	@GetMapping("/migrate/{id}")
	public String strategizerCSV(@PathVariable int id) throws JSQLParserException, Exception {
		logger.info("Inside migrate for: " + id);
		return migrateService.migrate(id);
	}
	
	@GetMapping("/migrate/status/{id}")
	public List<MigratorStatusModel> getMigratorSatus(@PathVariable int id) {
		logger.info("Inside get Migrator Status for: " + id); 
		return strategizerService.getMigratorStatus(id);
	}


}
