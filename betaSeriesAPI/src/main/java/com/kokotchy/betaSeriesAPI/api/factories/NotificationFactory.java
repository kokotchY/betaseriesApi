package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Notification;

/**
 * Notification factory
 * 
 * @author kokotchy
 */
public class NotificationFactory {
	/**
	 * Create a notification from json object
	 * 
	 * @param jsonObject
	 *            JSON object
	 * @return Notification
	 */
	public static Notification createNotification(JSONObject jsonObject) {
		Notification notification = new Notification();
		try {
			notification.setId(jsonObject.getInt("id"));
			notification.setHtml(jsonObject.getString("html"));
			notification.setDate(jsonObject.getInt("date"));
			notification.setSeen(UtilsJson.getJSONBooleanFromPath(jsonObject,
					"seen"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return notification;
	}

	/**
	 * Create a notification from a node
	 * 
	 * @param node
	 *            Node
	 * @return Notification
	 */
	public static Notification createNotification(Node node) {
		Notification notification = new Notification();
		notification.setId(UtilsXml.readInt(node, "id"));
		notification.setHtml(UtilsXml.readString(node, "html"));
		notification.setDate(UtilsXml.readInt(node, "date"));
		notification.setSeen(UtilsXml.readBoolean(node, "seen"));
		return notification;
	}
}
