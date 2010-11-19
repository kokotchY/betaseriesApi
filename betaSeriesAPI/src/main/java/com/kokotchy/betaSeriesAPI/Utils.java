package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utils class
 * 
 * @author kokotchy
 */
public class Utils {

	/**
	 * TODO Fill it
	 */
	private static final String API_KEY_FILE = "apiKey.txt";

	/**
	 * TODO Fill it
	 * 
	 * @param userDir
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getApiKey(String userDir) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(userDir, API_KEY_FILE)));
		String key = reader.readLine().trim();
		reader.close();
		return key;
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
			for (byte element : digest) {
				int value = element;
				if (value < 0) {
					value += 256;
				}
				if (value <= 14) {
					buffer.append("0" + Integer.toHexString(value));
				} else {
					buffer.append(Integer.toHexString(value));
				}
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
	 * Return the parameters to the form key=val&(key=val)*
	 * 
	 * @param params
	 *            Params to get as string
	 * @return String representation of the parameters
	 */
	public static String getParamAsString2(Map<String, String> params) {
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
}
