package CodeSmell.Project;

import java.io.IOException;

import org.junit.Test;

import codesmellservice.ExcelReader;
import codesmellservice.Gui;
import codesmellservice.RuleSet;

public class GuiTest {
	
	@Test
	public void testBotaoCriarRegra() throws ClassNotFoundException, IOException {
		ExcelReader to_test = new ExcelReader("test.xlsx");
		RuleSet teste = new RuleSet(to_test);
		Gui gui = new Gui(to_test, teste);
		gui.open();
	}

}
