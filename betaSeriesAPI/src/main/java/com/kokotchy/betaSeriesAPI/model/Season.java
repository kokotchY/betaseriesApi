/**
 * 
 */
package com.kokotchy.betaSeriesAPI.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kokotchy
 * 
 */
public class Season {

	/**
	 * 
	 */
	private int number;

	/**
	 * 
	 */
	private Map<String, Episode> episodes;

	/**
	 * @param number
	 */
	public Season(int number) {
		this.number = number;
		episodes = new HashMap<String, Episode>();
	}

	/**
	 * @param episode
	 */
	public void addEpisode(Episode episode) {
		episodes.put(episode.getNb(), episode);
	}

	/**
	 * @param number
	 * @return
	 */
	public Episode getEpisode(String number) {
		return episodes.get(number);
	}

	/**
	 * @return the episodes
	 */
	public Map<String, Episode> getEpisodes() {
		return episodes;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

}
