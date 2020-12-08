package codesmellservice;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		ExcelReader excelReader = new ExcelReader("Defeitos.xlsx");
		RuleSet ruleSet=new RuleSet(excelReader);
		Gui gui = new Gui(excelReader, ruleSet);
		gui.open();
		
    }
	
	
    
    
}
