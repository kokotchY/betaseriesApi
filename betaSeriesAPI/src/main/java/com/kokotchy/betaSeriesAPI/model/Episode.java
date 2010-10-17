/**
 * 
 */
package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * @author kokotchy
 * 
 */
public class Episode {

	/**
	 * @param node
	 * @return
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
	 * 
	 */
	private String nb;

	/**
	 * 
	 */
	private int date;

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	private String screen;

	/**
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the nb
	 */
	public String getNb() {
		return nb;
	}

	/**
	 * @return the screen
	 */
	public String getScreen() {
		return screen;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param nb
	 *            the nb to set
	 */
	public void setNb(String nb) {
		this.nb = nb;
	}

	/**
	 * @param screen
	 *            the screen to set
	 */
	public void setScreen(String screen) {
		this.screen = screen;
	}

	/**
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
