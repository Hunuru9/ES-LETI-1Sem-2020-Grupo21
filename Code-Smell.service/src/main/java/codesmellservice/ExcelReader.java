package codesmellservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	private String fileName;
	private String[][] data;
	private String[] columnNames;
	
	public ExcelReader(String fileName) {
		this.fileName = fileName;
		try {
			FileInputStream inputStream = new FileInputStream(new File(this.fileName));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			
			this.data = new String[numofRowsExcel(inputStream,workbook,firstSheet)-1][numofColumnsExcel(inputStream,workbook,firstSheet)];
			this.columnNames = getColumnNames(inputStream,workbook,firstSheet);
			
			readExcelFile(inputStream,workbook,firstSheet);
			
			workbook.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[][] getData() {
		return data;
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}

	public int numofRowsExcel(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {
		return firstSheet.getLastRowNum()+1;
	}

	public int numofColumnsExcel(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {
		Iterator<Row> iterator = firstSheet.iterator();

		Row nextRow = iterator.next();
		return nextRow.getLastCellNum();
	}
	
	public String[] getColumnNames(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {
		String[] columnNamesArray = new String[numofColumnsExcel(inputStream, workbook, firstSheet)];
		Iterator<Row> iterator = firstSheet.iterator();	

		int i = 0;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			if(i == 0) {
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					columnNamesArray[cell.getColumnIndex()] = cell.getStringCellValue();
				}
			}else {
				break;
			}
			i++;
		}
		return columnNamesArray;
	}

	
	
	public void readExcelFile(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {

		Iterator<Row> rowIterator = firstSheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();


				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:

					data[cell.getRowIndex()-1][cell.getColumnIndex()]=cell.getStringCellValue();

					break;
				case Cell.CELL_TYPE_BOOLEAN:

					data[cell.getRowIndex()-1][cell.getColumnIndex()]=Boolean.toString(cell.getBooleanCellValue());

					break;
				case Cell.CELL_TYPE_NUMERIC:

					data[cell.getRowIndex()-1][cell.getColumnIndex()]=Double.toString(cell.getNumericCellValue());

					break;
				default:
					break;
				}
			}
			System.out.println();
		}
	}
	
	public double[] getMetricValues(String metric) {
		double[] values = new double[this.data.length];
		for(int i = 0; i!= this.columnNames.length; i++) {
			if(this.columnNames[i].equals(metric)) {
				for(int j = 0; j!= values.length; j++) {
					values[j] = Double.valueOf(this.data[j][i]);
					System.out.println(values[j]);
				}
			}
		}
		return values;
	}


}
