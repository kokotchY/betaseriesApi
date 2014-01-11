package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.api.IShows;
import com.kokotchy.betaSeriesAPI.api.factories.EpisodeFactory;
import com.kokotchy.betaSeriesAPI.api.factories.ShowFactory;
import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * Shows API
 * 
 * @author kokotchy
 */
public class Shows implements IShows {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Create the shows api with the given key
	 * 
	 * @param apiKey
	 *            API Key
	 */
	public Shows(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean add(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		Document document = UtilsXml.executeQuery("shows/add/" + url, apiKey,
				params);
		return !UtilsXml.hasErrors(document);
	}

	@Override
	public Show display(String url) {
		Document document = UtilsXml.executeQuery("shows/display/" + url,
				apiKey);
		if (!UtilsXml.hasErrors(document)) {
			return ShowFactory.createShow(document
					.selectSingleNode("/root/show"));
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Show> displayAll() {
		Document document = UtilsXml.executeQuery("shows/display/all", apiKey);
		Set<Show> result = new HashSet<Show>();
		List<Node> nodes = document.selectNodes("/root/shows/show");
		if (nodes.size() > 0) {
			for (Node node : nodes) {
				result.add(ShowFactory.createShow(node));
			}
		}
		return result;
	}

	@Override
	public Set<Season> getEpisodes(String url) {
		return getEpisodesFromSeason(null, url, -1);
	}

	@Override
	public Season getEpisodes(String url, int seasonNb) {
		Set<Season> episodesFromSeason = getEpisodesFromSeason(null, url, seasonNb);
		Iterator<Season> iterator = episodesFromSeason.iterator();
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

	@Override
	public Set<Season> getEpisodes(String token, String url) {
		return getEpisodesFromSeason(token, url, -1);
	}

	@Override
	public Season getEpisodes(String token, String url, int seasonNb) {
		Set<Season> episodesFromSeason = getEpisodesFromSeason(token, url, seasonNb);
		Iterator<Season> iterator = episodesFromSeason.iterator();
		while (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

	/**
	 * Return the episodes from the given season. If seasonNb is < 0, then
	 * retrieve all seasons
	 * 
	 * @param token
	 * @param url
	 *            Url of the show
	 * @param seasonNb
	 *            Number of the season
	 * @return List of seasons with the episodes
	 */
	@SuppressWarnings("unchecked")
	private Set<Season> getEpisodesFromSeason(String token, String url, int seasonNb) {
		Document document = null;
		Map<String, String> params = new HashMap<String, String>();
		if (token != null) {
			params.put(Constants.TOKEN, token);
		}
		if (seasonNb > 0) {
			params.put(Constants.SEASON, "" + seasonNb);
		}
		document = UtilsXml.executeQuery("shows/episodes/" + url, apiKey,
				params);
		List<Node> seasons = document.selectNodes("/root/seasons/season");
		Set<Season> result = new HashSet<Season>();
		if (seasons.size() > 0) {
			for (Node node : seasons) {
				Season season = new Season(UtilsXml.getInt(node, Constants.NUMBER));
				List<Node> episodes = node.selectNodes("episodes/episode");
				for (Node episodeNode : episodes) {
					season.addEpisode(EpisodeFactory.createEpisode(episodeNode));
				}
				result.add(season);
			}
		}
		return result;
	}

	@Override
	public boolean recommend(String token, String url, String login) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		params.put(Constants.FRIEND, login);
		Document document = UtilsXml.executeQuery("shows/recommend/" + url, apiKey, params);
		return !UtilsXml.hasErrors(document);
	}

	@Override
	public boolean remove(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		Document document = UtilsXml.executeQuery("shows/remove/" + url,
				apiKey, params);
		return !UtilsXml.hasErrors(document);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Show> search(String title) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SHOW_TITLE, title);
		Document document = UtilsXml.executeQuery("shows/search", apiKey,
				params);
		Set<Show> shows = new HashSet<Show>();
		if (!UtilsXml.hasErrors(document)) {
			List<Node> nodes = document.selectNodes("/root/shows/show");
			if (nodes.size() > 0) {
				for (Node showNode : nodes) {
					Show show = ShowFactory.createShow(showNode);
					shows.add(show);
				}
			}
		}
		return shows;
	}
}
