package com.kokotchy.betaSeriesAPI;

import java.util.Map.Entry;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.api.IStatus;
import com.kokotchy.betaSeriesAPI.model.StatusInfo;
import com.kokotchy.betaSeriesAPI.model.Version;

/**
 * Test status api
 * 
 * @author kokotchy
 */
public class TestStatus extends TestCase {

	/**
	 * Status api for json
	 */
	private IStatus statusJson;

	/**
	 * Status api for xml
	 */
	private IStatus statusXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String userDir = System.getProperty("user.dir");
		String key = Utils.getApiKey(userDir);
		com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi.setApiKey(key);
		com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi.setApiKey(key);
		statusJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getStatus();
		statusXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getStatus();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(userDir + "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(userDir + "/src/test/resources/xml/");
	}

	/**
	 * Test status
	 */
	public void testStatus() {
		StatusInfo statusInfoXml = statusXml.getStatus();
		StatusInfo statusInfoJson = statusJson.getStatus();
		assertEquals(statusInfoXml.getFiles().size(), statusInfoJson.getFiles().size());
		assertEquals(statusInfoXml.getFiles(), statusInfoJson.getFiles());
		assertEquals(statusInfoXml.getVersions().size(), statusInfoJson.getVersions().size());
		int nb = 0;
		for (Entry<Integer, Version> entry : statusInfoXml.getVersions().entrySet()) {
			for (Entry<Integer, Version> entry2 : statusInfoXml.getVersions().entrySet()) {
				if (entry2.getKey().equals(entry.getKey())) {
					if (entry.getValue().equals(entry2.getValue())) {
						assertTrue(true);
						nb++;
					} else {
						fail("Not equals " + entry + "," + entry2);
					}
				}
			}
		}
		assertEquals(statusInfoJson.getVersions().size(), nb);
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
