package codesmellservice;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		ExcelReader excelReader = new ExcelReader("Defeitos.xlsx");
		Gui gui = new Gui(excelReader);
		gui.open();
    }
	
	
    
    
}
