package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
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
		JSONObject jsonObject = UtilsJson.executeQuery(action, apiKey, params);
		if (!UtilsJson.hasErrors(jsonObject)) {
			JSONObject subtitlesList = UtilsJson.getJSONObjectFromPath(jsonObject,
					"/root/subtitles");
			JSONObject[] array = UtilsJson.getArray(subtitlesList);
			if (array.length > 0) {
				for (JSONObject object : array) {
					subtitles.add(SubtitleFactory.createSubtitle(object));
				}
			}
			return subtitles;
		}
		return null;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param file
	 * @param subtitleLanguage
	 * @return
	 */
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
		JSONObject jsonObject = UtilsJson.executeQuery("subtitles/show", apiKey, params);
		JSONObject subtitles = UtilsJson.getJSONObjectFromPath(jsonObject, "/root/subtitles/");
		JSONObject[] array = UtilsJson.getArray(subtitles);
		Set<Subtitle> result = new HashSet<Subtitle>();
		if (!UtilsJson.hasErrors(jsonObject)) {
			for (JSONObject subtitle : array) {
				result.add(SubtitleFactory.createSubtitle(subtitle));
			}
		}
		return result;
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

	@Override
	public Set<Subtitle> show(String url, SubtitleLanguage subtitleLanguage,
			int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		if (season > 0) {
			params.put(Constants.SEASON, "" + season);
		}
		if (episode > 0) {
			params.put(Constants.EPISODE, "" + episode);
		}
		switch (subtitleLanguage) {
		case VO:
			params.put(Constants.SUBTITLE_LANGUAGE, Constants.SUBTITLE_LANG_VO);
			break;
		case VF:
			params.put(Constants.SUBTITLE_LANGUAGE, Constants.SUBTITLE_LANG_VF);
			break;
		}

		JSONObject jsonObject = UtilsJson.executeQuery("subtitles/show/" + url,
				apiKey, params);
		if (!UtilsJson.hasErrors(jsonObject)) {
			JSONObject subtitlesList = UtilsJson.getJSONObjectFromPath(jsonObject,
					"/root/subtitles");
			Set<Subtitle> subtitles = new HashSet<Subtitle>();
			JSONObject[] array = UtilsJson.getArray(subtitlesList);
			if (array.length > 0) {
				for (JSONObject object : array) {
					subtitles.add(SubtitleFactory.createSubtitle(object));
				}
			}
			return subtitles;
		}
		return null;
	}
}
