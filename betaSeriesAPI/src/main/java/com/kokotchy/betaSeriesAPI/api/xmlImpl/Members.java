package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.api.NotImplementedException;
import com.kokotchy.betaSeriesAPI.api.factories.EpisodeFactory;
import com.kokotchy.betaSeriesAPI.api.factories.MemberFactory;
import com.kokotchy.betaSeriesAPI.api.factories.NotificationFactory;
import com.kokotchy.betaSeriesAPI.model.Episode;
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
		UtilsXml.executeQuery("members/destroy", apiKey, params);
		// TODO Check for error
		return true;
	}

	@Override
	public int getDateCache(String token, boolean identifieduser) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage) {
		return getEpisodes2(token, subtitleLanguage, false);
	}

	@Override
	public List<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage, boolean onlyNext) {
		return getEpisodes2(token, subtitleLanguage, onlyNext);
	}

	@Override
	public List<Notification> getNotifications(String token, boolean seen,
			int nb, int lastId, SortType sort) {
		return getNotificationsWithParameters(token, seen, nb, lastId, sort);
	}

	@Override
	public List<Notification> getNotifications(String token, boolean seen,
			int nb, SortType sort) {
		return getNotificationsWithParameters(token, seen, nb, -1, sort);
	}

	@Override
	public List<Notification> getNotifications(String token, boolean seen,
			SortType sort) {
		return getNotificationsWithParameters(token, seen, -1, -1, sort);
	}

	@Override
	public List<Notification> getNotifications(String token, int nb,
			SortType sort) {
		return getNotificationsWithParameters(token, null, nb, -1, sort);
	}

	@Override
	public Member infos(String token) {
		return getInfosForUser(token, true);
	}

	@Override
	public Member infos(String token, int lastCache) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member infosOfUser(String user) {
		return getInfosForUser(user, false);
	}

	@Override
	public Member infosOfUser(String user, int lastCache) {
		// TODO Auto-generated method stub
		return null;
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
		UtilsXml.executeQuery("members/watched/" + url, apiKey, params);
		// TODO Check for error
		return true;
	}

	@Override
	public boolean signup(String login, String password, String email) {
		throw new NotImplementedException();
	}

	private List<Episode> getEpisodes2(String token,
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
		List<Episode> result = new LinkedList<Episode>();
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
	 * @return Member informations
	 */
	private Member getInfosForUser(String user, boolean identifiedUser) {
		Document document;
		if (!identifiedUser) {
			document = UtilsXml.executeQuery("members/infos/" + user, apiKey);
		} else {
			Map<String, String> params = new HashMap<String, String>();
			params.put(Constants.TOKEN, user);
			document = UtilsXml.executeQuery("members/infos", apiKey, params);
		}
		return MemberFactory.createMember(document
				.selectSingleNode("/root/member"));
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
	private List<Notification> getNotificationsWithParameters(String token,
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
		List<Notification> notifications = new LinkedList<Notification>();
		for (Node node : nodes) {
			notifications.add(NotificationFactory.createNotification(node));
		}
		return notifications;
	}
}
