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
		/*Rule r=new Rule("is_feature_envy", "ATFD", "ATFD");
		r.setNomeRegra("BOAS");
		r.setMetricaX(15.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(20.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		
		List<Integer> result=ruleSet.codeSmellIds(r, "ATFD", "LAA");
		
		for(int i=0; i!=result.size(); i++) {
			System.out.println(result.get(i));
		}*/
		
	}
	
    
    
}
