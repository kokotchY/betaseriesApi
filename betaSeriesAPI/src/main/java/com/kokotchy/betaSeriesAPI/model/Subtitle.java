package com.kokotchy.betaSeriesAPI.model;

/**
 * @author kokotchy
 * 
 */
public class Subtitle {

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private int season;

	/**
	 * 
	 */
	private int episode;

	/**
	 * 
	 */
	private SubtitleLanguage language;

	/**
	 * 
	 */
	private String source;

	/**
	 * 
	 */
	private String file;

	/**
	 * TODO Change to url
	 */
	private String url;

	/**
	 * @return the episode
	 */
	public int getEpisode() {
		return episode;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @return the language
	 */
	public SubtitleLanguage getLanguage() {
		return language;
	}

	/**
	 * @return the season
	 */
	public int getSeason() {
		return season;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param episode
	 *            the episode to set
	 */
	public void setEpisode(int episode) {
		this.episode = episode;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(SubtitleLanguage language) {
		this.language = language;
	}

	/**
	 * @param season
	 *            the season to set
	 */
	public void setSeason(int season) {
		this.season = season;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
