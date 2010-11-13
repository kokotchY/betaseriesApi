package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utils to handle json file
 * 
 * @author kokotchy
 */
public class UtilsJson {

	/**
	 * Debug option
	 */
	public static boolean debug = false;

	/**
	 * Directory containing example of json
	 */
	public static String debugPath;

	/**
	 * Execute the action with the api key
	 * 
	 * @param action
	 *            Action to execute
	 * @param apiKey
	 *            Api key
	 * @return Resulting object
	 */
	public static JSONObject executeQuery(String action, String apiKey) {
		return executeQuery(action, apiKey, new HashMap<String, String>());
	}

	/**
	 * Execute the action with the api key and the params
	 * 
	 * @param action
	 *            Action to execute
	 * @param apiKey
	 *            Api key
	 * @param params
	 *            Parameters
	 * @return Resulting object
	 */
	public static JSONObject executeQuery(String action, String apiKey,
			Map<String, String> params) {
		params.put("key", apiKey);
		BufferedReader reader = null;
		try {
			if (!action.endsWith(".json")) {
				action += ".json";
			}
			if (debug) {
				System.out.println("Debug mode activated, use file");
				File file = new File(debugPath + action);
				reader = new BufferedReader(new FileReader(file));
			} else {
				String urlPattern = "http://%s/%s?%s";
				String host = "api.betaseries.com";
				URL url = new URL(String.format(urlPattern, host, action, Utils
						.getParamAsString(params)));
				URLConnection connection = url.openConnection();
				reader = new BufferedReader(new InputStreamReader(connection
						.getInputStream()));
			}
			String line = "";
			StringBuffer json = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			JSONObject result = new JSONObject(json.toString());
			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Return a boolean value from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param name
	 *            Name
	 * @return Boolean value
	 */
	public static boolean getBooleanValue(JSONObject jsonObject, String name) {
		return getIntValue(jsonObject, name) == 1;
	}

	/**
	 * Return the int value with the given name
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param name
	 *            Name
	 * @return Int value
	 */
	public static int getIntValue(JSONObject jsonObject, String name) {
		if (jsonObject.has(name)) {
			try {
				return jsonObject.getInt(name);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * Return a json array with the given name
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param name
	 *            Name
	 * @return JSON array
	 */
	public static JSONArray getJSONArray(JSONObject jsonObject, String name) {
		if (jsonObject.has(name)) {
			try {
				return jsonObject.getJSONArray(name);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new JSONArray();
	}

	/**
	 * Return the json array from the given path (similar to xpath)
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param path
	 *            Path
	 * @return Json Array
	 */
	public static JSONArray getJSONArrayFromPath(JSONObject jsonObject,
			String path) {
		try {
			String[] split = path.split("/");
			return getLastObject(jsonObject, split).getJSONArray(
					split[split.length - 1]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return a boolean from a path (1 = true, 0 = false)
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param path
	 *            Path
	 * @return Boolean value
	 */
	public static boolean getJSONBooleanFromPath(JSONObject jsonObject,
			String path) {
		return getJSONIntFromPath(jsonObject, path) == 1;
	}

	/**
	 * Return an int from the given path
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param path
	 *            Path to the value
	 * @return Int value
	 */
	public static int getJSONIntFromPath(JSONObject jsonObject, String path) {
		return Integer.parseInt(getJSONStringFromPath(jsonObject, path));
	}

	/**
	 * Return the json object from the jsonObject with the name
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param name
	 *            Name
	 * @return Json object
	 */
	public static JSONObject getJSONObject(JSONObject jsonObject, String name) {
		if (jsonObject.has(name)) {
			try {
				return jsonObject.getJSONObject(name);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Return the json object from the path
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param path
	 *            Path to the object
	 * @return Json object
	 */
	public static JSONObject getJSONObjectFromPath(JSONObject jsonObject,
			String path) {
		String[] split = path.split("/");
		try {
			return getLastObject(jsonObject, split).getJSONObject(
					split[split.length - 1]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return the string value from the path
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param path
	 *            Path
	 * @return String value
	 */
	public static String getJSONStringFromPath(JSONObject jsonObject,
			String path) {
		String[] split = path.split("/");
		try {
			return getLastObject(jsonObject, split).getString(
					split[split.length - 1]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return the last object from the path
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param split
	 *            Part of the apth
	 * @return Json Object
	 * @throws JSONException
	 */
	private static JSONObject getLastObject(JSONObject jsonObject,
			String[] split) throws JSONException {
		JSONObject object = jsonObject;
		for (int i = 0; i < split.length - 1; i++) {
			String part = split[i].trim();
			if (part.length() != 0) {
				object = object.getJSONObject(part);
			}
		}
		return object;
	}

	/**
	 * Return the string value from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @param name
	 *            Name of the string
	 * @return String value
	 * @throws JSONException
	 */
	public static String getStringValue(JSONObject jsonObject, String name) {
		if (jsonObject.has(name)) {
			try {
				return jsonObject.getString(name);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Return true if the document has errors, false otherwise
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Return true if there is an error, false otherwise
	 */
	public static boolean hasErrors(JSONObject jsonObject) {
		return getJSONIntFromPath(jsonObject, "/root/code") == 0;
	}

	/**
	 * Set debug option
	 * 
	 * @param debug
	 *            the debug to set
	 */
	public static void setDebug(boolean debug) {
		UtilsJson.debug = debug;
	}

	/**
	 * Define the debuging path
	 * 
	 * @param debugPath
	 *            the debugPath to set
	 */
	public static void setDebugPath(String debugPath) {
		UtilsJson.debugPath = debugPath;
	}
}