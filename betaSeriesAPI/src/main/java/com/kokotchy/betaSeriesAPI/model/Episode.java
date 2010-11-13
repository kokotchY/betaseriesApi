package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;
import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Episode API
 * 
 * @author kokotchy
 */
public class Episode {

	/**
	 * Create a new episode from the json object
	 * 
	 * @param json
	 *            json object
	 * @return Episode
	 */
	public static Episode createEpisode(JSONObject json) {
		Episode episode = new Episode();
		episode.setShow(UtilsJson.getStringValue(json, "show"));
		episode.setNb(UtilsJson.getStringValue(json, "number"));
		episode.setShowUrl(UtilsJson.getStringValue(json, "url"));
		episode.setDate(UtilsJson.getIntValue(json, "date"));
		episode.setTitle(UtilsJson.getStringValue(json, "title"));
		return episode;
	}

	/**
	 * Create an episode from the node
	 * 
	 * @param node
	 *            Node
	 * @return Episode from the node
	 */
	@SuppressWarnings("unchecked")
	public static Episode createEpisode(Node node) {
		Episode episode = new Episode();
		String number = UtilsXml.readString(node, "number");
		if (number == null) {
			number = UtilsXml.readString(node, "episode");
		}
		episode.setNb(number);
		episode.setDate(UtilsXml.readInt(node, "date"));
		episode.setTitle(UtilsXml.readString(node, "title"));
		episode.setDescription(UtilsXml.readString(node, "description"));
		episode.setScreen(UtilsXml.readString(node, "screen"));
		episode.setShow(UtilsXml.readString(node, "show"));
		episode.setShowUrl(UtilsXml.readString(node, "url"));

		List<Node> subsNode = node.selectNodes("subs/sub");
		for (Node sub : subsNode) {
			episode.addSubtitle(Subtitle.createSubtitle(sub));
		}

		return episode;
	}

	/**
	 * Number of the episode
	 */
	private String nb;

	/**
	 * Url of the show
	 */
	private String showUrl;

	/**
	 * Show of the episode
	 */
	private String show;

	/**
	 * Date of the episode
	 */
	private int date;

	/**
	 * Title of the episode
	 */
	private String title;

	/**
	 * Description of the episode
	 */
	private String description;

	/**
	 * TODO Change to url Url of a screen of the episode
	 */
	private String screen;

	/**
	 * List of subtitles for the episodes
	 */
	private List<Subtitle> subtitles;

	/**
	 * Create a new episode
	 */
	public Episode() {
		subtitles = new LinkedList<Subtitle>();
	}

	/**
	 * Add subtitle to the episode
	 * 
	 * @param subtitle
	 *            Subtitle
	 */
	public void addSubtitle(Subtitle subtitle) {
		subtitles.add(subtitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Episode)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the date of the episode
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * Return the description of the episode
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Return the number of the episode
	 * 
	 * @return the nb
	 */
	public String getNb() {
		return nb;
	}

	/**
	 * Return the screen url of the episode
	 * 
	 * @return the screen
	 */
	public String getScreen() {
		return screen;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * Return the url of the show
	 * 
	 * @return the showUrl
	 */
	public String getShowUrl() {
		return showUrl;
	}

	/**
	 * Return the subtitles for the episode
	 * 
	 * @return the subtitles
	 */
	public List<Subtitle> getSubtitles() {
		return subtitles;
	}

	/**
	 * Return the title of the episode
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, nb);
		result = HashCodeUtil.hash(result, showUrl);
		result = HashCodeUtil.hash(result, show);
		result = HashCodeUtil.hash(result, date);
		result = HashCodeUtil.hash(result, title);
		result = HashCodeUtil.hash(result, description);
		result = HashCodeUtil.hash(result, screen);
		result = HashCodeUtil.hash(result, subtitles);
		return result;
	}

	/**
	 * Set the date of the episode
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * Set the description of the episode
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the number of the episode
	 * 
	 * @param nb
	 *            the nb to set
	 */
	public void setNb(String nb) {
		this.nb = nb;
	}

	/**
	 * Set the screen url of the episode
	 * 
	 * @param screen
	 *            the screen to set
	 */
	public void setScreen(String screen) {
		this.screen = screen;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * Set the url of the show
	 * 
	 * @param showUrl
	 *            the showUrl to set
	 */
	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	/**
	 * Set the title of the episode
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if (!title.equals(title.trim())) {
			System.out.println("Problem...");
		}
		this.title = title;
	}

	@Override
	public String toString() {
		String format = "[%s - %s] %s";
		return String.format(format, show, nb, title);
	}

}
