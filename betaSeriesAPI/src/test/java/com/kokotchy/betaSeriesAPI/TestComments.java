package com.kokotchy.betaSeriesAPI;

import junit.framework.TestCase;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class TestComments extends TestCase {

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Comments commentsJson;

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Comments commentsXml;

	/**
	 *
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		commentsJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getComments();
		commentsXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getComments();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriejson/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriexml/");
	}

}
