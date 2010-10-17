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
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

/**
 * Utils
 * 
 * @author kokotchy
 * 
 */
public class Utils {

	/**
	 * Execute the action to betaserie server with the api key
	 * 
	 * @param action
	 *            Action to execute
	 * @param apiKey
	 *            API Key
	 * @return Resulting document
	 */
	public static Document executeQuery(String action, String apiKey) {
		return executeQuery(action, apiKey, new HashMap<String, String>());
	}

	/**
	 * Execute the action to betaserie server with the api key and params
	 * 
	 * @param action
	 *            Action to execute
	 * @param apiKey
	 *            API Key
	 * @param params
	 *            Parameters to the query
	 * @return Resulting document
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return the md5 of the given text
	 * 
	 * @param text
	 *            Text to get hash
	 * @return md5 hash of the text
	 */
	public static String getMD5(String text) {
		try {
			String result = "";
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes("UTF-8"));
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
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Return the parameters to the form key=val&(key=val)*
	 * 
	 * @param params
	 *            Params to get as string
	 * @return String representation of the parameters
	 */
	public static String getParamAsString(Map<String, String> params) {
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
	 * Return true if the document has errors, false otherwise
	 * 
	 * @param document
	 *            Document to check
	 * @return True if there is errors, false otherwise
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasErrors(Document document) {
		List<Node> selectNodes = document.selectNodes("/root/errors/error");
		return selectNodes.size() > 0;
	}

	/**
	 * Read the element of the node
	 * 
	 * @param node
	 *            Node
	 * @param string
	 *            Element to retrieve
	 * @return Value of the element of the node
	 */
	public static String readNode(Node node, String string) {
		Node selectedNode = node.selectSingleNode(string);
		if (selectedNode != null) {
			return selectedNode.getText();
		}
		return null;
	}
}
