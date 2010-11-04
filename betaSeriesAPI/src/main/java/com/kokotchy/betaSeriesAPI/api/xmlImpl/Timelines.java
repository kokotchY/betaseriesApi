package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.ITimelines;
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
	public List<Event> getFriendsTimeline(String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		return getTimeline(UtilsXml.executeQuery("timeline/friends.xml", apiKey, params));
	}

	@Override
	public List<Event> getFriendsTimeline(String token, int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("number", "" + nb);
		return getTimeline(UtilsXml.executeQuery("timeline/friends.xml", apiKey, params));
	}

	@Override
	public List<Event> getHomeTimeline() {
		return getTimeline(UtilsXml.executeQuery("timeline/home.xml", apiKey));
	}

	/**
	 * Return the timeline from the document
	 * 
	 * @param document
	 *            Document
	 * @return List of event
	 */
	@SuppressWarnings("unchecked")
	private List<Event> getTimeline(Document document) {
		List<Event> events = new LinkedList<Event>();
		List<Node> nodes = document.selectNodes("/root/timeline/item");
		for (Node node : nodes) {
			events.add(Event.createEvent(node));
		}
		return events;
	}

	@Override
	public List<Event> getHomeTimeline(int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("number", "" + nb);
		return getTimeline(UtilsXml.executeQuery("timeline/home.xml", apiKey, params));
	}

	@Override
	public List<Event> getTimelineOfUser(String user) {
		return getTimeline(UtilsXml.executeQuery("timeline/member/" + user + ".xml", apiKey));
	}

	@Override
	public List<Event> getTimelineOfUser(String user, int nb) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("number", "" + nb);
		return getTimeline(UtilsXml.executeQuery("timeline/member/" + user + ".xml", apiKey, params));
	}

}
