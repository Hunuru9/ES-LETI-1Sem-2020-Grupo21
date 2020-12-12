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
		
    }
	
	
    
    
}
