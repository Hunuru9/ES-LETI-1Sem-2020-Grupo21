package codesmellservice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private DefaultListModel<String> listModel = new DefaultListModel<>();;
	private JList<String> lista = new JList<>(listModel);
	
	private JTextField ruleName;
	private JTextField metrica1;
	private JComboBox operadorRelacional1;
	private JTextField metrica2;
	private JComboBox operadorRelacional2;
	private JComboBox operadorLogico;
	private JComboBox codeSm;
	private JComboBox<String> metricX;
	private JComboBox<String> metricY;
	
	private JDialog d;
	
	private RuleSet listaRegras;
	private Rule aux;

	public Gui(ExcelReader excelReader, RuleSet listaRegras) throws IOException, ClassNotFoundException {
		this.listaRegras=listaRegras;
		this.excelReader = excelReader;
		aux=new Rule("", "", "");
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
		
		atualizar=new JButton("Atualizar Regra");
		
		String codeSmell[]= {"", "is_long_method", "is_feature_envy"};
		codeSm = new JComboBox<String>(codeSmell);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = kit.getScreenSize();
		
		criar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String codeSmellValue = codeSm.getSelectedItem().toString();
				String ruleNameValue = ruleName.getText();
				
				String metrica1string = metricX.getSelectedItem().toString();
				String operadorRelacional1Value = operadorRelacional1.getSelectedItem().toString();
				String metrica1Value = metrica1.getText();
				
				String metrica2string = metricY.getSelectedItem().toString();
				System.out.println(metrica2string);
				String operadorRelacional2Value = operadorRelacional2.getSelectedItem().toString();
				String metrica2Value = metrica2.getText();
				
				String operadorLogicoValue = operadorLogico.getSelectedItem().toString();
				
				if(ruleNameValue.isEmpty() || !metrica1string.isEmpty() && metrica2string.isEmpty() && !operadorLogicoValue.isEmpty()
						|| !metrica2string.isEmpty() && metrica1string.isEmpty() && !operadorLogicoValue.isEmpty() || 
						metrica1string.isEmpty() && metrica2string.isEmpty() || !metrica1string.isEmpty() && !metrica2string.isEmpty() && operadorLogicoValue.isEmpty() ){
					d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					throw new IllegalArgumentException("Critérios inválidos");
					
				}else {
					
				Rule regra=new Rule(codeSmellValue, metrica1string, metrica2string);
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
				
				listModel.addElement(regra.toString());
				
				d.setVisible(false);
				}
				
			}
		});
		
		atualizar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int indice=listModel.indexOf(aux.toString());
				
				String codeSmellValue = codeSm.getSelectedItem().toString();
				String ruleNameValue = ruleName.getText();
				
				String metrica1string = metricX.getSelectedItem().toString();
				String operadorRelacional1Value = operadorRelacional1.getSelectedItem().toString();
				String metrica1Value = metrica1.getText();
				
				String metrica2string = metricY.getSelectedItem().toString();
				String operadorRelacional2Value = operadorRelacional2.getSelectedItem().toString();
				String metrica2Value = metrica2.getText();
				
				String operadorLogicoValue = operadorLogico.getSelectedItem().toString();
				
				if(ruleNameValue.isEmpty() || !metrica1string.isEmpty() && metrica2string.isEmpty() && !operadorLogicoValue.isEmpty()
						|| !metrica2string.isEmpty() && metrica1string.isEmpty() && !operadorLogicoValue.isEmpty() || 
						metrica1string.isEmpty() && metrica2string.isEmpty() || !metrica1string.isEmpty() && !metrica2string.isEmpty() && operadorLogicoValue.isEmpty() ) {
					d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					throw new IllegalArgumentException("Critérios inválidos");
				}else {
					
				aux.setMetricaXString(metrica1string); aux.setMetricaYString(metrica2string);
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
				String s=aux.toString();
				
				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
					System.out.println(listaRegras.getRegras().get(i).toString());
				}

				listModel.set(indice, s);
				d.setVisible(false);
				}
			}
		});
		
		lista.addMouseListener(new MouseAdapter() {

			@Override
            public void mouseClicked(MouseEvent e) {
            	if(e.getClickCount() == 2) {
            		
            		metricX.removeAllItems();
            		metricY.removeAllItems();
            		
            		String value = (String)lista.getModel().getElementAt(lista.locationToIndex(e.getPoint()));
	            	System.out.println(value);
	            	String [] nome=value.split(" ");

	            	Toolkit kit = Toolkit.getDefaultToolkit();
	  				Dimension tamanhoTela = kit.getScreenSize();
	  				
	  				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
	  					if(listaRegras.getRegras().get(i).getNomeRegra().equals(nome[1])) {
	  						aux=listaRegras.getRegras().get(i);
	  					}
	  				}
	  				
	  				d = new JDialog(frame, "Regra");
	  				JPanel popupPanel = new JPanel(new BorderLayout());
	  				JPanel ruleForm = new JPanel(new GridLayout(5,3));
	  				
	  				JLabel codeSmellLabel = new JLabel("Code Smell");
	  				//String codeSmell[]= {"", "is_long_method", "is_feature_envy"};
	  				//codeSm = new JComboBox<String>(codeSmell);
	  				codeSm.setBounds(50, 50, 90, 20);
	  				codeSm.setSelectedItem(aux.getCodeSmell());
	  				JLabel vazio = new JLabel("");
	  				JLabel v = new JLabel("");
	  				
	  				
	  				JLabel ruleNameLabel = new JLabel("Nome da regra");
	  				ruleName = new JTextField(aux.getNomeRegra());
	  				JLabel vazio1 = new JLabel("");
	  				JLabel v2 = new JLabel("");
	  				
	  				//JLabel metrica1Label= new JLabel("LOC ou ATFD");
	  				//String metricsX[]= {"", "LOC", "CYCLO", "ATFD", "LAA"};
	  				JLabel labelMetrica1 = new JLabel("1º Threshold");
	  				metricX = new JComboBox<String>();
	  				
	  				metricX.setBounds(50, 50, 90, 20);
	  				//metricX.setSelectedItem(aux.getMetricaXString());
	  				String operadores1[] = {"", "<", ">"};
	  				operadorRelacional1 = new JComboBox<String>(operadores1);
	  				operadorRelacional1.setBounds(50, 50, 90, 20);
	  				operadorRelacional1.setSelectedItem(aux.getmetricaXOperator());
	  				metrica1 = new JTextField(String.valueOf(aux.getMetricaX()));
	  				
	  				//JLabel operadorRelacional1Label = new JLabel("1º Operador Relacional");
	  				
	  				
	  				//JLabel metrica2Label= new JLabel("CYCLO ou LAA");
	  				//String metricsY[]= {"", "LOC", "CYCLO", "ATFD", "LAA"};
	  				JLabel labelMetrica2 = new JLabel("2º Threshold");
	  				metricY = new JComboBox<String>();
	  				metricY.setBounds(50, 50, 90, 20);
	  				//metricY.setSelectedItem(aux.getMetricaYString());
	  				String operadores2[] = {"", "<", ">"};
	  				operadorRelacional2 = new JComboBox<String>(operadores2);
	  				operadorRelacional2.setBounds(50, 50, 90, 20);
	  				operadorRelacional2.setSelectedItem(aux.getmetricaYOperator());
	  				metrica2 = new JTextField(String.valueOf(aux.getMetricaY()));
	  				
	  				if(aux.getCodeSmell().equals("is_long_method")) {
	  					metricX.addItem(""); metricX.addItem("LOC"); metricX.addItem("CYCLO");
	  					metricY.addItem(""); metricY.addItem("LOC"); metricY.addItem("CYCLO");
	  				}else {
	  					metricX.addItem(""); metricX.addItem("ATFD"); metricX.addItem("LAA");
	  					metricY.addItem(""); metricY.addItem("ATFD"); metricY.addItem("LAA");
	  				}
	  				
	  				metricX.setSelectedItem(aux.getMetricaXString());
	  				metricY.setSelectedItem(aux.getMetricaYString());
	  				//JLabel operadorRelacional2Label = new JLabel("2º Operador Relacional");
	  				
	  				JLabel operadorLogicoLabel = new JLabel("Operador Lógico");
	  				String operadoresLogico[] = {"", "AND", "OR"};
	  				operadorLogico = new JComboBox<String>(operadoresLogico);
	  				operadorLogico.setBounds(50, 50, 90, 20);
	  				operadorLogico.setSelectedItem(aux.getLogicalOperator());
	  				JLabel vazio2 = new JLabel("");
	  				JLabel v3 = new JLabel("");
	  				
	  				ruleForm.add(codeSmellLabel);
		  			ruleForm.add(codeSm);
		  			ruleForm.add(vazio);
		  			ruleForm.add(v);
		  				
		  			ruleForm.add(ruleNameLabel);
		  			ruleForm.add(ruleName);
		  			ruleForm.add(vazio1);
		  			ruleForm.add(v2);
		  				
		  			
					ruleForm.add(labelMetrica1);
		  			ruleForm.add(metricX);
		  			ruleForm.add(operadorRelacional1);
		 			ruleForm.add(metrica1);
		  				
		 			ruleForm.add(labelMetrica2);
		  			ruleForm.add(metricY);
		  			ruleForm.add(operadorRelacional2);
		  			ruleForm.add(metrica2);
		  				
		  			ruleForm.add(operadorLogicoLabel);
		  			ruleForm.add(operadorLogico);
		  			ruleForm.add(vazio2);
		  			ruleForm.add(v3);
  				
	  				popupPanel.add(ruleForm, BorderLayout.CENTER);
	  				popupPanel.add(atualizar, BorderLayout.SOUTH);
	  				d.setSize(410,200);
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
  				JPanel ruleForm = new JPanel(new GridLayout(5,4));
  				
  				JLabel codeSmellLabel = new JLabel("Code Smell");
  				//String codeSmell[]= {"", "is_long_method", "is_feature_envy"};
  				//codeSm = new JComboBox<String>(codeSmell);
  				codeSm.setBounds(50, 50, 90, 20);
  				JLabel vazio = new JLabel("");
  				JLabel v = new JLabel("");
  				
  				
  				JLabel ruleNameLabel = new JLabel("Nome da regra");
  				ruleName = new JTextField();
  				JLabel vazio1 = new JLabel("");
  				JLabel v2 = new JLabel("");
  				
	  			//JLabel metrica1Label= new JLabel("LOC ou ATFD");
	  			//String metricsX[]= {"", "LOC", "CYCLO"};
  				JLabel labelMetrica1 = new JLabel("1º Threshold");
	  			metricX = new JComboBox<String>();
	  			metricX.setBounds(50, 50, 90, 20);
	  			//metricX.setSelectedItem(aux.getCodeSmell());
	  			String operadores1[] = {"", "<", ">"};
	  			operadorRelacional1 = new JComboBox<String>(operadores1);
	  			operadorRelacional1.setBounds(50, 50, 90, 20);
	  			//operadorRelacional1.setSelectedItem(aux.getmetricaXOperator());
	  			metrica1 = new JTextField();
	  				
	  			//JLabel operadorRelacional1Label = new JLabel("1º Operador Relacional");
	  				
	  				
	  			//JLabel metrica2Label= new JLabel("CYCLO ou LAA");
	  			//String metricsY[]= {"", "LOC", "CYCLO"};
	  			JLabel labelMetrica2 = new JLabel("2º Threshold");
	  			metricY = new JComboBox<String>();
	  			metricY.setBounds(50, 50, 90, 20);
	  			//metricY.setSelectedItem(aux.getCodeSmell());
	  			String operadores2[] = {"", "<", ">"};
	  			operadorRelacional2 = new JComboBox<String>(operadores2);
	  			operadorRelacional2.setBounds(50, 50, 90, 20);
	  			//operadorRelacional2.setSelectedItem(aux.getmetricaYOperator());
	  			metrica2 = new JTextField();
	  				
	  			//JLabel operadorRelacional2Label = new JLabel("2º Operador Relacional");
	  				
	  			JLabel operadorLogicoLabel = new JLabel("Operador Lógico");
	  			String operadoresLogico[] = {"", "AND", "OR"};
	  			operadorLogico = new JComboBox<String>(operadoresLogico);
	  			operadorLogico.setBounds(50, 50, 90, 20);
	  			//operadorLogico.setSelectedItem(aux.getLogicalOperator());
	  			JLabel vazio2 = new JLabel("");
	  			JLabel v3 = new JLabel("");
  				
	  			ruleForm.add(codeSmellLabel);
	  			ruleForm.add(codeSm);
	  			ruleForm.add(vazio);
	  			ruleForm.add(v);
	  				
	  			ruleForm.add(ruleNameLabel);
	  			ruleForm.add(ruleName);
	  			ruleForm.add(vazio1);
	  			ruleForm.add(v2);
	  				
	  			ruleForm.add(labelMetrica1);
	  			ruleForm.add(metricX);
	  			ruleForm.add(operadorRelacional1);
	 			ruleForm.add(metrica1);
	  				
	 			ruleForm.add(labelMetrica2);
	  			ruleForm.add(metricY);
	  			ruleForm.add(operadorRelacional2);
	  			ruleForm.add(metrica2);
	  				
	  			ruleForm.add(operadorLogicoLabel);
	  			ruleForm.add(operadorLogico);
	  			ruleForm.add(vazio2);
	  			ruleForm.add(v3);
					
	  			popupPanel.add(ruleForm, BorderLayout.CENTER);
	  			popupPanel.add(criar, BorderLayout.SOUTH);
	  			d.setSize(410,200);
				d.setLocation(tamanhoTela.width/2-d.getWidth()/2, tamanhoTela.height/2-d.getHeight()/2);
	  			d.add(popupPanel);
	  			d.setVisible(true); 
	  				
        	   
			}
		});
		
		codeSm.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// TODO Auto-generated method stub
				metricX.removeAllItems();
				metricY.removeAllItems();
				if(codeSm.getSelectedItem().equals("is_long_method") ) {
					metricX.addItem(""); metricX.addItem("LOC"); metricX.addItem("CYCLO"); metricY.addItem(""); metricY.addItem("LOC"); metricY.addItem("CYCLO"); 
				}else if(codeSm.getSelectedItem().equals("is_feature_envy")){
					metricX.addItem(""); metricX.addItem("ATFD"); metricX.addItem("LAA"); metricY.addItem(""); metricY.addItem("ATFD"); metricY.addItem("LAA"); 
				}
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
