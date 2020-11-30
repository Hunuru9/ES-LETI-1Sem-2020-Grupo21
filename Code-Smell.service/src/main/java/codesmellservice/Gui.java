package codesmellservice;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Gui {
	private JFrame frame;
	
	/* ExcelReader object to read and filter the data from the excel file */
	private ExcelReader excelReader;
	
	/* JTable and JScrollPane to show the the data from the excel file */
	private JTable table;
	private JScrollPane scrollTable;
	private JPanel painelBotoes;
	private JButton criarRegra;
	private JButton criar;
	
	private DefaultListModel<String> listModel = new DefaultListModel<>();;
	private JList<String> lista = new JList<>(listModel);
	
	private JTextField ruleName;
	private JTextField metrica1;
	private JComboBox operadorRelacional1;
	private JTextField metrica2;
	private JComboBox operadorRelacional2;
	private JComboBox operadorLogico;
	private JComboBox cb;
	
	private JDialog d;
	
	private RuleSet listaRegras;

	public Gui(ExcelReader excelReader, RuleSet listaRegras) throws IOException, ClassNotFoundException {
		this.listaRegras=listaRegras;
		this.excelReader = excelReader;
		this.table = new JTable(excelReader.getData(), excelReader.getColumnNames());
		this.scrollTable = new JScrollPane(table);
		
		this.painelBotoes = new JPanel(new FlowLayout());
		
		frame=new JFrame("Code Smells");
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		addFrameContent();
	}
	
	public void addFrameContent() {
		
		JButton showExcel = new JButton("Mostrar Excel");
		painelBotoes.add(showExcel);
		
		JButton regras=new JButton("Regras");
		painelBotoes.add(regras);
		
		frame.add(painelBotoes, BorderLayout.NORTH);

		final JPanel excelPanel = new JPanel(new BorderLayout());
		final JPanel defeitosPanel = new JPanel(new BorderLayout());
		final JPanel showcase=new JPanel(new BorderLayout());
		
		JTextField bemVindo = new JTextField("Bem Vindo!");
		showcase.add(bemVindo);
		frame.add(showcase, BorderLayout.CENTER);

		criar = new JButton("Criar");
		
		criarRegra=new JButton("Criar Regra");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = kit.getScreenSize();
		
		criar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String codeSmellValue = cb.getSelectedItem().toString();
				String ruleNameValue = ruleName.getText();
				String metrica1Value = metrica1.getText();
				String operadorRelacional1Value = operadorRelacional1.getSelectedItem().toString();
				String metrica2Value = metrica2.getText();
				String operadorRelacional2Value = operadorRelacional2.getSelectedItem().toString();
				String operadorLogicoValue = operadorLogico.getSelectedItem().toString();
			
				Rule regra=new Rule(codeSmellValue);
				regra.setNomeRegra(ruleNameValue);
				if(metrica1Value.isEmpty()) {
					regra.setMetricaX(0.0);
				}else {
					regra.setMetricaX(Double.parseDouble(metrica1Value));
				}
				
				if(metrica2Value.isEmpty()) {
					regra.setMetricaY(0.0);
				}else {
					regra.setMetricaY(Double.parseDouble(metrica2Value));
				}
				
				regra.setmetricaXOperator(operadorRelacional1Value);
				regra.setmetricaYOperator(operadorRelacional2Value);
				regra.setLogicalOperator(operadorLogicoValue);
				
				listaRegras.addRegra1(regra);
				
				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
					System.out.println(listaRegras.getRegras().get(i).toString());
				}
				
				listModel.addElement(ruleNameValue);
				
				d.setVisible(false); 
				
				
			}
		});
		
		lista.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
            	if(e.getClickCount() == 2) {
            		
            		String value = (String)lista.getModel().getElementAt(lista.locationToIndex(e.getPoint()));
	            	System.out.println(value);
	            	
	            	Toolkit kit = Toolkit.getDefaultToolkit();
	  				Dimension tamanhoTela = kit.getScreenSize();
	  				
	  				d = new JDialog(frame, "Regra");
	  				JPanel popupPanel = new JPanel(new BorderLayout());
	  				JPanel ruleForm = new JPanel(new GridLayout(7,2));
	  				
	  				JLabel codeSmellLabel = new JLabel("Code Smell");
	  				String codeSmell[]= {"is_long_method", "is_feature_envy"};
	  				cb = new JComboBox<String>(codeSmell);
	  				cb.setBounds(50, 50, 90, 20);
	  				
	  				JLabel ruleNameLabel = new JLabel("Nome da regra");
	  				ruleName = new JTextField();
	  				
	  				JLabel metrica1Label= new JLabel("1ª Métrica");
	  				metrica1 = new JTextField();
	  				
	  				JLabel operadorRelacional1Label = new JLabel("1º Operador Relacional");
	  				String operadores1[] = {"", "<", ">"};
	  				operadorRelacional1 = new JComboBox<String>(operadores1);
	  				operadorRelacional1.setBounds(50, 50, 90, 20);
	  				
	  				JLabel metrica2Label= new JLabel("2ª Métrica");
	  				metrica2 = new JTextField();
	  				
	  				JLabel operadorRelacional2Label = new JLabel("2º Operador Relacional");
	  				String operadores2[] = {"", "<", ">"};
	  				operadorRelacional2 = new JComboBox<String>(operadores2);
	  				operadorRelacional2.setBounds(50, 50, 90, 20);
	  				
	  				JLabel operadorLogicoLabel = new JLabel("Operador Lógico");
	  				String operadoresLogico[] = {"", "AND", "OR"};
	  				operadorLogico = new JComboBox<String>(operadoresLogico);
	  				operadorLogico.setBounds(50, 50, 90, 20);
	  				
	  				ruleForm.add(codeSmellLabel);
	  				ruleForm.add(cb);
	  				ruleForm.add(ruleNameLabel);
	  				ruleForm.add(ruleName);
	  				ruleForm.add(metrica1Label);
	  				ruleForm.add(metrica1);
	  				ruleForm.add(operadorRelacional1Label);
	  				ruleForm.add(operadorRelacional1);
	  				ruleForm.add(metrica2Label);
	  				ruleForm.add(metrica2);
	  				ruleForm.add(operadorRelacional2Label);
	  				ruleForm.add(operadorRelacional2);
	  				ruleForm.add(operadorLogicoLabel);
	  				ruleForm.add(operadorLogico);
  				
	  				popupPanel.add(ruleForm, BorderLayout.CENTER);
	  				popupPanel.add(criar, BorderLayout.SOUTH);
	  				d.setSize(200,200);
	  				d.add(popupPanel);
	  				d.setVisible(true); 
            	  
            	  
            	}
            }
        });
		
		criarRegra.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				Toolkit kit = Toolkit.getDefaultToolkit();
				Dimension tamanhoTela = kit.getScreenSize();
				
				d = new JDialog(frame, "Regra");
				JPanel popupPanel = new JPanel(new BorderLayout());
				JPanel ruleForm = new JPanel(new GridLayout(7,2));
				
				JLabel codeSmellLabel = new JLabel("Code Smell");
  				//codeSmell = new JTextField();
  				String codeSmell[]= {"is_long_method", "is_feature_envy"};
  				cb=new JComboBox<String>(codeSmell);
  				cb.setBounds(50, 50, 90, 20);
  				
				JLabel ruleNameLabel = new JLabel("Nome da regra");
				ruleName = new JTextField();
				
				JLabel metrica1Label= new JLabel("1ª Métrica");
				metrica1 = new JTextField();
				
				JLabel operadorRelacional1Label = new JLabel("1º Operador Relacional");
				String operadores1[] = {"", "<", ">"};
  				operadorRelacional1 = new JComboBox<String>(operadores1);
  				operadorRelacional1.setBounds(50, 50, 90, 20);
				
				JLabel metrica2Label= new JLabel("2ª Métrica");
				metrica2 = new JTextField();
				
				JLabel operadorRelacional2Label = new JLabel("2º Operador Relacional");
				String operadores2[] = {"", "<", ">"};
  				operadorRelacional2 = new JComboBox<String>(operadores2);
  				operadorRelacional2.setBounds(50, 50, 90, 20);
				
				JLabel operadorLogicoLabel = new JLabel("Operador Lógico");
				String operadoresLogico[] = {"", "AND", "OR"};
  				operadorLogico = new JComboBox<String>(operadoresLogico);
  				operadorLogico.setBounds(50, 50, 90, 20);
				
				ruleForm.add(codeSmellLabel);
  				ruleForm.add(cb);
				ruleForm.add(ruleNameLabel);
				ruleForm.add(ruleName);
				ruleForm.add(metrica1Label);
				ruleForm.add(metrica1);
				ruleForm.add(operadorRelacional1Label);
				ruleForm.add(operadorRelacional1);
				ruleForm.add(metrica2Label);
				ruleForm.add(metrica2);
				ruleForm.add(operadorRelacional2Label);
				ruleForm.add(operadorRelacional2);
  				ruleForm.add(operadorLogicoLabel);
  				ruleForm.add(operadorLogico);
				
				popupPanel.add(ruleForm, BorderLayout.CENTER);
				popupPanel.add(criar, BorderLayout.SOUTH);
				d.setSize(200,200);
				d.add(popupPanel);
				d.setVisible(true); 
			}
		});
		
		regras.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				JScrollPane scroll=new JScrollPane(lista);
				scroll.setPreferredSize(new Dimension(400,400));
				defeitosPanel.add(scroll, BorderLayout.CENTER);
				
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
