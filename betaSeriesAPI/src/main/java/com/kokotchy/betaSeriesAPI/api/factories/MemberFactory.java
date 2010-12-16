package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.model.Member;

/**
 * Member factory
 * 
 * @author kokotchy
 */
public class MemberFactory {
	/**
	 * TODO Fill it
	 */
	private static final String SHOWS = "shows";

	/**
	 * TODO Fill it
	 */
	private static final String STATS = "stats";

	/**
	 * TODO Fill it
	 */
	private static final String AVATAR = "avatar";

	/**
	 * TODO Fill it
	 */
	private static final String LOGIN = "login";

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
			member.setLogin(UtilsJson.getStringValue(jsonObject, LOGIN));
			member.setAvatar(UtilsJson.getStringValue(jsonObject, AVATAR));
			member.setStats(StatsFactory.createStats(UtilsJson.getJSONObject(
					jsonObject, STATS)));
			JSONObject shows = UtilsJson.getJSONObject(jsonObject, SHOWS);
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
		member.setLogin(UtilsXml.readString(node, LOGIN));
		member.setAvatar(UtilsXml.readString(node, AVATAR));
		member.setStats(StatsFactory
				.createStats(node.selectSingleNode(STATS)));
		List<Node> shows = node.selectNodes("shows/show");
		for (Node show : shows) {
			member.addShow(ShowFactory.createShow(show));
		}
		return member;
	}
}
