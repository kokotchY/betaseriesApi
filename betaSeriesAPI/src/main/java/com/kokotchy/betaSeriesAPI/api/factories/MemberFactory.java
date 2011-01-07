package com.kokotchy.betaSeriesAPI.api.factories;

import java.util.List;

import org.dom4j.Node;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.Constants;
import com.kokotchy.betaSeriesAPI.model.Member;

/**
 * Member factory
 * 
 * @author kokotchy
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
		member.setLogin(UtilsJson.getStringValue(jsonObject,
				Constants.LOGIN));
		member.setAvatar(UtilsJson.getStringValue(jsonObject,
				Constants.AVATAR));
		member.setStats(StatsFactory.createStats(UtilsJson.getJSONObject(
				jsonObject, Constants.STATS)));
		JSONObject shows = UtilsJson.getJSONObject(jsonObject,
				Constants.SHOWS);
		JSONObject[] array = UtilsJson.getArray(shows);
		if (array.length > 0) {
			for (JSONObject show : array) {
				member.addShow(ShowFactory.createShow(show));
			}
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
		member.setLogin(UtilsXml.readString(node, Constants.LOGIN));
		member.setAvatar(UtilsXml.readString(node, Constants.AVATAR));
		member.setStats(StatsFactory.createStats(node
				.selectSingleNode(Constants.STATS)));
		List<Node> shows = node.selectNodes("shows/show");
		if (shows.size() > 0) {
			for (Node show : shows) {
				member.addShow(ShowFactory.createShow(show));
			}
		}
		return member;
	}
}
