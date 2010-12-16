package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * Episode factory
 * 
 * @author kokotchy
 */
public class EpisodeFactory {
	/**
	 * TODO Fill it
	 */
	private static final String SCREEN = "screen";

	/**
	 * TODO Fill it
	 */
	private static final String DESCRIPTION = "description";

	/**
	 * TODO Fill it
	 */
	private static final String EPISODE = "episode";

	/**
	 * TODO Fill it
	 */
	private static final String HAS_SEEN = "has_seen";

	/**
	 * TODO Fill it
	 */
	private static final String TITLE = "title";

	/**
	 * TODO Fill it
	 */
	private static final String DATE = "date";

	/**
	 * TODO Fill it
	 */
	private static final String URL = "url";

	/**
	 * TODO Fill it
	 */
	private static final String NUMBER = "number";

	/**
	 * TODO Fill it
	 */
	private static final String SHOW = "show";

	/**
	 * TODO Fill it
	 */
	private static final String DOWNLOADED = "downloaded";

	/**
	 * Create a new episode from the json object TODO Not the same as the other
	 * 
	 * @param json
	 *            json object
	 * @return Episode
	 */
	public static Episode createEpisode(JSONObject json) {
		Episode episode = new Episode();
		episode.setShow(UtilsJson.getStringValue(json, SHOW));
		episode.setNb(UtilsJson.getStringValue(json, NUMBER));
		episode.setShowUrl(UtilsJson.getStringValue(json, URL));
		episode.setDate(UtilsJson.getIntValue(json, DATE));
		episode.setTitle(UtilsJson.getStringValue(json, TITLE));
		episode.setSeen(UtilsJson.getBooleanValue(json, HAS_SEEN));
		episode.setDownloaded(UtilsJson.getBooleanValue(json, DOWNLOADED));
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
		String number = UtilsXml.readString(node, NUMBER);
		if (number == null) {
			number = UtilsXml.readString(node, EPISODE);
		}
		episode.setNb(number);
		episode.setDate(UtilsXml.readInt(node, DATE));
		episode.setTitle(UtilsXml.readString(node, TITLE));
		episode.setDescription(UtilsXml.readString(node, DESCRIPTION));
		episode.setScreen(UtilsXml.readString(node, SCREEN));
		episode.setShow(UtilsXml.readString(node, SHOW));
		episode.setShowUrl(UtilsXml.readString(node, URL));
		episode.setDownloaded(UtilsXml.readBoolean(node, DOWNLOADED));

		List<Node> subsNode = node.selectNodes("subs/sub");
		for (Node sub : subsNode) {
			episode.addSubtitle(SubtitleFactory.createSubtitle(sub));
		}

		episode.setSeen(UtilsXml.readBoolean(node, HAS_SEEN));

		return episode;
	}
}
