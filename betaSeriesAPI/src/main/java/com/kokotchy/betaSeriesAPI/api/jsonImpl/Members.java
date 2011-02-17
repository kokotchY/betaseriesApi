package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.UtilsJson;
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
	 *            API key
	 */
	public Members(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean addFriend(String token, String login) {
		throw new NotImplementedException();
	}

	@Override
	public String auth(String login, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.MEMBER_LOGIN, login);
		params.put(Constants.MEMBER_PASSWORD, Utils.getMD5(password));
		JSONObject object = UtilsJson.executeQuery("members/auth", apiKey,
				params);
		if (!UtilsJson.hasErrors(object)) {
			return UtilsJson
					.getJSONStringFromPath(object, "/root/member/token");
		}
		return null;
	}

	@Override
	public boolean destroy(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		JSONObject jsonObject = UtilsJson.executeQuery("members/destroy",
				apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public Set<String> getBadges(String token) {
		throw new NotImplementedException();
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
		JSONObject jsonObject = UtilsJson.executeQuery(action, apiKey, params);
		if (!UtilsJson.hasErrors(jsonObject)) {
			return UtilsJson.getIntValue(jsonObject, Constants.CACHED);
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
			params.put(Constants.VIEW, Constants.MEMBER_NEXT);
		}
		JSONObject jsonObject = UtilsJson.executeQuery("members/episodes/"
				+ lang, apiKey, params);
		if (!UtilsJson.hasErrors(jsonObject)) {
			JSONObject episodes = UtilsJson.getJSONObjectFromPath(jsonObject,
					"/root/episodes");
			JSONObject[] array = UtilsJson.getArray(episodes);
			if (array.length > 0) {
				for (JSONObject episode : array) {
					result.add(EpisodeFactory.createEpisode(episode));
				}
			}
		} /*
		 * else { throw new
		 * BetaseriesApiException(UtilsJson.getErrors(jsonObject)); }
		 */
		return result;
	}

	@Override
	public Set<Friend> getFriends(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		JSONObject jsonObject = UtilsJson.executeQuery("members/friends",
				apiKey, params);
		if (!UtilsJson.hasErrors(jsonObject)) {
			Set<Friend> result = new HashSet<Friend>();
			JSONObject friends = UtilsJson.getJSONObjectFromPath(jsonObject,
					"/root/friends");
			Iterator<?> iterator = friends.keys();
			try {
				while (iterator.hasNext()) {
					String name = (String) iterator.next();
					String friend = friends.getString(name);
					result.add(FriendFactory.createFriend(friend));
				}
			} catch (JSONException e) {
				e.printStackTrace();
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
	 * @param lastcache
	 * @return Member informations
	 */
	private Member getInfosForUser(String user, boolean identifiedUser, int lastcache) {
		JSONObject jsonObject;
		Map<String, String> params = new HashMap<String, String>();
		if (lastcache > 0) {
			params.put(Constants.SINCE, "" + lastcache);
		}
		if (!identifiedUser) {
			jsonObject = UtilsJson
					.executeQuery("members/infos/" + user, apiKey, params);
		} else {
			params.put(Constants.TOKEN, user);
			jsonObject = UtilsJson
					.executeQuery("members/infos", apiKey, params);
		}
		if (!UtilsJson.hasErrors(jsonObject)) {
			return MemberFactory.createMember(UtilsJson.getJSONObjectFromPath(
					jsonObject, "/root/member"));
		}
		return null;
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
	 * @param seen
	 *            If the notification has to be already seen or not
	 * @param nb
	 *            Number of notification
	 * @param lastId
	 *            Start of notification
	 * @param sort
	 * @return List of notification
	 */
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
		default:
		}

		if (nb > 0) {
			params.put(Constants.LIMIT, "" + nb);
		}
		if (lastId > 0) {
			params.put(Constants.MEMBER_LAST_ID, "" + lastId);
		}
		params.put(Constants.TOKEN, token);
		Set<Notification> result = new HashSet<Notification>();
		JSONObject jsonObject = UtilsJson.executeQuery("members/notifications",
				apiKey, params);
		if (!UtilsJson.hasErrors(jsonObject)) {
			JSONObject notifications = UtilsJson.getJSONObjectFromPath(
					jsonObject, "/root/notifications");
			JSONObject[] array = UtilsJson.getArray(notifications);
			if (array.length > 0) {
				for (JSONObject notification : array) {
					result.add(NotificationFactory
							.createNotification(notification));
				}
			}
			return result;
		}
		return null;
	}

	@Override
	public Set<String> getUserBadges(String login) {
		throw new NotImplementedException();
	}

	@Override
	public Set<Friend> getUserFriends(String user) {
		JSONObject jsonObject = UtilsJson.executeQuery("members/friends/" + user, apiKey);
		Set<Friend> result = new HashSet<Friend>();
		if (!UtilsJson.hasErrors(jsonObject)) {
			JSONObject friends = UtilsJson.getJSONObjectFromPath(jsonObject, "/root/friends");
			Iterator<?> iterator = friends.keys();
			try {
				while (iterator.hasNext()) {
					String name = (String) iterator.next();
					String friendName = friends.getString(name);
					Friend friend = FriendFactory.createFriend(friendName);
					result.add(friend);
				}
			} catch (JSONException e) {
				e.printStackTrace();
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
		JSONObject jsonObject = UtilsJson.executeQuery("members/is_active",
				apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
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
			JSONObject jsonObject = UtilsJson.executeQuery("members/note/" + url, apiKey, params);
			// TODO Check result
			return true;
		}
		return false;
	}

	@Override
	public boolean removeFriend(String token, String login) {
		throw new NotImplementedException();
	}

	@Override
	public boolean resetViewedShow(String token, String url) {
		return setWatched(token, url, 0, 0);
	}

	@Override
	public Set<String> search(String login) {
		throw new NotImplementedException();
	}

	@Override
	public boolean setDownloaded(String token, String url, int season,
			int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SEASON, "" + season);
		params.put(Constants.EPISODE, "" + episode);
		params.put(Constants.TOKEN, token);
		JSONObject jsonObject = UtilsJson.executeQuery("members/downloaded/"
				+ url, apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public boolean setWatched(String token, String url, int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SEASON, "" + season);
		params.put(Constants.EPISODE, "" + episode);
		params.put(Constants.TOKEN, token);
		JSONObject jsonObject = UtilsJson.executeQuery(
				"members/watched/" + url, apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public boolean signup(String login, String password, String email) {
		throw new NotImplementedException();
	}

}
