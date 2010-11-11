package com.kokotchy.betaSeriesAPI;

import java.util.List;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class TestShows extends TestCase {

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Shows showsJson;

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Shows showsXml;

	/**
	 *
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		showsJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getShows();
		showsXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getShows();
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
	public void testSearch() {
		List<Show> searchJson = showsJson.search("dexter");
		List<Show> searchXml = showsXml.search("dexter");
		assertEquals(searchXml.size(), searchJson.size());
		assertEquals(searchXml, searchJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsDisplayAll() {
		List<Show> displayAllJson = showsJson.displayAll();
		List<Show> displayAllXml = showsXml.displayAll();
		assertEquals(displayAllXml.size(), displayAllJson.size());
		assertTrue(displayAllJson.equals(displayAllXml));
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsDisplayDexter() {
		Show dexterJson = showsJson.display("dexter");
		Show dexterXml = showsXml.display("dexter");
		assertEquals(dexterXml, dexterJson);
	}
}
