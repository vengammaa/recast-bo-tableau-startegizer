package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.recast.recastBoTableau.strategizer.dto.QueryColumnAliasModel;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculations;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerCalculationsRepository;

@Service
public class StrategizerColculationsService {
	
	@Autowired(required = false)
	private StrategizerCalculationsRepository strategizerCalculationsRepository;
	
	public List<StrategizerCalculations> saveStrategizerCalculations(List<StrategizerCalculatedColumn> strategizerCalculatedColumnList,
			int strateTaskId, List<QueryColumnAliasModel> strQueryColumnAliasModel) {
		List<StrategizerCalculations> strategizerCalLst= new ArrayList<StrategizerCalculations>();
		for(StrategizerCalculatedColumn calculations:strategizerCalculatedColumnList)
		{
			StrategizerCalculations strategizerCalCol= new StrategizerCalculations();
			
			strategizerCalCol.setStratTaskId(strateTaskId);
			strategizerCalCol.setReportId(calculations.getReportId()!=null?calculations.getReportId():"");
			strategizerCalCol.setReportName(calculations.getReportName()!=null?calculations.getReportName():"");
			for(QueryColumnAliasModel queryColumnAlias: strQueryColumnAliasModel)
			{
				String columnName= calculations.getCalculatedFormula().substring(calculations.getCalculatedFormula().indexOf("[")+1,calculations.getCalculatedFormula().indexOf("]"));
				if(columnName.equalsIgnoreCase(queryColumnAlias.getColumnName()))
				{
					strategizerCalCol.setFormula(calculations.getCalculatedFormula()!=null?calculations.getCalculatedFormula().replace(columnName,queryColumnAlias.getAliasName()):"");
					strategizerCalCol.setColumnNames(queryColumnAlias.getAliasName());
				}
			}
			
			strategizerCalCol.setCalculationName("Calculation_"+getNumericString());
			
			strategizerCalLst.add(strategizerCalCol);
		}
		strategizerCalculationsRepository.saveAll(strategizerCalLst);
		
		return strategizerCalLst;
	}

	public  static String getNumericString()
    {
  
        // chose a Character random from this String
        String NumericString = "0123456789";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(18);
  
        for (int i = 0; i < 18; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(NumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(index);
        }
  
        return sb.toString();
    }

	
}
