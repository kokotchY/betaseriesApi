package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.api.factories.ErrorFactory;
import com.kokotchy.betaSeriesAPI.model.Error;

/**
 * Utils
 * 
 * @author kokotchy
 */
public class UtilsXml {

	/**
	 * User agent
	 */
	private static final String USER_AGENT = "kokotchY Java API";

	/**
	 * Host to connect
	 */
	private static String host = "api.betaseries.com";

	/**
	 * Debug path
	 */
	private static String debugPath = null;

	/**
	 * Debug option
	 */
	private static boolean debug = false;

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
		String uriPattern = "http://%s/%s?%s";
		params.put("key", apiKey);
		URL url = null;
		URLConnection connection = null;
		BufferedReader reader = null;
		try {
			if (action.endsWith(".xml")) {
				throw new RuntimeException("Don't use extension");
			}
			if (debug && debugPath != null) {
				File file = Utils
						.getDebugFile(debugPath, action, params, "xml");
				reader = new BufferedReader(new InputStreamReader(
						new FileInputStream(file), "UTF-8"));
			} else {
				action += ".xml";
				url = new URL(String.format(uriPattern, host, action, Utils
						.getParamAsString(params)));
				connection = url.openConnection();
				connection.setRequestProperty("User-Agent", USER_AGENT);
				reader = new BufferedReader(new InputStreamReader(connection
						.getInputStream()));
			}
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String xmlResponse = buffer.toString();
			if (debug) {
				System.out.println("XML Response: " + xmlResponse);
			}
			return DocumentHelper.parseText(xmlResponse);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.getInputStream().close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Return an int value from the attribute's node
	 * 
	 * @param node
	 *            Node
	 * @param name
	 *            Name of the attribute
	 * @return Int value
	 */
	public static int getAttributeIntValue(Node node, String name) {
		return Integer.parseInt(getAttributeStringValue(node, name));
	}

	/**
	 * Return the string value from the attribute's node
	 * 
	 * @param node
	 *            Node
	 * @param name
	 *            Name
	 * @return String value
	 */
	public static String getAttributeStringValue(Node node, String name) {
		Attribute code = ((Element) node).attribute(name);
		return code.getText();
	}

	/**
	 * Return the errors from the document
	 * 
	 * @param document
	 *            Document
	 * @return List of error
	 */
	@SuppressWarnings("unchecked")
	public static Set<Error> getErrors(Document document) {
		Set<Error> errors = new HashSet<Error>();
		List<Node> nodes = document.selectNodes("/root/errors/error");
		if (nodes.size() > 0) {
			for (Node node : nodes) {
				errors.add(ErrorFactory.createError(node));
			}
		}
		return errors;
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
		// return document.selectSingleNode("/root/code").getText().equals("0");
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
	public static boolean getBoolean(Node node, String name) {
		return getInt(node, name) == 1;
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
	public static int getInt(Node node, String name) {
		String text = getString(node, name);
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
	public static String getString(Node node, String string) {
		Node selectedNode = node.selectSingleNode(string);
		if (selectedNode != null) {
			// return
			// StringEscapeUtils.escapeHtml(selectedNode.getText()).trim()
			// .replace("\r", "\n");
			return StringEscapeUtils.unescapeHtml(
					StringEscapeUtils.escapeHtml(selectedNode.getText()))
					.trim().replace("\r", "\n");
		}
		return null;
	}

	/**
	 * Set the debug option
	 * 
	 * @param debug
	 *            Debug option
	 */
	public static void setDebug(boolean debug) {
		UtilsXml.debug = debug;
	}

	/**
	 * Debug directory
	 * 
	 * @param debugDirectory
	 *            Directory having debug files
	 */
	public static void setDebugPath(String debugPath) {
		UtilsXml.debugPath = debugPath;
	}

	/**
	 * Set the host to connect
	 * 
	 * @param host
	 *            Host
	 */
	public static void setHost(String host) {
		UtilsXml.host = host;
	}
}
