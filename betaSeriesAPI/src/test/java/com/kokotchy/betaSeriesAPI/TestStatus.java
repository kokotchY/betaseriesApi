package com.kokotchy.betaSeriesAPI;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.StatusInfo;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class TestStatus extends TestCase {

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Status statusJson;

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Status statusXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		statusJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getStatus();
		statusXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getStatus();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriejson/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriexml/");
	}

	/**
	 * TODO Fill it
	 */
	public void testStatus() {
		assertEquals(statusXml.getStatus(), statusJson.getStatus());
	}

	/**
	 * TODO Fill it
	 */
	public void testStatusXml() {
		StatusInfo status = statusXml.getStatus();
		StatusInfo status2 = statusXml.getStatus();
		System.out.println(status.hashCode());
		System.out.println(status2.hashCode());
		assertEquals(status, status2);
	}
}
