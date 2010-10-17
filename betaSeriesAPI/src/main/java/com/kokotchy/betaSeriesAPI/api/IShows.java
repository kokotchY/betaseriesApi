package com.kokotchy.betaSeriesAPI.api;

import java.util.List;

import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * @author kokotchy
 */
public interface IShows {

	/**
	 * TODO Fill it
	 * 
	 * @param url
	 * @param token
	 * @return
	 */
	public boolean add(String url, String token);

	/**
	 * Display the show from the given url
	 * 
	 * @param url
	 *            Url of the show
	 */
	public Show display(String url);

	/**
	 * Display all available shows
	 * 
	 * @return List of shows
	 */
	public List<Show> displayAll();

	/**
	 * Return the episodes of the given url
	 * 
	 * @param url
	 *            Url of the show
	 * @return List of seasons with the episodes
	 */
	public List<Season> getEpisodes(String url);

	/**
	 * Return the episodes of the show for the given season
	 * 
	 * @param url
	 *            Url of the show
	 * @param seasonNb
	 *            Number of the season
	 * @return Season with the episodes
	 */
	public Season getEpisodes(String url, int seasonNb);

	/**
	 * TODO Fill it
	 * 
	 * @param url
	 * @param token
	 * @return
	 */
	public boolean remove(String url, String token);

	/**
	 * Search for episodes with the given title
	 * 
	 * @param title
	 *            Title to search
	 * @return List of shows with the matching title
	 */
	public List<Show> search(String title);
}
