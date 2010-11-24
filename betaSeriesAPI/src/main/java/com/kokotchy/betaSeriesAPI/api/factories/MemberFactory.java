package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Member;

/**
 * @author canas
 */
public class MemberFactory {
	/**
	 * Create member object from json object
	 * 
	 * @param jsonObject
	 *            JSON object
	 * @return Member
	 */
	public static Member createMember(JSONObject jsonObject) {
		Member member = new Member();
		try {
			member.setLogin(UtilsJson.getStringValue(jsonObject, "login"));
			member.setAvatar(UtilsJson.getStringValue(jsonObject, "avatar"));
			member.setStats(StatsFactory.createStats(UtilsJson.getJSONObject(
					jsonObject, "stats")));
			JSONObject shows = UtilsJson.getJSONObject(jsonObject, "shows");
			for (String name : JSONObject.getNames(shows)) {
				JSONObject show = shows.getJSONObject(name);
				member.addShow(ShowFactory.createShow(show));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return member;
	}

	/**
	 * Create the member from the node
	 * 
	 * @param node
	 *            Node
	 * @return Member
	 */
	@SuppressWarnings("unchecked")
	public static Member createMember(Node node) {
		Member member = new Member();
		member.setLogin(UtilsXml.readString(node, "login"));
		member.setAvatar(UtilsXml.readString(node, "avatar"));
		member.setStats(StatsFactory
				.createStats(node.selectSingleNode("stats")));
		List<Node> shows = node.selectNodes("shows/show");
		for (Node show : shows) {
			member.addShow(ShowFactory.createShow(show));
		}
		return member;
	}
}
