package com.kokotchy.betaSeriesAPI;

import java.io.File;
import java.util.Set;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.api.IShows;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * Test shows api
 * 
 * @author kokotchy
 */
public class TestShows extends TestCase {

	/**
	 * Shows api for json
	 */
	private IShows showsJson;

	/**
	 * Shows api for xml
	 */
	private IShows showsXml;

	/**
	 *
	 */
	private String token;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String userDir = System.getProperty("user.dir");
		String key = Utils.getApiKey(userDir);
		File credentialsFile = new File(userDir,
				"src/test/resources/credentials.txt");
		String[] credentials = Utils.loadCredentials(credentialsFile);
		token = credentials[2];
		com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi.setApiKey(key);
		com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi.setApiKey(key);
		showsJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getShows();
		showsXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getShows();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(userDir + "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(userDir + "/src/test/resources/xml/");
	}

	/**
	 * Test the recommendation of a show
	 */
	public void testRecommend() {
		boolean recommendJson = showsJson.recommend(token, "a-developers-life", "kokotchY");
		boolean recommendXml = showsXml.recommend(token, "a-developers-life", "kokotchY");
		assertEquals(recommendXml, recommendJson);
		assertTrue(recommendJson);
		assertTrue(recommendXml);
	}

	/**
	 * Test display of all shows
	 */
	public void testShowsDisplayAll() {
		Set<Show> displayAllJson = showsJson.displayAll();
		Set<Show> displayAllXml = showsXml.displayAll();
		assertEquals(displayAllXml.size(), displayAllJson.size());

		for (Show show : displayAllXml) {
			for (Show show2 : displayAllJson) {
				if (show2.getUrl().equals(show.getUrl())) {
					if (show.equals(show2)) {
						assertTrue(true);
					} else {
						fail("Not equals " + show + "," + show2);
					}
					// assertEquals(show, show2);
				}
			}
		}

		assertEquals(displayAllXml, displayAllJson);
	}

	/**
	 * Test display of all shows for json
	 */
	public void testShowsDisplayAllEqualsJson() {
		Set<Show> displayAllJson = showsJson.displayAll();
		Set<Show> displayAllJson2 = showsJson.displayAll();
		assertEquals(displayAllJson, displayAllJson2);
	}

	/**
	 * Test display of all shows for xml
	 */
	public void testShowsDisplayAllEqualsXml() {
		Set<Show> displayAllXml = showsXml.displayAll();
		Set<Show> displayAllXml2 = showsXml.displayAll();
		assertEquals(displayAllXml, displayAllXml2);
	}

	/**
	 * Test display dexter show informations
	 */
	public void testShowsDisplayDexter() {
		Show dexterJson = showsJson.display("dexter");
		Show dexterXml = showsXml.display("dexter");
		assertEquals(dexterXml, dexterJson);
	}

	/**
	 * Test display dexter show informations for json
	 */
	public void testShowsDisplayDexterEqualsJson() {
		Show dexterJson = showsJson.display("dexter");
		Show dexterJson2 = showsJson.display("dexter");
		assertEquals(dexterJson, dexterJson2);
	}

	/**
	 * Test display dexter show informations for xml
	 */
	public void testShowsDisplayDexterEqualsXml() {
		Show dexterXml = showsXml.display("dexter");
		Show dexterXml2 = showsXml.display("dexter");
		assertEquals(dexterXml, dexterXml2);
	}

	/**
	 * Test equals of two shows
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
	 * Test the search
	 */
	public void testShowsSearch() {
		Set<Show> searchJson = showsJson.search("dexter");
		Set<Show> searchXml = showsXml.search("dexter");
		assertEquals(searchXml.size(), searchJson.size());
		assertEquals(searchXml, searchJson);
	}

	/**
	 * Test show for two json
	 */
	public void testShowsSearchEqualsJson() {
		Set<Show> searchJson = showsJson.search("dexter");
		Set<Show> searchJson2 = showsJson.search("dexter");
		assertEquals(searchJson, searchJson2);
	}

	/**
	 * Test show for two xml
	 */
	public void testShowsSearchEqualsXml() {
		Set<Show> searchXml = showsXml.search("dexter");
		Set<Show> searchXml2 = showsXml.search("dexter");
		assertEquals(searchXml, searchXml2);
	}
}
