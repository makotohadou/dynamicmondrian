package br.com.itecbrazil.mondrianitecspring.utils;

import java.io.InputStream;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;

import mondrian.olap.Util;
import mondrian.spi.impl.FilterDynamicSchemaProcessor;

public class DynamicSchemaProcessor extends FilterDynamicSchemaProcessor {

	//@Autowired
	//private QueryContext queryContext;
	

	@Override
	protected String filter(final String schemaUrl, final Util.PropertyList connectInfo, final InputStream stream)
			throws java.lang.Exception {
		
		ApplicationContext appCtx = ApplicationContextUtils
			    .getApplicationContext();
		QueryContext queryContext = (QueryContext) appCtx.getBean("queryContext");
		
		String originalSchema = super.filter(schemaUrl, connectInfo, stream);
		originalSchema = SchemaUtils.removeSchemaTags(originalSchema);
		String modifiedSchema = "<Schema name=\"Vendas Produtos\">";
		for (HashMap<String,Object> filter : queryContext.getUserFilterList().values() ) {
			String customCube = "";
			customCube = originalSchema.replace("%DATAINICIAL%","'"+filter.get("dataInicial")+"'")
					.replace("%DATAFINAL%","'"+filter.get("dataFinal")+"'")
					.replace("%CUBENAME%", filter.get("report")+"-"+filter.get("user"))
					.replace("%VIEWNAME%", "VIEW"+filter.get("user"));
			modifiedSchema += customCube;
		}
		modifiedSchema+="</Schema>";
		return modifiedSchema;
		
	}

}