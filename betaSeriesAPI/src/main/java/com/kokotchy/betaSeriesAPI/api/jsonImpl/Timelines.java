package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.ITimelines;
import com.kokotchy.betaSeriesAPI.model.Event;

/**
 * Timeline API
 * 
 * @author kokotchy
 * 
 */
public class Timelines implements ITimelines {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Timelines
	 * 
	 * @param apiKey
	 *            API key
	 */
	public Timelines(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Event> getFriendsTimeline(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		return getTimeline(UtilsJson.executeQuery("timeline/friends", apiKey,
				params));
	}

	@Override
	public List<Event> getFriendsTimeline(String token, int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("number", "" + nb);
		return getTimeline(UtilsJson.executeQuery("timeline/friends", apiKey,
				params));
	}

	@Override
	public List<Event> getHomeTimeline() {
		return getTimeline(UtilsJson.executeQuery("timeline/home", apiKey));
	}

	@Override
	public List<Event> getHomeTimeline(int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("number", "" + nb);
		return getTimeline(UtilsJson.executeQuery("timeline/home", apiKey,
				params));
	}

	/**
	 * Return the timeline from the document
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return List of event
	 */
	private List<Event> getTimeline(JSONObject jsonObject) {
		List<Event> events = new LinkedList<Event>();
		JSONArray eventsArray = UtilsJson.getJSONArrayFromPath(jsonObject,
				"/root/timeline");
		int length = eventsArray.length();
		try {
			for (int i = 0; i < length; i++) {
				events.add(Event.createEvent(eventsArray.getJSONObject(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return events;
	}

	@Override
	public List<Event> getTimelineOfUser(String user) {
		return getTimeline(UtilsJson.executeQuery("timeline/member/" + user,
				apiKey));
	}

	@Override
	public List<Event> getTimelineOfUser(String user, int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("number", "" + nb);
		return getTimeline(UtilsJson.executeQuery("timeline/member/" + user,
				apiKey, params));
	}
}
