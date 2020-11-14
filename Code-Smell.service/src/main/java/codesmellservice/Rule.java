package codesmellservice;

public class Rule {
	private Double metricaX;
	private Double metricaY;
	private String nome;
	private String logicalOperator;
	private String codeSmell;
	
	public Rule(String codeSmell) {
		this.codeSmell=codeSmell;
		nome="";
		metricaX=0.0;
		metricaY=0.0;
		logicalOperator="";
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
		
	public Double getMetricaX() {
		return metricaX;
	}
	
	public void setMetricaX(Double valor) {
		metricaX=valor;
	}

	public Double getMetricaY() {
		return metricaY;
	}

	public void setMetricaY(Double valor) {
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
				return "Rule: " + nome + " | " + "LOC: " + metricaX + " " + logicalOperator + " CYCLO: " + metricaY;
			} else {
				if(metricaX!=0.0 && metricaY==0.0) {
					return "Rule: " + nome + " | " + "LOC: " + metricaX;
				}else {
					return "Rule: " + nome + " | " + "CYCLO: " + metricaY;
				}
			}
		}else {
			if(logicalOperator!="") {
				return "Rule: " + nome + " | " + "ATFD: " + metricaX + " "  + logicalOperator +  " LAA: " + metricaY;
			}else {
				if(metricaX!=0.0 && metricaY==0.0) {
					return "Rule: " + nome + " | " + "ATFD: " + metricaX;
				}else {
					return "Rule: " + nome + " | " + "LAA: " + metricaY;
				}
			}
		}
	}
	
}
