package codesmellservice;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

	private List<Rule> lista;
	private ExcelReader excel;

	public RuleSet(ExcelReader excel) {
		this.excel=excel;
		lista=new ArrayList<Rule>();
	}

	public void addRegra1(Rule r) {
		lista.add(r);
	}

	public List<Rule> getRegras() {
		return lista;
	}
	
	
	public List<Integer> codeSmellIds(Rule r, String xMetrica, String yMetrica) {
		List<String> resultados=new ArrayList<String>();
		List<Integer> valores=new ArrayList<Integer>();
		
		double [] firstColumn=excel.getMetricValues(xMetrica);
		double [] secondColumn=excel.getMetricValues(yMetrica);

		String s=r.getmetricaXOperator();
		String s2=r.getmetricaYOperator();
		String aux=s+s2;
		System.out.println(aux);
		
		resultados=stringValues(aux, r, firstColumn, secondColumn);
		valores=methodIDS(resultados);
		
		return valores;
	}
	
	public List<Integer> methodIDS(List<String> values)  {
		List<Integer> methodIds=new ArrayList<Integer>();
		for(int i=0; i!=values.size(); i++)  {
			if(values.get(i).equals("true")) {
				methodIds.add(1+i);
			}
		}
		return methodIds;
	}
	
	public List<String> stringValues(String aux, Rule r, double [] firstColumn, double [] secondColumn) {
		List<String> resultados=new ArrayList<String>();
		System.out.println(r.getLogicalOperator());
		switch(r.getLogicalOperator()) {
		case "AND":
			for(int i=0; i!=firstColumn.length; i++) {
				switch(aux) {
				case ">>":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(firstColumn[i]>r.getMetricaX() && secondColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]>r.getMetricaY() && secondColumn[i]>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]>r.getMetricaX() && firstColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]>r.getMetricaX() && secondColumn[i]>r.getMetricaY()) {
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
							if(firstColumn[i]>r.getMetricaX() && secondColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]>r.getMetricaY() && secondColumn[i]<r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]>r.getMetricaX() && firstColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]>r.getMetricaX() && secondColumn[i]<r.getMetricaY()) {
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
							if(firstColumn[i]<r.getMetricaX() && secondColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]<r.getMetricaY() && secondColumn[i]<r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]<r.getMetricaX() && firstColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]<r.getMetricaX() && secondColumn[i]<r.getMetricaY()) {
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
							if(firstColumn[i]<r.getMetricaX() && secondColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]<r.getMetricaY() && secondColumn[i]>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]<r.getMetricaX() && firstColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]<r.getMetricaX() && secondColumn[i]>r.getMetricaY()) {
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
			for(int i=0; i!=firstColumn.length; i++) {
				switch(aux) {
				case ">>":
					if(!r.getMetricaXString().equals(r.getMetricaYString())) {
						if(r.getMetricaXString().equals("LOC") && r.getMetricaYString().equals("CYCLO") || 
								r.getMetricaXString().equals("ATFD") && r.getMetricaYString().equals("LAA")) {
							if(firstColumn[i]>r.getMetricaX() && secondColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]>r.getMetricaY() && secondColumn[i]>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]>r.getMetricaX() || firstColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]>r.getMetricaX() || secondColumn[i]>r.getMetricaY()) {
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
							if(firstColumn[i]>r.getMetricaX() && secondColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]>r.getMetricaY() && secondColumn[i]<r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]>r.getMetricaX() || firstColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]>r.getMetricaX() || secondColumn[i]<r.getMetricaY()) {
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
							if(firstColumn[i]<r.getMetricaX() && secondColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]<r.getMetricaY() && secondColumn[i]<r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]<r.getMetricaX() || firstColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]<r.getMetricaX() || secondColumn[i]<r.getMetricaY()) {
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
							if(firstColumn[i]<r.getMetricaX() && secondColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]<r.getMetricaY() && secondColumn[i]>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
					}else {
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]<r.getMetricaX() || firstColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]<r.getMetricaX() || secondColumn[i]>r.getMetricaY()) {
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
				for(int i=0; i!=secondColumn.length; i++) {
					switch(aux) {
					case ">":
						if(r.getMetricaYString().equals("CYCLO") || r.getMetricaYString().equals("LAA")) {
							if(secondColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]>r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
						break;
					case "<":
						if(r.getMetricaYString().equals("CYCLO") || r.getMetricaYString().equals("LAA")) {
							if(secondColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(firstColumn[i]<r.getMetricaY()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
						break;
					}
				}	
			}else {
				for(int i=0; i!=firstColumn.length; i++) {
					switch(aux) {
					case ">":
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]>r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}
						break;
					case"<":
						if(r.getMetricaXString().equals("LOC") || r.getMetricaXString().equals("ATFD")) {
							if(firstColumn[i]<r.getMetricaX()) {
								resultados.add("true");
							}else {
								resultados.add("false");
							}
						}else {
							if(secondColumn[i]<r.getMetricaX()) {
								resultados.add("true");
							}else {
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
	
}
