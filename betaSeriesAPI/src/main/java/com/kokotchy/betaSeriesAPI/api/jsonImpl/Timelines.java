package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.kokotchy.betaSeriesAPI.api.ITimelines;
import com.kokotchy.betaSeriesAPI.model.Event;

/**
 * Timeline API
 * 
 * @author kokotchy
 * 
 */
public class Timelines implements ITimelines {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Timelines
	 * 
	 * @param apiKey
	 *            API key
	 */
	public Timelines(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Event> getFriendsTimeline(String token) {
		throw new NotImplementedException();
	}

	@Override
	public List<Event> getFriendsTimeline(String token, int nb) {
		throw new NotImplementedException();
	}

	@Override
	public List<Event> getHomeTimeline() {
		throw new NotImplementedException();
	}

	@Override
	public List<Event> getHomeTimeline(int nb) {
		throw new NotImplementedException();
	}

	@Override
	public List<Event> getTimelineOfUser(String user) {
		throw new NotImplementedException();
	}

	@Override
	public List<Event> getTimelineOfUser(String user, int nb) {
		throw new NotImplementedException();
	}

}
