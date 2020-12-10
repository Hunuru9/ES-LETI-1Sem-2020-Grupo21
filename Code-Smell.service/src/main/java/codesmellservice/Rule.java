package codesmellservice;

/**
 * <h1>Rule</h1>
 * A classe Rule é como o nome indica a classe da regra que vai ser utilizada para fazer a deteção de defeitos.
 * 
 * @author Gonçalo Morgado
 * @since 2020-12-10
 */

public class Rule {
	private double metricaX;
	private double metricaY;
	private String nome;
	private String logicalOperator;
	private String codeSmell;
	private String metricaXOperator;
	private String metricaYOperator;
	private String stringMetricaX;
	private String stringMetricaY;

	/**
	 * Este é o construtor da classe Rule que inicia uma regra não definida inicialmente.
	 * @param codeSmell A regra pede um codeSmell ser iniciada.
	 * @param stringMetricaX A regra pede uma string para ser iniciada.
	 * @param stringMetricaY A regra pede uma string para ser iniciada.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public Rule(String codeSmell, String stringMetricaX, String stringMetricaY) {
		this.codeSmell=codeSmell;
		this.stringMetricaX=stringMetricaX;
		this.stringMetricaY=stringMetricaY;
		nome="";
		metricaX=0.0;
		metricaY=0.0;
		metricaXOperator="";
		metricaYOperator="";
		logicalOperator="";
	}

	/**
	 * Método get para dar a stringMetricaX da regra.
	 * @return retorna uma string correspondente à stringMetricaX da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getMetricaXString() {
		return stringMetricaX;
	}

	/**
	 * Método get para dar a stringMetricaY da regra.
	 * @return retorna uma string correspondente à stringMetricaY da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getMetricaYString() {
		return stringMetricaY;
	}

	/**
	 * Método set para mudar a stringMetricaX da regra.
	 * @param s O método pede uma string para trocar o valor de stringMetricaX.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setMetricaXString(String s) {
		stringMetricaX=s;
	}

	/**
	 * Método set para mudar a stringMetricaY da regra.
	 * @param s O método pede uma string para trocar o valor de stringMetricaY.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setMetricaYString(String s) {
		stringMetricaY=s;
	}

	/**
	 * Método set para mudar o MetricaXOperator da regra.
	 * @param s O método pede uma string para trocar o valor de MetricaXOperator.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setmetricaXOperator(String operator) {
		metricaXOperator=operator;
	}

	/**
	 * Método set para mudar o MetricaYOperator da regra.
	 * @param s O método pede uma string para trocar o valor de MetricaYOperator.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setmetricaYOperator(String operator) {
		metricaYOperator=operator;
	}

	/**
	 * Método get para dar o MetricaXOperator da regra.
	 * @result retorna uma string correspondente à MetricaXOperator da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getmetricaXOperator() {
		return metricaXOperator;
	}

	/**
	 * Método get para dar o MetricaYOperator da regra.
	 * @result retorna uma string correspondente à MetricaYOperator da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getmetricaYOperator() {
		return metricaYOperator;
	}

	/**
	 * Método set para mudar o codeSmell da regra.
	 * @param s O método pede uma string para trocar o valor de codeSmell.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setCodeSmell(String smell) {
		codeSmell=smell;
	}

	/**
	 * Método get para dar o codeSmell da regra.
	 * @result O método dá uma string correspondente ao codesmell da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public String getCodeSmell() {
		return codeSmell;
	}

	/**
	 * Método get para dar o nome da regra.
	 * @result O método dá uma string correspondente ao nome da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public String getNomeRegra() {
		return nome;
	}

	/**
	 * Método set para mudar o nome da regra.
	 * @param s O método pede uma string para trocar o valor nome.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public void setNomeRegra(String s) {
		nome=s;
	}

	/**
	 * Método get para dar a metricaX da regra.
	 * @result O método dá um double correspondente à metricaX da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public double getMetricaX() {
		return metricaX;
	}

	/**
	 * Método set para mudar a metricaX da regra.
	 * @param valor O método pede um double para trocar o valor da metricaX.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public void setMetricaX(double valor) {
		metricaX=valor;
	}

	/**
	 * Método get para dar a metricaY da regra.
	 * @result O método dá um double correspondente à metricaY da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public double getMetricaY() {
		return metricaY;
	}

	/**
	 * Método set para mudar a metricaY da regra.
	 * @param valor O método pede um double para trocar o valor da metricaY.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public void setMetricaY(double valor) {
		metricaY=valor;
	}

	/**
	 * Método get para dar a metricaX da regra.
	 * @result O método dá uma string correspondente ao logicalOperator da regra.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public String getLogicalOperator() {
		return logicalOperator;
	}

	/**
	 * Método set para mudar a logicalOperator da regra.
	 * @param valor O método pede um double para trocar o valor do logicalOperator.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public void setLogicalOperator(String s) {
		logicalOperator=s;
	}

	/**
	 * Método para descrever a regra com as suas informações numa String
	 * @result O método retorna uma string com todos os parametros da regra devidamente identificados.
	 * 
	 * @author Gonçalo Morgado
	 * @since 2020-12-10
	 */
	public String toString() {
		if(logicalOperator!="") {
			return "Rule: " + nome + " | " + stringMetricaX + metricaXOperator + metricaX + " " + logicalOperator + " " + stringMetricaY + metricaYOperator + metricaY + " | " + "CodeSmell: " + codeSmell;
		}else {
			if(metricaX==0.0 && metricaY!=0.0) {
				return "Rule: " + nome + " | " + stringMetricaY + metricaYOperator + metricaY + " | " + "CodeSmell: " + codeSmell;
			}else {
				return "Rule: " + nome + " | " + stringMetricaX + metricaXOperator + metricaX + " | " + "CodeSmell: " + codeSmell;
			}
		}
	}


}
