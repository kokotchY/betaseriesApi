package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kokotchy
 * 
 */
public class BetaSerie {

	/**
	 * 
	 */
	private String API_KEY = "debfa5c405f5";

	/**
	 * 
	 */
	private String user;

	/**
	 * 
	 */
	private String pass;

	/**
	 * 
	 */
	private String userToken = null;

	/**
	 * @param pass
	 * @param user
	 * 
	 */
	public BetaSerie(String user, String pass) {
		this.user = user;
		this.pass = Utils.getMD5(pass);
	}

	/**
	 * @return
	 */
	public boolean login() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", user);
		params.put("password", pass);
		params.put("key", API_KEY);
		String requestPattern = "http://%s/%s/%s.%s?%s";
		String request = String.format(requestPattern, "api.betaseries.com",
				"members", "auth", "xml", Utils.getParamAsString(params));
		try {
			URL url = new URL(request);
			URLConnection connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			// SAXParserFactory factory = SAXParserFactory.newInstance();
			// SAXParser parser = factory.newSAXParser();
			// BetaSerieHandler handler = new BetaSerieHandler();
			// parser.parse(connection.getInputStream(), handler);
			// if (handler.isError()) {
			// System.out.println("Errors when trying to login:");
			// for (BetaSerieError error : handler.getErrors()) {
			// System.out.println(error);
			// }
			// return false;
			// } else {
			// userToken = handler.getToken();
			// if (userToken == null) {
			// return false;
			// }
			// }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// } catch (ParserConfigurationException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (SAXException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}
		System.out.println("Token: " + userToken);
		return true;
	}
}