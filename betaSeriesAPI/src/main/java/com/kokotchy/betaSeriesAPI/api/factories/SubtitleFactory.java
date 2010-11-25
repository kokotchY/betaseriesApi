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
	 * Create a subtitle from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Subtitle
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
}
