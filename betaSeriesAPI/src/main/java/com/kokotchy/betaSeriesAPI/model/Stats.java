package com.kokotchy.betaSeriesAPI.model;

/**
 * @author kokotchy
 * 
 */
public class Stats {

	/**
	 * 
	 */
	private int shows;

	/**
	 * 
	 */
	private int seasons;

	/**
	 * 
	 */
	private int episodes;

	/**
	 * TODO Better type
	 */
	private String progress;

	/**
	 * 
	 */
	private int episodesToWatch;

	/**
	 * 
	 */
	private String timeOnTv;

	/**
	 * 
	 */
	private String timeToSpend;

	/**
	 * @return the episodes
	 */
	public int getEpisodes() {
		return episodes;
	}

	/**
	 * @return the episodesToWatch
	 */
	public int getEpisodesToWatch() {
		return episodesToWatch;
	}

	/**
	 * @return the progress
	 */
	public String getProgress() {
		return progress;
	}

	/**
	 * @return the seasons
	 */
	public int getSeasons() {
		return seasons;
	}

	/**
	 * @return the shows
	 */
	public int getShows() {
		return shows;
	}

	/**
	 * @return the timeOnTv
	 */
	public String getTimeOnTv() {
		return timeOnTv;
	}

	/**
	 * @return the timeToSpend
	 */
	public String getTimeToSpend() {
		return timeToSpend;
	}

	/**
	 * @param episodes
	 *            the episodes to set
	 */
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	/**
	 * @param episodesToWatch
	 *            the episodesToWatch to set
	 */
	public void setEpisodesToWatch(int episodesToWatch) {
		this.episodesToWatch = episodesToWatch;
	}

	/**
	 * @param progress
	 *            the progress to set
	 */
	public void setProgress(String progress) {
		this.progress = progress;
	}

	/**
	 * @param seasons
	 *            the seasons to set
	 */
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	/**
	 * @param shows
	 *            the shows to set
	 */
	public void setShows(int shows) {
		this.shows = shows;
	}

	/**
	 * @param timeOnTv
	 *            the timeOnTv to set
	 */
	public void setTimeOnTv(String timeOnTv) {
		this.timeOnTv = timeOnTv;
	}

	/**
	 * @param timeToSpend
	 *            the timeToSpend to set
	 */
	public void setTimeToSpend(String timeToSpend) {
		this.timeToSpend = timeToSpend;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
