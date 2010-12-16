package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Stats;

/**
 * Stats factory
 * 
 * @author kokotchy
 */
public class StatsFactory {
	/**
	 * TODO Fill it
	 */
	private static final String TIME_TO_SPEND = "time_to_spend";

	/**
	 * TODO Fill it
	 */
	private static final String TIME_ON_TV = "time_on_tv";

	/**
	 * TODO Fill it
	 */
	private static final String EPISODES_TO_WATCH = "episodes_to_watch";

	/**
	 * TODO Fill it
	 */
	private static final String PROGRESS = "progress";

	/**
	 * TODO Fill it
	 */
	private static final String EPISODES = "episodes";

	/**
	 * TODO Fill it
	 */
	private static final String SEASONS = "seasons";

	/**
	 * TODO Fill it
	 */
	private static final String SHOWS = "shows";

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
			stats.setShows(jsonObject.getInt(SHOWS));
			stats.setSeasons(jsonObject.getInt(SEASONS));
			stats.setEpisodes(jsonObject.getInt(EPISODES));
			stats.setProgress(jsonObject.getString(PROGRESS));
			stats.setEpisodesToWatch(jsonObject.getInt(EPISODES_TO_WATCH));
			stats.setTimeOnTv(jsonObject.getInt(TIME_ON_TV));
			stats.setTimeToSpend(jsonObject.getInt(TIME_TO_SPEND));
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
		stats.setShows(UtilsXml.readInt(node, SHOWS));
		stats.setSeasons(UtilsXml.readInt(node, SEASONS));
		stats.setEpisodes(UtilsXml.readInt(node, EPISODES));
		stats.setProgress(UtilsXml.readString(node, PROGRESS));
		stats.setEpisodesToWatch(UtilsXml.readInt(node, EPISODES_TO_WATCH));
		stats.setTimeOnTv(UtilsXml.readInt(node, TIME_ON_TV));
		stats.setTimeToSpend(UtilsXml.readInt(node, TIME_TO_SPEND));
		return stats;
	}
}
