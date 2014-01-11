package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * Episode factory
 * 
 * @author kokotchy
 */
public class EpisodeFactory {
	/**
	 * Create a new episode from the json object
	 * 
	 * @param json
	 *            json object
	 * @return Episode
	 */
	public static Episode createEpisode(JSONObject json) {
		Episode episode = new Episode();
		String nb = UtilsJson.getStringValue(json, Constants.NUMBER);
		if (nb == null) {
			nb = UtilsJson.getStringValue(json, Constants.EPISODE);
		}
		episode.setNb(nb);
		episode.setDate(UtilsJson.getIntValue(json, Constants.DATE));
		episode.setTitle(UtilsJson.getStringValue(json, Constants.TITLE));
		episode.setDescription(UtilsJson.getStringValue(json, Constants.DESCRIPTION));
		episode.setScreen(UtilsJson.getStringValue(json, Constants.SCREEN));
		episode.setShow(UtilsJson.getStringValue(json, Constants.SHOW));
		episode.setShowUrl(UtilsJson.getStringValue(json, Constants.URL));
		episode.setDownloaded(UtilsJson.getBooleanValue(json,
				Constants.DOWNLOADED));

		JSONObject subs = UtilsJson.getJSONObject(json, "subs");
		if (subs != null) {
			JSONObject[] array = UtilsJson.getArray(subs);
			if (array.length > 0) {
				for (JSONObject jsonObject : array) {
					episode.addSubtitle(SubtitleFactory.createSubtitle(jsonObject));
				}
			}
		}
		episode.setSeen(UtilsJson.getBooleanValue(json, Constants.HAS_SEEN));

		return episode;
	}

	/**
	 * Create an episode from the node
	 * 
	 * @param node
	 *            Node
	 * @return Episode from the node
	 */
	@SuppressWarnings("unchecked")
	public static Episode createEpisode(Node node) {
		Episode episode = new Episode();
		String number = UtilsXml.getString(node, Constants.NUMBER);
		if (number == null) {
			number = UtilsXml.getString(node, Constants.EPISODE);
		}
		episode.setNb(number);
		episode.setDate(UtilsXml.getInt(node, Constants.DATE));
		episode.setTitle(UtilsXml.getString(node, Constants.TITLE));
		episode
				.setDescription(UtilsXml
				.getString(node, Constants.DESCRIPTION));
		episode.setScreen(UtilsXml.getString(node, Constants.SCREEN));
		episode.setShow(UtilsXml.getString(node, Constants.SHOW));
		episode.setShowUrl(UtilsXml.getString(node, Constants.URL));
		episode.setDownloaded(UtilsXml.getBoolean(node, Constants.DOWNLOADED));

		List<Node> subsNode = node.selectNodes("subs/sub");
		if (subsNode.size() > 0) {
			for (Node sub : subsNode) {
				episode.addSubtitle(SubtitleFactory.createSubtitle(sub));
			}
		}

		episode.setSeen(UtilsXml.getBoolean(node, Constants.HAS_SEEN));

		return episode;
	}
}
