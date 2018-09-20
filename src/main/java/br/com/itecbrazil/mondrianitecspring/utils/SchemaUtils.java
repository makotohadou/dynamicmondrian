package br.com.itecbrazil.mondrianitecspring.utils;

public class SchemaUtils {
	
	public static String removeSchemaTags(String schema) {
		
		String[] components = schema.split("<Schema name=\"Vendas Produtos\">");
		components = components[1].split("</Schema>");
		return components[0];
		
	}
	

}
