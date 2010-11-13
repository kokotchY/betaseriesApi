package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;
import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Model of a subtitle
 * 
 * @author kokotchy
 */
public class Subtitle {

	/**
	 * TODO Fill it
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static Subtitle createSubtitle(JSONObject jsonObject) {
		Subtitle subtitle = new Subtitle();
		subtitle.setTitle(UtilsJson.getStringValue(jsonObject, "title"));
		subtitle.setSeason(UtilsJson.getIntValue(jsonObject, "season"));
		subtitle.setEpisode(UtilsJson.getIntValue(jsonObject, "episode"));

		String language = UtilsJson.getStringValue(jsonObject, "language");
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

		subtitle.setSource(UtilsJson.getStringValue(jsonObject, "source"));
		subtitle.setFile(UtilsJson.getStringValue(jsonObject, "file"));
		subtitle.setUrl(UtilsJson.getStringValue(jsonObject, "url"));

		return subtitle;
	}

	/**
	 * Create a new subtitle from the node
	 * 
	 * @param node
	 *            Node
	 * @return Subtitle
	 */
	public static Subtitle createSubtitle(Node node) {
		Subtitle subtitle = new Subtitle();
		subtitle.setTitle(UtilsXml.readString(node, "title"));
		subtitle.setSeason(UtilsXml.readInt(node, "season"));
		subtitle.setEpisode(UtilsXml.readInt(node, "episode"));

		String language = UtilsXml.readString(node, "language");
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

		subtitle.setSource(UtilsXml.readString(node, "source"));
		subtitle.setFile(UtilsXml.readString(node, "file"));
		subtitle.setUrl(UtilsXml.readString(node, "url"));

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
	private SubtitleSource source;

	/**
	 * File
	 */
	private String file;

	/**
	 * Url to the subtitle TODO Change to url
	 */
	private String url;

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
