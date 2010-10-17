package com.kokotchy.betaSeriesAPI.api;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * @author kokotchy
 * 
 */
public class Members {

	/**
	 * 
	 */
	private String apiKey;

	/**
	 * 
	 */
	private String token;

	/**
	 * @param apiKey
	 */
	public Members(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @param login
	 * @param password
	 */
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

	/**
	 * 
	 */
	public void destroy() {

	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 */
	public void infos() {

	}

	/**
	 * @param user
	 */
	public void infos(String user) {

	}
}
