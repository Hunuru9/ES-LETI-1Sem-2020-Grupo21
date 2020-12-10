package codesmellservice;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

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

	private DefaultListModel<String> listModelRegras = new DefaultListModel<>();
	private DefaultListModel<String> listModelMethods = new DefaultListModel<>();

	private JList<String> lista = new JList<>(listModelRegras);
	private JList<String> listaMethodfilter = new JList<>(listModelMethods);


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

	private final int hGap = 5;
	private final int vGap = 5;

	private JPanel teste_west = new JPanel(new BorderLayout(hGap,vGap));
	private JPanel teste_east = new JPanel();

	public Gui(ExcelReader excelReader, RuleSet listaRegras) throws IOException, ClassNotFoundException {
		this.listaRegras=listaRegras;
		this.excelReader = excelReader;
		scroll=new JScrollPane(lista);
		this.scrollMethodID = new JScrollPane(listaMethodfilter);
		aux=new Rule("");
		this.table = new JTable(excelReader.getData(), excelReader.getColumnNames());
		this.scrollTable = new JScrollPane(table);

		this.painelBotoes = new JPanel(new FlowLayout());

		frame=new JFrame("Code Smells");
		frame.setLayout(new BorderLayout(hGap,vGap));
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

		final JPanel excelPanel = new JPanel(new BorderLayout(hGap,vGap));
		final JPanel regrasPanel = new JPanel(new BorderLayout(hGap,vGap));
		final JPanel showcase=new JPanel(new BorderLayout(hGap,vGap));
		final JPanel defeitosPanel = new JPanel(new BorderLayout(hGap,vGap));

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

				//				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
				//					System.out.println(listaRegras.getRegras().get(i).toString());
				//				}

				listModelRegras.addElement(ruleNameValue);

				d.setVisible(false); 


			}
		});

		atualizar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int indice=listModelRegras.indexOf(aux.getNomeRegra());

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

				//				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
				//					System.out.println(listaRegras.getRegras().get(i).toString());
				//				}

				listModelRegras.set(indice, ruleNameValue);

				d.setVisible(false);
			}
		});

		lista.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {

					String value = (String)lista.getModel().getElementAt(lista.locationToIndex(e.getPoint()));
					//					System.out.println(value);

					Toolkit kit = Toolkit.getDefaultToolkit();
					Dimension tamanhoTela = kit.getScreenSize();

					for(int i=0; i!=listaRegras.getRegras().size(); i++) {
						if(listaRegras.getRegras().get(i).getNomeRegra().equals(value)) {
							aux=listaRegras.getRegras().get(i);
						}
					}

					d = new JDialog(frame, "Regra");

					JPanel popupPanel = new JPanel(new BorderLayout(hGap,vGap));
					JPanel ruleForm = new JPanel(new GridLayout(7,2,hGap,vGap));

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

					d.setSize(300,300);

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

				JPanel popupPanel = new JPanel(new BorderLayout(hGap,vGap));
				JPanel ruleForm = new JPanel(new GridLayout(7,2,hGap,vGap));

				popupPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

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
				d.setSize(300,300);
				d.setLocation(tamanhoTela.width/2-d.getWidth()/2, tamanhoTela.height/2-d.getHeight()/2);
				d.add(popupPanel);
				d.setVisible(true); 
				centerFrame();
			}
		});


		filtrar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				Toolkit kit = Toolkit.getDefaultToolkit();
				Dimension tamanhoTela = kit.getScreenSize();
				d = new JDialog(frame, "Morcela");
				JPanel popupPanel = new JPanel(new BorderLayout(hGap,vGap));
				JPanel comboBoxSegment = new JPanel(new GridLayout(1,2,hGap,vGap));
				comboBoxSegment.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				comboBoxSegment.setSize(400,50);
				JLabel MethodIDchoice = new JLabel("Escolha uma Regra/Ferramenta");
				//				String MethodIDlist[]= {"MethodID1", "MethodID2"};
				String[] RegrasCombobox = new String[listaRegras.getRegras().size() + 2];
				RegrasCombobox[0]="PMD";
				RegrasCombobox[1]="iPlasma";
				for(int i = 0;i < listaRegras.getRegras().size();i++){
					RegrasCombobox[i+2] = listaRegras.getRegras().get(i).getNomeRegra();
				}
				MethodIDbox = new JComboBox<String>(RegrasCombobox);
				//MethodIDbox.setBounds(50, 50, 90, 20);

				comboBoxSegment.add(MethodIDchoice);
				comboBoxSegment.add(MethodIDbox);

				popupPanel.add(comboBoxSegment,BorderLayout.CENTER);
				popupPanel.add(Results,BorderLayout.SOUTH);
				d.setSize(500,120);
				d.setLocation(tamanhoTela.width/2-d.getWidth()/2, tamanhoTela.height/2-d.getHeight()/2);
				d.add(popupPanel);
				d.setVisible(true);
				centerFrame();

			}
		});

		Results.addActionListener(new java.awt.event.ActionListener() {

			JPanel qi_panel = new JPanel (new GridLayout(4,2,hGap,vGap));

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				listModelMethods.removeAllElements();
				teste_west.remove(scrollMethodID);
				String MethodIDValue = MethodIDbox.getSelectedItem().toString();
				if(MethodIDValue.equals("iPlasma") || MethodIDValue.equals("PMD")){
					for(int i = 0;i < listaRegras.excelTools(MethodIDValue).size();i++){
						System.out.println(Integer.toString(listaRegras.excelTools(MethodIDValue).get(i)));
						listModelMethods.add(i,Integer.toString(listaRegras.excelTools(MethodIDValue).get(i)));
					}	
					listaRegras.codeSmellIds(aux, "LOC", "CYCLO");
					listaRegras.quality_indicators(MethodIDValue, listaRegras.getResultadosBool());

				}else{
					for(int i=0; i!=listaRegras.getRegras().size(); i++) {
						if(listaRegras.getRegras().get(i).getNomeRegra().equals(MethodIDValue)) {
							aux=listaRegras.getRegras().get(i);
						}
					}
					String fetchCodeSmell = aux.getCodeSmell();
					List<Integer> resultadoslista = new ArrayList<Integer>();

					switch(fetchCodeSmell){
					case "is_long_method":
						resultadoslista = listaRegras.codeSmellIds(aux, "LOC", "CYCLO");

						listaRegras.quality_indicators(fetchCodeSmell, listaRegras.getResultadosBool());
						break;
					case "is_feature_envy":
						resultadoslista = listaRegras.codeSmellIds(aux, "ATFD", "LAA");	
						listaRegras.quality_indicators(fetchCodeSmell, listaRegras.getResultadosBool());
						break;
					}
					for(int i = 0;i < resultadoslista.size();i++){
						System.out.println(Integer.toString(resultadoslista.get(i)));

						listModelMethods.add(i,Integer.toString(resultadoslista.get(i)));
					}				

				}

				qi_panel.removeAll();
				qi_panel.add(new JLabel("DCI"));
				JLabel dci_value = new JLabel(Integer.toString(0));
				qi_panel.add(dci_value);
				qi_panel.add(new JLabel("DII"));
				JLabel dii_value = new JLabel(Integer.toString(0));
				qi_panel.add(dii_value);
				qi_panel.add(new JLabel("ADCI"));
				JLabel adci_value = new JLabel(Integer.toString(0));
				qi_panel.add(adci_value);
				qi_panel.add(new JLabel("ADII"));
				JLabel adii_value = new JLabel(Integer.toString(0));
				qi_panel.add(adii_value);
				for (Map.Entry mapElement : listaRegras.getMap().entrySet()) { 
					String key = (String)mapElement.getKey();
					if(key.equals("DCI")) {
						dci_value.setText(Integer.toString((Integer)mapElement.getValue()));
					}else {
						if(key.equals("DII")) {
							dii_value.setText(Integer.toString((Integer)mapElement.getValue()));
						}else {
							if (key.equals("ADCI")) {
								adci_value.setText(Integer.toString((Integer)mapElement.getValue()));
							}else {
								if (key.equals("ADII")) {
									adii_value.setText(Integer.toString((Integer)mapElement.getValue()));
								}
							}
						}
					}
				}
				teste_west.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				scrollMethodID = new JScrollPane(listaMethodfilter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollMethodID.setPreferredSize(new Dimension(150,500));
				DefaultListCellRenderer renderer = (DefaultListCellRenderer) listaMethodfilter.getCellRenderer();
				renderer.setHorizontalAlignment(JLabel.CENTER);
				JLabel method_id_title = new JLabel("Method ID's");
				teste_west.add(method_id_title, BorderLayout.NORTH);
				method_id_title.setHorizontalAlignment(JLabel.CENTER);
				//teste_west.add(Box.createVerticalStrut(10));
				teste_west.add(scrollMethodID, BorderLayout.CENTER);
				teste_east.add(qi_panel);
				teste_east.setAlignmentX(Component.CENTER_ALIGNMENT);
				teste_west.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				//frame.add(defeitosPanel);
				frame.revalidate();
				frame.repaint();
				centerFrame();

			}
		});

		regras.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				regrasPanel.add(scroll, BorderLayout.CENTER);

				regrasPanel.add(criarRegra,BorderLayout.NORTH);


				frame.setSize(600,600);


				frame.remove(showcase);
				frame.remove(excelPanel);
				frame.remove(defeitosPanel);
				frame.add(regrasPanel);
				frame.validate();
				frame.repaint();

				centerFrame();


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

				defeitosPanel.add(teste_west,BorderLayout.WEST);
				defeitosPanel.add(teste_east,BorderLayout.CENTER);
				frame.setSize(600,600);

				frame.remove(showcase);
				frame.remove(regrasPanel);
				frame.remove(excelPanel);
				frame.add(defeitosPanel);
				frame.revalidate();
				frame.repaint();

				centerFrame();


			}
		});


		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void open() {
		frame.setState(JFrame.NORMAL);
		frame.setVisible(true);
		frame.setResizable(false);

		centerFrame();
	}

	public void centerFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

	}

}
