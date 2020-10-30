package CodeSmell.Project;

import java.awt.*;
import java.io.*;
import javax.swing.*;
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

	public Gui() throws IOException, ClassNotFoundException {


		frame=new JFrame("Code Smells");
		frame.setLayout(new BorderLayout());
		excel.setPreferredSize(new Dimension(800,800));
		excel.setLayout(new GridLayout());
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
					JLabel cellStringContent = new JLabel(cell.getStringCellValue());
					excel.add(cellStringContent);
					//System.out.println(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					JLabel cellBooleanContent = new JLabel(Boolean.toString(cell.getBooleanCellValue()));
					excel.add(cellBooleanContent);
					//System.out.print(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					JLabel cellIntContent = new JLabel(Double.toString(cell.getNumericCellValue()));
					excel.add(cellIntContent);
					//System.out.print(cell.getNumericCellValue());
					break;
				}
				//System.out.print(" - ");
			}
			System.out.println();
		}

		workbook.close();
		inputStream.close();
	}

	public void readMethodInfo(int index) {

	}

}
