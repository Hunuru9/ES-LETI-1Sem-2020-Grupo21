package codesmellservice;

public class Rule {
	private double metricaX;
	private double metricaY;
	private String nome;
	private String logicalOperator;
	private String codeSmell;
	private String metricaXOperator;
	private String metricaYOperator;
	
	public Rule(String codeSmell) {
		this.codeSmell=codeSmell;
		nome="";
		metricaX=0.0;
		metricaY=0.0;
		metricaXOperator="";
		metricaYOperator="";
		logicalOperator="";
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
		if(codeSmell.equals("is_long_method")) {
			if(logicalOperator!="") {
				return "Rule: " + nome + " | " + "LOC" + metricaXOperator + metricaX + " " + logicalOperator + " CYCLO" + metricaYOperator + metricaY;
			} else {
				if(metricaX!=0.0 && metricaY==0.0) {
					return "Rule: " + nome + " | " + "LOC" + metricaXOperator + metricaX;
				}else {
					return "Rule: " + nome + " | " + "CYCLO" + metricaYOperator + metricaY;
				}
			}
		}else {
			if(logicalOperator!="") {
				return "Rule: " + nome + " | " + "ATFD" + metricaXOperator + metricaX + " "  + logicalOperator +  " LAA" + metricaYOperator + metricaY;
			}else {
				if(metricaX!=0.0 && metricaY==0.0) {
					return "Rule: " + nome + " | " + "ATFD" + metricaXOperator + metricaX;
				}else {
					return "Rule: " + nome + " | " + "LAA" + metricaYOperator + metricaY;
				}
			}
		}
	}
	
}
