package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.IPlanning;
import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * Planning API
 * 
 * @author kokotchy
 */
public class Planning implements IPlanning {

	/**
	 * Api key
	 */
	private String apiKey;

	/**
	 * Create a new planning with the key
	 * 
	 * @param apiKey
	 *            Api key
	 */
	public Planning(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Episode> getGeneralPlanning() {
		return getPlanning(null, null, null);
	}

	@Override
	public List<Episode> getMemberPlanning(boolean unseen, String token) {
		return getPlanning(unseen, token, true);
	}

	@Override
	public List<Episode> getMemberPlanning(String login, boolean unseen) {
		return getPlanning(unseen, login, false);
	}

	/**
	 * Return the planning. If all parameters are null, return the general
	 * planning. Unseen parameter used to select only unseen episodes. Token is
	 * used for the identified user if identifiedUser, or the login of the user
	 * if not identifiedUser
	 * 
	 * @param unseen
	 *            Only uneseen erpisode
	 * @param token
	 *            If identifiedUser => identified user toke, otherwise login of
	 *            the member
	 * @param identifiedUser
	 * @return List of episodes
	 */
	private List<Episode> getPlanning(Boolean unseen, String token,
			Boolean identifiedUser) {
		JSONObject jsonObject = null;
		if (unseen == null && token == null && identifiedUser == null) {
			jsonObject = UtilsJson
					.executeQuery("planning/general.json", apiKey);
		} else {
			Map<String, String> params = new HashMap<String, String>();
			String action;
			if (unseen != null && unseen) {
				params.put("view", "unseen");
			}
			if (identifiedUser) {
				params.put("token", token);
				action = "planning/member.json";
			} else {
				action = "planning/member/" + token + ".json";
			}
			jsonObject = UtilsJson.executeQuery(action, apiKey, params);
		}

		JSONArray jsonArrayFromPath = UtilsJson.getJSONArrayFromPath(
				jsonObject, "/root/planning");
		List<Episode> episodes = new LinkedList<Episode>();
		try {
			int length = jsonArrayFromPath.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = jsonArrayFromPath.getJSONObject(i);
				episodes.add(Episode.createEpisode(object));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return episodes;
	}
}
