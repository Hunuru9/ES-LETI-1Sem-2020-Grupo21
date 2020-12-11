package codesmellservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <h1>RuleSet<h1>
 * A classe RuleSet é uma classe destinada para o armazenamento de todas as regras criadas
 * pelo utilizador mas também é onde estão implementados os métodos que serão utilizados para detetar
 * os method id's das regras que o utilizador escolher, que possuem code smells segundo alguns critérios. 
 * Também é nesta classe que estão implementados métodos que iriam permitir a deteção de defeitos
 * (ex: DCI, DII, ADCI, ADII) de uma ferramenta ou regra escolhida pelo utilizador. 
 * 
 * 
 * @author Gonçalo Morgado
 * @since 2020-12-10
 * 
 */
public class RuleSet {

	private List<Rule> lista;
	private ExcelReader excel;
	private HashMap<String, Integer> map;
	private List<String> resultadosBool;

	/**
	 * O construtor da classe RuleSet é inicializado a partir de uma instância da classe ExcelReader para 
	 * mais tarde, toda a informação dentro do ficheiro de Excel, ser utilizada nos filtragem dos method id's
	 * e dos defeitos das ferramentas ou regras.
	 * @param excel A classe RuleSet utiliza esta objecto para filtrar os method id's, ao comparar os valores de uma regra definida
	 * pelo utilizador, ao valores no ficheiro Excel
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public RuleSet(ExcelReader excel) {
		this.excel=excel;
		lista=new ArrayList<Rule>();
		resultadosBool = new ArrayList<String>();
		this.map = new HashMap<>(); 
	}

	/**
	 * Método para adicionar uma regra à lista de regras
	 * @param rule Regra dada para adicionar à lista de regras 
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void addRegra1(Rule rule) {
		lista.add(rule);
	}

	/**
	 * Método para obter a lista de regras definidas pelo utlizador
	 * @results é devolvida uma lista de todas as regras utilizadas pelo utilizador 
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Rule> getRegras() {
		return lista;
	}

	/**
	 * Método para obter uma lista de resultados da análise de codeSmells de cada método declarado no ficheiro excel
	 * @results é devolvida uma lista strings com os resultados da análise
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public List<String> getResultadosBool(){
		return this.resultadosBool;
	}

	/**
	 * Método que retorna um HashMap com as siglas dos indicadores de qualidade e os seus respectivos
	 * valores, de uma determinada regra ou ferramenta.
	 * 
	 * @results é devolvido um HashMap com as strings das siglas e os respectivos valores dos indicadores
	 * de qualidade
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public HashMap<String, Integer> getMap(){
		return this.map;
	}

	/**
	 * Neste método é feita a análise de codeSmells de uma determinada regra escolhida pelo utilizador, 
	 * a partir da comparação de duas listas, onde estas são valores das métricas retiradas do ficheiro 
	 * excel.
	 * <p>
	 * Esta comparação é efetuada com as métricas definidas numa determinada regra criada pelo utilizador,
	 * onde se podem apresentar vários critérios de comparação, como por exemplo: Se LOC é maior do que 50
	 * ou CYCLO é menor do que 30, então o método tem um codeSmell.
	 * <p>
	 * @param aux String dos operadores relacionais utilizados na regra para a comparação de valores
	 * @param r Regra escolhida pelo utilizador para sua análise
	 * @param firstColumn Valores de uma coluna correspondente a uma métrica do ficheiro Excel
	 * @param secondColumn Valores de uma coluna correspondente a uma métrica do ficheiro Excel
	 * @results É devolvida uma lista de strings com os resultados (i.e. se tem codeSmell retorna "true" 
	 * e se não tem codeSmell retorna "false") da comparação das thresholds da regra escolhida pelo utilizador,
	 * com os valores das colunas das métricas dadas como parâmetro, do ficheiro excel.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<String> stringValues(String aux, Rule r, List<String> firstColumn, List<String> secondColumn) {
		List<String> resultados=new ArrayList<String>();
		System.out.println(r.getLogicalOperator());
		switch(r.getLogicalOperator()) {
		case "AND":
			for(int i=0; i!=firstColumn.size(); i++) {
				switch(aux) {
				case ">>":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() && Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() && Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() && Double.parseDouble(firstColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))>r.getMetricaX() && Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				case "><":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() && Double.parseDouble(firstColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))>r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<<":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() && Double.parseDouble(firstColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))<r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<>":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() && Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaY() && Double.parseDouble(secondColumn.get(i))>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() && Double.parseDouble(firstColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))<r.getMetricaX() && Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				}
			}
			break;
		case "OR":
			for(int i=0; i!=firstColumn.size(); i++) {
				switch(aux) {
				case ">>":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() || Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaY() || Double.parseDouble(secondColumn.get(i))>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() || Double.parseDouble(firstColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))>r.getMetricaX() || Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				case "><":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() || Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaY() || Double.parseDouble(secondColumn.get(i))<r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX() || Double.parseDouble(firstColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))>r.getMetricaX() || Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<<":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() || Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaY() || Double.parseDouble(secondColumn.get(i))<r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() || Double.parseDouble(firstColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))<r.getMetricaX() || Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<>":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() || Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaY() || Double.parseDouble(secondColumn.get(i))>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX() || Double.parseDouble(firstColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(Double.parseDouble(secondColumn.get(i))<r.getMetricaX() || Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}
					break;
				}
			}
			break;
		case "":
			if(r.getMetricaX()==0.0 && r.getMetricaY()!=0.0) {
				for(int i=0; i!=secondColumn.size(); i++) {
					switch(aux) {
					case ">":
						if(Double.parseDouble(secondColumn.get(i))>r.getMetricaY()) {
							resultados.add("true");
						}else {
							resultados.add("false");
						}
						break;
					case "<":
						if(Double.parseDouble(secondColumn.get(i))<r.getMetricaY()) {
							resultados.add("true");
						}else {
							resultados.add("false");
						}
						break;
					}
				}	
			}else {
				for(int i=0; i!=firstColumn.size(); i++) {
					switch(aux) {
					case ">":
						if(Double.parseDouble(firstColumn.get(i))>r.getMetricaX()) {
							resultados.add("true");
						}else {
							resultados.add("false");
						}
						break;
					case"<":
						if(Double.parseDouble(firstColumn.get(i))<r.getMetricaX()) {
							resultados.add("true");
						}else {
							resultados.add("false");
						}
						break;
					}
				}	
			}
			break;
		}
		return resultados;
	}

	/**
	 * O método tem como função calcular o número de DCI's, DII's, ADCI's e ADII's da regra do utilizador ou da
	 * ferramenta PMD e iPlasma.
	 * O cálculo é feito através da comparação da lista dos resultados dado como parâmetro com a coluna do tipo de 
	 * codeSmell da regra caso não seja PMD ou iPlasma. No caso do PMD ou do iPlasma compara-se a coluna da 
	 * ferramenta PMD / iPlasma com a coluna do is_long_method.
	 * @param tool String da ferramenta / do tipo de codeSmell utilizado na regra definida pelo utilizador
	 * @param resultados Lista de resultados provenientes da comparação das thresholds da regra escolhida pelo               
	 * utilizador, com os valores das colunas das métricas dadas como parâmetro, do ficheiro excel.
	 * @return É devolvido um HashMap<K,V> em que as key's referem-se aos DCI, DII, ADCI e ADII e os Values
	 * referem-se à contagem de cada um deles 
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public HashMap<String,Integer> qualityIndicators(String tool, List<String> resultados){

		this.map = new HashMap<String,Integer>();

		List<String> allValues = excel.getColumnValues(tool); //Valores do long_method por exemplo
		List<String> list_to_compare; //nossos valores
		boolean to_compare;
		if(tool.equals("PMD") || tool.equals("iPlasma")) {
			list_to_compare = excel.getColumnValues(tool);
			allValues = excel.getColumnValues("is_long_method");
		}else {
			allValues = excel.getColumnValues(tool);
			list_to_compare = resultados;
		}
		this.map.put("DCI", 0);
		this.map.put("DII", 0);
		this.map.put("ADCI", 0);
		this.map.put("ADII", 0);
		for(int i = 0; i <= allValues.size()-1; i++) {
			if(Boolean.parseBoolean(list_to_compare.get(i)) &&  Boolean.parseBoolean(allValues.get(i))) {
				this.map.put("DCI", map.get("DCI") + 1);

			}else 
				if (Boolean.parseBoolean(list_to_compare.get(i)) && !Boolean.parseBoolean(allValues.get(i)) ){
					this.map.put("DII", map.get("DII") + 1);
				}
				else
					if (!Boolean.parseBoolean(list_to_compare.get(i)) && !Boolean.parseBoolean(allValues.get(i)) ){
						this.map.put("ADCI", map.get("ADCI") + 1);
					}
					else {
						this.map.put("ADII", map.get("ADII") + 1);
					}
		}
		System.out.println(this.map.toString());
		return this.map;
	}

	/**
	 * Este método utiliza dois métodos(stringValues e methodIDS) para obter resultados (se uma determinado
	 * método tem codeSmell ou não) numa lista de strings e posteriormente filtrar estes resultados para apresentar
	 * uma lista de inteiros que possui os method Id's que têm codeSmells.
	 * 
	 * @param r Regra escolhida pelo utilizador para análise
	 * @param xMetrica String de uma métrica de um determinado codeSmell (neste caso: LOC ou ATFD)
	 * @param yMetrica String de uma métrica de um determinado codeSmell (neste caso: CYCLO ou LAA)
	 * @results devolve uma lista de inteiros dos method id's que têm codeSmells
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Integer> codeSmellIds(Rule r, String xMetrica, String yMetrica) {

		List<Integer> valores=new ArrayList<Integer>();

		List<String> firstColumn=excel.getColumnValues(xMetrica);
		List<String> secondColumn=excel.getColumnValues(yMetrica);

		String s=r.getmetricaXOperator();
		String s2=r.getmetricaYOperator();
		String aux=s+s2;

		this.resultadosBool=stringValues(aux, r, firstColumn, secondColumn);
		valores=methodIDS(this.resultadosBool);

		return valores;
	}

	/**
	 * Neste método é feita a filtragem de uma lista que contém os resultados dos métodos 
	 * que sofreram análise anterior, para depois ser apresentado uma lista de inteiros que 
	 * irá devolver apenas os method ID's que têm codeSmells.
	 * 
	 * @param values lista de strings que tem resultados, true ou false, se um determinado método possui codeSmells
	 * @results devolve uma lista de inteiros dos method id's que têm codeSmells
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Integer> methodIDS(List<String> values)  {
		List<Integer> methodIds=new ArrayList<Integer>();
		for(int i=0; i!=values.size(); i++)  {
			if(values.get(i).equals("true")) {
				methodIds.add(1+i);
			}
		}
		return methodIds;
	}

	/**
	 * Neste método, é feita a filtragem dos method ID's que têm codeSmells, apenas das ferramentas (
	 * iPlasma ou PMD) que estão descritas no Excel.
	 * 
	 * @param ferramenta String de uma determinada ferramenta que utilizador escolheu para análise 
	 * @results devolve uma lista de inteiros dos method id's que têm codeSmells
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Integer> excelTools(String ferramenta) {
		List<String> tool = excel.getColumnValues(ferramenta);
		List<String> methodId= excel.getColumnValues("MethodID");
		List<Integer> results=new ArrayList<Integer>();
		for(int i=0; i!=methodId.size(); i++) {
			if(tool.get(i).equals("true")) {
				int id=(int) Double.parseDouble(methodId.get(i));

				//System.out.println("ID: " + id);

				results.add(id);
			}
		}

		return results;
	}

}
