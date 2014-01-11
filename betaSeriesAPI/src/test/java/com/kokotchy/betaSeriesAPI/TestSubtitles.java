package com.kokotchy.betaSeriesAPI;

import java.util.Set;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.api.ISubtitles;
import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Test subtitles api
 * 
 * @author kokotchy
 */
public class TestSubtitles extends TestCase {

	/**
	 * Subtitles api for json
	 */
	private ISubtitles subtitlesJson;

	/**
	 * Subtitles api for xml
	 */
	private ISubtitles subtitlesXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String userDir = System.getProperty("user.dir");
		String key = Utils.getApiKey(userDir);
		com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi.setApiKey(key);
		com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi.setApiKey(key);
		subtitlesJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getSubtitles();
		subtitlesXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getSubtitles();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(userDir + "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(userDir + "/src/test/resources/xml/");
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesFromFilename() {
		String filename = "How.I.Met.Your.Mother.S06E16.HDTV.XviD-LOL.avi";
		Set<Subtitle> subtitlesFromFileJson = subtitlesJson.getSubtitlesForFile(filename);
		Set<Subtitle> subtitlesFromFileXml = subtitlesXml.getSubtitlesForFile(filename);
		assertEquals(subtitlesFromFileXml.size(), subtitlesFromFileJson.size());
		assertEquals(subtitlesFromFileXml, subtitlesFromFileJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesFromFilenameEqualsJson() {
		String filename = "How.I.Met.Your.Mother.S06E16.HDTV.XviD-LOL.avi";
		Set<Subtitle> subtitlesFromFileJson = subtitlesJson.getSubtitlesForFile(filename);
		Set<Subtitle> subtitlesFromFileJson2 = subtitlesJson.getSubtitlesForFile(filename);
		assertEquals(subtitlesFromFileJson, subtitlesFromFileJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesFromFilenameEqualsXml() {
		String filename = "How.I.Met.Your.Mother.S06E16.HDTV.XviD-LOL.avi";
		Set<Subtitle> subtitlesFromFileXml = subtitlesXml.getSubtitlesForFile(filename);
		Set<Subtitle> subtitlesFromFileXml2 = subtitlesXml.getSubtitlesForFile(filename);
		assertEquals(subtitlesFromFileXml, subtitlesFromFileXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesFromFilenameVF() {
		String filename = "How.I.Met.Your.Mother.S06E16.HDTV.XviD-LOL.avi";
		Set<Subtitle> subtitlesFromFileXml = subtitlesXml.getSubtitlesForFile(filename, SubtitleLanguage.VF);
		Set<Subtitle> subtitlesFromFileJson = subtitlesJson.getSubtitlesForFile(filename, SubtitleLanguage.VF);
		assertEquals(subtitlesFromFileXml.size(), subtitlesFromFileJson.size());
		assertEquals(subtitlesFromFileXml, subtitlesFromFileJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesFromFilenameVFEqualsJson() {
		String filename = "How.I.Met.Your.Mother.S06E16.HDTV.XviD-LOL.avi";
		Set<Subtitle> subtitlesFromFileJson2 = subtitlesJson.getSubtitlesForFile(filename, SubtitleLanguage.VF);
		Set<Subtitle> subtitlesFromFileJson = subtitlesJson.getSubtitlesForFile(filename, SubtitleLanguage.VF);
		assertEquals(subtitlesFromFileJson, subtitlesFromFileJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testSubtitlesFromFilenameVFEqualsXml() {
		String filename = "How.I.Met.Your.Mother.S06E16.HDTV.XviD-LOL.avi";
		Set<Subtitle> subtitlesFromFileXml2 = subtitlesXml.getSubtitlesForFile(filename, SubtitleLanguage.VF);
		Set<Subtitle> subtitlesFromFileXml = subtitlesXml.getSubtitlesForFile(filename, SubtitleLanguage.VF);
		assertEquals(subtitlesFromFileXml, subtitlesFromFileXml2);
	}

	/**
	 * Test the last subtitles
	 */
	public void testSubtitlesLast() {
		Set<Subtitle> lastSubtitlesJson = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		Set<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml.size(), lastSubtitlesJson.size());
		assertEquals(lastSubtitlesXml, lastSubtitlesJson);
	}

	/**
	 * Test last subtitles for two json
	 */
	public void testSubtitlesLastJson() {
		Set<Subtitle> lastSubtitlesJson = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		Set<Subtitle> lastSubtitlesJson2 = subtitlesJson.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesJson, lastSubtitlesJson2);
	}

	/**
	 * Test last subtitles for two xml
	 */
	public void testSubtitlesLastXml() {
		Set<Subtitle> lastSubtitlesXml = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		Set<Subtitle> lastSubtitlesXml2 = subtitlesXml.getLastSubtitles(5,
				SubtitleLanguage.ALL);
		assertEquals(lastSubtitlesXml, lastSubtitlesXml2);
	}

	/**
	 * Test subtitles for the show Dexter
	 */
	public void testSubtitlesShowDexter() {
		String url = "dexter";
		SubtitleLanguage subtitle = SubtitleLanguage.VF;
		int season = 1;
		int episode = -1;
		Set<Subtitle> subtitlesListXml = subtitlesXml.show(url, subtitle,
				season, episode);
		Set<Subtitle> subtitlesListJson = subtitlesJson.show(url, subtitle,
				season, episode);
		assertEquals(subtitlesListXml.size(), subtitlesListJson.size());
		assertEquals(subtitlesListXml, subtitlesListJson);
	}

	/**
	 * Test equals for subtitles show Dexter with json
	 */
	public void testSubtitlesShowDexterJson() {
		String url = "dexter";
		SubtitleLanguage subtitle = SubtitleLanguage.VF;
		int season = 1;
		int episode = -1;
		Set<Subtitle> subtitlesListJson2 = subtitlesJson.show(url, subtitle,
				season, episode);
		Set<Subtitle> subtitlesListJson = subtitlesJson.show(url, subtitle,
				season, episode);
		assertEquals(subtitlesListJson, subtitlesListJson2);
	}

	/**
	 * Test equals for subtitles show Dexter with xml
	 */
	public void testSubtitlesShowDexterXml() {
		String url = "dexter";
		SubtitleLanguage subtitle = SubtitleLanguage.VF;
		int season = 1;
		int episode = -1;
		Set<Subtitle> subtitlesListXml = subtitlesXml.show(url, subtitle,
				season, episode);
		Set<Subtitle> subtitlesListXml2 = subtitlesXml.show(url, subtitle,
				season, episode);
		assertEquals(subtitlesListXml, subtitlesListXml2);
	}
}
