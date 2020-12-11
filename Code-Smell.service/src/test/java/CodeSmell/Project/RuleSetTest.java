package CodeSmell.Project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import codesmellservice.ExcelReader;
import codesmellservice.Rule;
import codesmellservice.RuleSet;

public class RuleSetTest {

	@Test
	public void testQualityIndicatorsUserRule() {
		ExcelReader to_test = new ExcelReader("Defeitos.xlsx");
		RuleSet teste = new RuleSet(to_test);
		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(80.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(10.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		Map<String, Integer> result_map = teste.qualityIndicators(r.getCodeSmell(), teste.getResultadosBool());
		Map<String,Integer> expected_map = new HashMap<String,Integer>();
		
		/*Fazendo formulas no ficheiro excel é possivel chegar aos valores DCI's DII's ADCI's ADII's esperados*/
		
	    expected_map.put("DCI", 137);
	    expected_map.put("DII", 0);
	    expected_map.put("ADCI", 280);
	    expected_map.put("ADII", 3);
	    
	    assertEquals(expected_map,result_map);
	}
	
	@Test
	public void testQualityIndicatorPMD() {
		ExcelReader to_test = new ExcelReader("Defeitos.xlsx");
		RuleSet teste = new RuleSet(to_test);
		List<String> resultados = new ArrayList<>();
		Map<String, Integer> result_map = teste.qualityIndicators("PMD", resultados);
		Map<String,Integer> expected_map = new HashMap<String,Integer>();
		
		/*Fazendo formulas no ficheiro excel é possivel chegar aos valores DCI's DII's ADCI's ADII's esperados*/
		
		expected_map.put("DCI", 140);
	    expected_map.put("DII", 18);
	    expected_map.put("ADCI", 262);
	    expected_map.put("ADII", 0);
	    
	    assertEquals(expected_map,result_map);
		
	}

}
