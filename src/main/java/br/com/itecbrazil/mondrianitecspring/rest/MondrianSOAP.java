package br.com.itecbrazil.mondrianitecspring.rest;

import mondrian.server.DynamicContentFinder;
import mondrian.server.RepositoryContentFinder;
import mondrian.xmla.impl.MondrianXmlaServlet;

import java.util.HashMap;
import java.util.Map;


public class MondrianSOAP extends MondrianXmlaServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * A map of datasources definitions to {@link DynamicContentFinder}
	 * instances.
	 */
	private final Map<String, DynamicContentFinder> finders =
			new HashMap<String, DynamicContentFinder>();

	@Override
	protected RepositoryContentFinder makeContentFinder(String dataSources) {
		if (!finders.containsKey(dataSources)) {
			finders.put(dataSources, new DynamicContentFinder(dataSources));
		}
		return finders.get(dataSources);
	}
	@Override
	public void destroy() {
		for (DynamicContentFinder finder : finders.values()) {
			finder.shutdown();
		}
		super.destroy();
	}

}
