package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.api.IPlanning;
import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * Planning API
 * 
 * @author kokotchy
 * 
 */
public class Planning implements IPlanning {

	/**
	 * 
	 */
	private String apiKey;

	public Planning(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Episode> getGeneralPlanning() {
		return getPlanning(null, null, null);
	}

	@Override
	public List<Episode> getMemberPlanning(boolean unseen, String token) {
		return getPlanning(unseen, token, true);
	}

	@Override
	public List<Episode> getMemberPlanning(String login, boolean unseen) {
		return getPlanning(unseen, login, false);
	}

	/**
	 * @param unseen
	 * @param token
	 * @param identifiedUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Episode> getPlanning(Boolean unseen, String token,
			Boolean identifiedUser) {
		Document document = null;
		if (unseen == null && token == null && identifiedUser == null) {
			document = Utils.executeQuery("planning/general.xml", apiKey);
		} else {
			Map<String, String> params = new HashMap<String, String>();
			String action;
			if (unseen != null && unseen) {
				params.put("view", "unseen");
			}
			if (identifiedUser) {
				params.put("token", token);
				action = "planning/member.xml";
			} else {
				action = "planning/member/" + token + ".xml";
			}
			document = Utils.executeQuery(action, apiKey, params);
		}

		List<Node> nodes = document.selectNodes("/root/planning/episode");
		List<Episode> episodes = new LinkedList<Episode>();
		for (Node node : nodes) {
			episodes.add(Episode.createEpisode(node));
		}
		return episodes;
	}
}
