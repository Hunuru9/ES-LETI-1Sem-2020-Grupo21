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

/**
 * 
 * O GUI representa a interface na qual o objetivo do trabalho e implementado, ou seja 
 * trata da representacao visual do codigo implementado nas outras classes para o fim 
 * de visualizacao de defeitos detetados.
 * 
 * @author Francisco Nunes
 * @since 2020-12-10
 */

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
	private JComboBox codeSm;
	private JComboBox<String> metricX;
	private JComboBox<String> metricY;
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

	/**
	 * Este e o metodo construtor do GUI.
	 * 
	 * @throws IOException se o ficheiro nao conseguir ser lido
	 * @throws ClassNotFoundException ahm...
	 * @param excelReader O GUI pede um objeto do tipo ExcelReader para ser iniciado.
	 * @param listaRegras O GUI pede um objeto do tipo RuelSet para ser iniciado.
	 * Neste construtor sao iniciados todos os atributos, e criada a JFrame que 
	 * vai receber o Layout inicial com o addFrameContent().
	 * 
	 * @author Francisco Nunes
	 * @since 2020-12-10
	 */

	public Gui(ExcelReader excelReader, RuleSet listaRegras) throws IOException, ClassNotFoundException {
		this.listaRegras=listaRegras;
		this.excelReader = excelReader;
		scroll=new JScrollPane(lista);
		this.scrollMethodID = new JScrollPane(listaMethodfilter);
		aux=new Rule("", "", "");
		this.table = new JTable(excelReader.getData(), excelReader.getColumnNames());
		this.scrollTable = new JScrollPane(table);

		this.painelBotoes = new JPanel(new FlowLayout());

		frame=new JFrame("Code Smells");
		frame.setLayout(new BorderLayout(hGap,vGap));
		frame.setResizable(true);
		addFrameContent();
	}

	/**
	 * Neste metodo sao tratadas todas as alteracoes fruto da interacao do utilizador com o GUI.
	 * <p>
	 * Dentro deste metodo existem varios butoes que interagem diretamente com o GUI alterando
	 * o JPanel mostrado (i.e. showExcel, regras, detetarDefeitos) e outros que servem para
	 * introduzir valores criando regras (criar, criarRegra e lista) ou para a detecao de
	 * defeitos (filtrar e Results).
	 * <p>
	 * Os botoes showExcel,regras e detetarDefeitos sao utilizados para fazer a troca de JPanel na JFrame 
	 * para mostrar o conteudo respetivo de cada area.
	 * <p>
	 * O botao criarRegra abre uma JDialog onde e possivel ser criada uma regra, tendo JComboBox
	 * e JTextField para o efeito. A JCombobox inicial utiliza o codeSm para atualizar as escolhas
	 * possíveis nas outras JCombobox em face a opcao escolhida na inicial.
	 * <p>
	 * O botao criar ao ser clicado, le os conteudos de cada JCombobox e JTextField no JDialog e 
	 * cria uma regra com essa informacao, colocando-a posteriormente na JList listaRegras.
	 * <p>
	 * O botao lista funciona ao clciar uma regra existente na listaRegras 2 vezes, levando
	 * o utilizador para a JDialog que foi utilizada para a criar, com as informacoes dessa mesma regra
	 * nos lugares respetivos para ser possível edita-la. O botao criar que aparecia na criacao da regra
	 * e agora trocado pelo botao atualizar.
	 * <p>
	 * O botao filtrar abre uma JDialog com uma unica JCombobox a pedir a regra a ser utilizada para filtrar
	 * os MethodIDs que tem CodeSmells.
	 * <p>
	 * O botao Results coloca os resultados filtrados apartir da regra escolhida na JList listaMethodfilter e 
	 * altera os valores dos indicadores de qualidade face aos resultados.
	 * 
	 * 
	 * @author Francisco Nunes
	 * @since 2020-12-10
	 */

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
				String metrica1Value = metrica1.getText();
				String operadorRelacional1Value = operadorRelacional1.getSelectedItem().toString();

				String metrica2string = metricY.getSelectedItem().toString();
				String metrica2Value = metrica2.getText();
				String operadorRelacional2Value = operadorRelacional2.getSelectedItem().toString();

				String operadorLogicoValue = operadorLogico.getSelectedItem().toString();

				if(ruleNameValue.isEmpty() || !metrica1string.isEmpty() && metrica2string.isEmpty() && !operadorLogicoValue.isEmpty()
						|| !metrica2string.isEmpty() && metrica1string.isEmpty() && !operadorLogicoValue.isEmpty() || 
						metrica1string.isEmpty() && metrica2string.isEmpty() || !metrica1string.isEmpty() && !metrica2string.isEmpty() && operadorLogicoValue.isEmpty() ){
					d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					throw new IllegalArgumentException("Criterios invalidos");

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

					//				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
					//					System.out.println(listaRegras.getRegras().get(i).toString());
					//				}

					listModelRegras.addElement(regra.toString());

					d.setVisible(false); 
				}

			}
		});

		atualizar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int indice=listModelRegras.indexOf(aux.toString());

				String codeSmellValue = codeSm.getSelectedItem().toString();
				String ruleNameValue = ruleName.getText();

				String metrica1string = metricX.getSelectedItem().toString();
				String metrica1Value = metrica1.getText();
				String operadorRelacional1Value = operadorRelacional1.getSelectedItem().toString();

				String metrica2string = metricY.getSelectedItem().toString();
				String metrica2Value = metrica2.getText();
				String operadorRelacional2Value = operadorRelacional2.getSelectedItem().toString();

				String operadorLogicoValue = operadorLogico.getSelectedItem().toString();

				if(ruleNameValue.isEmpty() || !metrica1string.isEmpty() && metrica2string.isEmpty() && !operadorLogicoValue.isEmpty()
						|| !metrica2string.isEmpty() && metrica1string.isEmpty() && !operadorLogicoValue.isEmpty() || 
						metrica1string.isEmpty() && metrica2string.isEmpty() || !metrica1string.isEmpty() && !metrica2string.isEmpty() && operadorLogicoValue.isEmpty() ) {
					d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					throw new IllegalArgumentException("Criterios invalidos");
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

					//				for(int i=0; i!=listaRegras.getRegras().size(); i++) {
					//					System.out.println(listaRegras.getRegras().get(i).toString());
					//				}

					listModelRegras.set(indice, s);
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
					//					System.out.println(value);
					String [] nome=value.split(" ");

					Toolkit kit = Toolkit.getDefaultToolkit();
					Dimension tamanhoTela = kit.getScreenSize();

					for(int i=0; i!=listaRegras.getRegras().size(); i++) {
						if(listaRegras.getRegras().get(i).getNomeRegra().equals(nome[1])) {
							aux=listaRegras.getRegras().get(i);
						}
					}

					d = new JDialog(frame, "Regra");

					JPanel popupPanel = new JPanel(new BorderLayout(hGap,vGap));
					JPanel ruleForm = new JPanel(new GridLayout(5,4,hGap,vGap));
					JPanel info = new JPanel(new BorderLayout(hGap,vGap));
					
					JLabel infoLabel = new JLabel("Para criar uma regra, primeiro tem que escolher um codeSmell !");
					info.add(infoLabel);

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


					JLabel labelMetrica1 = new JLabel("1º Threshold");
					metricX = new JComboBox<String>();

					metricX.setBounds(50, 50, 90, 20);

					String operadores1[] = {"", "<", ">"};
					operadorRelacional1 = new JComboBox<String>(operadores1);
					operadorRelacional1.setBounds(50, 50, 90, 20);
					operadorRelacional1.setSelectedItem(aux.getmetricaXOperator());
					metrica1 = new JTextField(String.valueOf(aux.getMetricaX()));


					JLabel labelMetrica2 = new JLabel("2º Threshold");
					metricY = new JComboBox<String>();
					metricY.setBounds(50, 50, 90, 20);

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


					JLabel operadorLogicoLabel = new JLabel("Operador Logico");
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

					popupPanel.add(info, BorderLayout.NORTH);
					popupPanel.add(ruleForm, BorderLayout.CENTER);
					popupPanel.add(atualizar, BorderLayout.SOUTH);
					d.setSize(450,240);
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
				
				if(codeSm.getSelectedItem().equals("is_long_method") || codeSm.getSelectedItem().equals("is_feature_envy")) {
					codeSm.setSelectedIndex(0);
				}else {

					d = new JDialog(frame, "Regra");
	
					JPanel popupPanel = new JPanel(new BorderLayout(hGap,vGap));
					JPanel ruleForm = new JPanel(new GridLayout(5,4,hGap,vGap));
					JPanel info = new JPanel(new BorderLayout(hGap,vGap));
	
					popupPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					
					JLabel infoLabel = new JLabel("Para criar uma regra, primeiro tem que escolher um codeSmell !");
					info.add(infoLabel);
	
					JLabel codeSmellLabel = new JLabel("Code Smell");
					codeSm.setBounds(50, 50, 90, 20);
					JLabel vazio = new JLabel("");
					JLabel v = new JLabel("");
	
	
					JLabel ruleNameLabel = new JLabel("Nome da regra");
					ruleName = new JTextField();
					JLabel vazio1 = new JLabel("");
					JLabel v2 = new JLabel("");
	
	
					JLabel labelMetrica1 = new JLabel("1º Threshold");
					metricX = new JComboBox<String>();
					metricX.setBounds(50, 50, 90, 20);
					String operadores1[] = {"", "<", ">"};
					operadorRelacional1 = new JComboBox<String>(operadores1);
					operadorRelacional1.setBounds(50, 50, 90, 20);
					metrica1 = new JTextField();
	
	
					JLabel labelMetrica2 = new JLabel("2º Threshold");
					metricY = new JComboBox<String>();
					metricY.setBounds(50, 50, 90, 20);
					//metricY.setSelectedItem(aux.getCodeSmell());
					String operadores2[] = {"", "<", ">"};
					operadorRelacional2 = new JComboBox<String>(operadores2);
					operadorRelacional2.setBounds(50, 50, 90, 20);
	
					metrica2 = new JTextField();
	
					JLabel operadorLogicoLabel = new JLabel("Operador Logico");
					String operadoresLogico[] = {"", "AND", "OR"};
					operadorLogico = new JComboBox<String>(operadoresLogico);
					operadorLogico.setBounds(50, 50, 90, 20);
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
					
					popupPanel.add(info, BorderLayout.NORTH);
					popupPanel.add(ruleForm, BorderLayout.CENTER);
					popupPanel.add(criar, BorderLayout.SOUTH);
					d.setSize(450,240);
					d.setLocation(tamanhoTela.width/2-d.getWidth()/2, tamanhoTela.height/2-d.getHeight()/2);
					d.add(popupPanel);
					d.setVisible(true); 
					centerFrame();
				}
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
					listaRegras.qualityIndicators(MethodIDValue, listaRegras.getResultadosBool());

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

						listaRegras.qualityIndicators(fetchCodeSmell, listaRegras.getResultadosBool());
						break;
					case "is_feature_envy":
						resultadoslista = listaRegras.codeSmellIds(aux, "ATFD", "LAA");	
						listaRegras.qualityIndicators(fetchCodeSmell, listaRegras.getResultadosBool());
						break;
					}
					for(int i = 0;i < resultadoslista.size();i++){
						System.out.println(Integer.toString(resultadoslista.get(i)));

						listModelMethods.add(i,Integer.toString(resultadoslista.get(i)));
					}				

				}

				qi_panel.removeAll();

				JLabel dci_label = new JLabel("DCI: ");
				qi_panel.add(dci_label);
				JLabel dci_value = new JLabel(Integer.toString(0));
				qi_panel.add(dci_value);

				JLabel dii_label = new JLabel("DII: ");
				qi_panel.add(dii_label);
				JLabel dii_value = new JLabel(Integer.toString(0));
				qi_panel.add(dii_value);

				JLabel adci_label = new JLabel("ADCI: ");
				qi_panel.add(adci_label);
				JLabel adci_value = new JLabel(Integer.toString(0));
				qi_panel.add(adci_value);

				JLabel adii_label = new JLabel("ADII: ");
				qi_panel.add(adii_label);
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
				dci_label.setFont(new Font(dci_label.getFont().getName(), Font.BOLD, 18));
				dii_label.setFont(new Font(dii_label.getFont().getName(), Font.BOLD, 18));
				adci_label.setFont(new Font(adci_label.getFont().getName(), Font.BOLD, 18));
				adii_label.setFont(new Font(adii_label.getFont().getName(), Font.BOLD, 18));

				dci_value.setFont(new Font(dci_value.getFont().getName(), Font.PLAIN, 18));
				dii_value.setFont(new Font(dci_value.getFont().getName(), Font.PLAIN, 18));
				adci_value.setFont(new Font(dci_value.getFont().getName(), Font.PLAIN, 18));
				adii_value.setFont(new Font(dci_value.getFont().getName(), Font.PLAIN, 18));
				qi_panel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
				teste_west.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				scrollMethodID = new JScrollPane(listaMethodfilter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollMethodID.setPreferredSize(new Dimension(150,500));
				DefaultListCellRenderer renderer = (DefaultListCellRenderer) listaMethodfilter.getCellRenderer();
				renderer.setHorizontalAlignment(JLabel.CENTER);
				JLabel method_id_title = new JLabel("Method ID's com defeitos:");
				teste_west.add(method_id_title, BorderLayout.NORTH);
				method_id_title.setHorizontalAlignment(JLabel.CENTER);
				//teste_west.add(Box.createVerticalStrut(10));
				teste_west.add(scrollMethodID, BorderLayout.CENTER);
				teste_east.add(qi_panel);
				teste_east.setAlignmentX(Component.CENTER_ALIGNMENT);
				teste_west.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 50));
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


				frame.setSize(400,600);


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
				//defeitosPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 100, 100));
				frame.setSize(400,600);

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

	/**
	 * Este metodo inicializa o GUI.
	 * 
	 * @author Francisco Nunes
	 * @since 2020-12-10
	 */
	public void open() {
		frame.setState(JFrame.NORMAL);
		frame.setVisible(true);
		frame.setResizable(false);

		centerFrame();
	}

	/**
	 * Este metodo coloca a aplicacao do GUI no centro do ecra.
	 * 
	 * @author Francisco Nunes
	 * @since 2020-12-10
	 */
	public void centerFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

	}

}
