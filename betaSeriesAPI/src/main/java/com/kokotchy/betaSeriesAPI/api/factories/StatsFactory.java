package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Stats;

/**
 * @author canas
 */
public class StatsFactory {
	/**
	 * Create stats from json Object
	 * 
	 * @param jsonObject
	 *            JSON Object
	 * @return Stats
	 */
	public static Stats createStats(JSONObject jsonObject) {
		Stats stats = new Stats();
		try {
			stats.setShows(jsonObject.getInt("shows"));
			stats.setSeasons(jsonObject.getInt("seasons"));
			stats.setEpisodes(jsonObject.getInt("episodes"));
			stats.setProgress(jsonObject.getString("progress"));
			stats.setEpisodesToWatch(jsonObject.getInt("episodes_to_watch"));
			stats.setTimeOnTv(jsonObject.getInt("time_on_tv"));
			stats.setTimeToSpend(jsonObject.getInt("time_to_spend"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return stats;
	}

	/**
	 * Create a new stat from the node
	 * 
	 * @param node
	 *            Node
	 * @return Stat
	 */
	public static Stats createStats(Node node) {
		Stats stats = new Stats();
		stats.setShows(UtilsXml.readInt(node, "shows"));
		stats.setSeasons(UtilsXml.readInt(node, "seasons"));
		stats.setEpisodes(UtilsXml.readInt(node, "episodes"));
		stats.setProgress(UtilsXml.readString(node, "progress"));
		stats.setEpisodesToWatch(UtilsXml.readInt(node, "episodes_to_watch"));
		stats.setTimeOnTv(UtilsXml.readInt(node, "time_on_tv"));
		stats.setTimeToSpend(UtilsXml.readInt(node, "time_to_spend"));
		return stats;
	}
}
