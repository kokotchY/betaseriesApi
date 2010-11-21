package com.kokotchy.betaSeriesAPI;

import junit.framework.TestCase;

/**
 * Test comments api
 * 
 * @author kokotchy
 */
public class TestComments extends TestCase {

	/**
	 * Comments api for json
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Comments commentsJson;

	/**
	 * Comments api for xml
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Comments commentsXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String userDir = System.getProperty("user.dir");
		String key = Utils.getApiKey(userDir);
		com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi.setApiKey(key);
		com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi.setApiKey(key);
		commentsJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getComments();
		commentsXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getComments();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(userDir + "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(userDir + "/src/test/resources/xml/");
	}

	/**
	 * TODO Fill it
	 */
	public void testTrivial() {
		assertTrue(true);
	}
}
