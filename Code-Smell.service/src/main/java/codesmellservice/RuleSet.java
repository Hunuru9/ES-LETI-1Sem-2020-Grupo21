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
	
}
