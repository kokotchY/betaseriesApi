package com.kokotchy.betaSeriesAPI;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.StatusInfo;

/**
 * Test status api
 * 
 * @author kokotchy
 */
public class TestStatus extends TestCase {

	/**
	 * Status api for json
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Status statusJson;

	/**
	 * Status api for xml
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
				+ "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/xml/");
	}

	/**
	 * Test status
	 */
	public void testStatus() {
		StatusInfo statusInfoXml = statusXml.getStatus();
		StatusInfo statusInfoJson = statusJson.getStatus();
		assertEquals(statusInfoXml.getFiles(), statusInfoJson.getFiles());
		assertEquals(statusInfoXml.getVersions(), statusInfoJson.getVersions());
		assertEquals(statusInfoXml, statusInfoJson);
	}

	/**
	 * Test status for two json
	 */
	public void testStatusEqualsJson() {
		StatusInfo status = statusJson.getStatus();
		StatusInfo status2 = statusJson.getStatus();
		assertEquals(status, status2);
	}

	/**
	 * Test status for two xml
	 */
	public void testStatusEqualsXml() {
		StatusInfo status = statusXml.getStatus();
		StatusInfo status2 = statusXml.getStatus();
		assertEquals(status, status2);
	}
}
