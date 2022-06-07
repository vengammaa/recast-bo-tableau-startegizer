package com.lti.recast.recastBoTableau.strategizer.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.recast.recastBoTableau.strategizer.dto.QueryColumnAliasModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculatedFormulaModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerVisualizationConvertor;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsVisualization;
import com.lti.recast.recastBoTableau.strategizer.entity.SapboTableauMapping;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculations;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerVisualizationConversion;
import com.lti.recast.recastBoTableau.strategizer.repository.SapboTableauMappingRepository;
import com.lti.recast.recastBoTableau.strategizer.util.CommonConstants;
import com.lti.recast.recastBoTableau.strategizer.util.EntityBuilder;
import com.lti.recast.recastBoTableau.strategizer.util.StrategizerUtility;

@Service
public class StrategizerVisualConvertorSerice {

	@Autowired
	private SapboTableauMappingRepository sapBoTableauMappingRepository;

	static Double constval = 3600.0;

	@Transactional
	public List<StrategizerVisualizationConversion> saveVisualConversionData(
			List<AnalysisReportsVisualization> filteredVisualDetailsList,
			List<StrategizerCalculatedColumn> strategizerCalculatedFormulaList, List<AnalysisReport> analysisReport,
			int stratTaskId, List<QueryColumnAliasModel> queryColumnAliasLst,
			List<StrategizerCalculations> strCalculation) {
		// TODO Auto-generated method stub
		List<StrategizerVisualizationConversion> strategizerVisualizationList = new ArrayList<StrategizerVisualizationConversion>();
		HashMap<String, String> selfJoinMap = selfJoin(filteredVisualDetailsList);
		double headerHeight = calcheaderHeight(filteredVisualDetailsList);
		double bodyHeight = calculateBodyHeight(filteredVisualDetailsList, selfJoinMap.get("Body"));

		// strategizerVisualizationList =
		// visualTableauMappingHeader(filteredVisualDetailsList,
		// selfJoinMap.get("Header"),
		// strategizerCalculatedFormulaList, stratTaskId, strategizerVisualizationList);
		/*
		 * strategizerVisualizationList =
		 * visualTableauMappingFooter(filteredVisualDetailsList,
		 * selfJoinMap.get("Footer"), strategizerVisualizationList,stratTaskId
		 * strategizerCalculatedFormulaList, headerHeight, bodyHeight);
		 */

		strategizerVisualizationList = visualTableauMappingVisual(filteredVisualDetailsList, selfJoinMap.get("Body"),
				strategizerCalculatedFormulaList, headerHeight, analysisReport, stratTaskId,
				strategizerVisualizationList, queryColumnAliasLst, strCalculation);

		return strategizerVisualizationList;
	}

	private List<StrategizerVisualizationConversion> visualTableauMappingVisual(
			List<AnalysisReportsVisualization> analysisVisualDetailsList, String bodyId,
			List<StrategizerCalculatedColumn> strategizerCalculatedFormulaList, double headerHeight,
			List<AnalysisReport> analysisReport, int stratTaskId,
			List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList,
			List<QueryColumnAliasModel> queryColumnAliasLst, List<StrategizerCalculations> strCalculation) {
		List<StrategizerVisualizationConversion> finalStrategizerVisualizationConvertorList = new ArrayList<>();

		HashMap<String, Integer> elementMap = elementMap(analysisVisualDetailsList);
		int counter = 1;
		for (AnalysisReportsVisualization visualDetailsList : analysisVisualDetailsList) {
			String jsonFormula = "";
			StrategizerVisualizationConversion strategizerVisualConverter = new StrategizerVisualizationConversion();
			if (visualDetailsList.getElementType().equalsIgnoreCase("HTable")
					|| visualDetailsList.getElementType().equalsIgnoreCase("VTable")) {

				strategizerVisualConverter = EntityBuilder.prepareStrategizerVisualConversion(visualDetailsList,
						stratTaskId, queryColumnAliasLst, headerHeight, elementMap, strCalculation, counter,
						strategizerVisualConverter);
				SapboTableauMapping sapbomapping = sapBoTableauMappingRepository.findBySapboComponent(
						visualDetailsList.getElementType() != null ? visualDetailsList.getElementType() : "");
				;
				strategizerVisualConverter.setSourceComponentName(visualDetailsList.getElementType());
				strategizerVisualConverter.setTargetComponentName(sapbomapping.getTableauComponent());
				jsonFormula = prepareFormulaForHTableAndVTable(
						visualDetailsList.getChartAxes() != null ? visualDetailsList.getChartAxes() : "",
						analysisReport, visualDetailsList.getElementType(), queryColumnAliasLst);
				strategizerVisualConverter.setFormulaName(jsonFormula != null ? jsonFormula : "");
				strategizerVisualizationConvertorList.add(strategizerVisualConverter);
				counter++;
			} else if (visualDetailsList.getElementType().equalsIgnoreCase("Visualization")) {
				strategizerVisualConverter = EntityBuilder.prepareStrategizerVisualConversion(visualDetailsList,
						stratTaskId, queryColumnAliasLst, headerHeight, elementMap, strCalculation, counter,
						strategizerVisualConverter);

				if (visualDetailsList.getChartType().equalsIgnoreCase("Pie")
						|| visualDetailsList.getChartType().equalsIgnoreCase("Doughnut")) {
					SapboTableauMapping sapbomapping = sapBoTableauMappingRepository.findBySapboComponent(
							visualDetailsList.getChartType() != null ? visualDetailsList.getChartType() : ""); //
					strategizerVisualConverter.setSourceComponentName(
							visualDetailsList.getChartType() != null ? visualDetailsList.getChartType() : "");
					strategizerVisualConverter.setTargetComponentName(
							sapbomapping.getTableauComponent() != null ? sapbomapping.getTableauComponent() : "");
					jsonFormula = prepareFormulaForPie(
							visualDetailsList.getChartAxes() != null ? visualDetailsList.getChartAxes() : "",
							analysisReport, visualDetailsList.getChartType(), queryColumnAliasLst);
					strategizerVisualConverter.setFormulaName(jsonFormula != null ? jsonFormula : "");
					strategizerVisualizationConvertorList.add(strategizerVisualConverter);

					counter++;

				} else if (visualDetailsList.getChartType().equalsIgnoreCase("VerticalBar")) {
					SapboTableauMapping sapbomapping = sapBoTableauMappingRepository.findBySapboComponent(
							visualDetailsList.getChartType() != null ? visualDetailsList.getChartType() : ""); //
					strategizerVisualConverter.setSourceComponentName(
							visualDetailsList.getChartType() != null ? visualDetailsList.getChartType() : "");
					strategizerVisualConverter.setTargetComponentName(
							sapbomapping.getTableauComponent() != null ? sapbomapping.getTableauComponent() : "");
					strategizerVisualConverter = EntityBuilder.prepareStrategizerVisualConversion(visualDetailsList,
							stratTaskId, queryColumnAliasLst, headerHeight, elementMap, strCalculation, counter,
							strategizerVisualConverter);
					jsonFormula = prepareFormulaForVerticalBar(
							visualDetailsList.getChartAxes() != null ? visualDetailsList.getChartAxes() : "",
							analysisReport, visualDetailsList.getChartType(), queryColumnAliasLst);
					strategizerVisualConverter.setFormulaName(jsonFormula != null ? jsonFormula : "");
					strategizerVisualizationConvertorList.add(strategizerVisualConverter);
					counter++;
				}  else if (visualDetailsList.getChartType().equalsIgnoreCase("Simple Table")) {
					SapboTableauMapping sapbomapping = sapBoTableauMappingRepository.findBySapboComponent(
							visualDetailsList.getChartType() != null ? visualDetailsList.getChartType() : ""); //
					strategizerVisualConverter.setSourceComponentName(
							visualDetailsList.getChartType() != null ? visualDetailsList.getChartType() : "");
					strategizerVisualConverter.setTargetComponentName(
							sapbomapping.getTableauComponent() != null ? sapbomapping.getTableauComponent() : "");
					strategizerVisualConverter = EntityBuilder.prepareStrategizerVisualConversion(visualDetailsList,
							stratTaskId, queryColumnAliasLst, headerHeight, elementMap, strCalculation, counter,
							strategizerVisualConverter);
					jsonFormula = prepareFormulaForSimpleTable(
							visualDetailsList.getChartAxes() != null ? visualDetailsList.getChartAxes() : "",
							analysisReport, visualDetailsList.getChartType(), queryColumnAliasLst);
					strategizerVisualConverter.setFormulaName(jsonFormula != null ? jsonFormula : "");
					strategizerVisualizationConvertorList.add(strategizerVisualConverter);
					counter++;
				}

			}
			else if (visualDetailsList.getElementType().equalsIgnoreCase("XTable")) {
				
				strategizerVisualConverter = EntityBuilder.prepareStrategizerVisualConversion(visualDetailsList,
						stratTaskId, queryColumnAliasLst, headerHeight, elementMap, strCalculation, counter,
						strategizerVisualConverter);
				SapboTableauMapping sapbomapping = sapBoTableauMappingRepository.findBySapboComponent(
						visualDetailsList.getElementType() != null ? visualDetailsList.getElementType() : "");
				;
				strategizerVisualConverter.setSourceComponentName(visualDetailsList.getElementType());
				strategizerVisualConverter.setTargetComponentName(sapbomapping.getTableauComponent());
				jsonFormula = prepareFormulaForCrossTab(
						visualDetailsList.getChartAxes() != null ? visualDetailsList.getChartAxes() : "",
						analysisReport, visualDetailsList.getChartType(), queryColumnAliasLst);
				strategizerVisualConverter.setFormulaName(jsonFormula != null ? jsonFormula : "");
				strategizerVisualizationConvertorList.add(strategizerVisualConverter);
				counter++;
			}

		}

		int size = strategizerVisualizationConvertorList.size();
		System.err.println(strategizerVisualizationConvertorList.size());
		int intialcount=0;
		while (size >= 4) {	
			finalStrategizerVisualizationConvertorList.addAll(foursheets(strategizerVisualizationConvertorList.subList(intialcount, intialcount+4)));
			size = size-4;
			intialcount=intialcount+4;
			}
		if(size==3)
		{
			finalStrategizerVisualizationConvertorList.addAll(threesheets(strategizerVisualizationConvertorList.subList(intialcount,strategizerVisualizationConvertorList.size())));
		}
		else if(size==2)
		{
			finalStrategizerVisualizationConvertorList.addAll(twosheets(strategizerVisualizationConvertorList.subList(intialcount,strategizerVisualizationConvertorList.size())));
		}
		else if(size==1) {
			finalStrategizerVisualizationConvertorList.addAll(onesheet(strategizerVisualizationConvertorList.subList(intialcount,strategizerVisualizationConvertorList.size())));
		}		
		return finalStrategizerVisualizationConvertorList;
	}
	
	 int namecount =1;
	
	  private List<StrategizerVisualizationConversion> foursheets(List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList)
	  {		  
		 
		  List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList1 = new ArrayList<>();
				int xcount = 1;
				int ycount = 1;
				for (StrategizerVisualizationConversion strategizerCalculations : strategizerVisualizationConvertorList) {
					
					if (xcount == 1 && ycount == 1) {
						strategizerCalculations.setTargetPositionX(CommonConstants.MIN_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MIN_Y);
						xcount++;
					} else if (ycount == 1 && xcount == 2) {
						strategizerCalculations.setTargetPositionX(CommonConstants.MAX_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MIN_Y);
						ycount++;
					} else if (xcount == 2 && ycount == 2) {
						strategizerCalculations.setTargetPositionX(CommonConstants.MIN_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MAX_Y);
						ycount++;
					} else {
						strategizerCalculations.setTargetPositionX(CommonConstants.MAX_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MAX_Y);
					}
					strategizerCalculations.setTargetMinimalWidth(Integer.toString(Integer.parseInt(CommonConstants.MAX_WIDTH)/2));
					strategizerCalculations.setTargetMinimalHeight(Integer.toString(Integer.parseInt(CommonConstants.MAX_HEIGHT)/2));
					strategizerCalculations.setDashboardName(CommonConstants.DASHBOARD_NAME+namecount);
					strategizerVisualizationConvertorList1.add(strategizerCalculations);
				}
				namecount++;
				System.err.println(" namecount "+ namecount);
		  return strategizerVisualizationConvertorList1;
	  }
	 
	  private List<StrategizerVisualizationConversion> threesheets(List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList)
	  {
		  List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList1 = new ArrayList<>();
				int xcount = 1;
				int ycount = 1;
				for (StrategizerVisualizationConversion strategizerCalculations : strategizerVisualizationConvertorList) {
					if (xcount == 1 && ycount == 1) {
						strategizerCalculations.setTargetPositionX(CommonConstants.MIN_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MIN_Y);
						strategizerCalculations.setTargetMinimalWidth(Integer.toString(Integer.parseInt(CommonConstants.MAX_WIDTH)/2));
						strategizerCalculations.setTargetMinimalHeight(Integer.toString(Integer.parseInt(CommonConstants.MAX_HEIGHT)/2));
						xcount++;
					} else if (ycount == 1 && xcount == 2) {
						strategizerCalculations.setTargetPositionX(CommonConstants.MIN_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MAX_Y);
						strategizerCalculations.setTargetMinimalWidth(Integer.toString(Integer.parseInt(CommonConstants.MAX_WIDTH)/2));
						strategizerCalculations.setTargetMinimalHeight(Integer.toString(Integer.parseInt(CommonConstants.MAX_HEIGHT)/2));
						ycount++;
					} else {
						strategizerCalculations.setTargetPositionX(CommonConstants.MAX_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MIN_Y);
						strategizerCalculations.setTargetMinimalWidth(Integer.toString(Integer.parseInt(CommonConstants.MAX_WIDTH)/2));
						strategizerCalculations.setTargetMinimalHeight(CommonConstants.MAX_HEIGHT);
					}
					strategizerCalculations.setDashboardName(CommonConstants.DASHBOARD_NAME+namecount);
					strategizerVisualizationConvertorList1.add(strategizerCalculations);
				}
				namecount++;
		  return strategizerVisualizationConvertorList1;
	  }
	  private List<StrategizerVisualizationConversion> twosheets(List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList)
	  {
		 
		  List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList1 = new ArrayList<>();
				int xcount = 1;
				int ycount = 1;
				for (StrategizerVisualizationConversion strategizerCalculations : strategizerVisualizationConvertorList) {
					if (xcount == 1 && ycount == 1) {
						strategizerCalculations.setTargetPositionX(CommonConstants.MIN_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MIN_Y);
						xcount++;
					}
					 else {
						strategizerCalculations.setTargetPositionX(CommonConstants.MAX_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MIN_Y);
						ycount++;
					}
					strategizerCalculations.setTargetMinimalWidth(Integer.toString(Integer.parseInt(CommonConstants.MAX_WIDTH)/2));
					strategizerCalculations.setTargetMinimalHeight(CommonConstants.MAX_HEIGHT);
					strategizerCalculations.setDashboardName(CommonConstants.DASHBOARD_NAME+namecount);
					strategizerVisualizationConvertorList1.add(strategizerCalculations);
				}
			   namecount++;
		  return strategizerVisualizationConvertorList1;
	  }
	  private List<StrategizerVisualizationConversion> onesheet(List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList)
	  {
		  List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList1 = new ArrayList<>();
				int xcount = 1;
				int ycount = 1;
				for (StrategizerVisualizationConversion strategizerCalculations : strategizerVisualizationConvertorList) {
					if (xcount == 1 && ycount == 1) {
						strategizerCalculations.setTargetPositionX(CommonConstants.MIN_X);
						strategizerCalculations.setTargetPositionY(CommonConstants.MIN_Y);
					}
					strategizerCalculations.setTargetMinimalWidth(CommonConstants.MAX_WIDTH);
					strategizerCalculations.setTargetMinimalHeight(CommonConstants.MAX_HEIGHT);
					strategizerCalculations.setDashboardName(CommonConstants.DASHBOARD_NAME+namecount);
					strategizerVisualizationConvertorList1.add(strategizerCalculations);
				}
				namecount++;
		  return strategizerVisualizationConvertorList1;
	  }
	@SuppressWarnings("unused")
	private String fetchCalculatedFormulaVisual(String formulaName,
			List<StrategizerCalculatedFormulaModel> filteredStrategizerCalculatedFormulaList,
			AnalysisReportsVisualization visualDetails) {
		// TODO Auto-generated method stub

		String reportId = visualDetails.getReportId();
		String reportTabId = visualDetails.getReportTabId();

		Iterator<StrategizerCalculatedFormulaModel> iter = filteredStrategizerCalculatedFormulaList.iterator();

		while (iter.hasNext()) {
			StrategizerCalculatedFormulaModel x = iter.next();

			if (x.getReportId().equalsIgnoreCase(reportId) && x.getReportTabId().equalsIgnoreCase(reportTabId)) {
				if (x.getFormula().equalsIgnoreCase(formulaName)) {
					return x.getCalculatedFormula().split("=")[0];
				}
			}

		}

		return formulaName;
	}

	private StrategizerVisualizationConvertor setStrategizerConvertorObject(
			StrategizerVisualizationConvertor strategizerVisualConverter, AnalysisReportsVisualization visualDetails,
			Double maxWidth, Double maxHeight) {
		// TODO Auto-generated method stub

		strategizerVisualConverter.setReportId(visualDetails.getReportId());
		strategizerVisualConverter.setReportTabId(visualDetails.getReportTabId());
		strategizerVisualConverter.setReportTabName(visualDetails.getReportTabName());
		strategizerVisualConverter.setParentElement("Body");

		strategizerVisualConverter.setFont("''");
		strategizerVisualConverter.setColor("''");
		strategizerVisualConverter.setValueType("''");

		return strategizerVisualConverter;
	}

	private List<StrategizerVisualizationConvertor> visualTableauMappingFooter(
			List<AnalysisReportsVisualization> visualDetailsList, String footerId,
			List<StrategizerVisualizationConvertor> strategizerVisualizationConvertorList,
			List<StrategizerCalculatedFormulaModel> strategizerCalculatedFormulaList, double headerHeight,
			double bodyHeight) {

		List<StrategizerCalculatedFormulaModel> filteredStrategizerCalculatedFormulaList = strategizerCalculatedFormulaList
				.stream().filter(x -> x.getColumnQualification().equalsIgnoreCase("Unnamed Dimension"))
				.collect(Collectors.toList());

		for (AnalysisReportsVisualization analysisReportVisual : visualDetailsList) {
			Double maxWidth = Double.parseDouble(analysisReportVisual.getMaximumWidth());
			Double maxHeight = Double.parseDouble(analysisReportVisual.getMaximumHeight());
			if (!(analysisReportVisual.getParentId() == null) && analysisReportVisual.getParentId().equals(footerId)) {
				if (analysisReportVisual.getElementType().equals("Cell")) {
					if (analysisReportVisual.getFormula().contains("String")
							|| analysisReportVisual.getFormula().contains("Numeric")) {
						StrategizerVisualizationConvertor strategizerVisualConverter = new StrategizerVisualizationConvertor();
						if (analysisReportVisual.getFormula().contains("String")) {
							SapboTableauMapping sapbomapping = sapBoTableauMappingRepository
									.findBySapboComponent("StringCell");
							strategizerVisualConverter.setTargetComponentName(sapbomapping.getTableauComponent());
						} else {
							SapboTableauMapping sapbomapping = sapBoTableauMappingRepository
									.findBySapboComponent("NumericCell");
							strategizerVisualConverter.setTargetComponentName(sapbomapping.getTableauComponent());
						}
						strategizerVisualConverter.setReportId(analysisReportVisual.getReportId());
						strategizerVisualConverter.setReportTabId(analysisReportVisual.getReportTabId());
						strategizerVisualConverter.setReportTabName(analysisReportVisual.getReportTabName());
						strategizerVisualConverter.setParentElement("Footer");
						strategizerVisualConverter.setSourceComponentName("StringCell");
						String minimalHeightSource = analysisReportVisual.getMinimalHeight();
						String minimalWidthSource = analysisReportVisual.getMinimalWidth();
						String xPositionSource = analysisReportVisual.getxPosition();
						String yPositionSource = analysisReportVisual.getyPosition();

						if (minimalHeightSource == null) {
							minimalHeightSource = "0";
						}
						if (minimalWidthSource == null) {
							minimalWidthSource = "0";
						}
						if (xPositionSource == null) {
							xPositionSource = "0";
						}
						if (yPositionSource == null) {
							yPositionSource = "0";
						}
						strategizerVisualConverter.setSourceMinimalHeight(minimalHeightSource);
						strategizerVisualConverter.setSourceMinimalWidth(minimalWidthSource);
						strategizerVisualConverter.setSourcePositionX(xPositionSource);
						strategizerVisualConverter.setSourcePositionY(yPositionSource);

						Double conxPosition = (Double.parseDouble(xPositionSource)) / constval;
						Double conyPosition = (Double.parseDouble(yPositionSource)) / constval;
						conyPosition += (headerHeight + bodyHeight);
						Double conminimalWidth = (Double.parseDouble(minimalWidthSource)) / constval;
						Double conminimalHeight = (Double.parseDouble(minimalHeightSource)) / constval;

						strategizerVisualConverter
								.setTargetPositionX(StrategizerUtility.calXPosition(conxPosition, maxWidth));
						// strategizerVisualConverter.setTargetPositionY("650");
						strategizerVisualConverter
								.setTargetPositionY(StrategizerUtility.calYPosition(conyPosition, maxHeight));
						strategizerVisualConverter
								.setTargetMinimalWidth(StrategizerUtility.calXPosition(conminimalWidth, maxWidth));
						strategizerVisualConverter
								.setTargetMinimalHeight(StrategizerUtility.calYPosition(conminimalHeight, maxHeight));

						String font = analysisReportVisual.getFont().substring(12, 14);
						if (font.contains(",")) {
							font = font.replace(",", "");
						}

						strategizerVisualConverter.setFont(font);
						String formula = setFormulaForCell(analysisReportVisual,
								filteredStrategizerCalculatedFormulaList);
						strategizerVisualConverter.setFormulaName(formula);

						strategizerVisualConverter.setColor(analysisReportVisual.getBackgroundColor());
						strategizerVisualConverter.setValueType("Function");
						strategizerVisualizationConvertorList.add(strategizerVisualConverter);
					} else {
						// image
					}

				} else {
					System.out.println("Strategizer Coming feature for " + analysisReportVisual.getElementType());
				}
			}

			if (analysisReportVisual.getElementName().equalsIgnoreCase("Footer")
					&& analysisReportVisual.getElementType().equalsIgnoreCase("Page Zone")) {

				// System.out.println(analysisReportVisual.getElementType());

				StrategizerVisualizationConvertor strategizerVisualConverter = new StrategizerVisualizationConvertor();

				String minimalHeight = analysisReportVisual.getMinimalHeight();
				Double conminimalHeight = (Double.parseDouble(minimalHeight)) / constval;

				strategizerVisualConverter.setReportId(analysisReportVisual.getReportId());
				strategizerVisualConverter.setReportTabId(analysisReportVisual.getReportTabId());
				strategizerVisualConverter.setReportTabName(analysisReportVisual.getReportTabName());
				strategizerVisualConverter.setParentElement("Footer");
				strategizerVisualConverter.setSourceComponentName("TextBox");
				strategizerVisualConverter.setTargetComponentName("TextBox");

				String minimalHeightSource = analysisReportVisual.getMinimalHeight()!=null?analysisReportVisual.getMinimalHeight():"0";
				String minimalWidthSource = analysisReportVisual.getMinimalWidth()!=null?analysisReportVisual.getMinimalWidth():"0";
				String xPositionSource = analysisReportVisual.getxPosition()!=null?analysisReportVisual.getxPosition():"0";
				String yPositionSource = analysisReportVisual.getyPosition()!=null?analysisReportVisual.getyPosition():"0";

				strategizerVisualConverter.setSourceMinimalHeight(minimalHeightSource);
				strategizerVisualConverter.setSourceMinimalWidth(minimalWidthSource);
				strategizerVisualConverter.setSourcePositionX(xPositionSource);
				strategizerVisualConverter.setSourcePositionY(yPositionSource);

				strategizerVisualConverter.setTargetPositionX("0");
				double conyPosition = maxHeight - conminimalHeight;
				// double conyPosition = maxHeight-(headerHeight + bodyHeight)+conminimalHeight;
				strategizerVisualConverter.setTargetPositionY(StrategizerUtility.calYPosition(conyPosition, maxHeight));

				strategizerVisualConverter.setTargetMinimalWidth("1280");
				strategizerVisualConverter
						.setTargetMinimalHeight(StrategizerUtility.calYPosition(conminimalHeight, maxHeight));

				strategizerVisualConverter.setFont("''");
				strategizerVisualConverter.setFormulaName("''");
				strategizerVisualConverter.setColor(analysisReportVisual.getBackgroundColor());
				strategizerVisualConverter.setValueType("Static");
				strategizerVisualizationConvertorList.add(strategizerVisualConverter);

			}

		}

		return strategizerVisualizationConvertorList;
	}

	private List<StrategizerVisualizationConversion> visualTableauMappingHeader(
			List<AnalysisReportsVisualization> visualDetailsList, String headerId,
			List<StrategizerCalculatedColumn> strategizerCalculatedFormulaList, int stratTaskId,
			List<StrategizerVisualizationConversion> strategizerVisualizationConvertorList) {
		// TODO Auto-generated method stub

		List<StrategizerCalculatedColumn> filteredStrategizerCalculatedFormulaList = strategizerCalculatedFormulaList
				.stream().filter(x -> x.getColumnQualification().equalsIgnoreCase("Unnamed Dimension"))
				.collect(Collectors.toList());

		for (AnalysisReportsVisualization analysisReportVisual : visualDetailsList) {
			Double maxWidth = Double.parseDouble(analysisReportVisual.getMaximumWidth());
			Double maxHeight = Double.parseDouble(analysisReportVisual.getMaximumHeight());
			if (!(analysisReportVisual.getParentId() == null) && analysisReportVisual.getParentId().equals(headerId)) {
				/*
				 * if (analysisReportVisual.getElementType().equals("Cell")) {
				 * 
				 * if (analysisReportVisual.getFormula().contains("String") ||
				 * analysisReportVisual.getFormula().contains("Numeric")) {
				 * StrategizerVisualizationConversion strategizerVisualConverter = new
				 * StrategizerVisualizationConversion();
				 * 
				 * if (analysisReportVisual.getFormula().contains("String")) {
				 * SapboTableauMapping sapbomapping = sapBoTableauMappingRepository
				 * .findBySapboComponent("StringCell");
				 * strategizerVisualConverter.setTargetComponentName(sapbomapping.
				 * getTableauComponent()); } else { SapboTableauMapping sapbomapping =
				 * sapBoTableauMappingRepository .findBySapboComponent("NumericCell");
				 * strategizerVisualConverter.setTargetComponentName(sapbomapping.
				 * getTableauComponent()); }
				 * strategizerVisualConverter.setStratTaskId(stratTaskId);
				 * 
				 * strategizerVisualConverter.setReportId(analysisReportVisual.getReportId());
				 * strategizerVisualConverter.setReportTabId(analysisReportVisual.getReportTabId
				 * ()); strategizerVisualConverter.setReportTabName(analysisReportVisual.
				 * getReportTabName()); strategizerVisualConverter.setParentElement("Header");
				 * strategizerVisualConverter.setSourceComponentName("StringCell");
				 * 
				 * String minimalHeightSource = analysisReportVisual.getMinimalHeight(); String
				 * minimalWidthSource = analysisReportVisual.getMinimalWidth(); String
				 * xPositionSource = analysisReportVisual.getxPosition(); String yPositionSource
				 * = analysisReportVisual.getyPosition();
				 * 
				 * if (minimalHeightSource == null) { minimalHeightSource = "0"; } if
				 * (minimalWidthSource == null) { minimalWidthSource = "0"; } if
				 * (xPositionSource == null) { xPositionSource = "0"; } if (yPositionSource ==
				 * null) { yPositionSource = "0"; }
				 * strategizerVisualConverter.setSourceMinimalHeight(minimalHeightSource);
				 * strategizerVisualConverter.setSourceMinimalWidth(minimalWidthSource);
				 * strategizerVisualConverter.setSourcePositionX(xPositionSource);
				 * strategizerVisualConverter.setSourcePositionY(yPositionSource);
				 * 
				 * Double conxPosition = (Double.parseDouble(xPositionSource)) / constval;
				 * Double conyPosition = (Double.parseDouble(yPositionSource)) / constval;
				 * Double conminimalWidth = (Double.parseDouble(minimalWidthSource)) / constval;
				 * Double conminimalHeight = (Double.parseDouble(minimalHeightSource)) /
				 * constval;
				 * 
				 * strategizerVisualConverter
				 * .setTargetPositionX(StrategizerUtility.calXPosition(conxPosition, maxWidth));
				 * strategizerVisualConverter
				 * .setTargetPositionY(StrategizerUtility.calYPosition(conyPosition,
				 * maxHeight)); strategizerVisualConverter
				 * .setTargetMinimalWidth(StrategizerUtility.calXPosition(conminimalWidth,
				 * maxWidth)); strategizerVisualConverter
				 * .setTargetMinimalHeight(StrategizerUtility.calYPosition(conminimalHeight,
				 * maxHeight));
				 * 
				 * String font = analysisReportVisual.getFont().substring(12, 14); if
				 * (font.contains(",")) { font = font.replace(",", ""); }
				 * 
				 * strategizerVisualConverter.setFont(font); // Set formula
				 * 
				 * String formula = setFormulaForCell(analysisReportVisual,
				 * filteredStrategizerCalculatedFormulaList);
				 * strategizerVisualConverter.setFormulaName(formula);
				 * 
				 * strategizerVisualConverter.setColor(analysisReportVisual.getBackgroundColor()
				 * ); strategizerVisualConverter.setValueType("Function");
				 * strategizerVisualizationConvertorList.add(strategizerVisualConverter);
				 * 
				 * }
				 * 
				 * else { // image }
				 * 
				 * }
				 */

				if (analysisReportVisual.getElementName().equalsIgnoreCase("Header")) {
					StrategizerVisualizationConversion strategizerVisualConverter = new StrategizerVisualizationConversion();
					String minimalHeight = analysisReportVisual.getMinimalHeight();

					Double conminimalHeight = (Double.parseDouble(minimalHeight)) / constval;
					strategizerVisualConverter.setStratTaskId(stratTaskId);
					strategizerVisualConverter.setReportId(analysisReportVisual.getReportId());
					strategizerVisualConverter.setReportTabId(analysisReportVisual.getReportTabId());
					strategizerVisualConverter.setReportTabName(analysisReportVisual.getReportTabName());
					strategizerVisualConverter.setParentElement("Header");
					strategizerVisualConverter.setSourceComponentName("TextBox");
					strategizerVisualConverter.setTargetComponentName("TextBox");

					String minimalHeightSource = analysisReportVisual.getMinimalHeight();
					String minimalWidthSource = analysisReportVisual.getMinimalWidth();
					String xPositionSource = analysisReportVisual.getxPosition();
					String yPositionSource = analysisReportVisual.getyPosition();

					if (minimalHeightSource == null) {
						minimalHeightSource = "0";
					}
					if (minimalWidthSource == null) {
						minimalWidthSource = "0";
					}
					if (xPositionSource == null) {
						xPositionSource = "0";
					}
					if (yPositionSource == null) {
						yPositionSource = "0";
					}

					strategizerVisualConverter.setSourceMinimalHeight(minimalHeightSource);
					strategizerVisualConverter.setSourceMinimalWidth(minimalWidthSource);
					strategizerVisualConverter.setSourcePositionX(xPositionSource);
					strategizerVisualConverter.setSourcePositionY(yPositionSource);
					strategizerVisualConverter.setTargetPositionX("0");
					strategizerVisualConverter.setTargetPositionY("0");
					strategizerVisualConverter.setTargetMinimalWidth("1280");
					strategizerVisualConverter
							.setTargetMinimalHeight(StrategizerUtility.calYPosition(conminimalHeight, maxHeight));
					strategizerVisualConverter.setFont("''");
					strategizerVisualConverter.setFormulaName("''");
					strategizerVisualConverter.setColor(analysisReportVisual.getBackgroundColor());
					strategizerVisualConverter.setValueType("Static");
					strategizerVisualizationConvertorList.add(strategizerVisualConverter);
				}
			}
		}
		return strategizerVisualizationConvertorList;
	}

	private String setFormulaForCell(AnalysisReportsVisualization visualDetails,
			List<StrategizerCalculatedFormulaModel> strategizerCalculatedFormulaList) {
		// TODO Auto-generated method stub

		String formula = visualDetails.getFormula();

		System.out.println("Formula in cell::" + formula);

		if (!formula.equalsIgnoreCase("[]")) {
			if (formula.contains("|")) {
				String formulaCellArray[] = formula.split("\\|");
				for (int j = 0; j < formulaCellArray.length; j++) {
					String formulaData = formulaCellArray[j];
					formulaData = formulaData.substring(11, formulaData.length() - 1);

					String[] formulaCellArrayData = formulaData.split(",");
					String formulaObjectId = formulaCellArrayData[4];

					String formulaValue = formulaCellArrayData[0];
					if (formulaObjectId.equalsIgnoreCase("]") && !formulaValue.equalsIgnoreCase("")) {
						return StrategizerUtility.fetchCalculatedFormula(formulaValue, visualDetails,
								strategizerCalculatedFormulaList);
					} else {
						return formulaValue.substring(2, formulaValue.length() - 1);
					}
				}

			} else {
				System.out.println("Formula for Cell:" + formula);

				String formulaData = formula.substring(11, formula.length() - 1);

				// System.out.println("Formula Data:"+formulaData);

				String[] formulaCellArrayData = formulaData.split(",");
				String formulaObjectId = formulaCellArrayData[4];
				String formulaValue = formulaCellArrayData[0];
				// System.out.println("Formula Value::"+formulaValue);
				if (formulaObjectId.equalsIgnoreCase("]") && !formulaValue.equalsIgnoreCase("")) {
					// return formulaValue;
					return StrategizerUtility.fetchCalculatedFormula(formulaValue, visualDetails,
							strategizerCalculatedFormulaList);
				} else {
					return formulaValue.substring(2, formulaValue.length() - 1);
				}
			}
		}

		return null;

	}

	public HashMap<String, String> selfJoin(List<AnalysisReportsVisualization> visualDetailsList) {
		HashMap<String, String> selfJoinMap = new HashMap<String, String>();
		for (int i = 0; i < visualDetailsList.size(); i++) {
			if (visualDetailsList.get(i).getElementType().equals("Page Zone")) {
				selfJoinMap.put(visualDetailsList.get(i).getElementName(), visualDetailsList.get(i).getElementId());
			}
		}
		return selfJoinMap;
	}

	private HashMap<String, Integer> elementMap(List<AnalysisReportsVisualization> visualDetailsList) {
		HashMap<String, Integer> elementHashMap = new HashMap<String, Integer>();
		for (int i = 0; i < visualDetailsList.size(); i++) {
			if (!elementHashMap.containsKey(visualDetailsList.get(i).getElementId())) {
				elementHashMap.put(visualDetailsList.get(i).getElementId(), i);
			}
		}
		System.out.println("ElementMap :: " + elementHashMap.toString());
		return elementHashMap;
	}

	public double calcheaderHeight(List<AnalysisReportsVisualization> visualDetailsList) {
		double headerHeight = 0.0;
		for (int i = 0; i < visualDetailsList.size(); i++) {
			if (visualDetailsList.get(i).getElementName().equals("Header")) {
				headerHeight = Double.parseDouble(visualDetailsList.get(i).getMinimalHeight());
			}

		}
		headerHeight = headerHeight / 3600;
		return headerHeight;
	}

	private double calculateBodyHeight(List<AnalysisReportsVisualization> visualDetailsList, String bodyId) {
		// TODO Auto-generated method stub

		double maxHeight = 0.0;

		for (int i = 0; i < visualDetailsList.size(); i++) {
			if (!(visualDetailsList.get(i).getParentId() == null)
					&& visualDetailsList.get(i).getParentId().equals(bodyId)) {
				AnalysisReportsVisualization visualElementsObj = visualDetailsList.get(i);
				String xPositionVal = visualElementsObj.getxPosition();
				String yPositionVal = visualElementsObj.getyPosition();
				String minimalHeight = visualElementsObj.getMinimalHeight();

				if (xPositionVal == null || xPositionVal.isEmpty()) {
					xPositionVal = "0.00";
				}
				if (yPositionVal == null || yPositionVal.isEmpty()) {
					yPositionVal = "0.00";
				}
				if (minimalHeight == null || minimalHeight.isEmpty()) {
					minimalHeight = "0.00";
				}

				double yPosition = Double.parseDouble(yPositionVal);

				double height = Double.parseDouble(minimalHeight);

				double sumHeight = height + yPosition;

				if (maxHeight < sumHeight) {
					maxHeight = sumHeight;
				}
			}

		}

		maxHeight = maxHeight / 3600;

		BigDecimal bd2 = new BigDecimal(maxHeight).setScale(2, RoundingMode.HALF_DOWN);

		// maxHeight is Approximate Body Height
		maxHeight = bd2.doubleValue() + 0.50;

		return maxHeight;
	}

	public String prepareFormulaForHTableAndVTable(String chartAxesString, List<AnalysisReport> analysisReportList,
			String elementType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		JSONArray jsonVariableList = null;
		JSONObject jsonHTable = new JSONObject();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");

		}

		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");

			JSONArray namejsonArray = new JSONArray();
			JSONArray textjsonArray = new JSONArray();
			for (int k = 0; k < formulaJSONArray.length(); k++) {
				JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);

				String dataObjectId = formulaJSONObj.getString("dataObjectId") != null
						? formulaJSONObj.getString("dataObjectId")
						: "";
				int counter = 0;
				for (Object jsonVariable : jsonVariableList) {

					JSONObject jsonVar = (JSONObject) jsonVariable;
					if (dataObjectId.equals(jsonVar.getString("id"))) {
						counter = counter + 1;
						if (jsonVar.getString("dataType").equals("Numeric")
								&& jsonVar.getString("qualification").equals("Measure")) {
							String columnName = jsonVar.getString("name").substring(2,
									jsonVar.getString("name").length() - 1);
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());
								}
							}
						} else {
							String columnName = jsonVar.getString("name");
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());
								}
							}
						}
					}

				}
				if (counter == 0) {
					if (formulaJSONObj.getString("dataType").equals("Numeric")
							&& formulaJSONObj.getString("qualification").equals("Measure")) {
						String columnName = formulaJSONObj.getString("name").substring(2,
								formulaJSONObj.getString("name").length() - 1);
						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
								textjsonArray.put(columnAlias.getAliasName());
							}
						}

					} else {
						String columnName = formulaJSONObj.getString("name").substring(2,
								formulaJSONObj.getString("name").length() - 1);
						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
								namejsonArray.put(columnAlias.getAliasName());
							}
						}

					}
				}
			}

			jsonHTable.put("type", "Table");
			JSONArray MeasureArray = new JSONArray();
			MeasureArray.put("Measure Names");
			if (elementType.equals("HTable")) {
				jsonHTable.put("cols", namejsonArray);
				jsonHTable.put("rows", MeasureArray);
			} else {
				jsonHTable.put("rows", namejsonArray);
				jsonHTable.put("cols", MeasureArray);
			}

			JSONArray filter = new JSONArray();
			JSONObject filterObj = new JSONObject();
			filterObj.put("column", "Measure Names");
			filterObj.put("from", "");
			filterObj.put("to", "");
			filterObj.put("members", textjsonArray);
			filter.put(filterObj);
			jsonHTable.put("filter", filter);

			JSONArray structure = new JSONArray();
			JSONObject structureObj = new JSONObject();
			JSONArray multipleValue = new JSONArray();
			multipleValue.put("Multiple Values");
			structureObj.put("type", "Table");
			structureObj.put("text", multipleValue);
			structure.put(structureObj);

			jsonHTable.put("structure", structure);

		}

		return jsonHTable.toString();

	}

	public String prepareFormulaForPie(String chartAxesString, List<AnalysisReport> analysisReportList,
			String chartType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		JSONArray jsonVariableList = null;
		JSONObject jsonPie = new JSONObject();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");
		}
		JSONArray namejsonArray = new JSONArray();
		JSONArray textjsonArray = new JSONArray();
		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			if (!chartJSONObj.isNull("formulaList")) {
				JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");
				for (int k = 0; k < formulaJSONArray.length(); k++) {
					JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);
					String dataObjectId = formulaJSONObj.getString("dataObjectId") != null
							? formulaJSONObj.getString("dataObjectId")
							: "";
					int counter = 0;
					for (Object jsonVariable : jsonVariableList) {

						JSONObject jsonVar = (JSONObject) jsonVariable;
						if (dataObjectId.equals(jsonVar.getString("id"))) {
							counter = counter + 1;
							if (jsonVar.getString("dataType").equals("Numeric")
									&& jsonVar.getString("qualification").equals("Measure")) {
								String columnName = jsonVar.getString("name").substring(2,
										jsonVar.getString("name").length() - 1);
								for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

									if (columnName.equals(columnAlias.getColumnName())) {
										namejsonArray.put(columnAlias.getAliasName());
									}
								}
							} else {
								String columnName = jsonVar.getString("name");
								for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

									if (columnName.equals(columnAlias.getColumnName())) {
										textjsonArray.put(columnAlias.getAliasName());
									}
								}
							}
						}

					}
					if (counter == 0) {
						String columnName;

						if (formulaJSONObj.getString("dataType").equals("Numeric")
								&& formulaJSONObj.getString("qualification").equals("Measure")) {
							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position2 == position1) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());
								}
							}

						} else {

							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position1 == position2) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());
								}
							}

						}
					}

				}
				JSONArray structure = new JSONArray();
				JSONObject structureObj = new JSONObject();
				if (chartType.equalsIgnoreCase("Pie")) {
					jsonPie.put("type", "Pie");
					structureObj.put("type", "Pie");
				} else if (chartType.equalsIgnoreCase("Doughnut")) {
					jsonPie.put("type", "Donut");
					structureObj.put("type", "Donut");
				}
				structureObj.put("color", namejsonArray);
				structureObj.put("wsize", textjsonArray);
				structureObj.put("size", textjsonArray);
				structure.put(structureObj);
				jsonPie.put("structure", structure);
			}
		}
		return jsonPie.toString();
	}

	public String prepareFormulaForVerticalBar(String chartAxesString, List<AnalysisReport> analysisReportList,
			String ChartType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		JSONArray jsonVariableList = null;
		JSONObject jsonVerticalBar = new JSONObject();
		JSONArray namejsonArray = new JSONArray();
		JSONArray textjsonArray = new JSONArray();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");
		}
		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			if (!chartJSONObj.isNull("formulaList")) {
				JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");
				for (int k = 0; k < formulaJSONArray.length(); k++) {
					JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);
					String dataObjectId = formulaJSONObj.getString("dataObjectId") != null
							? formulaJSONObj.getString("dataObjectId")
							: "";
					int counter = 0;
					for (Object jsonVariable : jsonVariableList) {
						JSONObject jsonVar = (JSONObject) jsonVariable;
						if (dataObjectId.equals(jsonVar.getString("id"))) {
							counter = counter + 1;
							if (jsonVar.getString("dataType").equals("Numeric")
									&& jsonVar.getString("qualification").equals("Measure")) {
								String columnName = jsonVar.getString("name").substring(2,
										jsonVar.getString("name").length() - 1);
								for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

									if (columnName.equals(columnAlias.getColumnName())) {
										namejsonArray.put(columnAlias.getAliasName());
									}
								}
							} else {
								String columnName = jsonVar.getString("name");
								for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

									if (columnName.equals(columnAlias.getColumnName())) {
										textjsonArray.put(columnAlias.getAliasName());
									}
								}
							}
						}
					}
					if (counter == 0) {
						String columnName;
						if (formulaJSONObj.getString("dataType").equals("Numeric")
								&& formulaJSONObj.getString("qualification").equals("Measure")) {
							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position2 == position1) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());
								}
							}
						} else {
							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position1 == position2) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());
								}
							}

						}
					}
				}
				if (ChartType.equals("VerticalBar")) {
					jsonVerticalBar.put("type", "Bar");
					jsonVerticalBar.put("header", "Bar");
					jsonVerticalBar.put("rows", textjsonArray);
					jsonVerticalBar.put("cols", namejsonArray);

				} else if (ChartType.equals("Line")) {
					jsonVerticalBar.put("type", "Line");
					jsonVerticalBar.put("header", "Line");
					jsonVerticalBar.put("rows", namejsonArray);
					jsonVerticalBar.put("cols", textjsonArray);
				}

				else {
					jsonVerticalBar.put("type", "Bar");
					jsonVerticalBar.put("header", "Bar");
					jsonVerticalBar.put("rows", namejsonArray);
					jsonVerticalBar.put("cols", textjsonArray);
				}
				JSONArray structure = new JSONArray();
				JSONObject structureObj = new JSONObject();
				structureObj.put("type", "Column");
				structure.put(structureObj);
				jsonVerticalBar.put("structure", structure);
			}
		}
		return jsonVerticalBar.toString();
	}

	public String prepareFormulaForSimpleTable(String chartAxesString, List<AnalysisReport> analysisReportList,
			String ChartType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		JSONArray jsonVariableList = null;
		JSONObject jsonSimple = new JSONObject();
		JSONArray namejsonArray = new JSONArray();
		JSONArray textjsonArray = new JSONArray();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");
		}
		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			if (!chartJSONObj.isNull("formulaList")) {
				JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");
				for (int k = 0; k < formulaJSONArray.length(); k++) {
					JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);
					String dataObjectId = formulaJSONObj.getString("dataObjectId") != null
							? formulaJSONObj.getString("dataObjectId")
							: "";
					int counter = 0;
					for (Object jsonVariable : jsonVariableList) {
						JSONObject jsonVar = (JSONObject) jsonVariable;
						if (dataObjectId.equals(jsonVar.getString("id"))) {
							counter = counter + 1;
							if (jsonVar.getString("dataType").equals("Numeric")
									&& jsonVar.getString("qualification").equals("Measure")) {
								String columnName = jsonVar.getString("name").substring(2,
										jsonVar.getString("name").length() - 1);
								for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

									if (columnName.equals(columnAlias.getColumnName())) {
										namejsonArray.put(columnAlias.getAliasName());
									}
								}
							} else {
								String columnName = jsonVar.getString("name");
								for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
									if (columnName.equals(columnAlias.getColumnName())) {
										textjsonArray.put(columnAlias.getAliasName());
									}
								}
							}
						}
					}
					if (counter == 0) {
						String columnName;
						if (formulaJSONObj.getString("dataType").equals("Numeric")
								&& formulaJSONObj.getString("qualification").equals("Measure")) {
							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position2 == position1) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());
								}
							}
						} else {
							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position1 == position2) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());

								}
							}

						}
					}
				}
				jsonSimple.put("type", "Table");
				jsonSimple.put("header", "Table");
				jsonSimple.put("rows", textjsonArray);
				jsonSimple.put("rowsOrdinal", namejsonArray);
				JSONArray structure = new JSONArray();
				JSONObject structureObj = new JSONObject();
				structureObj.put("type", "Table");
				structure.put(structureObj);
				jsonSimple.put("structure", structure);
			}
		}
		return jsonSimple.toString();
	}

	public String prepareFormulaForCrossTab(String chartAxesString, List<AnalysisReport> analysisReportList,
			String ChartType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		JSONArray jsonVariableList = null;
		JSONObject jsonCrossTab = new JSONObject();
		JSONArray namejsonArray = new JSONArray();
		JSONArray colJsonArray = new JSONArray();
		JSONArray textjsonArray = new JSONArray();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");
		}
		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			if (!chartJSONObj.isNull("role")) {
				String columnName;
				if (chartJSONObj.getString("role").equals("Row")) {
					if (!chartJSONObj.isNull("formulaList")) {
						JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");

						for (int k = 0; k < formulaJSONArray.length(); k++) {
							JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);

							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position1 == position2) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());

								}
							}
						}

					}

				} else if (chartJSONObj.getString("role").equals("Column")) {
					if (!chartJSONObj.isNull("formulaList")) {
						JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");

						for (int k = 0; k < formulaJSONArray.length(); k++) {
							JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);
							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position1 == position2) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									colJsonArray.put(columnAlias.getAliasName());

								}
							}
						}
					}
				} else if (chartJSONObj.getString("role").equals("Body")) {
					if (!chartJSONObj.isNull("formulaList")) {
						JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");

						for (int k = 0; k < formulaJSONArray.length(); k++) {
							JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);
							int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
							int position2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
							if (position1 == position2) {
								columnName = formulaJSONObj.getString("name").substring(position1 + 1,
										formulaJSONObj.getString("name").length() - 1);
							} else {
								columnName = formulaJSONObj.getString("name").substring(position2 + 1,
										formulaJSONObj.getString("name").length() - 1);
							}
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());

								}
							}

						}
					}
				}

			}

		}
		jsonCrossTab.put("type", "Table");
		jsonCrossTab.put("header", "Cross Tab");
		jsonCrossTab.put("rows", namejsonArray);
		jsonCrossTab.put("cols", colJsonArray);
		JSONArray structure = new JSONArray();
		JSONObject structureObj = new JSONObject();
		structureObj.put("type", "Table");
		// JSONArray text = new JSONArray();
		structureObj.put("text", textjsonArray);
		structure.put(structureObj);
		jsonCrossTab.put("structure", structure);
		return jsonCrossTab.toString();

	}

}
