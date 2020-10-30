package CodeSmell.Project;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Gui {
	private JFrame frame;
	private JPanel excel = new JPanel();
	String[][] data = new String[numofRowsExcel()][numofColumnsExcel()];
	String[] columnNames = {"First Name", "Last Name","Sport","Vegetarian"};

	public Gui() throws IOException, ClassNotFoundException {


		frame=new JFrame("Code Smells");
		frame.setLayout(new BorderLayout());
		excel.setPreferredSize(new Dimension(800,800));
		excel.setLayout(new FlowLayout());
		JTable table = new JTable(data, columnNames);
		JScrollPane scroll2=new JScrollPane(excel);
		frame.add(scroll2, BorderLayout.CENTER);

		JPanel painel=new JPanel(new FlowLayout());
		JButton showExcel=new JButton("Mostrar Excel");
		painel.add(showExcel);
		JButton detetarDefeitos=new JButton("Detetar Defeitos");
		painel.add(detetarDefeitos);
		frame.add(painel, BorderLayout.NORTH);

		final String[] pizzas = { "Cheese", "Pepperoni", "Sausage", "Veggie" };

		detetarDefeitos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String regra = (String) JOptionPane.showInputDialog(frame,"Escolha regra",null);
				String regraCombo = (String) JOptionPane.showInputDialog(frame, 
						"Escolha regra",
						"regra",
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						pizzas, 
						null);
			}
		});

		showExcel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					readExcelFile();
				}catch(IOException ex) {
					System.out.println("Conas");
				}
			}
		});


		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

	public int numofRowsExcel() throws IOException {
		String excelFilePath = "Defeitos.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		return firstSheet.getLastRowNum();
	}

	public int numofColumnsExcel() throws IOException {
		String excelFilePath = "Defeitos.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		return nextRow.getLastCellNum();
	}

	public void readExcelFile() throws IOException {
		String excelFilePath = "Defeitos.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();


				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					//					JLabel cellStringContent = new JLabel(cell.getStringCellValue());
					data[cell.getRowIndex()][cell.getColumnIndex()]=cell.getStringCellValue();
					//					System.out.println(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					//					JLabel cellBooleanContent = new JLabel(Boolean.toString(cell.getBooleanCellValue()));
					data[cell.getRowIndex()][cell.getColumnIndex()]=Boolean.toString(cell.getBooleanCellValue());
					//					System.out.print(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					//					JLabel cellIntContent = new JLabel(Double.toString(cell.getNumericCellValue()));
					data[cell.getRowIndex()][cell.getColumnIndex()]=Double.toString(cell.getNumericCellValue());
					//					System.out.print(cell.getNumericCellValue());
					break;
				}
				System.out.print(" - ");
			}
			System.out.println();
		}

		workbook.close();
		inputStream.close();
	}

	public void readMethodInfo(int index) {

	}

}
