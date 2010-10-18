/**
 * 
 */
package com.kokotchy.betaSeriesAPI.api;

/**
 * @author kokotchy
 * 
 */
public interface ITimeline {

	/**
	 * 
	 */
	public void getFriendsTimeline();

	/**
	 * @param nb
	 */
	public void getFriendsTimeline(int nb);

	/**
	 * 
	 */
	public void getHomeTimeline();

	/**
	 * @param nb
	 */
	public void getHomeTimeline(int nb);

	/**
	 * @param user
	 */
	public void getUserTimeline(String user);

	/**
	 * @param user
	 * @param nb
	 */
	public void getUserTimeline(String user, int nb);
}
