package com.kokotchy.betaSeriesAPI;

import java.io.File;
import java.util.Set;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.model.Friend;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;
import com.kokotchy.betaSeriesAPI.model.Show;
import com.kokotchy.betaSeriesAPI.model.SortType;

/**
 * Test members api
 * 
 * @author kokotchy
 */
public class TestMembers extends TestCase {

	/**
	 * Comments api for json
	 */
	private IMembers membersJson;

	/**
	 * Comments api for xml
	 */
	private IMembers membersXml;

	/**
	 * Login
	 */
	private String login;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Token
	 */
	private String token;

	/**
	 * Api key
	 */
	private String key;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String userDir = System.getProperty("user.dir");
		key = Utils.getApiKey(userDir);
		File credentialsFile = new File(userDir,
				"src/test/resources/credentials.txt");
		String[] credentials = Utils.loadCredentials(credentialsFile);
		login = credentials[0];
		password = credentials[1];
		token = credentials[2];
		com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi.setApiKey(key);
		com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi.setApiKey(key);
		membersJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getMembers();
		membersXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getMembers();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(userDir + "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(userDir + "/src/test/resources/xml/");
	}

	/**
	 * Test authentication of the user
	 */
	public void testAuth() {
		assertEquals(membersXml.auth(login, password), membersJson.auth(login,
				password));
	}

	/**
	 * TODO Fill it
	 */
	public void testDateCacheIdentifiedUser() {
		int dateCacheJson = membersJson.getDateCache(token, true);
		int dateCacheXml = membersXml.getDateCache(token, true);
		assertEquals(dateCacheXml, dateCacheJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testDateCacheIdentifiedUserEqualsJson() {
		int dateCacheJson = membersJson.getDateCache(token, true);
		int dateCacheJson2 = membersJson.getDateCache(token, true);
		assertEquals(dateCacheJson, dateCacheJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testDateCacheIdentifiedUserEqualsXml() {
		int dateCacheXml = membersXml.getDateCache(token, true);
		int dateCacheXml2 = membersXml.getDateCache(token, true);
		assertEquals(dateCacheXml, dateCacheXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testDateCacheUser() {
		int dateCacheJson = membersJson.getDateCache("delphiki", false);
		int dateCacheXml = membersXml.getDateCache("delphiki", false);
		assertEquals(dateCacheXml, dateCacheJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testDateCacheUserEqualsJson() {
		int dateCacheJson = membersJson.getDateCache("delphiki", false);
		int dateCacheJson2 = membersJson.getDateCache("delphiki", false);
		assertEquals(dateCacheJson, dateCacheJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testDateCacheUserEqualsXml() {
		int dateCacheXml = membersXml.getDateCache("delphiki", false);
		int dateCacheXml2 = membersXml.getDateCache("delphiki", false);
		assertEquals(dateCacheXml, dateCacheXml2);
	}

	/**
	 * Testing set an episode as downloaded
	 */
	public void testDownloaded() {
		boolean downloadedXml = membersXml
				.setDownloaded(token, "firefly", 1, 1);
		boolean downloadedJson = membersJson.setDownloaded(token, "firefly", 1,
				1);
		assertEquals(downloadedXml, downloadedJson);
	}

	/**
	 * Test friends
	 */
	public void testFriends() {
		Set<Friend> friendsJson = membersJson.getFriends(token);
		Set<Friend> friendsXml = membersXml.getFriends(token);
		assertEquals(friendsXml, friendsJson);
	}

	/**
	 * Test equals set with json
	 */
	public void testFriendsEqualsJson() {
		Set<Friend> friendsJson = membersJson.getFriends(token);
		Set<Friend> friendsJson2 = membersJson.getFriends(token);
		assertEquals(friendsJson, friendsJson2);
	}

	/**
	 * Test equals set with xml
	 */
	public void testFriendsEqualsXml() {
		Set<Friend> friendsXml2 = membersXml.getFriends(token);
		Set<Friend> friendsXml = membersXml.getFriends(token);
		assertEquals(friendsXml, friendsXml2);
	}

	/**
	 * Test informations with xml and json
	 */
	public void testInfos() {
		Member infosJson = membersJson.infos(token);
		Member infosXml = membersXml.infos(token);
		for (Show show : infosXml.getShows()) {
			for (Show show2 : infosJson.getShows()) {
				if (show2.getUrl().equals(show.getUrl())) {
					assertEquals(show, show2);
				}
			}
		}
	}

	/**
	 * Test equals of two json for informations of the user
	 */
	public void testInfosEqualsJson() {
		Member infosJson = membersJson.infos(token);
		Member infosJson2 = membersJson.infos(token);
		assertEquals(infosJson, infosJson2);
	}

	/**
	 * Test equals of two xml for informations of the user
	 */
	public void testInfosEqualsXml() {
		Member infosXml = membersXml.infos(token);
		Member infosXml2 = membersXml.infos(token);
		assertEquals(infosXml, infosXml2);
	}

	/**
	 * Test if user still active with xml and json
	 */
	public void testIsActive() {
		assertEquals(membersXml.isActive(token), membersJson.isActive(token));
	}

	/**
	 * Test the info of a user
	 */
	public void testMembersInfosOfUser() {
		Member memberXml = membersXml.infosOfUser("delphiki");
		Member memberJson = membersJson.infosOfUser("delphiki");
		for (Show show : memberXml.getShows()) {
			for (Show show2 : memberJson.getShows()) {
				if (show2.getUrl().equals(show.getUrl())) {
					assertEquals(show, show2);
				}
			}
		}
	}

	/**
	 * Test nofication sorting
	 */
	public void testNotificationAlreadySeen() {
		Set<Notification> notificationsJson = membersJson.getNotifications(
				token, true, SortType.NONE);
		Set<Notification> notificationsXml = membersXml.getNotifications(token,
				true, SortType.NONE);
		assertEquals(notificationsXml, notificationsJson);
	}

	/**
	 * Test nofication sorting
	 */
	public void testNotificationSortAlreadySeenSortAsc() {
		Set<Notification> notificationsJson = membersJson.getNotifications(
				token, true, SortType.ASC);
		Set<Notification> notificationsXml = membersXml.getNotifications(token,
				true, SortType.ASC);
		assertEquals(notificationsXml, notificationsJson);
	}

	/**
	 * Test nofication sorting
	 */
	public void testNotificationSortAlreadySeenSortDesc() {
		Set<Notification> notificationsJson = membersJson.getNotifications(
				token, true, SortType.DESC);
		Set<Notification> notificationsXml = membersXml.getNotifications(token,
				true, SortType.DESC);
		assertEquals(notificationsXml, notificationsJson);
	}

	/**
	 * Test the rate of an episode
	 */
	public void testRate() {
		assertTrue(membersJson.rate(token, "firefly", 1, 1, 5));
		assertTrue(membersXml.rate(token, "firefly", 1, 1, 5));
	}

	/**
	 * Test that rating isn't possible with a rate below 1
	 */
	public void testRateLessZero() {
		assertFalse(membersJson.rate(token, "firefly", 1, 1, 0));
		assertFalse(membersXml.rate(token, "firefly", 1, 1, 0));
	}

	/**
	 * Test that rating isn't possible with a rate after 5
	 */
	public void testRateMoreFive() {
		assertFalse(membersJson.rate(token, "firefly", 1, 1, 6));
		assertFalse(membersXml.rate(token, "firefly", 1, 1, 6));
	}

	/**
	 * Test the signup of an account
	 */
	public void testSignup() {
		fail("Not implemented");
	}

	/**
	 * Test friends of a user
	 */
	public void testUserFriends() {
		fail("Not implemented");
	}

	/**
	 * Test friends of a user equals json
	 */
	public void testUserFriendsEqualsJson() {
		fail("Not implemented");
	}

	/**
	 * Test friends of a user equals xml
	 */
	public void testUserFriendsEqualsXml() {
		fail("Not implemented");
	}
}
