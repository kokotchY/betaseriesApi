package com.kokotchy.betaSeriesAPI.api.factories;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Subtitle Factory
 * 
 * @author kokotchy
 */
public class SubtitleFactory {
	/**
	 * Create a subtitle from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Subtitle
	 */
	public static Subtitle createSubtitle(JSONObject jsonObject) {
		Subtitle subtitle = new Subtitle();
		subtitle
				.setTitle(UtilsJson.getStringValue(jsonObject, Constants.TITLE));
		subtitle.setSeason(UtilsJson.getIntValue(jsonObject, Constants.SEASON));
		subtitle.setEpisode(UtilsJson
				.getIntValue(jsonObject, Constants.EPISODE));

		String language = UtilsJson.getStringValue(jsonObject,
				Constants.LANGUAGE);
		if (language != null) {
			if (language.equals(Constants.VF)) {
				subtitle.setLanguage(SubtitleLanguage.VF);
			} else if (language.equals(Constants.VO)) {
				subtitle.setLanguage(SubtitleLanguage.VO);
			} else if (language.equals(Constants.VOVF)) {
				subtitle.setLanguage(SubtitleLanguage.VOVF);
			} else {
				subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
			}
		} else {
			subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
		}

		subtitle.setSource(UtilsJson.getStringValue(jsonObject,
				Constants.SOURCE));
		subtitle.setFile(UtilsJson.getStringValue(jsonObject, Constants.FILE));
		subtitle.setUrl(UtilsJson.getStringValue(jsonObject, Constants.URL));
		subtitle.setQuality(UtilsJson
				.getIntValue(jsonObject, Constants.QUALITY));

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
		subtitle.setTitle(UtilsXml.readString(node, Constants.TITLE));
		subtitle.setSeason(UtilsXml.readInt(node, Constants.SEASON));
		subtitle.setEpisode(UtilsXml.readInt(node, Constants.EPISODE));

		String language = UtilsXml.readString(node, Constants.LANGUAGE);
		if (language != null) {
			if (language.equals(Constants.VF)) {
				subtitle.setLanguage(SubtitleLanguage.VF);
			} else if (language.equals(Constants.VO)) {
				subtitle.setLanguage(SubtitleLanguage.VO);
			} else if (language.equals(Constants.VOVF)) {
				subtitle.setLanguage(SubtitleLanguage.VOVF);
			} else {
				subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
			}
		} else {
			subtitle.setLanguage(SubtitleLanguage.UNKNOWN);
		}

		subtitle.setSource(UtilsXml.readString(node, Constants.SOURCE));
		subtitle.setFile(UtilsXml.readString(node, Constants.FILE));
		subtitle.setUrl(UtilsXml.readString(node, Constants.URL));
		subtitle.setQuality(UtilsXml.readInt(node, Constants.QUALITY));

		return subtitle;
	}
}
