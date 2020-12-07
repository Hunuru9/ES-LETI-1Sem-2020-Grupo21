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
		//excelReader.getMetricValues("LOC");
		Gui gui = new Gui(excelReader, ruleSet);
		gui.open();
		
		Rule r=new Rule("is_long_method");
        r.setNomeRegra("BOAS");
        r.setMetricaX(80.0);
        r.setmetricaXOperator(">");
        r.setMetricaY(10.0);
        r.setmetricaYOperator(">");
        r.setLogicalOperator("AND");
        List <String> firstColumn=excelReader.getColumnValues("LOC");
        List <String> secondColumn=excelReader.getColumnValues("CYCLO");
        String aux=r.getmetricaXOperator()+r.getmetricaYOperator();
        ruleSet.quality_indicators(r, ruleSet.stringValues(aux, r, firstColumn, secondColumn));
    }
	
	
    
    
}
