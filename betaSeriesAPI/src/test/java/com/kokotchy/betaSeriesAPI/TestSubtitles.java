package com.kokotchy.betaSeriesAPI;

import java.util.List;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class TestSubtitles extends TestCase {

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Subtitles subtitlesJson;

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Subtitles subtitlesXml;

	/**
	 *
	 */
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
	 * TODO Fill it
	 */
	public void testLastSubtitles() {
		List<Subtitle> lastSubtitlesJson = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		List<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml, lastSubtitlesJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testXmlLastSubtitles() {
		List<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		List<Subtitle> lastSubtitlesXml2 = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml, lastSubtitlesXml2);
	}
}
