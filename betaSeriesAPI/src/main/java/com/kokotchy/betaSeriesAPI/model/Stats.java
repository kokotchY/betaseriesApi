package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Model of statistics
 * 
 * @author kokotchy
 */
public class Stats {

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
	 * TODO Fill it
	 */
	private int nbFriends;

	/**
	 * TODO Fill it
	 */
	private int nbBadges;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Stats)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

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
	 * TODO Fill it
	 * 
	 * @return Return the nbBadges to get
	 */
	public int getNbBadges() {
		return nbBadges;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return Return the nbFriends to get
	 */
	public int getNbFriends() {
		return nbFriends;
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

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, shows);
		result = HashCodeUtil.hash(result, seasons);
		result = HashCodeUtil.hash(result, episodes);
		result = HashCodeUtil.hash(result, progress);
		result = HashCodeUtil.hash(result, episodesToWatch);
		result = HashCodeUtil.hash(result, timeOnTv);
		result = HashCodeUtil.hash(result, timeToSpend);
		result = HashCodeUtil.hash(result, nbFriends);
		return result;
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
	 * TODO Fill it
	 * Set the field with the given value
	 * nbBadges
	 */
	public void setNbBadges(int nbBadges) {
		this.nbBadges = nbBadges;
	}

	/**
	 * TODO Fill it
	 * Set the field with the given value
	 * nbFriends
	 */
	public void setNbFriends(int nbFriends) {
		this.nbFriends = nbFriends;
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
