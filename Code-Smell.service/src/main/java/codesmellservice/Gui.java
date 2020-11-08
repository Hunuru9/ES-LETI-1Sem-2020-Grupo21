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
	private ExcelReader excelReader;

	public Gui(ExcelReader excelReader) throws IOException, ClassNotFoundException {

		this.excelReader = excelReader;
		frame=new JFrame("Code Smells");
		addFrameContent();
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);

	}
	
	public void addFrameContent() {
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
				JTable table = new JTable(excelReader.getData(), excelReader.getColumnNames());
				JScrollPane scrollTable=new JScrollPane(table);
				try {
					excelReader.readExcelFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				excelPanel.add(scrollTable, BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				
				frame.remove(showcase);
				frame.remove(defeitosPanel);
				frame.add(excelPanel);
				frame.revalidate();
				frame.repaint();
			}
		});


		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void open() {
		frame.setState(JFrame.NORMAL);
		frame.setVisible(true);
	}

}
