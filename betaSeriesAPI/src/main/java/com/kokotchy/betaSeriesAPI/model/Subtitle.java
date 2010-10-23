package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * Model of a subtitle
 * 
 * @author kokotchy
 */
public class Subtitle {

	/**
	 * Create a new subtitle from the node
	 * 
	 * @param node
	 *            Node
	 * @return Subtitle
	 */
	public static Subtitle createSubtitle(Node node) {
		Subtitle subtitle = new Subtitle();
		subtitle.setTitle(Utils.readString(node, "title"));
		subtitle.setSeason(Utils.readInt(node, "season"));
		subtitle.setEpisode(Utils.readInt(node, "episode"));

		String language = Utils.readString(node, "language");
		if (language != null) {
			if (language.equals("VF")) {
				subtitle.setLanguage(SubtitleLanguage.VF);
			} else if (language.equals("VO")) {
				subtitle.setLanguage(SubtitleLanguage.VO);
			} else if (language.equals("VOVF")) {
				subtitle.setLanguage(SubtitleLanguage.VOVF);
			} else {
				subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
			}
		} else {
			subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
		}

		subtitle.setSource(Utils.readString(node, "source"));
		subtitle.setFile(Utils.readString(node, "file"));
		subtitle.setUrl(Utils.readString(node, "url"));

		return subtitle;
	}

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
	private String source;

	/**
	 * File
	 */
	private String file;

	/**
	 * Url to the subtitle
	 * 
	 * TODO Change to url
	 */
	private String url;

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
	public String getSource() {
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
	 * Return the season
	 * 
	 * @param season
	 *            the season to set
	 */
	public void setSeason(int season) {
		this.season = season;
	}

	/**
	 * Set the source
	 * 
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
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
