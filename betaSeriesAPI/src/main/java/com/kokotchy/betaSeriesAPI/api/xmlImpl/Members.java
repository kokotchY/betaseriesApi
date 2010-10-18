package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.api.SubtitleLanguage;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Member;
import com.kokotchy.betaSeriesAPI.model.Notification;

/**
 * Members API
 * 
 * @author kokotchy
 * 
 */
public class Members implements IMembers {

	/**
	 * API Key
	 */
	private String apiKey;

	/**
	 * Token of logged user
	 */
	private String token;

	/**
	 * Create new members api with the given key
	 * 
	 * @param apiKey
	 *            API Key
	 */
	public Members(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public boolean auth(String login, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", login);
		params.put("password", Utils.getMD5(password));
		Document document = Utils.executeQuery("members/auth.xml", apiKey,
				params);
		if (document.selectSingleNode("/root/code").getText().equals("1")) {
			Node tokenNode = document.selectSingleNode("/root/member/token");
			token = tokenNode.getText();
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {

	}

	@Override
	public List<Episode> getEpisodes(String token,
			SubtitleLanguage subtitleLanguage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getNotifications(boolean seen, int nb, int lastId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the token of the user
	 * 
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	@Override
	public Member infos(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member infosOfUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetViewedShow(String token, String url) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWatched(String token, String url, int season, int episode) {
		// TODO Auto-generated method stub

	}
}
