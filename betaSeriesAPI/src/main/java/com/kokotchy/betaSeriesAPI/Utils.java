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
import java.util.Map.Entry;

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
		URL url = null;
		URLConnection connection = null;
		try {
			url = new URL(String.format(uriPattern, action,
					getParamAsString(params)));
			connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String xmlResponse = buffer.toString();
			System.out.println("XML Response: " + xmlResponse);
			return DocumentHelper.parseText(xmlResponse);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.getInputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			StringBuffer buffer = new StringBuffer();
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes("UTF-8"));
			byte[] digest = msgDigest.digest();
			for (int i = 0; i < digest.length; i++) {
				int value = digest[i];
				if (value < 0) {
					value += 256;
				}
				buffer.append(Integer.toHexString(value));
			}
			return buffer.toString();
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
		StringBuffer buffer = new StringBuffer();
		int idx = 0;
		int size = params.size();

		for (Entry<String, String> entry : params.entrySet()) {
			buffer.append(entry.getKey() + "=" + entry.getValue());
			if (++idx < size) {
				buffer.append("&");
			}
		}

		return buffer.toString();
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
	 * Read a boolean from the node
	 * 
	 * @param node
	 *            Node
	 * @param name
	 *            Name of the key
	 * @return True if the value is 1, false otherwise
	 */
	public static boolean readBoolean(Node node, String name) {
		return readInt(node, name) == 1;
	}

	/**
	 * Read an int from the node
	 * 
	 * @param node
	 *            Node
	 * @param name
	 *            Name of the key
	 * @return Integer
	 */
	public static int readInt(Node node, String name) {
		String text = readString(node, name);
		if (text != null) {
			return Integer.parseInt(text);
		} else {
			return -1;
		}
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
	public static String readString(Node node, String string) {
		Node selectedNode = node.selectSingleNode(string);
		if (selectedNode != null) {
			return selectedNode.getText();
		}
		return null;
	}
}
