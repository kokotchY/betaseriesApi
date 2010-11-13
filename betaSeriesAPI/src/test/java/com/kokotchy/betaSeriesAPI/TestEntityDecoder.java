package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

/**
 *Test the entity decoder
 * 
 * @author kokotchy
 */
public class TestEntityDecoder extends TestCase {

	/**
	 * Entity convert
	 */
	public void testConvertEntity() {
		Map<String, String> showsTitle = new HashMap<String, String>();
		File file = new File(System.getProperty("user.dir"),
				"src/test/resources/testEntity.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			String key = null;
			String value = null;
			while ((line = reader.readLine()) != null) {
				if (value != null) {
					showsTitle.put(key, value);
					value = null;
					key = null;
				} else if (key != null) {
					value = line;
				} else {
					key = line;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Entry<String, String> entry : showsTitle.entrySet()) {
			assertEquals(entry.getKey(), EntityDecoder.htmlToChar(entry
					.getValue()));
		}
	}

}
