package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Model of a subtitle
 * 
 * @author kokotchy
 */
public class Subtitle {

	/**
	 * Title
	 */
	private String title;

	/**
	 * Season
	 */
	private int season;

	/**
	 * Episode
	 */
	private int episode;

	/**
	 * Language
	 */
	private SubtitleLanguage language;

	/**
	 * Source
	 */
	private SubtitleSource source;

	/**
	 * File
	 */
	private String file;

	/**
	 * Url to the subtitle TODO Change to url
	 */
	private String url;

	/**
	 * Quality of a subtitle (from 1 to 5)
	 */
	private int quality;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Subtitle)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the episode
	 * 
	 * @return the episode
	 */
	public int getEpisode() {
		return episode;
	}

	/**
	 * Return the file
	 * 
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Return the language
	 * 
	 * @return the language
	 */
	public SubtitleLanguage getLanguage() {
		return language;
	}

	/**
	 * Return the quality of the subtitle
	 * 
	 * @return the quality
	 */
	public int getQuality() {
		return quality;
	}

	/**
	 * Return the season
	 * 
	 * @return the season
	 */
	public int getSeason() {
		return season;
	}

	/**
	 * Return the source
	 * 
	 * @return the source
	 */
	public SubtitleSource getSource() {
		return source;
	}

	/**
	 * Return the title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Return the url
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, title);
		result = HashCodeUtil.hash(result, season);
		result = HashCodeUtil.hash(result, episode);
		result = HashCodeUtil.hash(result, language);
		result = HashCodeUtil.hash(result, source);
		result = HashCodeUtil.hash(result, file);
		result = HashCodeUtil.hash(result, url);
		return result;
	}

	/**
	 * Set the episode
	 * 
	 * @param episode
	 *            the episode to set
	 */
	public void setEpisode(int episode) {
		this.episode = episode;
	}

	/**
	 * Set the file
	 * 
	 * @param file
	 *            the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * Set the language
	 * 
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(SubtitleLanguage language) {
		this.language = language;
	}

	/**
	 * Set the quality of a subtitle
	 * 
	 * @param quality
	 *            the quality to set
	 */
	public void setQuality(int quality) {
		this.quality = quality;
	}

	/**
	 * Return the season
	 * 
	 * @param season
	 *            the season to set
	 */
	public void setSeason(int season) {
		this.season = season;
	}

	/**
	 * Set the source of the subtitle
	 * 
	 * @param source
	 *            Source
	 */
	public void setSource(String source) {
		if (source != null) {
			if (source.equals("addic7ed")) {
				setSource(SubtitleSource.ADDIC7ED);
			} else if (source.equals("seriessub")) {
				setSource(SubtitleSource.SERIESSUB);
			} else if (source.equals("soustitres")) {
				setSource(SubtitleSource.SOUSTITRES);
			} else if (source.equals("tvsubtitles")) {
				setSource(SubtitleSource.TVSUBTITLES);
			} else if (source.equals("usub")) {
				setSource(SubtitleSource.USUB);
			}
		}
	}

	/**
	 * Set the source
	 * 
	 * @param source
	 *            the source to set
	 */
	public void setSource(SubtitleSource source) {
		this.source = source;
	}

	/**
	 * Set the title
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set the url
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		String format = "[%s] S%dE%d %s";
		return String.format(format, language, season, episode, title);
	}
}
