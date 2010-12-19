package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Stats;

/**
 * Stats factory
 * 
 * @author kokotchy
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
			stats.setShows(jsonObject.getInt(Constants.SHOWS));
			stats.setSeasons(jsonObject.getInt(Constants.SEASONS));
			stats.setEpisodes(jsonObject.getInt(Constants.EPISODES));
			stats.setProgress(jsonObject.getString(Constants.PROGRESS));
			stats.setEpisodesToWatch(jsonObject
					.getInt(Constants.EPISODES_TO_WATCH));
			stats.setTimeOnTv(jsonObject.getInt(Constants.TIME_ON_TV));
			stats.setTimeToSpend(jsonObject.getInt(Constants.TIME_TO_SPEND));
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
		stats.setShows(UtilsXml.readInt(node, Constants.SHOWS));
		stats.setSeasons(UtilsXml.readInt(node, Constants.SEASONS));
		stats.setEpisodes(UtilsXml.readInt(node, Constants.EPISODES));
		stats.setProgress(UtilsXml.readString(node, Constants.PROGRESS));
		stats.setEpisodesToWatch(UtilsXml.readInt(node,
				Constants.EPISODES_TO_WATCH));
		stats.setTimeOnTv(UtilsXml.readInt(node, Constants.TIME_ON_TV));
		stats.setTimeToSpend(UtilsXml.readInt(node, Constants.TIME_TO_SPEND));
		return stats;
	}
}
