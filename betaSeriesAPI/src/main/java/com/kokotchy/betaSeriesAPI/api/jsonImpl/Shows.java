package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
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
		JSONObject jsonObject = UtilsJson.executeQuery("shows/add/" + url,
				apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public Show display(String url) {
		JSONObject jsonObject = UtilsJson.executeQuery("shows/display/" + url,
				apiKey);
		if (!UtilsJson.hasErrors(jsonObject)) {
			return Show.createShow(UtilsJson.getJSONObjectFromPath(jsonObject,
					"/root/show"));
		}
		return null;
	}

	@Override
	public Set<Show> displayAll() {
		Set<Show> result = new HashSet<Show>();
		JSONObject jsonObject = UtilsJson.executeQuery("shows/display/all",
				apiKey);
		JSONObject shows = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/shows");
		String[] names = JSONObject.getNames(shows);
		try {
			for (String name : names) {
				result.add(Show.createShow(shows.getJSONObject(name)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Set<Season> getEpisodes(String url) {
		return getEpisodesFromSeason(url, -1);
	}

	@Override
	public Season getEpisodes(String url, int seasonNb) {
		Set<Season> episodesFromSeason = getEpisodesFromSeason(url, seasonNb);
		Iterator<Season> iterator = episodesFromSeason.iterator();
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
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
	private Set<Season> getEpisodesFromSeason(String url, int seasonNb) {
		JSONObject jsonObject = null;
		Map<String, String> params = new HashMap<String, String>();
		if (seasonNb > 0) {
			params.put("season", "" + seasonNb);
		}
		jsonObject = UtilsJson.executeQuery("shows/episodes/" + url, apiKey,
				params);
		JSONArray seasonsArray = UtilsJson.getJSONArrayFromPath(jsonObject,
				"/root/seasons");
		Set<Season> result = new HashSet<Season>();
		try {
			int seasonsLength = seasonsArray.length();
			for (int i = 0; i < seasonsLength; i++) {
				JSONObject seasonObject = seasonsArray.getJSONObject(i);
				Season season = new Season(UtilsJson.getIntValue(seasonObject,
						"number"));
				JSONArray episodesArray = UtilsJson.getJSONArray(seasonObject,
						"episodes");
				int episodesLength = episodesArray.length();
				for (int j = 0; j < episodesLength; j++) {
					JSONObject episodeObject = episodesArray.getJSONObject(j);
					season.addEpisode(Episode.createEpisode(episodeObject));
				}
				result.add(season);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean remove(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		JSONObject jsonObject = UtilsJson.executeQuery("shows/remove/" + url,
				apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public Set<Show> search(String title) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		JSONObject jsonObject = UtilsJson.executeQuery("shows/search", apiKey,
				params);
		Set<Show> shows = new HashSet<Show>();
		if (!UtilsJson.hasErrors(jsonObject)) {
			try {
				JSONObject showsList = UtilsJson.getJSONObjectFromPath(
						jsonObject, "/root/shows");
				String[] names = JSONObject.getNames(showsList);
				for (String name : names) {
					Show show = Show.createShow(showsList.getJSONObject(name));
					shows.add(show);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return shows;
	}

}
