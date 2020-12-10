package codesmellservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <h1>ExcelReader<h1>
 * A classe ExcelReader trata da conversão do conteudo de um ficheiro excel para uma matriz de Strings
 * de maneira a poder ser intrepertado numa JTable posteriormente no GUI. 
 * 
 * @author Hugo Silva
 * @since 2020-12-10
 * 
 */
public class ExcelReader {
	
	private String fileName;
	private String[][] data;
	private String[] columnNames;
	
	/**
	 * O construtor da classe ExcelReader localiza a informação necessária da folha Excel e altera
	 * o valor dos seus parametros para poder traduzir a informação da folha Excel para a matriz de strings data.
	 * @param fileName O ExcelReader pede um fileName que só irá funcionar para tradução se for um ficheiro excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public ExcelReader(String fileName) {
		this.fileName = fileName;
		try {
			FileInputStream inputStream = new FileInputStream(new File(this.fileName));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			
			this.data = new String[numofRowsExcel(inputStream,workbook,firstSheet)-1][numofColumnsExcel(inputStream,workbook,firstSheet)];
			this.columnNames = loadColumnNames(inputStream,workbook,firstSheet);
			
			readExcelFile(inputStream,workbook,firstSheet);
			
			workbook.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método get para obter os dados do ficheiro Excel
	 * @results devolve uma matriz de strings com a informação dos dados do ficheiro Excel
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public String[][] getData() {
		return data;
	}
	
	/**
	 * Método get para obter os nomes das colunas.
	 * @results devolve um vetor de strings com o nome da cada coluna do ficheiro Excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * Método para obter o numero de linhas do ficheiro Excel (incluindo linha dos nomes das colunas).
	 * @results devolve um inteiro com o número do linhas do ficheiro Excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public int numofRowsExcel(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {
		return firstSheet.getLastRowNum()+1;
	}

	/**
	 * Método para obter o numero de colunas do ficheiro Excel.
	 * @results devolve um inteiro com o número de colunas do ficheiro Excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public int numofColumnsExcel(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {
		Iterator<Row> iterator = firstSheet.iterator();

		Row nextRow = iterator.next();
		return nextRow.getLastCellNum();
	}
	
	/**
	 * Método para obter os nomes das colunas.
	 * @param inputStream argumento necessário para ler a folha do ficheiro excel.
	 * @param workbook argumento necessário para entrar na folha do ficheiro excel.
	 * @param firstSheet argumento necessário para entrar na folha do ficheiro excel.
	 * @results devolve um vetor de strings com o nome da cada coluna do ficheiro Excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public String[] loadColumnNames(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {
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

	/**
	 * Método para traduzir os dados do ficheiro excel para a matriz de strings data.
	 * @param inputStream argumento necessário para ler a folha do ficheiro excel.
	 * @param workbook argumento necessário para entrar na folha do ficheiro excel.
	 * @param firstSheet argumento necessário para entrar na folha do ficheiro excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
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
//			System.out.println();
		}
	}
	/**
	 * Método para obter os dados de uma coluna.
	 * @param columnName O método precisa do nome da coluna que se pretende analizar.
	 * @results devolve uma lista de strings com os dados da coluna pedida como argumento.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public List<String> getColumnValues(String columnName){
		List<String> columnValues = new ArrayList<>();
		for(int i = 0; i!= this.columnNames.length; i++) {
			if(this.columnNames[i].equals(columnName)) {
				for(int j = 0; j!= this.data.length; j++) {
					columnValues.add(this.data[j][i]);
//					System.out.println(columnValues.get(j));
				}
			}
		}
		return columnValues;
	}


}
