package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.api.NotImplementedException;
import com.kokotchy.betaSeriesAPI.api.factories.EpisodeFactory;
import com.kokotchy.betaSeriesAPI.api.factories.FriendFactory;
import com.kokotchy.betaSeriesAPI.api.factories.MemberFactory;
import com.kokotchy.betaSeriesAPI.api.factories.NotificationFactory;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Friend;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;
import com.kokotchy.betaSeriesAPI.model.SortType;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Members API
 * 
 * @author kokotchy
 */
public class Members implements IMembers {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Create new members api with the given key
	 * 
	 * @param apiKey
	 *            API Key
	 */
	public Members(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String auth(String login, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.MEMBER_LOGIN, login);
		params.put(Constants.MEMBER_PASSWORD, Utils.getMD5(password));
		Document document = UtilsXml.executeQuery("members/auth", apiKey,
				params);
		if (!UtilsXml.hasErrors(document)) {
			Node tokenNode = document.selectSingleNode("/root/member/token");
			return tokenNode.getText();
		}
		return null;
	}

	@Override
	public boolean destroy(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		Document document = UtilsXml.executeQuery("members/destroy", apiKey,
				params);
		return !UtilsXml.hasErrors(document);
	}

	@Override
	public int getDateCache(String token, boolean identifieduser) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.NO_DATA, "1");
		String action;
		if (identifieduser) {
			action = "members/infos";
			params.put(Constants.TOKEN, token);
		} else {
			action = "members/infos/" + token;
		}
		Document document = UtilsXml.executeQuery(action, apiKey, params);
		if (!UtilsXml.hasErrors(document)) {
			return UtilsXml.readInt(document, Constants.CACHED);
		}
		return -1;
	}

	@Override
	public Set<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage) {
		return getEpisodesList(token, subtitleLanguage, false);
	}

	@Override
	public Set<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage, boolean onlyNext) {
		return getEpisodesList(token, subtitleLanguage, onlyNext);
	}

	/**
	 * Return the list of episodes to watch for the logged user. The language of the subtitle is specified with subtitleLanguage. If onlyNext is set, only the first episode by show is returned.
	 * 
	 * @param token
	 *            Token of the user
	 * @param subtitleLanguage
	 *            Language of the subtitles
	 * @param onlyNext
	 *            If set, only one episode by show
	 * @return List of show
	 */
	@SuppressWarnings("unchecked")
	private Set<Episode> getEpisodesList(String token,
			SubtitleLanguage subtitleLanguage, boolean onlyNext) {
		String lang = null;
		switch (subtitleLanguage) {
		case VF:
			lang = Constants.LANG_VF;
			break;
		case VOVF:
			lang = Constants.LANG_VOVF;
			break;
		case ALL:
			lang = Constants.LANG_ALL;
			break;
		}
		Set<Episode> result = new HashSet<Episode>();
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		if (onlyNext) {
			params.put(Constants.MEMBER_VIEW, Constants.MEMBER_NEXT);
		}
		Document document = UtilsXml.executeQuery("members/episodes/" + lang,
				apiKey, params);
		List<Node> nodes = document.selectNodes("/root/episodes/episode");
		for (Node node : nodes) {
			result.add(EpisodeFactory.createEpisode(node));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Friend> getFriends(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		Document document = UtilsXml.executeQuery("members/friends", apiKey,
				params);
		if (!UtilsXml.hasErrors(document)) {
			Set<Friend> result = new HashSet<Friend>();
			List<Node> friends = document.selectNodes("/root/friends/friend");
			for (Node node : friends) {
				String text = node.getText();
				Friend friend = FriendFactory.createFriend(text);
				result.add(friend);
			}
			return result;
		}
		return null;
	}

	/**
	 * Return the information about the user. If it is the identified user,
	 * identifiedUser has to be true and user has to be the token. If
	 * identifiedUser is false, then the user is the login of the user to
	 * retrieve.
	 * 
	 * @param user
	 *            User or token to retrieve
	 * @param identifiedUser
	 *            If user if the user or the token
	 * @param lastCache
	 * @return Member informations
	 */
	private Member getInfosForUser(String user, boolean identifiedUser, int lastCache) {
		Document document;
		Map<String, String> params = new HashMap<String, String>();
		if (lastCache > 0) {
			params.put(Constants.SINCE, "" + lastCache);
		}
		if (!identifiedUser) {
			document = UtilsXml.executeQuery("members/infos/" + user, apiKey, params);
		} else {
			params.put(Constants.TOKEN, user);
			document = UtilsXml.executeQuery("members/infos", apiKey, params);
		}
		return MemberFactory.createMember(document
				.selectSingleNode("/root/member"));
	}

	@Override
	public Set<Notification> getNotifications(String token, boolean seen,
			int nb, int lastId, SortType sort) {
		return getNotificationsWithParameters(token, seen, nb, lastId, sort);
	}

	@Override
	public Set<Notification> getNotifications(String token, boolean seen,
			int nb, SortType sort) {
		return getNotificationsWithParameters(token, seen, nb, -1, sort);
	}

	@Override
	public Set<Notification> getNotifications(String token, boolean seen,
			SortType sort) {
		return getNotificationsWithParameters(token, seen, -1, -1, sort);
	}

	@Override
	public Set<Notification> getNotifications(String token, int nb,
			SortType sort) {
		return getNotificationsWithParameters(token, null, nb, -1, sort);
	}

	/**
	 * Return the notifications with the given parameter. Conditions for the
	 * parameters to be used:
	 * <ul>
	 * <li>seen has not to be null</li>
	 * <li>nb greater than 0</li>
	 * <li>lastId greater than 0</li>
	 * </ul>
	 * 
	 * @param token
	 *            Token of the logged user
	 * @param seen
	 *            If the notification has to be already seen or not
	 * @param nb
	 *            Number of notification
	 * @param lastId
	 *            Start of notification
	 * @param sort
	 * @return List of notification
	 */
	@SuppressWarnings("unchecked")
	private Set<Notification> getNotificationsWithParameters(String token,
			Boolean seen, int nb, int lastId, SortType sort) {
		Map<String, String> params = new HashMap<String, String>();
		if (seen != null) {
			params.put(Constants.SEEN, seen ? Constants.YES : Constants.NO);
		}

		switch (sort) {
		case ASC:
			params.put(Constants.SORT, Constants.ORDER_ASC);
			break;
		case DESC:
			params.put(Constants.SORT, Constants.ORDER_DESC);
			break;
		default:
		}

		if (nb > 0) {
			params.put(Constants.LIMIT, "" + nb);
		}
		if (lastId > 0) {
			params.put(Constants.MEMBER_LAST_ID, "" + lastId);
		}
		params.put(Constants.TOKEN, token);
		Document document = UtilsXml.executeQuery("members/notifications",
				apiKey, params);
		List<Node> nodes = document
				.selectNodes("/root/notifications/notification");
		Set<Notification> notifications = new HashSet<Notification>();
		for (Node node : nodes) {
			notifications.add(NotificationFactory.createNotification(node));
		}
		return notifications;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Friend> getUserFriends(String user) {
		Document document = UtilsXml.executeQuery("members/friends/" + user, apiKey);
		if (!UtilsXml.hasErrors(document)) {
			Set<Friend> result = new HashSet<Friend>();
			List<Node> friends = document.selectNodes("/root/friends/friend");
			for (Node node : friends) {
				String text = node.getText();
				Friend friend = FriendFactory.createFriend(text);
				result.add(friend);
			}
			return result;
		}
		return null;
	}

	@Override
	public Member infos(String token) {
		return getInfosForUser(token, true, -1);
	}

	@Override
	public Member infos(String token, int lastCache) {
		return getInfosForUser(token, true, lastCache);
	}

	@Override
	public Member infosOfUser(String user) {
		return getInfosForUser(user, false, -1);
	}

	@Override
	public Member infosOfUser(String user, int lastCache) {
		return getInfosForUser(user, false, lastCache);
	}

	@Override
	public boolean isActive(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		Document document = UtilsXml.executeQuery("members/is_active", apiKey,
				params);
		Node node = document.selectSingleNode("/root");
		return UtilsXml.readBoolean(node, Constants.ERROR_CODE);
	}

	@Override
	public boolean rate(String token, String url, int season, int episode,
			int rate) {
		if (rate > 0 && rate <= 5) {
			Map<String, String> params = new HashMap<String, String>();
			params.put(Constants.TOKEN, token);
			params.put(Constants.SEASON, "" + season);
			params.put(Constants.EPISODE, "" + episode);
			params.put(Constants.RATE, "" + rate);
			Document document = UtilsXml.executeQuery("members/note/" + url, apiKey, params);
			// TODO Check result
			return true;
		}
		return false;
	}

	@Override
	public boolean resetViewedShow(String token, String url) {
		return setWatched(token, url, 0, 0);
	}

	@Override
	public boolean setDownloaded(String token, String url, int season,
			int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SEASON, "" + season);
		params.put(Constants.EPISODE, "" + episode);
		params.put(Constants.TOKEN, token);
		Document document = UtilsXml.executeQuery("members/downloaded/" + url,
				apiKey, params);
		if (!UtilsXml.hasErrors(document)) {
			Node root = document.selectSingleNode("/root");
			return UtilsXml.readBoolean(root, "downloaded");
		}
		return false;
	}

	@Override
	public boolean setWatched(String token, String url, int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SEASON, "" + season);
		params.put(Constants.EPISODE, "" + episode);
		params.put(Constants.TOKEN, token);
		Document document = UtilsXml.executeQuery("members/watched/" + url,
				apiKey, params);
		// TODO Check for error
		return !UtilsXml.hasErrors(document);
	}

	@Override
	public boolean signup(String login, String password, String email) {
		throw new NotImplementedException();
	}
}
