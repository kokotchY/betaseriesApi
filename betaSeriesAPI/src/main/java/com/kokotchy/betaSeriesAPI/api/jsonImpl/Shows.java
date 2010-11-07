package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.IShows;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Shows implements IShows {

	/**
	 * TODO Fill it
	 */
	private String apiKey;

	/**
	 * TODO Fill it
	 * 
	 * @param apiKey
	 */
	public Shows(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean add(String url, String token) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		JSONObject jsonObject = UtilsJson.executeQuery("shows/add/" + url
				+ ".json", apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public Show display(String url) {
		JSONObject jsonObject = UtilsJson.executeQuery("shows/display/" + url
				+ ".json", apiKey);
		if (!UtilsJson.hasErrors(jsonObject)) {
			return Show.createShow(UtilsJson.getJSONObjectFromPath(jsonObject,
					"/root/show"));
		}
		return null;
	}

	@Override
	public List<Show> displayAll() {
		List<Show> result = new LinkedList<Show>();
		JSONObject jsonObject = UtilsJson.executeQuery(
				"shows/display/all.json", apiKey);
		JSONArray jsonArrayFromPath = UtilsJson.getJSONArrayFromPath(
				jsonObject, "/root/shows");
		try {
			for (int i = 0; i < jsonArrayFromPath.length(); i++) {
				result.add(Show.createShow(jsonArrayFromPath.getJSONObject(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
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
	private List<Season> getEpisodesFromSeason(String url, int seasonNb) {
		JSONObject jsonObject = null;
		Map<String, String> params = new HashMap<String, String>();
		if (seasonNb > 0) {
			params.put("season", "" + seasonNb);
		}
		jsonObject = UtilsJson.executeQuery("shows/episodes/" + url + ".json",
				apiKey, params);
		JSONArray seasonsArray = UtilsJson.getJSONArrayFromPath(jsonObject,
				"/root/seasons");
		List<Season> result = new LinkedList<Season>();
		try {
			for (int i = 0; i < seasonsArray.length(); i++) {
				JSONObject seasonObject = seasonsArray.getJSONObject(i);
				Season season = new Season(UtilsJson.getIntValue(seasonObject,
						"number"));
				JSONArray episodesArray = UtilsJson.getJSONArray(seasonObject,
						"episodes");
				for (int j = 0; j < episodesArray.length(); j++) {
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
		JSONObject jsonObject = UtilsJson.executeQuery("shows/remove/" + url
				+ ".json", apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
	}

	@Override
	public List<Show> search(String title) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", title);
		JSONObject jsonObject = UtilsJson.executeQuery("shows/search.json",
				apiKey, params);
		List<Show> shows = new LinkedList<Show>();
		if (!UtilsJson.hasErrors(jsonObject)) {
			try {
				JSONArray showsArray = UtilsJson.getJSONArrayFromPath(
						jsonObject, "/root/shows");
				for (int i = 0; i < showsArray.length(); i++) {
					Show show = Show.createShow(showsArray.getJSONObject(i));
					shows.add(show);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return shows;
	}

}
