package CodeSmell.Project;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.jupiter.api.BeforeAll;

import codesmellservice.Rule;

public class RuleTest {
	@Test
	public void testSetRegra() {
		Rule regra=new Rule("","","");
		regra.setNomeRegra("BOMDIA");
		regra.setCodeSmell("is_long_method");
		regra.setMetricaXString("LOC");
		regra.setMetricaYString("CYCLO");
		regra.setMetricaX(50.0);
		regra.setMetricaY(20.0);
		regra.setmetricaXOperator(">");
		regra.setmetricaYOperator(">");
		regra.setLogicalOperator("AND");
		String s = regra.toString();
		assertEquals("Rule: BOMDIA | LOC>50.0 AND CYCLO>20.0 | CodeSmell: is_long_method", s);
	}

}
