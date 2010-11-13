package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;
import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Model of a member
 * 
 * @author kokotchy
 */
public class Member {

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
			member.setStats(Stats.createStats(UtilsJson.getJSONObject(
					jsonObject, "stats")));
			JSONArray showsArray = UtilsJson.getJSONArray(jsonObject, "shows");
			for (int i = 0; i < showsArray.length(); i++) {
				JSONObject show = showsArray.getJSONObject(i);
				member.addShow(Show.createShow(show));
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
		member.setStats(Stats.createStats(node.selectSingleNode("stats")));
		List<Node> shows = node.selectNodes("shows/show");
		for (Node show : shows) {
			member.addShow(Show.createShow(show));
		}
		return member;
	}

	/**
	 * Login of the member
	 */
	private String login;

	/**
	 * Avatar of the user
	 */
	private String avatar;

	/**
	 * Statistics about the user
	 * 
	 * TODO Remove stats object an include directly in Member model
	 */
	private Stats stats;

	/**
	 * List of shows followed by the member
	 */
	private List<Show> shows = new LinkedList<Show>();

	/**
	 * Add the given show
	 * 
	 * @param show
	 *            Show
	 */
	public void addShow(Show show) {
		shows.add(show);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Member)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the avatar
	 * 
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Return the login
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Return the shows
	 * 
	 * @return the shows
	 */
	public List<Show> getShows() {
		return shows;
	}

	/**
	 * Return statistics
	 * 
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, login);
		result = HashCodeUtil.hash(result, avatar);
		result = HashCodeUtil.hash(result, stats);
		result = HashCodeUtil.hash(result, shows);
		return result;
	}

	/**
	 * Return avatar
	 * 
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Set login
	 * 
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Set stats
	 * 
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return login + " with " + shows.size() + " shows";
	}
}
