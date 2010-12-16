package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Subtitle Factory
 * 
 * @author kokotchy
 */
public class SubtitleFactory {
	/**
	 * TODO Fill it
	 */
	private static final String URL = "url";

	/**
	 * TODO Fill it
	 */
	private static final String FILE = "filename";

	/**
	 * TODO Fill it
	 */
	private static final String SOURCE = "source";

	/**
	 * TODO Fill it
	 */
	private static final String VOVF = "VOVF";

	/**
	 * TODO Fill it
	 */
	private static final String VO = "VO";

	/**
	 * TODO Fill it
	 */
	private static final String VF = "VF";

	/**
	 * TODO Fill it
	 */
	private static final String LANGUAGE = "language";

	/**
	 * TODO Fill it
	 */
	private static final String EPISODE = "episode";

	/**
	 * TODO Fill it
	 */
	private static final String SEASON = "season";

	/**
	 * TODO Fill it
	 */
	private static final String TITLE = "title";

	/**
	 * TODO Fill it
	 */
	private static final String QUALITY = "quality";

	/**
	 * Create a subtitle from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Subtitle
	 */
	public static Subtitle createSubtitle(JSONObject jsonObject) {
		Subtitle subtitle = new Subtitle();
		subtitle.setTitle(UtilsJson.getStringValue(jsonObject, TITLE));
		subtitle.setSeason(UtilsJson.getIntValue(jsonObject, SEASON));
		subtitle.setEpisode(UtilsJson.getIntValue(jsonObject, EPISODE));

		String language = UtilsJson.getStringValue(jsonObject, LANGUAGE);
		if (language != null) {
			if (language.equals(VF)) {
				subtitle.setLanguage(SubtitleLanguage.VF);
			} else if (language.equals(VO)) {
				subtitle.setLanguage(SubtitleLanguage.VO);
			} else if (language.equals(VOVF)) {
				subtitle.setLanguage(SubtitleLanguage.VOVF);
			} else {
				subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
			}
		} else {
			subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
		}

		subtitle.setSource(UtilsJson.getStringValue(jsonObject, SOURCE));
		subtitle.setFile(UtilsJson.getStringValue(jsonObject, FILE));
		subtitle.setUrl(UtilsJson.getStringValue(jsonObject, URL));
		subtitle.setQuality(UtilsJson.getIntValue(jsonObject, QUALITY));

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
		subtitle.setTitle(UtilsXml.readString(node, TITLE));
		subtitle.setSeason(UtilsXml.readInt(node, SEASON));
		subtitle.setEpisode(UtilsXml.readInt(node, EPISODE));

		String language = UtilsXml.readString(node, LANGUAGE);
		if (language != null) {
			if (language.equals(VF)) {
				subtitle.setLanguage(SubtitleLanguage.VF);
			} else if (language.equals(VO)) {
				subtitle.setLanguage(SubtitleLanguage.VO);
			} else if (language.equals(VOVF)) {
				subtitle.setLanguage(SubtitleLanguage.VOVF);
			} else {
				subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
			}
		} else {
			subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
		}

		subtitle.setSource(UtilsXml.readString(node, SOURCE));
		subtitle.setFile(UtilsXml.readString(node, FILE));
		subtitle.setUrl(UtilsXml.readString(node, URL));
		subtitle.setQuality(UtilsXml.readInt(node, QUALITY));

		return subtitle;
	}
}
