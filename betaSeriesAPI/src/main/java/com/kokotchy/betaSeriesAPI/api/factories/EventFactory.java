package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
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
		event.setType(UtilsJson.getStringValue(jsonObject, Constants.TYPE));
		event.setRef(UtilsJson.getStringValue(jsonObject, Constants.REF));
		event.setLogin(UtilsJson.getStringValue(jsonObject, Constants.LOGIN));
		event.setHtml(UtilsJson.getStringValue(jsonObject, Constants.HTML));
		event.setDate(UtilsJson.getIntValue(jsonObject, Constants.DATE));
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
		event.setType(UtilsXml.getString(node, Constants.TYPE));
		event.setRef(UtilsXml.getString(node, Constants.REF));
		event.setLogin(UtilsXml.getString(node, Constants.LOGIN));
		event.setHtml(UtilsXml.getString(node, Constants.HTML));
		event.setDate(UtilsXml.getInt(node, Constants.DATE));
		return event;
	}
}
