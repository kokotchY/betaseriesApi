package com.kokotchy.betaSeriesAPI.api;

import java.util.Set;

import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * Interface to the shows
 * 
 * @author kokotchy
 */
public interface IShows {

	/**
	 * Add the given show to the logged user
	 * 
	 * @param url
	 *            Url of the show
	 * @param token
	 *            Token
	 * @return True if the show is added, false otherwise
	 */
	public boolean add(String url, String token);

	/**
	 * Display the show from the given url
	 * 
	 * @param url
	 *            Url of the show
	 * @return Show
	 */
	public Show display(String url);

	/**
	 * Display all available shows
	 * 
	 * @return List of shows
	 */
	public Set<Show> displayAll();

	/**
	 * Return the episodes of the given url
	 * 
	 * @param url
	 *            Url of the show
	 * @return List of seasons with the episodes
	 */
	public Set<Season> getEpisodes(String url);

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
	 * Return the seasons for the logged user for the given show
	 * 
	 * @param token
	 *            Token of the user
	 * @param url
	 *            Url of the show
	 * @return Seasons of the show
	 */
	public Set<Season> getEpisodes(String token, String url);

	/**
	 * Return the episodes of the show for the given season
	 * 
	 * @param url
	 *            Url of the show
	 * @param seasonNb
	 *            Number of the season
	 * @return Season with the episodes
	 */
	public Season getEpisodes(String token, String url, int seasonNb);

	/**
	 * Recommend a show to a usre
	 * 
	 * @param token
	 *            Token of the identified user
	 * @param url
	 *            Url of the show
	 * @param login
	 *            Login of the user to who the show is recommended
	 * @return True if the show is successfully recommended, false otherwise
	 */
	public boolean recommend(String token, String url, String login);

	/**
	 * Remove the show from the logged user
	 * 
	 * @param url
	 *            Url of the show
	 * @param token
	 *            Token
	 * @return True if the show is removed, false otherwise
	 */
	public boolean remove(String url, String token);

	/**
	 * Search for episodes with the given title
	 * 
	 * @param title
	 *            Title to search
	 * @return List of shows with the matching title
	 */
	public Set<Show> search(String title);
}
