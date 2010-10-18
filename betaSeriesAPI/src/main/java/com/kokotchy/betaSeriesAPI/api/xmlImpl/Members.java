package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.api.SubtitleLanguage;

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
	public void getEpisodes(String token, SubtitleLanguage subtitleLanguage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getNotifications(boolean seen, int nb, int lastId) {
		// TODO Auto-generated method stub

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
	public void infos() {

	}

	@Override
	public void infos(String user) {

	}

	@Override
	public void setWatched(String token, String url, int season, int episode) {
		// TODO Auto-generated method stub

	}
}
