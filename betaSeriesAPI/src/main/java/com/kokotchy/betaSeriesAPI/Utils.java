package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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
	public static String getApiKey(String userDir)
			throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				userDir, API_KEY_FILE)));
		String key = readLine(reader);
		reader.close();
		return key;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param action
	 * @param params
	 * @param type
	 *            Json or xml
	 * @return
	 */
	public static File getDebugFile(String debugPath, String action,
			Map<String, String> params, String type) {
		String[] paramsArray = new String[params.size()];
		int i = 0;
		for (Entry<String, String> entry : params.entrySet()) {
			paramsArray[i++] = entry.getKey() + "_" + entry.getValue();
		}
		Arrays.sort(paramsArray);
		StringBuffer buffer = new StringBuffer();
		for (i = 0; i < paramsArray.length; i++) {
			buffer.append(paramsArray[i]);
			if (i < paramsArray.length - 1) {
				buffer.append("_");
			}
		}
		String patternFilename = "%s-%s.%s";
		String filename = String.format(patternFilename, action, buffer, type);
		File file = new File(debugPath, filename);
		return file;
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

	/**
	 * TODO Fill it
	 * 
	 * @param credentials
	 * @return
	 */
	public static String[] loadCredentials(File credentials) {
		BufferedReader reader = null;
		String login = null;
		String password = null;
		String token = null;
		try {
			reader = new BufferedReader(new FileReader(credentials));
			login = readLine(reader);
			password = readLine(reader);
			token = readLine(reader);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new String[] { login, password, token };
	}

	/**
	 * TODO Fill it
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	private static String readLine(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		if (line != null) {
			return line.trim();
		}
		return null;
	}
}
