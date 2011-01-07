package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
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
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		return getTimeline(UtilsXml.executeQuery("timeline/friends", apiKey,
				params));
	}

	@Override
	public Set<Event> getFriendsTimeline(String token, int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		params.put(Constants.LIMIT, "" + nb);
		return getTimeline(UtilsXml.executeQuery("timeline/friends", apiKey,
				params));
	}

	@Override
	public Set<Event> getHomeTimeline() {
		return getTimeline(UtilsXml.executeQuery("timeline/home", apiKey));
	}

	@Override
	public Set<Event> getHomeTimeline(int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.LIMIT, "" + nb);
		return getTimeline(UtilsXml.executeQuery("timeline/home", apiKey,
				params));
	}

	/**
	 * Return the timeline from the document
	 * 
	 * @param document
	 *            Document
	 * @return List of event
	 */
	@SuppressWarnings("unchecked")
	private Set<Event> getTimeline(Document document) {
		Set<Event> events = new HashSet<Event>();
		List<Node> nodes = document.selectNodes("/root/timeline/item");
		if (nodes.size() > 0) {
			for (Node node : nodes) {
				events.add(EventFactory.createEvent(node));
			}
		}
		return events;
	}

	@Override
	public Set<Event> getTimelineOfUser(String user) {
		return getTimeline(UtilsXml.executeQuery("timeline/member/" + user,
				apiKey));
	}

	@Override
	public Set<Event> getTimelineOfUser(String user, int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.LIMIT, "" + nb);
		return getTimeline(UtilsXml.executeQuery("timeline/member/" + user,
				apiKey, params));
	}

}
