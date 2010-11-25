package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Event;

/**
 * Event factory
 * 
 * @author kokotchy
 */
public class EventFactory {
	/**
	 * Create an event from a json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Event
	 */
	public static Event createEvent(JSONObject jsonObject) {
		Event event = new Event();
		event.setType(UtilsJson.getStringValue(jsonObject, "type"));
		event.setRef(UtilsJson.getStringValue(jsonObject, "ref"));
		event.setLogin(UtilsJson.getStringValue(jsonObject, "login"));
		event.setHtml(UtilsJson.getStringValue(jsonObject, "html"));
		event.setDate(UtilsJson.getIntValue(jsonObject, "date"));
		return event;
	}

	/**
	 * Create a new event from a node
	 * 
	 * @param node
	 *            Node
	 * @return Event
	 */
	public static Event createEvent(Node node) {
		Event event = new Event();
		event.setType(UtilsXml.readString(node, "type"));
		event.setRef(UtilsXml.readString(node, "ref"));
		event.setLogin(UtilsXml.readString(node, "login"));
		event.setHtml(UtilsXml.readString(node, "html"));
		event.setDate(UtilsXml.readInt(node, "date"));
		return event;
	}
}
