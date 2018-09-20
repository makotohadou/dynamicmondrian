package br.com.itecbrazil.mondrianitecspring.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QueryContext {
	
	
	
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
