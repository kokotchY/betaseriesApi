package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.ISubtitles;
import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Subtitles API
 * 
 * @author kokotchy
 */
public class Subtitles implements ISubtitles {

	/**
	 * Api key
	 */
	private String apiKey;

	/**
	 * Create new subtitle api with the given key
	 * 
	 * @param apiKey
	 *            Key
	 */
	public Subtitles(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Subtitle> getLastSubtitles(int nb,
			SubtitleLanguage subtitleLanguage) {
		return getLastSubtitles(null, nb, subtitleLanguage);
	}

	@Override
	public List<Subtitle> getLastSubtitles(String url, int nb,
			SubtitleLanguage subtitleLanguage) {
		return getLastSubtitlesFromShow(url, nb, subtitleLanguage);
	}

	/**
	 * Return the last subtitles. If the url is not null, return subtitles for the show.
	 * If the nb is greater than 0, limit the number of subtitles
	 * 
	 * @param url
	 *            Url of the show
	 * @param nb
	 *            Number of subtitle
	 * @param subtitleLanguage
	 *            Language needed for the subtitles.
	 * @return List of subtitles
	 */
	@SuppressWarnings("unchecked")
	private List<Subtitle> getLastSubtitlesFromShow(String url, int nb,
			SubtitleLanguage subtitleLanguage) {
		Map<String, String> params = new HashMap<String, String>();
		if (nb > 0) {
			params.put("number", "" + nb);
		}

		switch (subtitleLanguage) {
		case VO:
			params.put("language", "VO");
			break;
		case VF:
			params.put("language", "VF");
			break;
		}

		List<Subtitle> subtitles = new LinkedList<Subtitle>();
		String action = null;
		if (url != null) {
			action = "subtitles/last/" + url + ".xml";
		} else {
			action = "subtitles/last.xml";
		}
		Document document = UtilsXml.executeQuery(action, apiKey, params);
		List<Node> nodes = document.selectNodes("/root/subtitles/subtitle");
		for (Node node : nodes) {
			subtitles.add(Subtitle.createSubtitle(node));
		}
		return subtitles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subtitle> show(String url, SubtitleLanguage subtitleLanguage,
			int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		if (season > 0) {
			params.put("season", "" + season);
		}
		if (episode > 0) {
			params.put("episode", "" + episode);
		}
		switch (subtitleLanguage) {
		case VO:
			params.put("language", "VO");
			break;
		case VF:
			params.put("language", "VF");
			break;
		}

		Document document = UtilsXml.executeQuery(
				"subtitles/show/" + url + ".xml", apiKey, params);
		List<Node> nodes = document.selectNodes("/root/subtitles/subtitle");
		List<Subtitle> subtitles = new LinkedList<Subtitle>();
		for (Node node : nodes) {
			subtitles.add(Subtitle.createSubtitle(node));
		}
		return subtitles;
	}

}
