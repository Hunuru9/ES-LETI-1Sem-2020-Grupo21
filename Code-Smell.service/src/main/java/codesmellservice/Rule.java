package codesmellservice;

/**
 * 
 * A classe Rule e como o nome indica a classe da regra que vai ser utilizada para fazer a detecao de defeitos.
 * 
 * @author Goncalo Morgado
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
	 * Este e o construtor da classe Rule que inicia uma regra nao definida inicialmente.
	 * @param codeSmell A regra pede um codeSmell ser iniciada.
	 * @param stringMetricaX A regra pede uma string para ser iniciada.
	 * @param stringMetricaY A regra pede uma string para ser iniciada.
	 * 
	 * @author Goncalo Morgado
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
	 * Metodo get para dar a stringMetricaX da regra.
	 * @return retorna uma string correspondente a stringMetricaX da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getMetricaXString() {
		return stringMetricaX;
	}

	/**
	 * Metodo get para dar a stringMetricaY da regra.
	 * @return retorna uma string correspondente a stringMetricaY da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getMetricaYString() {
		return stringMetricaY;
	}

	/**
	 * Metodo set para mudar a stringMetricaX da regra.
	 * @param s O metodo pede uma string para trocar o valor de stringMetricaX.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setMetricaXString(String s) {
		stringMetricaX=s;
	}

	/**
	 * Metodo set para mudar a stringMetricaY da regra.
	 * 
	 * @param s O metodo pede uma string para trocar o valor de stringMetricaY.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setMetricaYString(String s) {
		stringMetricaY=s;
	}

	/**
	 * Metodo set para mudar o MetricaXOperator da regra.
	 * @param operator O metodo pede uma string para trocar o valor de MetricaXOperator.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setmetricaXOperator(String operator) {
		metricaXOperator=operator;
	}

	/**
	 * Metodo set para mudar o MetricaYOperator da regra.
	 * @param operator O metodo pede uma string para trocar o valor de MetricaYOperator.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setmetricaYOperator(String operator) {
		metricaYOperator=operator;
	}

	/**
	 * Metodo get para dar o MetricaXOperator da regra.
	 * @return retorna uma string correspondente a MetricaXOperator da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getmetricaXOperator() {
		return metricaXOperator;
	}

	/**
	 * Metodo get para dar o MetricaYOperator da regra.
	 * @return retorna uma string correspondente a MetricaYOperator da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public String getmetricaYOperator() {
		return metricaYOperator;
	}

	/**
	 * Metodo set para mudar o codeSmell da regra.
	 * @param smell O metodo pede uma string para trocar o valor de codeSmell.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 * 
	 */
	public void setCodeSmell(String smell) {
		codeSmell=smell;
	}

	/**
	 * Metodo get para dar o codeSmell da regra.
	 * @return O metodo da uma string correspondente ao codesmell da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public String getCodeSmell() {
		return codeSmell;
	}

	/**
	 * Metodo get para dar o nome da regra.
	 * @return O metodo da uma string correspondente ao nome da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public String getNomeRegra() {
		return nome;
	}

	/**
	 * Metodo set para mudar o nome da regra.
	 * @param s O metodo pede uma string para trocar o valor nome.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public void setNomeRegra(String s) {
		nome=s;
	}

	/**
	 * Metodo get para dar a metricaX da regra.
	 * @return O metodo da um double correspondente a metricaX da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public double getMetricaX() {
		return metricaX;
	}

	/**
	 * Metodo set para mudar a metricaX da regra.
	 * @param valor O metodo pede um double para trocar o valor da metricaX.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public void setMetricaX(double valor) {
		metricaX=valor;
	}

	/**
	 * Metodo get para dar a metricaY da regra.
	 * @return O metodo da um double correspondente a metricaY da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public double getMetricaY() {
		return metricaY;
	}

	/**
	 * Metodo set para mudar a metricaY da regra.
	 * @param valor O metodo pede um double para trocar o valor da metricaY.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public void setMetricaY(double valor) {
		metricaY=valor;
	}

	/**
	 * Metodo get para dar a metricaX da regra.
	 * @return O metodo da uma string correspondente ao logicalOperator da regra.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public String getLogicalOperator() {
		return logicalOperator;
	}

	/**
	 * Metodo set para mudar a logicalOperator da regra.
	 * @param logical_operator O metodo pede um double para trocar o valor do logicalOperator.
	 * 
	 * @author Goncalo Morgado
	 * @since 2020-12-10
	 */
	public void setLogicalOperator(String logical_operator) {
		logicalOperator=logical_operator;
	}

	/**
	 * Metodo para descrever a regra com as suas informacoes numa String
	 * @return O metodo retorna uma string com todos os parametros da regra devidamente identificados.
	 * 
	 * @author Goncalo Morgado
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
