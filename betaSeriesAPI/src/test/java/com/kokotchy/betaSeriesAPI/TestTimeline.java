package com.kokotchy.betaSeriesAPI;

import java.util.List;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Event;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class TestTimeline extends TestCase {

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Timelines timelinesJson;

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Timelines timelinesXml;

	/**
	 *
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		timelinesJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getTimelines();
		timelinesXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getTimelines();
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
	public void testTimelineFriends() {
		String token = "6668bb6944a5";
		List<Event> friendsTimelineJson = timelinesJson
				.getFriendsTimeline(token);
		List<Event> friendsTimelineXml = timelinesXml.getFriendsTimeline(token);
		assertEquals(friendsTimelineXml, friendsTimelineJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineFriendsEqualsJson() {
		String token = "6668bb6944a5";
		List<Event> friendsTimelineJson = timelinesJson
				.getFriendsTimeline(token);
		List<Event> friendsTimelineJson2 = timelinesJson
				.getFriendsTimeline(token);
		assertEquals(friendsTimelineJson, friendsTimelineJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineFriendsEqualsXml() {
		String token = "6668bb6944a5";
		List<Event> friendsTimelineXml = timelinesXml.getFriendsTimeline(token);
		List<Event> friendsTimelineXml2 = timelinesXml
				.getFriendsTimeline(token);
		assertEquals(friendsTimelineXml, friendsTimelineXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineFriendsWithNb() {
		String token = "6668bb6944a5";
		int nb = 5;
		List<Event> friendsTimelineJson = timelinesJson.getFriendsTimeline(
				token, nb);
		List<Event> friendsTimelineXml = timelinesXml.getFriendsTimeline(token,
				nb);
		assertEquals(friendsTimelineXml, friendsTimelineJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineFriendsWithNbEqualsJson() {
		String token = "6668bb6944a5";
		int nb = 5;
		List<Event> friendsTimelineJson = timelinesJson.getFriendsTimeline(
				token, nb);
		List<Event> friendsTimelineJson2 = timelinesJson.getFriendsTimeline(
				token, nb);
		assertEquals(friendsTimelineJson, friendsTimelineJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineFriendsWithNbEqualsXml() {
		String token = "6668bb6944a5";
		int nb = 5;
		List<Event> friendsTimelineXml = timelinesXml.getFriendsTimeline(token,
				nb);
		List<Event> friendsTimelineXml2 = timelinesXml.getFriendsTimeline(
				token, nb);
		assertEquals(friendsTimelineXml, friendsTimelineXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineHome() {
		List<Event> homeTimelineJson = timelinesJson.getHomeTimeline();
		List<Event> homeTimelineXml = timelinesXml.getHomeTimeline();
		assertEquals(homeTimelineXml, homeTimelineJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineHomeEqualsJson() {
		List<Event> homeTimelineJson2 = timelinesJson.getHomeTimeline();
		List<Event> homeTimelineJson = timelinesJson.getHomeTimeline();
		assertEquals(homeTimelineJson, homeTimelineJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineHomeEqualsXml() {
		List<Event> homeTimelineXml2 = timelinesXml.getHomeTimeline();
		List<Event> homeTimelineXml = timelinesXml.getHomeTimeline();
		assertEquals(homeTimelineXml, homeTimelineXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineHomeWithNb() {
		int nb = 5;
		List<Event> homeTimelineJson = timelinesJson.getHomeTimeline(nb);
		List<Event> homeTimelineXml = timelinesXml.getHomeTimeline(nb);
		assertEquals(homeTimelineXml, homeTimelineJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineHomeWithNbEqualsJson() {
		int nb = 5;
		List<Event> homeTimelineJson2 = timelinesJson.getHomeTimeline(nb);
		List<Event> homeTimelineJson = timelinesJson.getHomeTimeline(nb);
		assertEquals(homeTimelineJson, homeTimelineJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineHomeWithNbEqualsXml() {
		int nb = 5;
		List<Event> homeTimelineXml2 = timelinesXml.getHomeTimeline(nb);
		List<Event> homeTimelineXml = timelinesXml.getHomeTimeline(nb);
		assertEquals(homeTimelineXml, homeTimelineXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineUser() {
		String user = "delphiki";
		List<Event> friendsTimelineJson = timelinesJson.getTimelineOfUser(user);
		List<Event> friendsTimelineXml = timelinesXml.getTimelineOfUser(user);
		assertEquals(friendsTimelineXml, friendsTimelineJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineUserEqualsJson() {
		String user = "delphiki";
		List<Event> friendsTimelineJson2 = timelinesJson
				.getTimelineOfUser(user);
		List<Event> friendsTimelineJson = timelinesJson.getTimelineOfUser(user);
		assertEquals(friendsTimelineJson, friendsTimelineJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineUserEqualsJsonWithNb() {
		String user = "delphiki";
		int nb = 5;
		List<Event> friendsTimelineJson2 = timelinesJson.getTimelineOfUser(
				user, nb);
		List<Event> friendsTimelineJson = timelinesJson.getTimelineOfUser(user,
				nb);
		assertEquals(friendsTimelineJson, friendsTimelineJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineUserEqualsXml() {
		String user = "delphiki";
		List<Event> friendsTimelineXml2 = timelinesXml.getTimelineOfUser(user);
		List<Event> friendsTimelineXml = timelinesXml.getTimelineOfUser(user);
		assertEquals(friendsTimelineXml, friendsTimelineXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineUserEqualsXmlWithNb() {
		String user = "delphiki";
		int nb = 5;
		List<Event> friendsTimelineXml2 = timelinesXml.getTimelineOfUser(user,
				nb);
		List<Event> friendsTimelineXml = timelinesXml.getTimelineOfUser(user,
				nb);
		assertEquals(friendsTimelineXml, friendsTimelineXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testTimelineUserWithNb() {
		String user = "delphiki";
		int nb = 5;
		List<Event> friendsTimelineJson = timelinesJson.getTimelineOfUser(user,
				nb);
		List<Event> friendsTimelineXml = timelinesXml.getTimelineOfUser(user,
				nb);
		assertEquals(friendsTimelineXml, friendsTimelineJson);
	}
}
