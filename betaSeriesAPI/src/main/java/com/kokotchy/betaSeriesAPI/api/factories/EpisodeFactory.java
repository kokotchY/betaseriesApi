package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Subtitle;

/**
 * @author canas
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
		episode.setShow(UtilsJson.getStringValue(json, "show"));
		episode.setNb(UtilsJson.getStringValue(json, "number"));
		episode.setShowUrl(UtilsJson.getStringValue(json, "url"));
		episode.setDate(UtilsJson.getIntValue(json, "date"));
		episode.setTitle(UtilsJson.getStringValue(json, "title"));
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
		String number = UtilsXml.readString(node, "number");
		if (number == null) {
			number = UtilsXml.readString(node, "episode");
		}
		episode.setNb(number);
		episode.setDate(UtilsXml.readInt(node, "date"));
		episode.setTitle(UtilsXml.readString(node, "title"));
		episode.setDescription(UtilsXml.readString(node, "description"));
		episode.setScreen(UtilsXml.readString(node, "screen"));
		episode.setShow(UtilsXml.readString(node, "show"));
		episode.setShowUrl(UtilsXml.readString(node, "url"));

		List<Node> subsNode = node.selectNodes("subs/sub");
		for (Node sub : subsNode) {
			episode.addSubtitle(Subtitle.createSubtitle(sub));
		}

		return episode;
	}
}
