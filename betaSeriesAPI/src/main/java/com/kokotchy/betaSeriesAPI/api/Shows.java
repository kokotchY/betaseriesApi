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
 * @author kokotchy
 * 
 */
public class Shows {

	/**
	 * 
	 */
	private String apiKey;

	/**
	 * @param apiKey
	 */
	public Shows(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @param url
	 */
	public Show display(String url) {
		Document document = Utils.executeQuery("shows/display/" + url + ".xml",
				apiKey);
		// if (document.selectSingleNode("/root/code").getText().equals("1")) {
		return Show.createShow(document.selectSingleNode("/root/show"));
		// } else {
		// return null;
		// }
	}

	/**
	 * @return
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
	 * @param url
	 * @return
	 */
	public List<Season> getEpisodes(String url) {
		return getEpisodesFromSeason(url, -1);
	}

	/**
	 * @param url
	 * @param seasonNb
	 * @return
	 */
	public Season getEpisodes(String url, int seasonNb) {
		return getEpisodesFromSeason(url, seasonNb).get(0);
	}

	/**
	 * @param url
	 * @param seasonNb
	 * @return
	 */
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
	 * @param title
	 * @return
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
