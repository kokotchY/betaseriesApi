package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
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
			action = "subtitles/last/" + url;
		} else {
			action = "subtitles/last";
		}
		JSONObject jsonObject = UtilsJson.executeQuery(action, apiKey, params);

		JSONArray subtitlesArray = UtilsJson.getJSONArrayFromPath(jsonObject,
				"/root/subtitles");
		int length = subtitlesArray.length();
		try {
			for (int i = 0; i < length; i++) {
				subtitles.add(Subtitle.createSubtitle(subtitlesArray
						.getJSONObject(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return subtitles;
	}

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

		JSONObject jsonObject = UtilsJson.executeQuery("subtitles/show/" + url,
				apiKey, params);
		JSONArray subtitlesArray = UtilsJson.getJSONArrayFromPath(jsonObject,
				"/root/subtitles");
		List<Subtitle> subtitles = new LinkedList<Subtitle>();
		int length = subtitlesArray.length();
		try {
			for (int i = 0; i < length; i++) {
				subtitles.add(Subtitle.createSubtitle(subtitlesArray
						.getJSONObject(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return subtitles;
	}
}
