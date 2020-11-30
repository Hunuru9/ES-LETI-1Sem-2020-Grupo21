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
	
	//Retorna os id's dos metodos que não tenham code smell's apos criar uma lista de string com resultados 
	/*public List<Integer> codeSmellIds(Rule r, String xMetrica, String yMetrica) {
		List<String> resultados=new ArrayList<String>();
		List<Integer> valores=new ArrayList<Integer>();
		
		double [] firstColumn=excel.getMetricValues(xMetrica);

		double [] secondColumn=excel.getMetricValues(yMetrica);
	
		//double [] methodId=excel.getMetricValues("MethodID");

		String s=r.getmetricaXOperator();
		String s2=r.getmetricaYOperator();
		String aux=s+s2;
		System.out.println(aux);
		
		resultados=excel.stringValues(aux, r, firstColumn, secondColumn);
		valores=methodIDS(resultados);
		
		return valores;
	}
	
	//Retorna os id's dos metodos que nao tenham code smells
	public List<Integer> methodIDS(List<String> values)  {
		List<Integer> methodIds=new ArrayList<Integer>();
		for(int i=0; i!=values.size(); i++)  {
			if(values.get(i).equals("false")) {
				methodIds.add(1+i);
			}
		}
		return methodIds;
		
	}
	
	//Retorna o número de defeitos (i.e. dci/dii/adci/adii)
	public String [] defects(List<String> toolValues, String[] codeSmell) {
		String [] defects=new String[4];
		int dci = 0, dii = 0, adci = 0, adii = 0;
		String aux="";
		for(int i=0; i!=toolValues.size(); i++) {
			aux=toolValues.get(i) + "/" + codeSmell[i];
			switch(aux) {
			case "true/true":
				dci++;
				aux="";
				break;
			
			case "true/false":
				dii++;
				aux="";
				break;
			
			case "false/false":
				adci++;
				aux="";
				break;
				
			case "false/true":
				adii++;
				aux="";
				break;
			}
		}
		String dciString="DCI: " + dci; defects[0]=dciString;
		String diiString="DII: " + dii; defects[1]=diiString;
		String adciString="ADCI: " + adci; defects[2]=adciString;
		String adiiString="ADII: " + adii; defects[3]=adiiString;
		
		return defects;
	}*/
}
