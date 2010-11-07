package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import com.kokotchy.betaSeriesAPI.api.IShows;
import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Shows implements IShows {

	/**
	 * TODO Fill it
	 */
	private String apiKey;

	/**
	 * TODO Fill it
	 * 
	 * @param apiKey
	 */
	public Shows(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean add(String url, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Show display(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Show> displayAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Season> getEpisodes(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Season getEpisodes(String url, int seasonNb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(String url, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Show> search(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
