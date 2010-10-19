package com.kokotchy.betaSeriesAPI.model;

import java.util.List;

/**
 * Model of a member
 * 
 * @author kokotchy
 */
public class Member {

	/**
	 * Login of the member
	 */
	private String login;

	/**
	 * Avatar of the user
	 */
	private String avatar;

	/**
	 * Statistics about the user
	 * TODO Remove stats object an include directly in Member model
	 */
	private Stats stats;

	/**
	 * List of shows followed by the member
	 */
	private List<Show> shows;

	/**
	 * Return the avatar
	 * 
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Return the login
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Return the shows
	 * 
	 * @return the shows
	 */
	public List<Show> getShows() {
		return shows;
	}

	/**
	 * Return statistics
	 * 
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * Return avatar
	 * 
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Set login
	 * 
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Set stats
	 * 
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	/**
	 * Add the given show
	 * 
	 * @param show
	 *            Show
	 */
	public void addShow(Show show) {
		shows.add(show);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
