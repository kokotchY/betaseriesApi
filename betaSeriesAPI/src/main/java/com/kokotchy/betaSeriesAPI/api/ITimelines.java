package com.kokotchy.betaSeriesAPI.api;

import java.util.Set;

import com.kokotchy.betaSeriesAPI.model.Event;

/**
 * Interface to the timeline of the events on the website
 * 
 * @author kokotchy
 */
public interface ITimelines {

	/**
	 * Return the timeline for the friends of the logged user
	 * 
	 * @param token
	 *            Token
	 * @return List of event
	 */
	public Set<Event> getFriendsTimeline(String token);

	/**
	 * Return the timeline for the friends of the logged user limited by the
	 * number
	 * 
	 * @param token
	 *            Token
	 * @param nb
	 *            Max number of event
	 * @return List of event
	 */
	public Set<Event> getFriendsTimeline(String token, int nb);

	/**
	 * Return the last events on the website (max 100)
	 * 
	 * @return List of event
	 */
	public Set<Event> getHomeTimeline();

	/**
	 * Return the last events on the website limited to the number
	 * 
	 * @param nb
	 *            Number of events
	 * @return List of event
	 */
	public Set<Event> getHomeTimeline(int nb);

	/**
	 * Return the timeline of the given user (max 100)
	 * 
	 * @param user
	 *            User
	 * @return List of event
	 */
	public Set<Event> getTimelineOfUser(String user);

	/**
	 * Return the timeline of the given user limited by the number
	 * 
	 * @param user
	 *            User
	 * @param nb
	 *            Number of event
	 * @return List of event
	 */
	public Set<Event> getTimelineOfUser(String user, int nb);
}
