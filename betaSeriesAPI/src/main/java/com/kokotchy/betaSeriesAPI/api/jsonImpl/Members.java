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
		try {
			token = object.getJSONObject("root").getJSONObject("member")
					.getString("token");
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getNotifications(boolean seen, int nb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getNotifications(boolean seen, int nb, int lastId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getNotifications(int nb) {
		// TODO Auto-generated method stub
		return null;
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
