package CodeSmell.Project;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import codesmellservice.App;
import codesmellservice.ExcelReader;
import codesmellservice.Gui;
import codesmellservice.Rule;
import codesmellservice.RuleSet;

public class RuleSetTest {
	
	private ExcelReader to_test;
	private RuleSet teste;
	
	@Before
	public void init() throws ClassNotFoundException, IOException {
		to_test = new ExcelReader("test.xlsx");
		teste = new RuleSet(to_test);
		Gui gui = new Gui(to_test,teste);
		gui.open();
	}
	
	@Test
	public void testLOCBANDCYCLOB() {
		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(4.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOBANDLOCB() {
		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(1.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(4.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testLOCBANDLOCB() {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(2.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOBANDCYCLOB() {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(1.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(2.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testLOCBANDCYCLOL() {

		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(2.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOBANDLOCL() {

		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(1.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(29.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testLOCBANDLOCL() {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(11.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOBANDCYCLOL() {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(1.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(5.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testLOCLANDCYCLOL() {

		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(29.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(2.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOLANDLOCL() {

		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(2.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(5.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testLOCLANDLOCL() {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(29.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(5.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOLANDCYCLOL() {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(2.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(5.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testLOCLANDCYCLOB() {

		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(29.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOLANDLOCB() {

		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(5.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(5.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testLOCLANDLOCB() {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(29.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(3.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testCYCLOLANDCYCLOB() {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(5.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
		
	}
	
	@Test
	public void testStringValuesORBB() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBBInv() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBBEqualLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(3.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBBEqualATFD() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","ATFD","ATFD");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBBEqualCYCLO() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBBEqualLAA() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","LAA","LAA");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBL() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBLInv() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBLEqualLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBLEqualATFD() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","ATFD","ATFD");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBLEqualCYCLO() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORBLEqualLAA() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","LAA","LAA");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	
	@Test
	public void testStringValuesORLL() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(4.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLLInv() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(4.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLLEqualLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(4.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLLEqualATFD() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","ATFD","ATFD");
		r.setMetricaX(3.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLLEqualCYCLO() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(3.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLLEqualLAA() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","LAA","LAA");
		r.setMetricaX(3.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLB() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","CYCLO");
		r.setMetricaX(3.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLBInv() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLBEqualLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","LOC");
		r.setMetricaX(3.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(3.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLBEqualATFD() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","ATFD","ATFD");
		r.setMetricaX(0.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(0.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLBEqualCYCLO() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","CYCLO","CYCLO");
		r.setMetricaX(1.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(1.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesORLBEqualLAA() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_feature_envy","LAA","LAA");
		r.setMetricaX(0.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(0.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("OR");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}

	@Test
	public void testStringValuesNBCYCLO() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","","CYCLO");
		r.setMetricaX(0.0);
		r.setmetricaXOperator("");
		r.setMetricaY(3.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesNBLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","","LOC");
		r.setMetricaX(0.0);
		r.setmetricaXOperator("");
		r.setMetricaY(3.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesNLLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","","LOC");
		r.setMetricaX(0.0);
		r.setmetricaXOperator("");
		r.setMetricaY(4.0);
		r.setmetricaYOperator("<");
		r.setLogicalOperator("");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesBNLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","");
		r.setMetricaX(3.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(0.0);
		r.setmetricaYOperator("");
		r.setLogicalOperator("");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	@Test
	public void testStringValuesLNLOC() throws ClassNotFoundException, IOException {

		Rule r = new Rule("is_long_method","LOC","");
		r.setMetricaX(4.0);
		r.setmetricaXOperator("<");
		r.setMetricaY(0.0);
		r.setmetricaYOperator("");
		r.setLogicalOperator("");
		teste.codeSmellIds(r, r.getMetricaXString(), r.getMetricaYString());		
		List<String> expected_list = new ArrayList<>();
		expected_list.add("true");
		expected_list.add("true");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("false");
		expected_list.add("true");
		expected_list.add("true");
	    
	    assertEquals(expected_list,teste.getResultadosBool());
	}
	
	
	
	@Test
	public void testQualityIndicatorsUserRule() throws ClassNotFoundException, IOException {
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
	
	
	@Test
	public void testQualityIndicatoriPlasma() {
		ExcelReader to_test = new ExcelReader("Defeitos.xlsx");
		RuleSet teste = new RuleSet(to_test);
		List<String> resultados = new ArrayList<>();
		Map<String, Integer> result_map = teste.qualityIndicators("iPlasma", resultados);
		Map<String,Integer> expected_map = new HashMap<String,Integer>();
		
		/*Fazendo formulas no ficheiro excel é possivel chegar aos valores DCI's DII's ADCI's ADII's esperados*/
		
		expected_map.put("DCI", 140);
	    expected_map.put("DII", 0);
	    expected_map.put("ADCI", 280);
	    expected_map.put("ADII", 0);
	    
	    assertEquals(expected_map,result_map);
		
	}
	
	@Test
	public void testExcelTools() {
		List<Integer> result = teste.excelTools("PMD");
		List<Integer> expected = new ArrayList<>();
		expected.add(3);
		expected.add(7);
		assertEquals(expected,result);
	}

}
