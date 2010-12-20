package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

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
	 */
	private Stats stats;

	/**
	 * List of shows followed by the member
	 */
	private List<Show> shows = new LinkedList<Show>();

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
	public boolean equals(Object obj) {
		if (!(obj instanceof Member)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

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

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, login);
		result = HashCodeUtil.hash(result, avatar);
		result = HashCodeUtil.hash(result, stats);
		result = HashCodeUtil.hash(result, shows);
		return result;
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

	@Override
	public String toString() {
		return login + " with " + shows.size() + " shows";
	}
}
