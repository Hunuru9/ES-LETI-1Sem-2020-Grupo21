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
 * 
 * A classe ExcelReader trata da conversao do conteudo de um ficheiro excel para uma matriz de Strings
 * de maneira a poder ser interpretado numa JTable posteriormente no GUI. 
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
	 * O construtor da classe ExcelReader localiza a informacao necessaria da folha Excel e altera
	 * o valor dos seus parametros para poder traduzir a informacao da folha Excel para a matriz de strings data.
	 * @param fileName O ExcelReader pede um fileName que so ira funcionar para traducao se for um ficheiro excel.
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
	 * Metodo get para obter os dados do ficheiro Excel
	 * @return devolve uma matriz de strings com a informacao dos dados do ficheiro Excel
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public String[][] getData() {
		return data;
	}
	
	/**
	 * Metodo get para obter os nomes das colunas.
	 * @return devolve um vetor de strings com o nome da cada coluna do ficheiro Excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * Metodo para obter o numero de linhas do ficheiro Excel (incluindo linha dos nomes das colunas).
	 * 
	 * @throws IOException se o ficheiro nao conseguir ser lido
	 * 
	 * @param inputStream argumento necessario para ler a folha do ficheiro excel.
	 * @param workbook argumento necessario para entrar na folha do ficheiro excel.
	 * @param firstSheet argumento necessario para entrar na folha do ficheiro excel.
	 * 
	 * @return devolve um inteiro com o numero do linhas do ficheiro Excel.
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public int numofRowsExcel(FileInputStream inputStream, Workbook workbook, Sheet firstSheet) throws IOException {
		return firstSheet.getLastRowNum()+1;
	}

	/**
	 * Metodo para obter o numero de colunas do ficheiro Excel.
	 * 
	 * 
	 * @throws IOException se o ficheiro nao conseguir ser lido
	 * 
	 * @param inputStream argumento necessario para ler a folha do ficheiro excel.
	 * @param workbook argumento necessario para entrar na folha do ficheiro excel.
	 * @param firstSheet argumento necessario para entrar na folha do ficheiro excel.
	 * 
	 * @return devolve um inteiro com o numero de colunas do ficheiro Excel.
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
	 * Metodo para obter os nomes das colunas.
	 * 
	 * @throws IOException se o ficheiro nao conseguir ser lido
	 * @param inputStream argumento necessario para ler a folha do ficheiro excel.
	 * @param workbook argumento necessario para entrar na folha do ficheiro excel.
	 * @param firstSheet argumento necessario para entrar na folha do ficheiro excel.
	 * @return devolve um vetor de strings com o nome da cada coluna do ficheiro Excel.
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
	 * Metodo para traduzir os dados do ficheiro excel para a matriz de strings data.
	 * @throws IOException se o ficheiro nao conseguir ser lido
	 * @param inputStream argumento necessario para ler a folha do ficheiro excel.
	 * @param workbook argumento necessario para entrar na folha do ficheiro excel.
	 * @param firstSheet argumento necessario para entrar na folha do ficheiro excel.
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
	 * Metodo para obter os dados de uma coluna.
	 * @param columnName O metodo precisa do nome da coluna que se pretende analizar.
	 * @return devolve uma lista de strings com os dados da coluna pedida como argumento.
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
