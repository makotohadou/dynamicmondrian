package br.com.itecbrazil.mondrianitecspring.utils;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
//@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QueryContext {


	private HashMap<String,HashMap<String,Object>> userFilterList;

	public void setUserFilter(HashMap<String,Object> userFilter) {
		if (userFilterList == null) {
			userFilterList = new HashMap<String,HashMap<String,Object>>();
		}
		removeOldEntries();
		userFilter.put("dateInsertion", new Date());
		userFilterList.put((String) userFilter.get("user"), userFilter);

	}

	public void setUserFilterList(HashMap<String,HashMap<String, Object>> userFilterList) {
		this.userFilterList = userFilterList;
	}

	private void removeOldEntries() {
		for (HashMap<String,Object> userFilter : userFilterList.values()) {
			Date dateInsertion = (Date) userFilter.get("dateInsertion");
			long diff = dateInsertion.getTime() - new Date().getTime();
			//Se já tem 1 hora que o filtro foi inserido
			if ( diff > 1000*60*60) {
				userFilterList.remove(userFilter.get("user"));
			}
		}
	}


	public HashMap<String,HashMap<String,Object>> getUserFilterList(){
		return this.userFilterList;
	}



	private String dataInicial;
	private String dataFinal;

	public QueryContext() {

	}

	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

}
