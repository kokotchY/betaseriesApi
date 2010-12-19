package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
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
			notification.setId(jsonObject.getInt(Constants.ID));
			notification.setHtml(jsonObject.getString(Constants.HTML));
			notification.setDate(jsonObject.getInt(Constants.DATE));
			notification.setSeen(UtilsJson.getJSONBooleanFromPath(jsonObject,
					Constants.SEEN));
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
		notification.setId(UtilsXml.readInt(node, Constants.ID));
		notification.setHtml(UtilsXml.readString(node, Constants.HTML));
		notification.setDate(UtilsXml.readInt(node, Constants.DATE));
		notification.setSeen(UtilsXml.readBoolean(node, Constants.SEEN));
		return notification;
	}
}
