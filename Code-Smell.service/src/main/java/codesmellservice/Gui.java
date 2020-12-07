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
	private JButton atualizar;
	private JButton filtrar;
	private JButton Results;
	
	private DefaultListModel<String> listModel = new DefaultListModel<>();;
	private JList<String> lista = new JList<>(listModel);
	private JList<String> listaMethodfilter = new JList<>(listModel);
	
	private JTextField ruleName;
	private JTextField metrica1;
	private JComboBox operadorRelacional1;
	private JTextField metrica2;
	private JComboBox operadorRelacional2;
	private JComboBox operadorLogico;
	private JComboBox cb;
	private JScrollPane scroll;
	private JComboBox MethodIDbox;
	private JScrollPane scrollMethodID;
	
	private JDialog d;
	
	private RuleSet listaRegras;
	private Rule aux;

	public Gui(ExcelReader excelReader, RuleSet listaRegras) throws IOException, ClassNotFoundException {
		this.listaRegras=listaRegras;
		this.excelReader = excelReader;
		scroll=new JScrollPane(lista);
		scrollMethodID = new JScrollPane(listaMethodfilter);
		aux=new Rule("");
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
		
		JButton regras=new JButton("Lista de Regras");
		painelBotoes.add(regras);
		
		JButton detetarDefeitos = new JButton("Detetar Defeitos");
		painelBotoes.add(detetarDefeitos);
		
		
		frame.add(painelBotoes, BorderLayout.NORTH);

		final JPanel excelPanel = new JPanel(new BorderLayout());
		final JPanel regrasPanel = new JPanel(new BorderLayout());
		final JPanel showcase=new JPanel(new BorderLayout());
		final JPanel defeitosPanel = new JPanel(new BorderLayout());
		
		JTextField bemVindo = new JTextField("Bem Vindo!");
		showcase.add(bemVindo);
		frame.add(showcase, BorderLayout.CENTER);

		criar = new JButton("Criar");
		
		criarRegra=new JButton("Criar Regra");
		
		atualizar=new JButton("Atualizar Regra");
		
		filtrar= new JButton("Filtrar Lista");
		
		Results = new JButton("Ver Resultados");
		
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
		
		atualizar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int indice=listModel.indexOf(aux.getNomeRegra());
				
				String codeSmellValue = cb.getSelectedItem().toString();
				String ruleNameValue = ruleName.getText();
				String metrica1Value = metrica1.getText();
				String operadorRelacional1Value = operadorRelacional1.getSelectedItem().toString();
				String metrica2Value = metrica2.getText();
				String operadorRelacional2Value = operadorRelacional2.getSelectedItem().toString();
				String operadorLogicoValue = operadorLogico.getSelectedItem().toString();
				
				aux.setCodeSmell(codeSmellValue);
				aux.setNomeRegra(ruleNameValue);
				if(metrica1Value.isEmpty()) {
					aux.setMetricaX(0.0);
				}else {
					aux.setMetricaX(Double.parseDouble(metrica1Value));
				}
				
				if(metrica2Value.isEmpty()) {
					aux.setMetricaY(0.0);
				}else {
					aux.setMetricaY(Double.parseDouble(metrica2Value));
				}
				
				aux.setmetricaXOperator(operadorRelacional1Value);
				aux.setmetricaYOperator(operadorRelacional2Value);
				aux.setLogicalOperator(operadorLogicoValue);
				
				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
					System.out.println(listaRegras.getRegras().get(i).toString());
				}
				
				listModel.set(indice, ruleNameValue);
				
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
	  				
	  				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
	  					if(listaRegras.getRegras().get(i).getNomeRegra().equals(value)) {
	  						aux=listaRegras.getRegras().get(i);
	  					}
	  				}
	  				
	  				d = new JDialog(frame, "Regra");
	  				JPanel popupPanel = new JPanel(new BorderLayout());
	  				JPanel ruleForm = new JPanel(new GridLayout(7,2));
	  				
	  				JLabel codeSmellLabel = new JLabel("Code Smell");
	  				String codeSmell[]= {"is_long_method", "is_feature_envy"};
	  				cb = new JComboBox<String>(codeSmell);
	  				cb.setBounds(50, 50, 90, 20);
	  				cb.setSelectedItem(aux.getCodeSmell());
	  				
	  				JLabel ruleNameLabel = new JLabel("Nome da regra");
	  				ruleName = new JTextField(aux.getNomeRegra());
	  				
	  				JLabel metrica1Label= new JLabel("LOC ou ATFD");
	  				metrica1 = new JTextField(String.valueOf(aux.getMetricaX()));
	  				
	  				JLabel operadorRelacional1Label = new JLabel("1º Operador Relacional");
	  				String operadores1[] = {"", "<", ">"};
	  				operadorRelacional1 = new JComboBox<String>(operadores1);
	  				operadorRelacional1.setBounds(50, 50, 90, 20);
	  				operadorRelacional1.setSelectedItem(aux.getmetricaXOperator());
	  				
	  				JLabel metrica2Label= new JLabel("CYCLO ou LAA");
	  				metrica2 = new JTextField(String.valueOf(aux.getMetricaY()));
	  				
	  				JLabel operadorRelacional2Label = new JLabel("2º Operador Relacional");
	  				String operadores2[] = {"", "<", ">"};
	  				operadorRelacional2 = new JComboBox<String>(operadores2);
	  				operadorRelacional2.setBounds(50, 50, 90, 20);
	  				operadorRelacional2.setSelectedItem(aux.getmetricaYOperator());
	  				
	  				JLabel operadorLogicoLabel = new JLabel("Operador Lógico");
	  				String operadoresLogico[] = {"", "AND", "OR"};
	  				operadorLogico = new JComboBox<String>(operadoresLogico);
	  				operadorLogico.setBounds(50, 50, 90, 20);
	  				operadorLogico.setSelectedItem(aux.getLogicalOperator());
	  				
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
	  				popupPanel.add(atualizar, BorderLayout.SOUTH);
	  				d.setSize(300,200);
					d.setLocation(tamanhoTela.width/2-d.getWidth()/2, tamanhoTela.height/2-d.getHeight()/2);
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
				
				JLabel metrica1Label= new JLabel("LOC ou ATFD");
				metrica1 = new JTextField();
				
				JLabel operadorRelacional1Label = new JLabel("1º Operador Relacional");
				String operadores1[] = {"", "<", ">"};
  				operadorRelacional1 = new JComboBox<String>(operadores1);
  				operadorRelacional1.setBounds(50, 50, 90, 20);
				
				JLabel metrica2Label= new JLabel("CYCLO ou LAA");
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
				d.setSize(300,200);
				d.setLocation(tamanhoTela.width/2-d.getWidth()/2, tamanhoTela.height/2-d.getHeight()/2);
				d.add(popupPanel);
				d.setVisible(true); 
			}
		});
		
		
		filtrar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				Toolkit kit = Toolkit.getDefaultToolkit();
				Dimension tamanhoTela = kit.getScreenSize();
				d = new JDialog(frame, "Escolha uam regra/ferramentaS");
				JPanel popupPanel = new JPanel(new BorderLayout());
				JPanel comboBoxSegment = new JPanel(new GridLayout(1,2));
				
				JLabel MethodIDchoice = new JLabel("Escolha um MethodID");
				String MethodIDlist[]= {"MethodID1", "MethodID2"};
				MethodIDbox = new JComboBox<String>(MethodIDlist);
				MethodIDbox.setBounds(50, 50, 90, 20);
				
				comboBoxSegment.add(MethodIDchoice);
				comboBoxSegment.add(MethodIDbox);
				
				popupPanel.add(comboBoxSegment,BorderLayout.CENTER);
				popupPanel.add(Results,BorderLayout.SOUTH);
				d.setSize(300,200);
				d.setLocation(tamanhoTela.width/2-d.getWidth()/2, tamanhoTela.height/2-d.getHeight()/2);
				d.add(popupPanel);
				d.setVisible(true);
				
				
			}
			});
		
		Results.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String MethodIDValue = MethodIDbox.getSelectedItem().toString();
				
				
				
				
				
			}
		});
		
		regras.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				regrasPanel.add(scroll, BorderLayout.CENTER);
				
				regrasPanel.add(criarRegra,BorderLayout.SOUTH);
				
				frame.remove(showcase);
				frame.remove(excelPanel);
				frame.remove(defeitosPanel);
				frame.add(regrasPanel);
				frame.validate();
				frame.repaint();

				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

		});

		showExcel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				excelPanel.add(scrollTable, BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				
				frame.remove(showcase);
				frame.remove(regrasPanel);
				frame.remove(defeitosPanel);
				frame.add(excelPanel);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		detetarDefeitos.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				defeitosPanel.add(filtrar,BorderLayout.NORTH);
				defeitosPanel.add(scrollMethodID,BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				frame.remove(showcase);
				frame.remove(regrasPanel);
				frame.remove(excelPanel);
				frame.add(defeitosPanel);
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
