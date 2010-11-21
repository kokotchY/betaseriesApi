package com.kokotchy.betaSeriesAPI;

import java.util.Set;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Comment;

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
	public void testCommentsShow() {
		String url = "himym";
		Set<Comment> commentsJson = this.commentsJson.getComments(url);
		Set<Comment> commentsXml = this.commentsXml.getComments(url);
		assertEquals(commentsXml, commentsJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsShowEqualsJson() {
		String url = "himym";
		Set<Comment> commentsJson = this.commentsJson.getComments(url);
		Set<Comment> commentsJson2 = this.commentsJson.getComments(url);
		assertEquals(commentsJson, commentsJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsShowEqualsXml() {
		String url = "himym";
		Set<Comment> commentsXml = this.commentsXml.getComments(url);
		Set<Comment> commentsXml2 = this.commentsXml.getComments(url);
		assertEquals(commentsXml, commentsXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsShowSpecificEpisode() {
		String url = "himym";
		int season = 6;
		int episode = 2;
		Set<Comment> commentsJson = this.commentsJson.getComments(url, season,
				episode);
		Set<Comment> commentsXml = this.commentsXml.getComments(url, season,
				episode);
		assertEquals(commentsXml, commentsJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsShowSpecificEpisodeEqualsJson() {
		String url = "himym";
		int season = 6;
		int episode = 2;
		Set<Comment> commentsJson2 = commentsJson.getComments(url, season,
				episode);
		Set<Comment> commentsJson = this.commentsJson.getComments(url, season,
				episode);
		assertEquals(commentsJson, commentsJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsShowSpecificEpisodeEqualsXml() {
		String url = "himym";
		int season = 6;
		int episode = 2;
		Set<Comment> commentsXml2 = commentsXml.getComments(url, season,
				episode);
		Set<Comment> commentsXml = this.commentsXml.getComments(url, season,
				episode);
		assertEquals(commentsXml, commentsXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsUser() {
		String user = "delphiki";
		Set<Comment> commentsJson = this.commentsJson.getUserComments(user);
		Set<Comment> commentsXml = this.commentsXml.getUserComments(user);
		assertEquals(commentsXml, commentsJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsUserEqualsJson() {
		String user = "delphiki";
		Set<Comment> commentsJson2 = commentsJson.getUserComments(user);
		Set<Comment> commentsJson = this.commentsJson.getUserComments(user);
		assertEquals(commentsJson, commentsJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testCommentsUserEqualsXml() {
		String user = "delphiki";
		Set<Comment> commentsXml2 = commentsXml.getUserComments(user);
		Set<Comment> commentsXml = this.commentsXml.getUserComments(user);
		assertEquals(commentsXml, commentsXml2);
	}
}
