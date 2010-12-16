package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.api.ITimelines;
import com.kokotchy.betaSeriesAPI.api.factories.EventFactory;
import com.kokotchy.betaSeriesAPI.model.Event;

/**
 * Timeline API
 * 
 * @author kokotchy
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
	public Set<Event> getFriendsTimeline(String token) {
		// FIXME Check for error
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		return getTimeline(UtilsJson.executeQuery("timeline/friends", apiKey,
				params));
	}

	@Override
	public Set<Event> getFriendsTimeline(String token, int nb) {
		// FIXME Check for error
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		params.put(Constants.LIMIT, "" + nb);
		return getTimeline(UtilsJson.executeQuery("timeline/friends", apiKey,
				params));
	}

	@Override
	public Set<Event> getHomeTimeline() {
		return getTimeline(UtilsJson.executeQuery("timeline/home", apiKey));
	}

	@Override
	public Set<Event> getHomeTimeline(int nb) {
		// FIXME Check for error
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.LIMIT, "" + nb);
		return getTimeline(UtilsJson.executeQuery("timeline/home", apiKey,
				params));
	}

	/**
	 * Return the timeline from the document
	 * FIXME Check for error
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return List of event
	 */
	private Set<Event> getTimeline(JSONObject jsonObject) {
		Set<Event> events = new HashSet<Event>();
		JSONObject eventsList = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/timeline");
		String[] names = JSONObject.getNames(eventsList);
		try {
			for (String name : names) {
				events.add(EventFactory.createEvent(eventsList
						.getJSONObject(name)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return events;
	}

	@Override
	public Set<Event> getTimelineOfUser(String user) {
		return getTimeline(UtilsJson.executeQuery("timeline/member/" + user,
				apiKey));
	}

	@Override
	public Set<Event> getTimelineOfUser(String user, int nb) {
		// FIXME Check for error
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.LIMIT, "" + nb);
		return getTimeline(UtilsJson.executeQuery("timeline/member/" + user,
				apiKey, params));
	}
}
