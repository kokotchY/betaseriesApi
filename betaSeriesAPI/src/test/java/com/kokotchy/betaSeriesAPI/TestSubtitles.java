package com.kokotchy.betaSeriesAPI;

import java.util.List;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Test subtitles api
 * 
 * @author kokotchy
 * 
 */
public class TestSubtitles extends TestCase {

	/**
	 * Subtitles api for json
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Subtitles subtitlesJson;

	/**
	 * Subtitles api for xml
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Subtitles subtitlesXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		subtitlesJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getSubtitles();
		subtitlesXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getSubtitles();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriejson/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriexml/");
	}

	/**
	 * Test the last subtitles
	 */
	public void testLastSubtitles() {
		List<Subtitle> lastSubtitlesJson = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		List<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml, lastSubtitlesJson);
	}

	/**
	 * Test last subtitles for two xml
	 */
	public void testXmlLastSubtitles() {
		List<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		List<Subtitle> lastSubtitlesXml2 = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml, lastSubtitlesXml2);
	}
}
