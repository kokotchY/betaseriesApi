package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Show;

/**
 * Show factory
 * 
 * @author kokotchy
 */
public class ShowFactory {
	/**
	 * Create a show from json object
	 * 
	 * @param jsonObject
	 *            jsonObject
	 * @return Show
	 */
	public static Show createShow(JSONObject jsonObject) {
		Show show = new Show();
		show.setTitle(UtilsJson.getStringValue(jsonObject, Constants.TITLE));
		show.setUrl(UtilsJson.getStringValue(jsonObject, Constants.URL));
		show.setDescription(UtilsJson.getStringValue(jsonObject,
				Constants.DESCRIPTION));
		show.setStatus(UtilsJson.getStringValue(jsonObject, Constants.STATUS));
		show.setBanner(UtilsJson.getStringValue(jsonObject, Constants.BANNER));
		show.setIdTvdb(UtilsJson.getIntValue(jsonObject, Constants.ID_THETVDB));
		JSONObject genres = UtilsJson.getJSONObject(jsonObject,
				Constants.GENRES);
		if (genres != null) {
			int idx = 0;
			boolean hasElement = genres.has("" + idx);
			try {
				while (hasElement) {
					show.addGenre(genres.getString("" + idx++));
					hasElement = genres.has("" + idx);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		JSONObject seasons = UtilsJson.getJSONObject(jsonObject,
				Constants.SEASONS);
		if (seasons != null) {
			JSONObject[] array = UtilsJson.getArray(seasons, 1);
			if (array.length > 0) {
				for (JSONObject season : array) {
					int episodes = UtilsJson.getIntValue(season,
							Constants.EPISODES);
					int number = UtilsJson
							.getIntValue(season, Constants.NUMBER);
					show.addNumberEpisodeForSeason(number, episodes);
				}
			}
		}
		show.setArchived(UtilsJson.getBooleanValue(jsonObject,
				Constants.ARCHIVE));
		return show;
	}

	/**
	 * Create a show from a node
	 * 
	 * @param node
	 *            Node
	 * @return Show
	 */
	@SuppressWarnings("unchecked")
	public static Show createShow(Node node) {
		Show show = new Show();
		show.setTitle(UtilsXml.getString(node, Constants.TITLE));
		show.setUrl(UtilsXml.getString(node, Constants.URL));
		show.setDescription(UtilsXml.getString(node, Constants.DESCRIPTION));
		show.setStatus(UtilsXml.getString(node, Constants.STATUS));
		show.setBanner(UtilsXml.getString(node, Constants.BANNER));
		show.setIdTvdb(UtilsXml.getInt(node, Constants.ID_THETVDB));
		List<Node> genres = node.selectNodes("genres/genre");
		if (genres.size() > 0) {
			for (Node nodeGenre : genres) {
				show.addGenre(nodeGenre.getStringValue());
			}
		}
		List<Node> seasons = node.selectNodes("seasons/season");
		if (seasons.size() > 0) {
			for (Node season : seasons) {
				Integer seasonNb = UtilsXml.getInt(season, Constants.NUMBER);
				Integer nbEpisode = UtilsXml.getInt(season, Constants.EPISODES);
				show.addNumberEpisodeForSeason(seasonNb, nbEpisode);
			}
		}
		show.setArchived(UtilsXml.getBoolean(node, Constants.ARCHIVE));
		return show;
	}
}
