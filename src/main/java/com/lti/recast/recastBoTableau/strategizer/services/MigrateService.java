package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lti.recast.recastBoTableau.strategizer.dto.BOTableauMigratorModel;
import com.lti.recast.recastBoTableau.strategizer.dto.MigratorModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculatedFormulaModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculationsModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerDatasourceModelModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerMetadataColumnModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerQueryModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerVisualizationConvertor;
import com.lti.recast.recastBoTableau.strategizer.entity.MigratorStatus;
import com.lti.recast.recastBoTableau.strategizer.entity.ReportStrategizer;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerVisualizationConversion;
import com.lti.recast.recastBoTableau.strategizer.repository.MigratorStatusRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.ReportStrategizerRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerCalculatedColumnRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerCalculationsRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerDatasourceModelRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerMetadataColumnRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerQueryConversionRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerVisualConversionRepository;
import com.lti.recast.recastBoTableau.strategizer.util.ModelBuilder;

import net.sf.jsqlparser.JSQLParserException;
@Service
public class MigrateService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired(required = false)
	private ReportStrategizerRepository reportStrategizerRepository;

	@Autowired(required = false)
	private StrategizerQueryConversionRepository strategizerQueryConversionRepository;

	@Autowired(required = false)
	private StrategizerCalculatedColumnRepository strategizerCalculatedColumnRepository;

	@Autowired(required = false)
	private StrategizerVisualConversionRepository strategizerVisualConvertorRepo;

	@Autowired(required = false)
	private StrategizerMetadataColumnRepository strategizerMetadataColumnRepository;

	@Autowired(required = false)
	private StrategizerDatasourceModelRepository strategizerDatasourceModelRepository;

	@Autowired(required = false)
	private StrategizerCalculationsRepository strategizerCalculationsRepository;

	//@Autowired(required = false)
	//private MigratorStatusRepository migratorStatusRepo;

	public String migrate(int stratId) throws JSQLParserException, Exception {
		// TODO Auto-generated method stub

		// List<ReportStrategizerModel> reportStrategizer =
		// reportStrategizerRepository.findById(stratId).stream().map(ModelBuilder::);
		List<MigratorStatus> lstMigrate = new ArrayList<MigratorStatus>();
		ReportStrategizer p = reportStrategizerRepository.findById(stratId).get();

		if (p.getSourceReportType().equalsIgnoreCase("BO")) {
			if (p.getTargetReportType().equalsIgnoreCase("Tableau")) {
				System.out.println("In Tableau Migrator");
				// MigratorStatus migratorStatus = new MigratorStatus();

				// long startTime = System.nanoTime();

				//LocalDateTime startTime = LocalDateTime.now();

				//DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");

				//List<StrategizerVisualizationConversion> strategizervisualData = strategizerVisualConvertorRepo
					//	.findDistinctByStratTaskId(stratId);

				BOTableauMigratorModel t = new BOTableauMigratorModel();

				List<StrategizerQueryModel> strategizerQueryModelList = strategizerQueryConversionRepository
						.findByStratTaskId(stratId).stream().map(ModelBuilder::strategizerModelQueryBuider)
						.collect(Collectors.toList());

				List<StrategizerVisualizationConvertor> strategizerVisualConvertorList = strategizerVisualConvertorRepo
						.findByStratTaskId(stratId).stream().map(ModelBuilder::strategizerModelVisualizationBuilder)
						.collect(Collectors.toList());

				List<StrategizerCalculatedFormulaModel> strategizerCalculatedFormulaList = strategizerCalculatedColumnRepository
						.findByStratTaskId(stratId).stream().map(ModelBuilder::strategizerModelCalculatedColumnBuilder)
						.collect(Collectors.toList());

				List<StrategizerMetadataColumnModel> strategizerMetadataColumnList = strategizerMetadataColumnRepository
						.findByStratTaskId(stratId).stream().map(ModelBuilder::strategizerModelMetadataColumnBuilder)
						.collect(Collectors.toList());

				List<StrategizerDatasourceModelModel> strategizerDataModelList = strategizerDatasourceModelRepository
						.findByStratTaskId(stratId).stream().map(ModelBuilder::strategizerModelDataModelBuilder)
						.collect(Collectors.toList());

				List<StrategizerCalculationsModel> strategizerCalculationModelList = strategizerCalculationsRepository
						.findByStratTaskId(stratId).stream().map(ModelBuilder::strategizerCalculationsModelBuilder)
						.collect(Collectors.toList());

				t.setStategizerId(stratId);
				t.setQueryModelList(strategizerQueryModelList);
				t.setVisualConvertorList(strategizerVisualConvertorList);
				t.setCalculatedFormulaList(strategizerCalculatedFormulaList);
				t.setMetadataColumnList(strategizerMetadataColumnList);
				t.setDatasourceModelList(strategizerDataModelList);
				t.setCalculationsList(strategizerCalculationModelList);

				MigratorModel model = ModelBuilder.BOTableauModelBuilder(t);

				String result = restTemplate.postForObject("http://RECAST-TABLEAU-MIGRATOR/boToTableau/migrator", model,
						String.class);
				// LocalDateTime endTime = LocalDateTime.now();
				// Long endTime = System.nanoTime();
				/*
				 * LocalDateTime endTime = LocalDateTime.now(); String formatStartTime =
				 * startTime.format(format); String formatEndTime = endTime.format(format);
				 * Timestamp startTime_object = Timestamp.valueOf(formatStartTime); Timestamp
				 * endTime_object = Timestamp.valueOf(formatEndTime); Duration totalTime =
				 * Duration.between(startTime, endTime); String actualtotalTime =
				 * Long.toString(totalTime.toHours()) + " hours " +
				 * Long.toString(totalTime.toMinutes()) + " minutes";
				 * 
				 * for (StrategizerVisualizationConversion visualDetail : strategizervisualData)
				 * { MigratorStatus migrate = new MigratorStatus();
				 * migrate.setStratTaskId(stratId);
				 * migrate.setReportId(visualDetail.getReportId());
				 * migrate.setReportTabId(visualDetail.getReportTabId());
				 * migrate.setEndTime(endTime_object); migrate.setStartTime(startTime_object);
				 * migrate.setMacroRuntime(actualtotalTime); lstMigrate.add(migrate); }
				 * 
				 * migratorStatusRepo.saveAll(lstMigrate);
				 */
				
				return result;
			}
		}

		return "Failure";
	}

}
