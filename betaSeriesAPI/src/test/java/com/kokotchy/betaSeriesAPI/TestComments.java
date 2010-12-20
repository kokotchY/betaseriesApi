package com.kokotchy.betaSeriesAPI;

import java.util.Set;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.api.IComments;
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
	private IComments commentsJson;

	/**
	 * Comments api for xml
	 */
	private IComments commentsXml;

	/**
	 *
	 */
	private String key;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String userDir = System.getProperty("user.dir");
		key = Utils.getApiKey(userDir);
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
	 * Test empty comments json
	 */
	public void testCommentsEmptyJson() {
		JSONObject jsonObject = UtilsJson.executeQuery("comments/empty", key);
		JSONObject comments = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/comments");
		assertEquals(0, comments.length());
	}

	/**
	 * Test empty comments xml
	 */
	public void testCommentsEmptyXml() {
		Document document = UtilsXml.executeQuery("comments/empty", key);
		Node node = document.selectSingleNode("/root/comments");
		assertFalse(node.hasContent());
	}

	/**
	 * Test comments of a show
	 */
	public void testCommentsShow() {
		String url = "himym";
		Set<Comment> commentsJson = this.commentsJson.getComments(url);
		Set<Comment> commentsXml = this.commentsXml.getComments(url);
		assertEquals(commentsXml, commentsJson);
	}

	/**
	 * Test comments of a show equals json
	 */
	public void testCommentsShowEqualsJson() {
		String url = "himym";
		Set<Comment> commentsJson = this.commentsJson.getComments(url);
		Set<Comment> commentsJson2 = this.commentsJson.getComments(url);
		assertEquals(commentsJson, commentsJson2);
	}

	/**
	 * Test comments of a show equals xml
	 */
	public void testCommentsShowEqualsXml() {
		String url = "himym";
		Set<Comment> commentsXml = this.commentsXml.getComments(url);
		Set<Comment> commentsXml2 = this.commentsXml.getComments(url);
		assertEquals(commentsXml, commentsXml2);
	}

	/**
	 * Test comments of a specific episode of a show
	 */
	public void testCommentsShowSpecificEpisode() {
		String url = "himym";
		int season = 6;
		int episode = 2;
		Set<Comment> commentsJson = this.commentsJson.getComments(url, season,
				episode);
		Set<Comment> commentsXml = this.commentsXml.getComments(url, season,
				episode);

		for (Comment comment : commentsXml) {
			for (Comment comment2 : commentsJson) {
				if (comment2.getLogin().equals(comment.getLogin())) {
					if (comment.equals(comment2)) {
						assertTrue(true);
					} else {
						assertEquals(comment.getContent(), comment2
								.getContent());
					}
				}
			}
		}

		assertEquals(commentsXml.size(), commentsJson.size());
		assertEquals(commentsXml, commentsJson);
	}

	/**
	 * Test comments of a specific episode of a show equals json
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
	 * Test comments of a specific episode of a show equals xml
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
	 * Test comments of a user
	 */
	public void testCommentsUser() {
		String user = "delphiki";
		Set<Comment> commentsJson = this.commentsJson.getUserComments(user);
		Set<Comment> commentsXml = this.commentsXml.getUserComments(user);
		assertEquals(commentsXml, commentsJson);
	}

	/**
	 * Test comments of a user equals json
	 */
	public void testCommentsUserEqualsJson() {
		String user = "delphiki";
		Set<Comment> commentsJson2 = commentsJson.getUserComments(user);
		Set<Comment> commentsJson = this.commentsJson.getUserComments(user);
		assertEquals(commentsJson, commentsJson2);
	}

	/**
	 * Test comments of a user equals xml
	 */
	public void testCommentsUserEqualsXml() {
		String user = "delphiki";
		Set<Comment> commentsXml2 = commentsXml.getUserComments(user);
		Set<Comment> commentsXml = this.commentsXml.getUserComments(user);
		assertEquals(commentsXml, commentsXml2);
	}
}
