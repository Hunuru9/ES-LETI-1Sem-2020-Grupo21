package codesmellservice;

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
	
	public String getMetricaXString() {
		return stringMetricaX;
	}
	
	public String getMetricaYString() {
		return stringMetricaY;
	}
	
	public void setMetricaXString(String s) {
		stringMetricaX=s;
	}
	
	public void setMetricaYString(String s) {
		stringMetricaY=s;
	}
	public void setmetricaXOperator(String operator) {
		metricaXOperator=operator;
	}
	
	public void setmetricaYOperator(String operator) {
		metricaYOperator=operator;
	}
	
	public String getmetricaXOperator() {
		return metricaXOperator;
	}
	
	public String getmetricaYOperator() {
		return metricaYOperator;
	}
	
	public void setCodeSmell(String smell) {
		codeSmell=smell;
	}
	
	public String getCodeSmell() {
		return codeSmell;
	}
	
	public String getNomeRegra() {
		return nome;
	}
	
	public void setNomeRegra(String s) {
		nome=s;
	}
		
	public double getMetricaX() {
		return metricaX;
	}
	
	public void setMetricaX(double valor) {
		metricaX=valor;
	}

	public double getMetricaY() {
		return metricaY;
	}

	public void setMetricaY(double valor) {
		metricaY=valor;
	}
		
	public String getLogicalOperator() {
		return logicalOperator;
	}
	
	public void setLogicalOperator(String s) {
		logicalOperator=s;
	}
	
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
