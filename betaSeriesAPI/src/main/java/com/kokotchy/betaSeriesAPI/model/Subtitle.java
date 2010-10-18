package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * @author kokotchy
 * 
 */
public class Subtitle {

	/**
	 * @param node
	 * @return
	 */
	public static Subtitle createSubtitle(Node node) {
		Subtitle subtitle = new Subtitle();
		subtitle.setTitle(Utils.readNode(node, "title"));
		subtitle.setSeason(Integer.parseInt(Utils.readNode(node, "season")));
		subtitle.setEpisode(Integer.parseInt(Utils.readNode(node, "episode")));

		String language = Utils.readNode(node, "language");
		if (language.equals("VF")) {
			subtitle.setLanguage(SubtitleLanguage.VF);
		} else if (language.equals("VO")) {
			subtitle.setLanguage(SubtitleLanguage.VO);
		} else if (language.equals("VOVF")) {
			subtitle.setLanguage(SubtitleLanguage.VOVF);
		}

		subtitle.setSource(Utils.readNode(node, "source"));
		subtitle.setFile(Utils.readNode(node, "file"));
		subtitle.setUrl(Utils.readNode(node, "url"));

		return subtitle;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
