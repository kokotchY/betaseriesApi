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
	 * TODO Not the same as the other
	 * 
	 * @param json
	 *            json object
	 * @return Episode
	 */
	public static Episode createEpisode(JSONObject json) {
		Episode episode = new Episode();
		episode.setShow(UtilsJson.getStringValue(json, Constants.SHOW));
		episode.setNb(UtilsJson.getStringValue(json, Constants.NUMBER));
		episode.setShowUrl(UtilsJson.getStringValue(json, Constants.URL));
		episode.setDate(UtilsJson.getIntValue(json, Constants.DATE));
		episode.setTitle(UtilsJson.getStringValue(json, Constants.TITLE));
		episode.setSeen(UtilsJson.getBooleanValue(json, Constants.HAS_SEEN));
		episode.setDownloaded(UtilsJson.getBooleanValue(json,
				Constants.DOWNLOADED));
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
		String number = UtilsXml.readString(node, Constants.NUMBER);
		if (number == null) {
			number = UtilsXml.readString(node, Constants.EPISODE);
		}
		episode.setNb(number);
		episode.setDate(UtilsXml.readInt(node, Constants.DATE));
		episode.setTitle(UtilsXml.readString(node, Constants.TITLE));
		episode
				.setDescription(UtilsXml
						.readString(node, Constants.DESCRIPTION));
		episode.setScreen(UtilsXml.readString(node, Constants.SCREEN));
		episode.setShow(UtilsXml.readString(node, Constants.SHOW));
		episode.setShowUrl(UtilsXml.readString(node, Constants.URL));
		episode.setDownloaded(UtilsXml.readBoolean(node, Constants.DOWNLOADED));

		List<Node> subsNode = node.selectNodes("subs/sub");
		for (Node sub : subsNode) {
			episode.addSubtitle(SubtitleFactory.createSubtitle(sub));
		}

		episode.setSeen(UtilsXml.readBoolean(node, Constants.HAS_SEEN));

		return episode;
	}
}
