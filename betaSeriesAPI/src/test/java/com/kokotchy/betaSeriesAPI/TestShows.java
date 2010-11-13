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
	public void testShowsDisplayAll() {
		List<Show> displayAllJson = showsJson.displayAll();
		List<Show> displayAllXml = showsXml.displayAll();
		assertEquals(displayAllXml.size(), displayAllJson.size());
		assertTrue(displayAllJson.equals(displayAllXml));
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsDisplayAllEqualsJson() {
		List<Show> displayAllJson = showsJson.displayAll();
		List<Show> displayAllJson2 = showsJson.displayAll();
		assertEquals(displayAllJson, displayAllJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsDisplayAllEqualsXml() {
		List<Show> displayAllXml = showsXml.displayAll();
		List<Show> displayAllXml2 = showsXml.displayAll();
		assertEquals(displayAllXml, displayAllXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsDisplayDexter() {
		Show dexterJson = showsJson.display("dexter");
		Show dexterXml = showsXml.display("dexter");
		assertEquals(dexterXml, dexterJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsDisplayDexterEqualsJson() {
		Show dexterJson = showsJson.display("dexter");
		Show dexterJson2 = showsJson.display("dexter");
		assertEquals(dexterJson, dexterJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsDisplayDexterEqualsXml() {
		Show dexterXml = showsXml.display("dexter");
		Show dexterXml2 = showsXml.display("dexter");
		assertEquals(dexterXml, dexterXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsEquals() {
		Show show = new Show();
		show.setUrl("futurama");
		show.setTitle("Futurama");
		Show show2 = new Show();
		show2.setUrl("futurama");
		show2.setTitle("Futurama");
		assertEquals(show, show2);

		show.setArchived(true);
		show2.setArchived(false);
		assertNotSame(show, show2);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsSearch() {
		List<Show> searchJson = showsJson.search("dexter");
		List<Show> searchXml = showsXml.search("dexter");
		assertEquals(searchXml.size(), searchJson.size());
		assertEquals(searchXml, searchJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsSearchEqualsJson() {
		List<Show> searchJson = showsJson.search("dexter");
		List<Show> searchJson2 = showsJson.search("dexter");
		assertEquals(searchJson, searchJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testShowsSearchEqualsXml() {
		List<Show> searchXml = showsXml.search("dexter");
		List<Show> searchXml2 = showsXml.search("dexter");
		assertEquals(searchXml, searchXml2);
	}
}