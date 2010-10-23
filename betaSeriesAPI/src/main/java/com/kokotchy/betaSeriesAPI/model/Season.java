package com.kokotchy.betaSeriesAPI.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Season of a show
 * 
 * @author kokotchy
 */
public class Season {

	/**
	 * Number of the season
	 */
	private int number;

	/**
	 * List of episodes
	 */
	private Map<String, Episode> episodes;

	/**
	 * Create a new season with the given number
	 * 
	 * @param number
	 *            Number of the season
	 */
	public Season(int number) {
		this.number = number;
		episodes = new HashMap<String, Episode>();
	}

	/**
	 * Add the episode to the season
	 * 
	 * @param episode
	 *            Episode
	 */
	public void addEpisode(Episode episode) {
		episodes.put(episode.getNb(), episode);
	}

	/**
	 * Return the episode with the given number
	 * 
	 * @param number
	 *            Number of the episode
	 * @return Episode with the given episode
	 */
	public Episode getEpisode(String number) {
		return episodes.get(number);
	}

	/**
	 * Return the list of episodes of the season
	 * 
	 * @return the episodes
	 */
	public Map<String, Episode> getEpisodes() {
		return episodes;
	}

	/**
	 * Return the number of the season
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Set the number of the season
	 * 
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "S" + number;
	}

}
