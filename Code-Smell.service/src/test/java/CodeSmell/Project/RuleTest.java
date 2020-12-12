package CodeSmell.Project;

import org.junit.jupiter.api.Test;

import codesmellservice.Rule;
import junit.framework.TestCase;

public class RuleTest extends TestCase {

	@Test
	public void testSetRegra() {
		Rule regra = new Rule("", "", "");
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

	@Test
	public void testRegraMetricaY() {
		Rule regra = new Rule("", "", "");
		regra.setNomeRegra("BOMDIA");
		regra.setCodeSmell("is_long_method");
		regra.setMetricaYString("CYCLO");
		regra.setMetricaY(20.0);
		regra.setmetricaYOperator(">");
		String s = regra.toString();
		assertEquals("Rule: BOMDIA | CYCLO>20.0 | CodeSmell: is_long_method", s);
	}

	@Test
	public void testRegraMetricaX() {
		Rule regra = new Rule("", "", "");
		regra.setNomeRegra("BOMDIA");
		regra.setCodeSmell("is_long_method");
		regra.setMetricaXString("LOC");
		regra.setMetricaX(50.0);
		regra.setmetricaXOperator(">");
		String s = regra.toString();
		assertEquals("Rule: BOMDIA | LOC>50.0 | CodeSmell: is_long_method", s);
	}

}
