package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.api.factories.EpisodeFactory;
import com.kokotchy.betaSeriesAPI.api.factories.MemberFactory;
import com.kokotchy.betaSeriesAPI.api.factories.NotificationFactory;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;
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
		params.put("login", login);
		params.put("password", Utils.getMD5(password));
		Document document = UtilsXml.executeQuery("members/auth", apiKey,
				params);
		if (!UtilsXml.hasErrors(document)) {
			Node tokenNode = document.selectSingleNode("/root/member/token");
			return tokenNode.getText();
		}
		return null;
	}

	@Override
	public void destroy(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		UtilsXml.executeQuery("members/destroy", apiKey, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage) {
		String lang = null;
		switch (subtitleLanguage) {
		case VF:
			lang = "vf";
			break;
		case VOVF:
			lang = "vovf";
			break;
		case ALL:
			lang = "all";
			break;
		}
		List<Episode> result = new LinkedList<Episode>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
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
			params.put("token", user);
			document = UtilsXml.executeQuery("members/infos", apiKey, params);
		}
		return MemberFactory.createMember(document
				.selectSingleNode("/root/member"));
	}

	@Override
	public List<Notification> getNotifications(String token, boolean seen) {
		return getNotificationsWithParameters(token, seen, -1, -1);
	}

	@Override
	public List<Notification> getNotifications(String token, boolean seen,
			int nb) {
		return getNotificationsWithParameters(token, seen, nb, -1);
	}

	@Override
	public List<Notification> getNotifications(String token, boolean seen,
			int nb, int lastId) {
		return getNotificationsWithParameters(token, seen, nb, lastId);
	}

	@Override
	public List<Notification> getNotifications(String token, int nb) {
		return getNotificationsWithParameters(token, null, nb, -1);
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
	 * @return List of notification
	 */
	@SuppressWarnings("unchecked")
	private List<Notification> getNotificationsWithParameters(String token,
			Boolean seen, int nb, int lastId) {
		Map<String, String> params = new HashMap<String, String>();
		if (seen != null) {
			params.put("seen", seen ? "yes" : "no");
		}
		if (nb > 0) {
			params.put("number", "" + nb);
		}
		if (lastId > 0) {
			params.put("last_id", "" + lastId);
		}
		params.put("token", token);
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

	@Override
	public Member infos(String token) {
		return getInfosForUser(token, true);
	}

	@Override
	public Member infosOfUser(String user) {
		return getInfosForUser(user, false);
	}

	@Override
	public boolean isActive(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		Document document = UtilsXml.executeQuery("members/is_active", apiKey,
				params);
		Node node = document.selectSingleNode("/root");
		return UtilsXml.readBoolean(node, "code");
	}

	@Override
	public void resetViewedShow(String token, String url) {
		setWatched(token, url, 0, 0);
	}

	@Override
	public void setWatched(String token, String url, int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("season", "" + season);
		params.put("episode", "" + episode);
		params.put("token", token);
		UtilsXml.executeQuery("members/watched/" + url, apiKey, params);
	}
}
