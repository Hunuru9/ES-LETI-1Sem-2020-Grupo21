package codesmellservice;

import java.io.IOException;
import java.util.List;


public class App
{
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		ExcelReader excelReader = new ExcelReader("Defeitos.xlsx");
		RuleSet lista=new RuleSet(excelReader);
		Gui gui = new Gui(excelReader, lista);
		gui.open();
		/*Rule r=new Rule("is_long_method");
		r.setNomeRegra("BOAS");
		r.setMetricaX(80.0);
		r.setmetricaXOperator(">");
		r.setMetricaY(10.0);
		r.setmetricaYOperator(">");
		r.setLogicalOperator("AND");
		
		List<Integer> result=lista.codeSmellIds(r, "LOC", "CYCLO");
		
		for(int i=0; i!=result.size(); i++) {
			System.out.println(result.get(i));
		}
		
		excelReader.getMetricValues("LOC");
		Gui gui = new Gui(excelReader);
		gui.open();

		RuleSet ruleSet=new RuleSet(excelReader);*/
		
    }
	
	
    
    
}
