package com.kokotchy.betaSeriesAPI.model;

import java.util.HashSet;
import java.util.Set;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Episode API
 * 
 * @author kokotchy
 */
public class Episode {

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
	private Set<Subtitle> subtitles;

	/**
	 * Episode is seen or not
	 */
	private boolean seen;

	/**
	 * Episode is downloaded by the user or not
	 */
	private boolean downloaded;

	/**
	 * Create a new episode
	 */
	public Episode() {
		subtitles = new HashSet<Subtitle>();
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
	public Set<Subtitle> getSubtitles() {
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
		result = HashCodeUtil.hash(result, seen);
		return result;
	}

	/**
	 * Return true if the episode has subtitles, false otherwise
	 * 
	 * @return True if episode has subtitles
	 */
	public boolean hasSubtitle() {
		return subtitles.size() > 0;
	}

	/**
	 * Return true if the episode is downloaded, false otherwise
	 * 
	 * @return If episode is downloaded
	 */
	public boolean isDownloaded() {
		return downloaded;
	}

	/**
	 * Return if the episode is seen
	 * 
	 * @return Return if the episode is seen
	 */
	public boolean isSeen() {
		return seen;
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
	 * Set the downloaded state of the episode
	 * 
	 * @param downloaded
	 *            the state to set
	 */
	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
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
	 * Set the seen state of the episode
	 * 
	 * @param seen
	 *            State
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/**
	 * Set the show
	 * 
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
		String format = "[%d - %s - %s] %s";
		return String.format(format, date, show, nb, title);
	}

}
