package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * Episode API
 * 
 * @author kokotchy
 * 
 */
public class Episode {

	/**
	 * Create an episode from the node
	 * 
	 * @param node
	 *            Node
	 * @return Episode from the node
	 */
	public static Episode createEpisode(Node node) {
		Episode episode = new Episode();
		episode.setNb(Utils.readNode(node, "number"));
		String value = Utils.readNode(node, "date");
		if (value != null) {
			episode.setDate(Integer.parseInt(value));
		} else {
			episode.setDate(0);
		}
		episode.setTitle(Utils.readNode(node, "title"));
		episode.setDescription(Utils.readNode(node, "description"));
		episode.setScreen(Utils.readNode(node, "screen"));
		return episode;
	}

	/**
	 * Number of the episode
	 */
	private String nb;

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
	 * TODO Change to url
	 * 
	 * Url of a screen of the episode
	 */
	private String screen;

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
	 * Return the title of the episode
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
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
	 * Set the title of the episode
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		String format = "[%s] %s";
		return String.format(format, nb, title);
	}

}
