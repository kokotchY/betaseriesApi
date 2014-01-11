package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.api.ISubtitles;
import com.kokotchy.betaSeriesAPI.api.factories.SubtitleFactory;
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
	public Set<Subtitle> getLastSubtitles(int nb,
			SubtitleLanguage subtitleLanguage) {
		return getLastSubtitles(null, nb, subtitleLanguage);
	}

	@Override
	public Set<Subtitle> getLastSubtitles(String url, int nb,
			SubtitleLanguage subtitleLanguage) {
		return getLastSubtitlesFromShow(url, nb, subtitleLanguage);
	}

	/**
	 * Return the last subtitles. If the url is not null, return subtitles for
	 * the show. If the nb is greater than 0, limit the number of subtitles
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
	private Set<Subtitle> getLastSubtitlesFromShow(String url, int nb,
			SubtitleLanguage subtitleLanguage) {
		Map<String, String> params = new HashMap<String, String>();
		if (nb > 0) {
			params.put(Constants.LIMIT, "" + nb);
		}

		switch (subtitleLanguage) {
		case VO:
			params.put(Constants.SUBTITLE_LANGUAGE, Constants.SUBTITLE_LANG_VO);
			break;
		case VF:
			params.put(Constants.SUBTITLE_LANGUAGE, Constants.SUBTITLE_LANG_VF);
			break;
		}

		Set<Subtitle> subtitles = new HashSet<Subtitle>();
		String action = null;
		if (url != null) {
			action = "subtitles/last/" + url;
		} else {
			action = "subtitles/last";
		}
		Document document = UtilsXml.executeQuery(action, apiKey, params);
		List<Node> nodes = document.selectNodes("/root/subtitles/subtitle");
		if (nodes.size() > 0) {
			for (Node node : nodes) {
				subtitles.add(SubtitleFactory.createSubtitle(node));
			}
		}
		return subtitles;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param file
	 * @param subtitleLanguage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Set<Subtitle> getSubtitles(String file, SubtitleLanguage subtitleLanguage) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SUBTITLE_FILE, file);
		if (subtitleLanguage != null) {
			switch (subtitleLanguage) {
			case VO:
				params.put(Constants.LANGUAGE, "VO");
				break;
			case VF:
				params.put(Constants.LANGUAGE, "VF");
				break;
			}
		}
		Document document = UtilsXml.executeQuery("subtitles/show", apiKey, params);
		List<Node> nodes = document.selectNodes("/root/subtitles/subtitle");
		Set<Subtitle> subtitles = new HashSet<Subtitle>();
		if (nodes.size() > 0) {
			for (Node node : nodes) {
				subtitles.add(SubtitleFactory.createSubtitle(node));
			}
		}
		return subtitles;
	}

	@Override
	public Set<Subtitle> getSubtitlesForFile(String file) {
		return getSubtitles(file, null);
	}

	@Override
	public Set<Subtitle> getSubtitlesForFile(String file,
			SubtitleLanguage subtitleLanguage) {
		return getSubtitles(file, subtitleLanguage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Subtitle> show(String url, SubtitleLanguage subtitleLanguage,
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

		Document document = UtilsXml.executeQuery("subtitles/show/" + url,
				apiKey, params);
		List<Node> nodes = document.selectNodes("/root/subtitles/subtitle");
		Set<Subtitle> subtitles = new HashSet<Subtitle>();
		if (nodes.size() > 0) {
			for (Node node : nodes) {
				subtitles.add(SubtitleFactory.createSubtitle(node));
			}
		}
		return subtitles;
	}

}
