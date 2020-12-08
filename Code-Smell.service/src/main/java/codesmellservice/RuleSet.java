package codesmellservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RuleSet {

	private List<Rule> lista;
	private ExcelReader excel;
	private HashMap<String, Integer> map;

	public RuleSet(ExcelReader excel) {
		this.excel=excel;
		lista=new ArrayList<Rule>();
		this.map = new HashMap<>(); 
	}

	public void addRegra1(Rule r) {
		lista.add(r);
	}

	public List<Rule> getRegras() {
		return lista;
	}
	
	public List<String> stringValues(String aux, Rule r, List<String> firstColumn, List<String> secondColumn) {
		List<String> resultados=new ArrayList<String>();
		System.out.println(r.getLogicalOperator());
		switch(r.getLogicalOperator()) {
		case "AND":
			for(int i=0; i!=firstColumn.size(); i++) {
				switch(aux) {
				case ">>":
					if(Double.parseDouble(firstColumn.get(i))>=r.getMetricaX() && Double.parseDouble(secondColumn.get(i))>=r.getMetricaY()) {
						resultados.add("true");
					}else {
						resultados.add("false");
					}
					break;
				case "><":
					if(Double.parseDouble(firstColumn.get(i))>=r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<=r.getMetricaY()) {
						resultados.add("true");
					}else {
						resultados.add("false");
					}
					break;
				case "<<":
					System.out.println(Double.parseDouble(secondColumn.get(i)) + " " + r.getMetricaY());
					if(Double.parseDouble(firstColumn.get(i))<=r.getMetricaX() && Double.parseDouble(secondColumn.get(i))<=r.getMetricaY()) {
						resultados.add("true");
					}else {
						resultados.add("false");
					}
					break;
				case "<>":
					if(Double.parseDouble(firstColumn.get(i))<=r.getMetricaX() && Double.parseDouble(secondColumn.get(i))>=r.getMetricaY()) {
						resultados.add("true");
					}else {
						resultados.add("false");
					}
					break;
				}
			}
			break;
		case "OR":
			for(int i=0; i!=firstColumn.size(); i++) {
				switch(aux) {
				case ">>":
					if(Double.parseDouble(firstColumn.get(i))>=r.getMetricaX() || Double.parseDouble(secondColumn.get(i))>=r.getMetricaY()) {
						resultados.add("true");
					} else {
						resultados.add("false");
					}
					break;
				case "><":
					if(Double.parseDouble(firstColumn.get(i))>=r.getMetricaX() || Double.parseDouble(secondColumn.get(i))<=r.getMetricaY()) {
						resultados.add("true");
					}else {
						resultados.add("false");
					}
					break;
				case "<<":
					if(Double.parseDouble(firstColumn.get(i))<=r.getMetricaX() ||  Double.parseDouble(secondColumn.get(i))<=r.getMetricaY()) {
						resultados.add("true");
					}else {
						resultados.add("false");
					}
					break;
				case "<>":
					if(Double.parseDouble(firstColumn.get(i))<=r.getMetricaX() || Double.parseDouble(secondColumn.get(i))>=r.getMetricaY()) {	
						resultados.add("true");
					}else {
						resultados.add("false");
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
						if(Double.parseDouble(firstColumn.get(i))>=r.getMetricaY()) {
							resultados.add("true");
						}else {
							resultados.add("false");
						}
						break;
					case "<":
						if(Double.parseDouble(secondColumn.get(i))<=r.getMetricaY()) {
						
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
						if(Double.parseDouble(firstColumn.get(i))>=r.getMetricaX()) {	
							resultados.add("true");
						}else {
							resultados.add("false");
						}
						break;
					case"<":
						if(Double.parseDouble(firstColumn.get(i))<=r.getMetricaX()) {						
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

	public HashMap<String,Integer> quality_indicators(String tool, List<String> resultados){
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
		for(int i = 0; i <= allValues.size()-1; i++) {
			if(Boolean.parseBoolean(list_to_compare.get(i)) &&  Boolean.parseBoolean(allValues.get(i))) {
				if(map.containsKey("DCI")) {
					this.map.put("DCI", map.get("DCI") + 1);
				}else {
					this.map.put("DCI", 1);
				}
			}else 
				if (Boolean.parseBoolean(list_to_compare.get(i)) && !Boolean.parseBoolean(allValues.get(i)) ){
					if(map.containsKey("DII")) {
						this.map.put("DII", map.get("DII") + 1);
					}else {
						this.map.put("DII", 1);
					}
				}
				else
					if (!Boolean.parseBoolean(list_to_compare.get(i)) && !Boolean.parseBoolean(allValues.get(i)) ){
						if(map.containsKey("ADCI")) {
							this.map.put("ADCI", map.get("ADCI") + 1);
						}else {
							this.map.put("ADCI", 1);
						}
					}
					else {
						if(map.containsKey("ADII")) {
							this.map.put("ADII", map.get("ADII") + 1);
						}else {
							this.map.put("ADII", 1);
						}
					}
		}
		System.out.println(this.map.toString());
		return this.map;
	}
	
	
	
}
