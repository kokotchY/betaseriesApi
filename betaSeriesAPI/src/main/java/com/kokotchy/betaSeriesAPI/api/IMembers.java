package com.kokotchy.betaSeriesAPI.api;

/**
 * @author kokotchy
 * 
 */
public interface IMembers {
	/**
	 * Auth the user with his login and password
	 * 
	 * @param login
	 *            Login of the user
	 * @param password
	 *            Password of the user
	 */
	public boolean auth(String login, String password);

	/**
	 * Destroy the token of the user
	 */
	public void destroy();

	/**
	 * @param showType
	 */
	public void getEpisodes(String token, SubtitleLanguage subtitleLanguage);

	/**
	 * TODO Fill it
	 */
	public void infos();

	/**
	 * TODO Fill it
	 * 
	 * @param user
	 */
	public void infos(String user);

	/**
	 * @param url
	 * @param season
	 * @param episode
	 */
	public void setWatched(String token, String url, int season, int episode);
}
