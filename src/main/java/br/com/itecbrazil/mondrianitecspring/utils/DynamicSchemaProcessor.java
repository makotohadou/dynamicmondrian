package br.com.itecbrazil.mondrianitecspring.utils;

import java.io.InputStream;

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
		String modifiedSchema = originalSchema.replace("%DATAINICIAL%","'"+queryContext.getDataInicial()+"'").
				replace("%DATAFINAL%","'"+queryContext.getDataFinal()+"'");
		return modifiedSchema;
		
	}

}