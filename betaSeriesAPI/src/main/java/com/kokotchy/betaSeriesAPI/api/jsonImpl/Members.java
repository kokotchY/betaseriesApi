package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Members implements IMembers {

	/**
	 * TODO Fill it
	 */
	private String apiKey;

	/**
	 * TODO Fill it
	 */
	private String token;

	/**
	 * TODO Fill it
	 * 
	 * @param apiKey
	 */
	public Members(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean auth(String login, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", login);
		params.put("password", Utils.getMD5(password));
		JSONObject object = UtilsJson.executeQuery("members/auth.json", apiKey,
				params);
		if (!UtilsJson.hasErrors(object)) {
			token = UtilsJson.getJSONStringFromPath(object,
					"/root/member/token");
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		UtilsJson.executeQuery("members/destroy.xml", apiKey, params);
	}

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
		JSONObject jsonObject = UtilsJson.executeQuery("members/episodes/"
				+ lang + ".json", apiKey, params);
		try {
			JSONArray episodes = UtilsJson.getJSONArrayFromPath(jsonObject,
					"/root/episodes");
			for (int i = 0; i < episodes.length(); i++) {
				JSONObject episode = episodes.getJSONObject(i);
				result.add(Episode.createEpisode(episode));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Notification> getNotifications(boolean seen) {
		return getNotificationsWithParameters(seen, -1, -1);
	}

	@Override
	public List<Notification> getNotifications(boolean seen, int nb) {
		return getNotificationsWithParameters(seen, nb, -1);
	}

	@Override
	public List<Notification> getNotifications(boolean seen, int nb, int lastId) {
		return getNotificationsWithParameters(seen, nb, lastId);
	}

	@Override
	public List<Notification> getNotifications(int nb) {
		return getNotificationsWithParameters(null, nb, -1);
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
	 * @return List of notification
	 */
	@SuppressWarnings("unchecked")
	private List<Notification> getNotificationsWithParameters(Boolean seen,
			int nb, int lastId) {
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
		List<Notification> notifications = new LinkedList<Notification>();
		// Document document =
		// UtilsXml.executeQuery("members/notifications.xml",
		// apiKey, params);
		// List<Node> nodes = document
		// .selectNodes("/root/notifications/notification");
		// for (Node node : nodes) {
		// notifications.add(Notification.createNotification(node));
		// }
		JSONObject jsonObject = UtilsJson.executeQuery(
				"members/notifications.json", apiKey, params);
		JSONArray notificationsArray = UtilsJson.getJSONArrayFromPath(
				jsonObject, "/root/notifications");
		try {
			for (int i = 0; i < notificationsArray.length(); i++) {
				JSONObject notification = notificationsArray.getJSONObject(i);
				notifications
						.add(Notification.createNotification(notification));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notifications;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	@Override
	public Member infos(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member infosOfUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActive(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetViewedShow(String token, String url) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWatched(String token, String url, int season, int episode) {
		// TODO Auto-generated method stub

	}

}
