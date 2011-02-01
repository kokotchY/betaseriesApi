package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
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
		JSONObject jsonObject = UtilsJson.executeQuery("shows/add/" + url,
				apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public Show display(String url) {
		JSONObject jsonObject = UtilsJson.executeQuery("shows/display/" + url,
				apiKey);
		if (!UtilsJson.hasErrors(jsonObject)) {
			return ShowFactory.createShow(UtilsJson.getJSONObjectFromPath(
					jsonObject, "/root/show"));
		}
		return null;
	}

	@Override
	public Set<Show> displayAll() {
		JSONObject jsonObject = UtilsJson.executeQuery("shows/display/all",
				apiKey);
		return getShows(jsonObject);
	}

	@Override
	public Set<Season> getEpisodes(String url) {
		return getEpisodesFromSeason(null, url, -1);
	}

	@Override
	public Season getEpisodes(String url, int seasonNb) {
		Set<Season> episodesFromSeason = getEpisodesFromSeason(null, url, seasonNb);
		if (episodesFromSeason != null) {
			Iterator<Season> iterator = episodesFromSeason.iterator();
			if (iterator.hasNext()) {
				return iterator.next();
			}
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
		if (episodesFromSeason != null) {
			Iterator<Season> iterator = episodesFromSeason.iterator();
			if (iterator.hasNext()) {
				return iterator.next();
			}
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
	private Set<Season> getEpisodesFromSeason(String token, String url, int seasonNb) {
		JSONObject jsonObject = null;
		Map<String, String> params = new HashMap<String, String>();
		if (seasonNb > 0) {
			params.put(Constants.SEASON, "" + seasonNb);
		}

		if (token != null) {
			params.put(Constants.TOKEN, token);
		}

		jsonObject = UtilsJson.executeQuery("shows/episodes/" + url, apiKey,
				params);
		if (!UtilsJson.hasErrors(jsonObject)) {
			JSONObject[] seasonArray = UtilsJson.getArray(UtilsJson.getJSONObjectFromPath(jsonObject, "/root/seasons"));
			Set<Season> result = new HashSet<Season>();
			int seasonsLength = seasonArray.length;
			for (int i = 0; i < seasonsLength; i++) {
				JSONObject seasonObject = seasonArray[i];
				Season season = new Season(UtilsJson.getIntValue(seasonObject,
						Constants.LIMIT));
				JSONObject[] episodesArray = UtilsJson.getArray(UtilsJson.getJSONObject(seasonObject, Constants.EPISODES));
				int episodesLength = episodesArray.length;
				for (int j = 0; j < episodesLength; j++) {
					JSONObject episodeObject = episodesArray[j];
					season.addEpisode(EpisodeFactory
							.createEpisode(episodeObject));
				}
				result.add(season);
			}
			return result;
		}
		return null;
	}

	/**
	 * Return the shows from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Shows
	 */
	private Set<Show> getShows(JSONObject jsonObject) {
		Set<Show> shows = new HashSet<Show>();
		if (!UtilsJson.hasErrors(jsonObject)) {
			// try {
			JSONObject showsList = UtilsJson.getJSONObjectFromPath(
					jsonObject, "/root/shows");
			Iterator<?> iterator = showsList.keys();
			try {
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Show show = ShowFactory.createShow(showsList.getJSONObject(key));
					shows.add(show);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return shows;
		}
		return null;
	}

	@Override
	public boolean recommend(String token, String url, String login) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		params.put(Constants.FRIEND, login);
		JSONObject jsonObject = UtilsJson.executeQuery("shows/recommend/" + url, apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public boolean remove(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.TOKEN, token);
		JSONObject jsonObject = UtilsJson.executeQuery("shows/remove/" + url,
				apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public Set<Show> search(String title) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.SHOW_TITLE, title);
		JSONObject jsonObject = UtilsJson.executeQuery("shows/search", apiKey,
				params);
		return getShows(jsonObject);
	}

}
