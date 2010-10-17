package com.kokotchy.betaSeriesAPI.api;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * Shows API
 * 
 * @author kokotchy
 */
public class Shows {

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

	/**
	 * Display the show from the given url
	 * 
	 * @param url
	 *            Url of the show
	 */
	public Show display(String url) {
		Document document = Utils.executeQuery("shows/display/" + url + ".xml",
				apiKey);
		if (Utils.hasErrors(document)) {
			return Show.createShow(document.selectSingleNode("/root/show"));
		}

		return null;
	}

	/**
	 * Display all available shows
	 * 
	 * @return List of shows
	 */
	public List<Show> displayAll() {
		Document document = Utils.executeQuery("shows/display/all.xml", apiKey);
		List<Show> result = new LinkedList<Show>();
		List<Node> nodes = document.selectNodes("/root/shows/show");
		for (Node node : nodes) {
			result.add(Show.createShow(node));
		}
		return result;
	}

	/**
	 * Return the episodes of the given url
	 * 
	 * @param url
	 *            Url of the show
	 * @return List of seasons with the episodes
	 */
	public List<Season> getEpisodes(String url) {
		return getEpisodesFromSeason(url, -1);
	}

	/**
	 * Return the episodes of the show for the given season
	 * 
	 * @param url
	 *            Url of the show
	 * @param seasonNb
	 *            Number of the season
	 * @return Season with the episodes
	 */
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
		if (seasonNb > 0) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("season", "" + seasonNb);
			document = Utils.executeQuery("shows/episodes/" + url + ".xml",
					apiKey, params);
		} else {
			document = Utils.executeQuery("shows/episodes/" + url + ".xml",
					apiKey);
		}

		List<Node> seasons = document.selectNodes("/root/seasons/season");
		List<Season> result = new LinkedList<Season>();
		for (Node node : seasons) {
			Season season = new Season(Integer.parseInt(node.selectSingleNode(
					"number").getText()));
			List<Node> episodes = node.selectNodes("episodes/episode");
			for (Node episodeNode : episodes) {
				Episode episode = Episode.createEpisode(episodeNode);
				season.addEpisode(episode);
			}
			result.add(season);
		}
		return result;
	}

	/**
	 * Search for episodes with the given title
	 * 
	 * @param title
	 *            Title to search
	 * @return List of shows with the matching title
	 */
	public List<Show> search(String title) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		Document document = Utils.executeQuery("shows/search.xml", apiKey,
				params);
		List<Show> shows = new LinkedList<Show>();
		if (document.selectSingleNode("/root/code").getText().equals("1")) {
			List<Node> nodes = document.selectNodes("/root/shows/show");
			for (Node showNode : nodes) {
				Show show = new Show();
				show.setUrl(showNode.selectSingleNode("url").getText());
				show.setTitle(showNode.selectSingleNode("title").getText());
				shows.add(show);
			}
		}
		return shows;
	}
}
