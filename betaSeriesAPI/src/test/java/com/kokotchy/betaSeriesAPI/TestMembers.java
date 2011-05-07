package com.kokotchy.betaSeriesAPI;

import java.io.File;
import java.util.Set;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Friend;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;
import com.kokotchy.betaSeriesAPI.model.Show;
import com.kokotchy.betaSeriesAPI.model.SortType;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

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

	/**
	 * Test authentication of the user
	 */
	public void testAuth() {
		assertEquals(membersXml.auth(login, password), membersJson.auth(login,
				password));
	}

	/**
	 * Test the date of the cache of an identified user
	 */
	public void testDateCacheIdentifiedUser() {
		int dateCacheJson = membersJson.getDateCache(token, true);
		int dateCacheXml = membersXml.getDateCache(token, true);
		assertEquals(dateCacheXml, dateCacheJson);
	}

	/**
	 * Test the date of the cache of an identified user equals json
	 */
	public void testDateCacheIdentifiedUserEqualsJson() {
		int dateCacheJson = membersJson.getDateCache(token, true);
		int dateCacheJson2 = membersJson.getDateCache(token, true);
		assertEquals(dateCacheJson, dateCacheJson2);
	}

	/**
	 * Test the date of the cache of an identified user equals xml
	 */
	public void testDateCacheIdentifiedUserEqualsXml() {
		int dateCacheXml = membersXml.getDateCache(token, true);
		int dateCacheXml2 = membersXml.getDateCache(token, true);
		assertEquals(dateCacheXml, dateCacheXml2);
	}

	/**
	 * Test the date of the cache of a user
	 */
	public void testDateCacheUser() {
		int dateCacheJson = membersJson.getDateCache("delphiki", false);
		int dateCacheXml = membersXml.getDateCache("delphiki", false);
		assertEquals(dateCacheXml, dateCacheJson);
	}

	/**
	 * Test the date of the cache of a user equals json
	 */
	public void testDateCacheUserEqualsJson() {
		int dateCacheJson = membersJson.getDateCache("delphiki", false);
		int dateCacheJson2 = membersJson.getDateCache("delphiki", false);
		assertEquals(dateCacheJson, dateCacheJson2);
	}

	/**
	 * Test the date of the cache of a user equals xml
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
	 * Test retrieve episodes with all kind of subtitles
	 */
	public void testEpisodesAllSubtitles() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.ALL);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.ALL);
		assertEquals(episodesXml, episodesJson);
	}

	/**
	 * Test retrieve episodes with all kind of subtitles equals json
	 */
	public void testEpisodesAllSubtitlesEqualsJson() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.ALL);
		Set<Episode> episodesJson2 = membersJson.getEpisodes(token,
				SubtitleLanguage.ALL);
		assertEquals(episodesJson, episodesJson2);
	}

	/**
	 * Test retrieve episodes with all kind of subtitles equals xml
	 */
	public void testEpisodesAllSubtitlesEqualsXml() {
		Set<Episode> episodesXml2 = membersXml.getEpisodes(token,
				SubtitleLanguage.ALL);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.ALL);
		assertEquals(episodesXml, episodesXml2);
	}

	/**
	 * Test retrieve only next episode for each show with all kind of subtitles
	 */
	public void testEpisodesAllSubtitlesOnlyNext() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.ALL, true);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.ALL, true);
		assertEquals(episodesXml, episodesJson);
	}

	/**
	 * Test VF subtitles for the episodes
	 */
	public void testEpisodesVfSubtitles() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.VF);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.VF);
		assertEquals(episodesXml, episodesJson);
	}

	/**
	 * Test VF subtitles for the episodes equals json
	 */
	public void testEpisodesVfSubtitlesEqualsJson() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.VF);
		Set<Episode> episodesJson2 = membersJson.getEpisodes(token,
				SubtitleLanguage.VF);
		assertEquals(episodesJson, episodesJson2);
	}

	/**
	 * Test VF subtitles for the episodes equals xml
	 */
	public void testEpisodesVfSubtitlesEqualsXml() {
		Set<Episode> episodesXml2 = membersJson.getEpisodes(token,
				SubtitleLanguage.VF);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.VF);
		assertEquals(episodesXml, episodesXml2);
	}

	/**
	 * Test VF subtitles for the only next episodes
	 */
	public void testEpisodesVfSubtitlesOnlyNext() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.VF, true);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.VF, true);
		assertEquals(episodesXml, episodesJson);
	}

	/**
	 * Test VO/VF subtitles for the episodes
	 */
	public void testEpisodesVoVfSubtitles() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.VOVF);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.VOVF);
		assertEquals(episodesXml, episodesJson);
	}

	/**
	 * Test VO/VF subtitles for the episodes equals json
	 */
	public void testEpisodesVoVfSubtitlesEqualsJson() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.VOVF);
		Set<Episode> episodesJson2 = membersJson.getEpisodes(token,
				SubtitleLanguage.VOVF);
		assertEquals(episodesJson, episodesJson2);
	}

	/**
	 * Test VO/VF subtitles for the episodes equals xml
	 */
	public void testEpisodesVoVfSubtitlesEqualsXml() {
		Set<Episode> episodesXml2 = membersXml.getEpisodes(token,
				SubtitleLanguage.VOVF);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.VOVF);
		assertEquals(episodesXml, episodesXml2);
	}

	/**
	 * Test VO/VF subtitles for only the next episode
	 */
	public void testEpisodesVoVfSubtitlesOnlyNext() {
		Set<Episode> episodesJson = membersJson.getEpisodes(token,
				SubtitleLanguage.VOVF, true);
		Set<Episode> episodesXml = membersXml.getEpisodes(token,
				SubtitleLanguage.VOVF, true);
		assertEquals(episodesXml, episodesJson);
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
	 * Test friends of a user
	 */
	public void testUserFriends() {
		Set<Friend> friendsJson = membersJson.getUserFriends("dev042");
		Set<Friend> friendsXml = membersXml.getUserFriends("dev042");
		assertEquals(friendsXml, friendsJson);
	}

	// /**
	// * Test the signup of an account
	// */
	// public void testSignup() {
	// fail("Not implemented");
	// }

	/**
	 * Test empty user friend
	 */
	public void testUserFriendsEmpty() {
		Set<Friend> friendsJson = membersJson.getUserFriends("dev002");
		Set<Friend> friendsXml = membersXml.getUserFriends("dev002");
		assertEquals(friendsXml, friendsJson);
		assertEquals(0, friendsJson.size());
		assertEquals(0, friendsXml.size());
	}

	/**
	 * Test friends of a user equals json
	 */
	public void testUserFriendsEqualsJson() {
		Set<Friend> friendsJson = membersJson.getUserFriends("dev042");
		Set<Friend> friendsJson2 = membersJson.getUserFriends("dev042");
		assertEquals(friendsJson, friendsJson2);
	}

	/**
	 * Test friends of a user equals xml
	 */
	public void testUserFriendsEqualsXml() {
		Set<Friend> friendsXml2 = membersXml.getUserFriends("dev042");
		Set<Friend> friendsXml = membersXml.getUserFriends("dev042");
		assertEquals(friendsXml, friendsXml2);
	}

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
}
