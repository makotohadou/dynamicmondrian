package br.com.itecbrazil.mondrianitecspring.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.itecbrazil.mondrianitecspring.utils.QueryContext;
import mondrian.olap.Connection;
import mondrian.olap.DriverManager;
import mondrian.olap.Query;
import mondrian.olap.Result;

@Controller
public class MondrianRest {
	
	@Autowired
	private QueryContext queryContext;

	@RequestMapping("/query")
	public String executeQuery() {
		Connection connection = DriverManager.getConnection(
				"Provider=mondrian;"+
						"Jdbc=jdbc:sqlserver://morifarma.bd.itecdomain.com.br:49926;"+
						"DatabaseName=MorifarmaProducao;"+
						"JdbcDrivers=com.microsoft.sqlserver.jdbc.SQLServerDriver;"+
						"JdbcUser=usr_itec.suporte;"+
						"JdbcPassword=ey_JJ23tK@#;"+
						"PoolNeeded=false;"+
						"Catalog=file:Itec-Schema.xml;"+
						"DynamicSchemaProcessor=br.com.itecbrazil.mondrianitecspring.utils.DynamicSchemaProcessor;",
						null);
		Query query = connection.parseQuery(
				"with member [Measures].[CountAllMembers] as Count([Data Movimentacao].allmembers) select NON EMPTY "
				+ "{[Measures].[CountAllMembers]} ON columns FROM [Vendas Produtos]");
		Result result = connection.execute(query);
		StringWriter out = new StringWriter();
		result.print(new PrintWriter(out)); 
		String resultado = out.toString();
		System.out.println(resultado);
		return result.toString();

	}
	
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	@ResponseBody
	public void ApplyFilter(@RequestBody HashMap<String, Object> filters) {
		
		//String dataInicial = (String) filters.get("dataInicial");
		//String dataFinal   = (String) filters.get("dataFinal");
		//queryContext.setDataFinal(dataFinal);
		//queryContext.setDataInicial(dataInicial);
		
		queryContext.setUserFilter(filters);
		
		
	}

}
