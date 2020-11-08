package codesmellservice;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Gui {
	private String excelFilePath = "Defeitos.xlsx";
	private JFrame frame;
	String[][] data = new String[numofRowsExcel()-1][numofColumnsExcel()];
	String[] columnNames = getColumnNames();

	public Gui() throws IOException, ClassNotFoundException {


		frame=new JFrame("Code Smells");
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);

		JPanel painel=new JPanel(new FlowLayout());
		JButton showExcel=new JButton("Mostrar Excel");
		painel.add(showExcel);
		JButton detetarDefeitos=new JButton("Detetar Defeitos");
		painel.add(detetarDefeitos);
		frame.add(painel, BorderLayout.NORTH);
		
		final JPanel excelPanel = new JPanel(new BorderLayout());
		final JPanel defeitosPanel = new JPanel(new BorderLayout());
		final JPanel showcase=new JPanel(new BorderLayout());
		JTextField bemVindo = new JTextField("Bem Vindo!");
		showcase.add(bemVindo);
		frame.add(showcase, BorderLayout.CENTER);
 

		detetarDefeitos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JTextPane texto=new JTextPane();
				texto.setEditable(true);
				texto.setPreferredSize(new Dimension(400,400));
				JScrollPane scroll2=new JScrollPane(texto);
				defeitosPanel.add(scroll2, BorderLayout.EAST);

				JList<String> lista=new JList<>();
				JScrollPane scroll=new JScrollPane(lista);
				scroll.setPreferredSize(new Dimension(400,400));
				defeitosPanel.add(scroll, BorderLayout.WEST);
				
				JButton criarRegra=new JButton("Criar Regra");
				defeitosPanel.add(criarRegra,BorderLayout.SOUTH);
				
				frame.remove(showcase);
				frame.remove(excelPanel);
				frame.add(defeitosPanel);
				frame.validate();
	            frame.repaint();
				
				frame.setSize(800,800);
			}
			
		});

		showExcel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					JTable table = new JTable(data, columnNames);
					JScrollPane scrollTable=new JScrollPane(table);
					excelPanel.add(scrollTable, BorderLayout.CENTER);
					readExcelFile();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					
					frame.remove(showcase);
					frame.remove(defeitosPanel);
					frame.add(excelPanel);
					frame.revalidate();
					frame.repaint();
				}catch(IOException ex) {
					System.err.println("Erro IOEXCEPTION");
				}
			}
		});


		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void open() {
		frame.setState(JFrame.NORMAL);
		frame.setVisible(true);
	}

	public String[] getColumnNames() throws IOException {
		String[] columnNamesArray = new String[numofColumnsExcel()];
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();	

		int i = 0;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			if(i == 0) {
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					System.out.println("Oi");
					System.out.println(cell.getStringCellValue());
					columnNamesArray[cell.getColumnIndex()] = cell.getStringCellValue();
				}
			}else {
				break;
			}
			i++;
		}
		workbook.close();
		inputStream.close();
		return columnNamesArray;
	}

	public int numofRowsExcel() throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		workbook.close();
		inputStream.close();
		return firstSheet.getLastRowNum()+1;
	}

	public int numofColumnsExcel() throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		Row nextRow = iterator.next();
		workbook.close();
		inputStream.close();
		return nextRow.getLastCellNum();
	}

	public void readExcelFile() throws IOException {

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
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

		workbook.close();
		inputStream.close();
	}

	public void readMethodInfo(int index) {
//waiting
	}

}
