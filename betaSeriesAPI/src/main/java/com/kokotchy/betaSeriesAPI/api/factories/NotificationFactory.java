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
	 * TODO Fill it
	 */
	private static final String SEEN = "seen";

	/**
	 * TODO Fill it
	 */
	private static final String DATE = "date";

	/**
	 * TODO Fill it
	 */
	private static final String HTML = "html";

	/**
	 * TODO Fill it
	 */
	private static final String ID = "id";

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
			notification.setId(jsonObject.getInt(ID));
			notification.setHtml(jsonObject.getString(HTML));
			notification.setDate(jsonObject.getInt(DATE));
			notification.setSeen(UtilsJson.getJSONBooleanFromPath(jsonObject,
					SEEN));
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
		notification.setId(UtilsXml.readInt(node, ID));
		notification.setHtml(UtilsXml.readString(node, HTML));
		notification.setDate(UtilsXml.readInt(node, DATE));
		notification.setSeen(UtilsXml.readBoolean(node, SEEN));
		return notification;
	}
}
