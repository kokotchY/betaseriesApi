package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
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
		show.setTitle(UtilsJson.getStringValue(jsonObject, "title"));
		show.setUrl(UtilsJson.getStringValue(jsonObject, "url"));
		show
				.setDescription(UtilsJson.getStringValue(jsonObject,
				"description"));
		show.setStatus(UtilsJson.getStringValue(jsonObject, "status"));
		show.setBanner(UtilsJson.getStringValue(jsonObject, "banner"));
		show.setIdTvdb(UtilsJson.getIntValue(jsonObject, "id_thetvdb"));
		JSONObject genres = UtilsJson.getJSONObject(jsonObject, "genres");
		if (genres != null) {
			String[] names = JSONObject.getNames(genres);
			try {
				for (String name : names) {
					show.addGenre(genres.getString(name));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		JSONObject seasons = UtilsJson.getJSONObject(jsonObject, "seasons");
		if (seasons != null) {
			String[] names = JSONObject.getNames(seasons);
			try {
				for (String name : names) {
					JSONObject season = seasons.getJSONObject(name);
					int episodes = UtilsJson.getIntValue(season, "episodes");
					int number = UtilsJson.getIntValue(season, "number");
					show.addNumberEpisodeForSeason(number, episodes);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		show.setArchived(UtilsJson.getBooleanValue(jsonObject, "archive"));
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
		show.setTitle(UtilsXml.readString(node, "title"));
		show.setUrl(UtilsXml.readString(node, "url"));
		show.setDescription(UtilsXml.readString(node, "description"));
		show.setStatus(UtilsXml.readString(node, "status"));
		show.setBanner(UtilsXml.readString(node, "banner"));
		show.setIdTvdb(UtilsXml.readInt(node, "id_thetvdb"));
		List<Node> genres = node.selectNodes("genres/genre");
		for (Node nodeGenre : genres) {
			show.addGenre(nodeGenre.getStringValue());
		}
		List<Node> seasons = node.selectNodes("seasons/season");
		for (Node season : seasons) {
			Integer seasonNb = UtilsXml.readInt(season, "number");
			Integer nbEpisode = UtilsXml.readInt(season, "episodes");
			show.addNumberEpisodeForSeason(seasonNb, nbEpisode);
		}
		show.setArchived(UtilsXml.readBoolean(node, "archive"));
		return show;
	}
}
