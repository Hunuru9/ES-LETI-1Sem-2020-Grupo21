package codesmellservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * A classe RuleSet e uma classe destinada para o armazenamento de todas as
 * regras criadas pelo utilizador mas tambem e onde estao implementados os
 * metodos que serao utilizados para detetar os method id's das regras que o
 * utilizador escolher, que possuem code smells segundo alguns criterios. Tambem
 * e nesta classe que estao implementados metodos que iriam permitir a detecao
 * de defeitos (ex: DCI, DII, ADCI, ADII) de uma ferramenta ou regra escolhida
 * pelo utilizador.
 * 
 * 
 * @author Goncalo Morgado
 * @since 2020-12-10
 * 
 */
public class RuleSet {

	private List<Rule> lista;
	private ExcelReader excel;
	private HashMap<String, Integer> map;
	private List<String> resultadosBool;

	/**
	 * O construtor da classe RuleSet e inicializado a partir de uma instancia da
	 * classe ExcelReader para mais tarde, toda a informacao dentro do ficheiro de
	 * Excel, ser utilizada nos filtragem dos method id's e dos defeitos das
	 * ferramentas ou regras.
	 * 
	 * @param excel A classe RuleSet utiliza esta objecto para filtrar os method
	 *              id's, ao comparar os valores de uma regra definida pelo
	 *              utilizador, ao valores no ficheiro Excel
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public RuleSet(ExcelReader excel) {
		this.excel = excel;
		lista = new ArrayList<Rule>();
		resultadosBool = new ArrayList<String>();
		this.map = new HashMap<>();
	}

	/**
	 * Metodo para adicionar uma regra a lista de regras
	 * 
	 * @param rule Regra dada para adicionar a lista de regras
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void addRegra1(Rule rule) {
		lista.add(rule);
	}

	/**
	 * Metodo para obter a lista de regras definidas pelo utlizador
	 * 
	 * @return e devolvida uma lista de todas as regras utilizadas pelo utilizador
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Rule> getRegras() {
		return lista;
	}

	/**
	 * Metodo para obter uma lista de resultados da analise de codeSmells de cada
	 * metodo declarado no ficheiro excel
	 * 
	 * @return e devolvida uma lista strings com os resultados da analise
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public List<String> getResultadosBool() {
		return this.resultadosBool;
	}

	/**
	 * Metodo que retorna um HashMap com as siglas dos indicadores de qualidade e os
	 * seus respectivos valores, de uma determinada regra ou ferramenta.
	 * 
	 * @return e devolvido um HashMap com as strings das siglas e os respectivos
	 *          valores dos indicadores de qualidade
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public HashMap<String, Integer> getMap() {
		return this.map;
	}

	/**
	 * Neste metodo e feita a analise de codeSmells de uma determinada regra
	 * escolhida pelo utilizador, a partir da comparacao de duas listas, onde estas
	 * sao valores das metricas retiradas do ficheiro excel.
	 * <p>
	 * Esta comparacao e efetuada com as metricas definidas numa determinada regra
	 * criada pelo utilizador, onde se podem apresentar varios criterios de
	 * comparacao, como por exemplo: Se LOC e maior do que 50 ou CYCLO e menor do
	 * que 30, entao o metodo tem um codeSmell.
	 * <p>
	 * 
	 * @param aux          String dos operadores relacionais utilizados na regra
	 *                     para a comparacao de valores
	 * @param r            Regra escolhida pelo utilizador para sua analise
	 * @param firstColumn  Valores de uma coluna correspondente a uma metrica do
	 *                     ficheiro Excel
	 * @param secondColumn Valores de uma coluna correspondente a uma metrica do
	 *                     ficheiro Excel
	 * @return e devolvida uma lista de strings com os resultados (i.e. se tem
	 *          codeSmell retorna "true" e se nao tem codeSmell retorna "false") da
	 *          comparacao das thresholds da regra escolhida pelo utilizador, com os
	 *          valores das colunas das metricas dadas como parametro, do ficheiro
	 *          excel.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<String> stringValues(String aux, Rule r, List<String> firstColumn, List<String> secondColumn) {
		List<String> resultados = new ArrayList<String>();
		switch (r.getLogicalOperator()) {
		case "AND":
			for (int i = 0; i != firstColumn.size(); i++) {
				switch (aux) {
				case ">>":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()
									&& Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									&& Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				case "><":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()
									&& Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									&& Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<<":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()
									&& Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									&& Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<>":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()
									&& Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									&& Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()
									&& Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				}
			}
			break;
		case "OR":
			for (int i = 0; i != firstColumn.size(); i++) {
				switch (aux) {
				case ">>":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()
									|| Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									|| Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				case "><":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()
									|| Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()
									|| Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<<":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()
									|| Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									|| Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				case "<>":
					if (!r.getMetricaXString().equals(r.getMetricaYString())) {
						if (r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO")
								|| r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()
									|| Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					} else {
						if (r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()
									|| Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()
									|| Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
					}
					break;
				}
			}
			break;
		case "":
			if (r.getMetricaX() == 0.0 && r.getMetricaY() != 0.0) {
				for (int i = 0; i != secondColumn.size(); i++) {
					switch (aux) {
					case ">":
						if (r.getMetricaYString().equals("LOC") || r.getMetricaYString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) > r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
						break;
					case "<":
						if (r.getMetricaYString().equals("LOC") || r.getMetricaYString().equals("ATFD")) {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(secondColumn.get(i)) < r.getMetricaY()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
						break;
					}
				}
			} else {
				for (int i = 0; i != firstColumn.size(); i++) {
					switch (aux) {
					case ">":
						if (r.getMetricaXString().equals("CYCLO") || r.getMetricaXString().equals("LAA")) {
							if (Double.parseDouble(secondColumn.get(i)) > r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) > r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						}
						break;
					case "<":
						if (r.getMetricaXString().equals("CYCLO") || r.getMetricaXString().equals("LAA")) {
							if (Double.parseDouble(secondColumn.get(i)) < r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
						} else {
							if (Double.parseDouble(firstColumn.get(i)) < r.getMetricaX()) {
								resultados.add("true");
							} else {
								resultados.add("false");
							}
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
	 * O metodo tem como funcao calcular o numero de DCI's, DII's, ADCI's e ADII's
	 * da regra do utilizador ou da ferramenta PMD e iPlasma. O calculo e feito
	 * atraves da comparacao da lista dos resultados dado como parametro com a
	 * coluna do tipo de codeSmell da regra caso nao seja PMD ou iPlasma. No caso do
	 * PMD ou do iPlasma compara-se a coluna da ferramenta PMD / iPlasma com a
	 * coluna do is_long_method.
	 * 
	 * @param tool       String da ferramenta / do tipo de codeSmell utilizado na
	 *                   regra definida pelo utilizador
	 * @param resultados Lista de resultados provenientes da comparacao das
	 *                   thresholds da regra escolhida pelo utilizador, com os
	 *                   valores das colunas das metricas dadas como parametro, do
	 *                   ficheiro excel.
	 * @return e devolvido um HashMap com Keys e Values em que as key's referem-se aos DCI, DII,
	 *         ADCI e ADII e os Values referem-se a contagem de cada um deles
	 * 
	 * @author Hugo Silva
	 * @since 2020-12-10
	 * 
	 */
	public HashMap<String, Integer> qualityIndicators(String tool, List<String> resultados) {

		this.map = new HashMap<String, Integer>();

		List<String> allValues = excel.getColumnValues(tool); // Valores do long_method por exemplo
		List<String> list_to_compare; // nossos valores
		if (tool.equals("PMD") || tool.equals("iPlasma")) {
			list_to_compare = excel.getColumnValues(tool);
			allValues = excel.getColumnValues("is_long_method");
		} else {
			allValues = excel.getColumnValues(tool);
			list_to_compare = resultados;
		}
		this.map.put("DCI", 0);
		this.map.put("DII", 0);
		this.map.put("ADCI", 0);
		this.map.put("ADII", 0);
		for (int i = 0; i <= allValues.size() - 1; i++) {
			if (Boolean.parseBoolean(list_to_compare.get(i)) && Boolean.parseBoolean(allValues.get(i))) {
				this.map.put("DCI", map.get("DCI") + 1);

			} else if (Boolean.parseBoolean(list_to_compare.get(i)) && !Boolean.parseBoolean(allValues.get(i))) {
				this.map.put("DII", map.get("DII") + 1);
			} else if (!Boolean.parseBoolean(list_to_compare.get(i)) && !Boolean.parseBoolean(allValues.get(i))) {
				this.map.put("ADCI", map.get("ADCI") + 1);
			} else {
				this.map.put("ADII", map.get("ADII") + 1);
			}
		}
		return this.map;
	}

	/**
	 * Este metodo utiliza dois metodos(stringValues e methodIDS) para obter
	 * resultados (se uma determinado metodo tem codeSmell ou nao) numa lista de
	 * strings e posteriormente filtrar estes resultados para apresentar uma lista
	 * de inteiros que possui os method Id's que tem codeSmells.
	 * 
	 * @param r        Regra escolhida pelo utilizador para analise
	 * @param xMetrica String de uma metrica de um determinado codeSmell (neste
	 *                 caso: LOC ou ATFD)
	 * @param yMetrica String de uma metrica de um determinado codeSmell (neste
	 *                 caso: CYCLO ou LAA)
	 * @return devolve uma lista de inteiros dos method id's que tem codeSmells
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Integer> codeSmellIds(Rule r, String xMetrica, String yMetrica) {

		List<Integer> valores = new ArrayList<Integer>();
//		String aux=xMetrica+yMetrica;
		if (xMetrica.equals("LOC") || xMetrica.equals("CYCLO") || yMetrica.equals("LOC") || yMetrica.equals("CYCLO")) {
			xMetrica = "LOC";
			yMetrica = "CYCLO";
		} else {
			xMetrica = "ATFD";
			yMetrica = "LAA";
		}

		List<String> firstColumn = excel.getColumnValues(xMetrica);
		List<String> secondColumn = excel.getColumnValues(yMetrica);

		String s = r.getmetricaXOperator();
		String s2 = r.getmetricaYOperator();
		String aux2 = s + s2;
		this.resultadosBool = stringValues(aux2, r, firstColumn, secondColumn);
		valores = methodIDS(this.resultadosBool);

		return valores;
	}

	/**
	 * Neste metodo e feita a filtragem de uma lista que contem os resultados dos
	 * metodos que sofreram analise anterior, para depois ser apresentado uma lista
	 * de inteiros que ira devolver apenas os method ID's que tem codeSmells.
	 * 
	 * @param values lista de strings que tem resultados, true ou false, se um
	 *               determinado metodo possui codeSmells
	 * @return devolve uma lista de inteiros dos method id's que tem codeSmells
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Integer> methodIDS(List<String> values) {
		List<Integer> methodIds = new ArrayList<Integer>();
		for (int i = 0; i != values.size(); i++) {
			if (values.get(i).equals("true")) {
				methodIds.add(1 + i);
			}
		}
		return methodIds;
	}

	/**
	 * Neste metodo, e feita a filtragem dos method ID's que tem codeSmells, apenas
	 * das ferramentas ( iPlasma ou PMD) que estao descritas no Excel.
	 * 
	 * @param ferramenta String de uma determinada ferramenta que utilizador
	 *                   escolheu para analise
	 * @return devolve uma lista de inteiros dos method id's que tem codeSmells
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public List<Integer> excelTools(String ferramenta) {
		List<String> tool = excel.getColumnValues(ferramenta);
		List<String> methodId = excel.getColumnValues("MethodID");
		List<Integer> results = new ArrayList<Integer>();
		for (int i = 0; i != methodId.size(); i++) {
			if (tool.get(i).equals("true")) {
				int id = (int) Double.parseDouble(methodId.get(i));

				results.add(id);
			}
		}

		return results;
	}

}
