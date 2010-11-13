package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.IShows;
import com.kokotchy.betaSeriesAPI.model.Episode;
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
		params.put("token", token);
		Document document = UtilsXml.executeQuery("shows/add/" + url, apiKey,
				params);
		return !UtilsXml.hasErrors(document);
	}

	@Override
	public Show display(String url) {
		Document document = UtilsXml.executeQuery("shows/display/" + url,
				apiKey);
		if (!UtilsXml.hasErrors(document)) {
			return Show.createShow(document.selectSingleNode("/root/show"));
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Show> displayAll() {
		Document document = UtilsXml.executeQuery("shows/display/all", apiKey);
		List<Show> result = new LinkedList<Show>();
		List<Node> nodes = document.selectNodes("/root/shows/show");
		for (Node node : nodes) {
			result.add(Show.createShow(node));
		}
		return result;
	}

	@Override
	public List<Season> getEpisodes(String url) {
		return getEpisodesFromSeason(url, -1);
	}

	@Override
	public Season getEpisodes(String url, int seasonNb) {
		return getEpisodesFromSeason(url, seasonNb).get(0);
	}

	/**
	 * Return the episodes from the given season. If seasonNb is < 0, then
	 * retrieve all seasons
	 * 
	 * @param url
	 *            Url of the show
	 * @param seasonNb
	 *            Number of the season
	 * @return List of seasons with the episodes
	 */
	@SuppressWarnings("unchecked")
	private List<Season> getEpisodesFromSeason(String url, int seasonNb) {
		Document document = null;
		Map<String, String> params = new HashMap<String, String>();
		if (seasonNb > 0) {
			params.put("season", "" + seasonNb);
		}
		document = UtilsXml.executeQuery("shows/episodes/" + url, apiKey,
				params);
		List<Node> seasons = document.selectNodes("/root/seasons/season");
		List<Season> result = new LinkedList<Season>();
		for (Node node : seasons) {
			Season season = new Season(UtilsXml.readInt(node, "number"));
			List<Node> episodes = node.selectNodes("episodes/episode");
			for (Node episodeNode : episodes) {
				season.addEpisode(Episode.createEpisode(episodeNode));
			}
			result.add(season);
		}
		return result;
	}

	@Override
	public boolean remove(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		Document document = UtilsXml.executeQuery("shows/remove/" + url,
				apiKey, params);
		return !UtilsXml.hasErrors(document);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Show> search(String title) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		Document document = UtilsXml.executeQuery("shows/search", apiKey,
				params);
		List<Show> shows = new LinkedList<Show>();
		if (!UtilsXml.hasErrors(document)) {
			List<Node> nodes = document.selectNodes("/root/shows/show");
			for (Node showNode : nodes) {
				Show show = Show.createShow(showNode);
				shows.add(show);
			}
		}
		return shows;
	}
}
