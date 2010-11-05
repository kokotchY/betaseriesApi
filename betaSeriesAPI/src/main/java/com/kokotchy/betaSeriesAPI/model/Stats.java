package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Model of statistics
 * 
 * @author kokotchy
 */
public class Stats {

	/**
	 * TODO Fill it
	 * 
	 * @param jsonObject
	 * @return
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
			// TODO Auto-generated catch block
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

	/**
	 * Number of shows
	 */
	private int shows;

	/**
	 * Number of seasons
	 */
	private int seasons;

	/**
	 * Number of episodes
	 */
	private int episodes;

	/**
	 * General progress of episodes
	 * 
	 * TODO Better type
	 */
	private String progress;

	/**
	 * Remaining episodes to watch
	 */
	private int episodesToWatch;

	/**
	 * Time spended watching episodes
	 */
	private int timeOnTv;

	/**
	 * Remaining time to watch remaining episodes
	 */
	private int timeToSpend;

	/**
	 * Return number of episodes
	 * 
	 * @return the episodes
	 */
	public int getEpisodes() {
		return episodes;
	}

	/**
	 * Return number of episodes to watch
	 * 
	 * @return the episodesToWatch
	 */
	public int getEpisodesToWatch() {
		return episodesToWatch;
	}

	/**
	 * Return general progress
	 * 
	 * @return the progress
	 */
	public String getProgress() {
		return progress;
	}

	/**
	 * Return number of seasons
	 * 
	 * @return the seasons
	 */
	public int getSeasons() {
		return seasons;
	}

	/**
	 * Return number of shows
	 * 
	 * @return the shows
	 */
	public int getShows() {
		return shows;
	}

	/**
	 * Return time spend watching episodes
	 * 
	 * @return the timeOnTv
	 */
	public int getTimeOnTv() {
		return timeOnTv;
	}

	/**
	 * Return time to spend to watch remaining episodes
	 * 
	 * @return the timeToSpend
	 */
	public int getTimeToSpend() {
		return timeToSpend;
	}

	/**
	 * Set the number of episodes
	 * 
	 * @param episodes
	 *            the episodes to set
	 */
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	/**
	 * Set the number of episodes to watch
	 * 
	 * @param episodesToWatch
	 *            the episodesToWatch to set
	 */
	public void setEpisodesToWatch(int episodesToWatch) {
		this.episodesToWatch = episodesToWatch;
	}

	/**
	 * Set the general progress
	 * 
	 * @param progress
	 *            the progress to set
	 */
	public void setProgress(String progress) {
		this.progress = progress;
	}

	/**
	 * Set the number of seasons
	 * 
	 * @param seasons
	 *            the seasons to set
	 */
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	/**
	 * Set the number of shows
	 * 
	 * @param shows
	 *            the shows to set
	 */
	public void setShows(int shows) {
		this.shows = shows;
	}

	/**
	 * Set the time spended watching episodes (in seconds)
	 * 
	 * @param timeOnTv
	 *            the timeOnTv to set
	 */
	public void setTimeOnTv(int timeOnTv) {
		this.timeOnTv = timeOnTv;
	}

	/**
	 * Set the time to spend to watch remaining episodes
	 * 
	 * @param timeToSpend
	 *            the timeToSpend to set
	 */
	public void setTimeToSpend(int timeToSpend) {
		this.timeToSpend = timeToSpend;
	}

	@Override
	public String toString() {
		String pattern = "%s = %s\n";
		String result = "";
		result += String.format(pattern, "shows", shows);
		result += String.format(pattern, "seasons", seasons);
		result += String.format(pattern, "episodes", episodes);
		result += String.format(pattern, "progress", progress);
		result += String.format(pattern, "episodes_to_watch", episodesToWatch);
		result += String.format(pattern, "time_on_tv", timeOnTv);
		result += String.format(pattern, "time_to_spend", timeToSpend);
		return result;
	}
}
