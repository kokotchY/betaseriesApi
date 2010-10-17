package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

/**
 * @author kokotchy
 * 
 */
public class Utils {

	/**
	 * @param action
	 * @param apiKey
	 * @return
	 */
	public static Document executeQuery(String action, String apiKey) {
		return executeQuery(action, apiKey, new HashMap<String, String>());
	}

	/**
	 * @param action
	 * @param apiKey
	 * @param params
	 * @return
	 */
	public static Document executeQuery(String action, String apiKey,
			Map<String, String> params) {
		String uriPattern = "http://api.betaseries.com/%s?%s";
		params.put("key", apiKey);
		try {
			URL url = new URL(String.format(uriPattern, action,
					getParamAsString(params)));
			URLConnection connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String xmlResponse = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				xmlResponse += line;
			}

			System.out.println("XML Response: " + xmlResponse);

			return DocumentHelper.parseText(xmlResponse);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param pass
	 * @return
	 */
	public static String getMD5(String pass) {
		try {
			String result = "";
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(pass.getBytes("UTF-8"));
			byte[] digest = msgDigest.digest();
			for (int i = 0; i < digest.length; i++) {
				int value = digest[i];
				if (value < 0) {
					value += 256;
				}
				result += Integer.toHexString(value);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param params
	 * @return
	 */
	public static Object getParamAsString(Map<String, String> params) {
		String result = "";
		int idx = 0;
		int size = params.size();
		for (String key : params.keySet()) {
			String value = params.get(key);
			result += key + "=" + value;
			if (++idx < size) {
				result += "&";
			}
		}
		return result;
	}

	/**
	 * @param node
	 * @param string
	 * @return
	 */
	public static String readNode(Node node, String string) {
		Node selectedNode = node.selectSingleNode(string);
		if (selectedNode != null) {
			return selectedNode.getText();
		}
		return null;
	}
}
