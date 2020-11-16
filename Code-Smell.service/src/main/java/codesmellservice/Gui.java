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
	
	/* ExcelReader object to read and filter the data from the excel file */
	private ExcelReader excelReader;
	
	/* JTable and JScrollPane to show the the data from the excel file */
	private JTable table;
	private JScrollPane scrollTable;

	public Gui(ExcelReader excelReader) throws IOException, ClassNotFoundException {
		
		this.excelReader = excelReader;
		this.table = new JTable(excelReader.getData(), excelReader.getColumnNames());
		this.scrollTable = new JScrollPane(table);
		
		frame=new JFrame("Code Smells");
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		addFrameContent();
	}
	
	public void addFrameContent() {
		JPanel painel=new JPanel(new FlowLayout());
		JButton showExcel=new JButton("Mostrar Excel");
		painel.add(showExcel);
		JButton detetarDefeitos=new JButton("Detetar Defeitos");
		painel.add(detetarDefeitos);
		frame.add(painel, BorderLayout.NORTH);

		final JPanel excelPanel = new JPanel(new BorderLayout());
		final JPanel defeitosPanel = new JPanel(new GridLayout());
		final JPanel showcase=new JPanel(new BorderLayout());
		JLabel bemVindo = new JLabel("Bem Vindo!");
		bemVindo.setHorizontalAlignment(JLabel.CENTER);
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
