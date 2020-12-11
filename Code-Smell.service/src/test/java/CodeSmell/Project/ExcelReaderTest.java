package CodeSmell.Project;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import codesmellservice.ExcelReader;
import junit.framework.TestCase;

public class ExcelReaderTest extends TestCase {
	
	@Test
	public void testGetColumnValues() {
		ExcelReader test = new ExcelReader("test.xlsx");
		List<String> results = test.getColumnValues("LOC");
		List<String> expected = Arrays.asList("3.0","3.0","5.0","11.0","11.0","11.0","29.0","3.0","3.0");
		assertEquals(expected,results);
	}

}
