package CodeSmell.Project;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;

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
		frame.setResizable(true);

		JPanel painel=new JPanel(new FlowLayout());
		JButton showExcel=new JButton("Mostrar Excel");
		painel.add(showExcel);
		JButton detetarDefeitos=new JButton("Detetar Defeitos");
		painel.add(detetarDefeitos);
		frame.add(painel, BorderLayout.NORTH);


		detetarDefeitos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JTextPane texto=new JTextPane();
				texto.setEditable(true);
				texto.setPreferredSize(new Dimension(400,400));
				JScrollPane scroll2=new JScrollPane(texto);
				frame.add(scroll2, BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();

				JList<String> lista=new JList<>();
				JScrollPane scroll=new JScrollPane(lista);
				scroll.setPreferredSize(new Dimension(400,400));
				frame.add(scroll, BorderLayout.WEST);
				frame.revalidate();
				frame.repaint();
				
				JButton criarRegra=new JButton("Criar Regra");
				frame.add(criarRegra,BorderLayout.SOUTH);
				frame.revalidate();
				frame.repaint();
				
				frame.setSize(800,800);
			}
			
		});

		showExcel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					excel.setPreferredSize(new Dimension(800,800));
					excel.setLayout(new FlowLayout());
					JTable table = new JTable(data, columnNames);
					JScrollPane scroll2=new JScrollPane(excel);
					frame.add(scroll2, BorderLayout.CENTER);
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
