package com.kokotchy.betaSeriesAPI.model;

import java.util.List;

/**
 * @author kokotchy
 */
public class Member {

	/**
	 * 
	 */
	private String login;

	/**
	 * 
	 */
	private String avatar;

	/**
	 * 
	 */
	private Stats stats;

	/**
	 * 
	 */
	private List<Show> shows;

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return the shows
	 */
	public List<Show> getShows() {
		return shows;
	}

	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
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
