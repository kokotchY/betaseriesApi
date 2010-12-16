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
	 * TODO Fill it
	 */
	private static final String ARCHIVE = "archive";

	/**
	 * TODO Fill it
	 */
	private static final String NUMBER = "number";

	/**
	 * TODO Fill it
	 */
	private static final String EPISODES = "episodes";

	/**
	 * TODO Fill it
	 */
	private static final String SEASONS = "seasons";

	/**
	 * TODO Fill it
	 */
	private static final String GENRES = "genres";

	/**
	 * TODO Fill it
	 */
	private static final String ID_THETVDB = "id_thetvdb";

	/**
	 * TODO Fill it
	 */
	private static final String BANNER = "banner";

	/**
	 * TODO Fill it
	 */
	private static final String STATUS = "status";

	/**
	 * TODO Fill it
	 */
	private static final String DESCRIPTION = "description";

	/**
	 * TODO Fill it
	 */
	private static final String URL = "url";

	/**
	 * TODO Fill it
	 */
	private static final String TITLE = "title";

	/**
	 * Create a show from json object
	 * 
	 * @param jsonObject
	 *            jsonObject
	 * @return Show
	 */
	public static Show createShow(JSONObject jsonObject) {
		Show show = new Show();
		show.setTitle(UtilsJson.getStringValue(jsonObject, TITLE));
		show.setUrl(UtilsJson.getStringValue(jsonObject, URL));
		show
				.setDescription(UtilsJson.getStringValue(jsonObject,
				DESCRIPTION));
		show.setStatus(UtilsJson.getStringValue(jsonObject, STATUS));
		show.setBanner(UtilsJson.getStringValue(jsonObject, BANNER));
		show.setIdTvdb(UtilsJson.getIntValue(jsonObject, ID_THETVDB));
		JSONObject genres = UtilsJson.getJSONObject(jsonObject, GENRES);
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
		JSONObject seasons = UtilsJson.getJSONObject(jsonObject, SEASONS);
		if (seasons != null) {
			String[] names = JSONObject.getNames(seasons);
			try {
				for (String name : names) {
					JSONObject season = seasons.getJSONObject(name);
					int episodes = UtilsJson.getIntValue(season, EPISODES);
					int number = UtilsJson.getIntValue(season, NUMBER);
					show.addNumberEpisodeForSeason(number, episodes);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		show.setArchived(UtilsJson.getBooleanValue(jsonObject, ARCHIVE));
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
		show.setTitle(UtilsXml.readString(node, TITLE));
		show.setUrl(UtilsXml.readString(node, URL));
		show.setDescription(UtilsXml.readString(node, DESCRIPTION));
		show.setStatus(UtilsXml.readString(node, STATUS));
		show.setBanner(UtilsXml.readString(node, BANNER));
		show.setIdTvdb(UtilsXml.readInt(node, ID_THETVDB));
		List<Node> genres = node.selectNodes("genres/genre");
		for (Node nodeGenre : genres) {
			show.addGenre(nodeGenre.getStringValue());
		}
		List<Node> seasons = node.selectNodes("seasons/season");
		for (Node season : seasons) {
			Integer seasonNb = UtilsXml.readInt(season, NUMBER);
			Integer nbEpisode = UtilsXml.readInt(season, EPISODES);
			show.addNumberEpisodeForSeason(seasonNb, nbEpisode);
		}
		show.setArchived(UtilsXml.readBoolean(node, ARCHIVE));
		return show;
	}
}
