/**
 * 
 */
package com.kokotchy.betaSeriesAPI.api;

/**
 * @author kokotchy
 * 
 */
public interface IComments {

	/**
	 * @param url
	 */
	public void getComments(String url);

	/**
	 * @param url
	 * @param season
	 * @param episode
	 */
	public void getComments(String url, int season, int episode);

	/**
	 * @param login
	 */
	public void getUserComments(String login);

	/**
	 * @param url
	 * @param text
	 */
	public void postComment(String url, String text);

	/**
	 * @param url
	 * @param text
	 * @param responseTo
	 */
	public void postComment(String url, String text, int responseTo);

	/**
	 * @param url
	 * @param text
	 * @param responseTo
	 * @param season
	 * @param episode
	 */
	public void postComment(String url, String text, int responseTo,
			int season, int episode);

	/**
	 * @param login
	 * @param text
	 * @param responseTo
	 */
	public void postUserComment(String login, String text, int responseTo);
}
