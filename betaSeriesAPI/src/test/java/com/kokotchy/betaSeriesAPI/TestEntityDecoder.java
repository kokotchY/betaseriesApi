package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

/**
 *Test the entity decoder
 * 
 * @author kokotchy
 */
public class TestEntityDecoder extends TestCase {

	private void add(String value, List<String> list) {
		// System.out.println("Add " + value);
		list.add(value);
	}

	/**
	 * TODO Fill it
	 * 
	 * @param showsTitle
	 */
	private Map<String, String> loadFile() {
		Map<String, String> showsTitle = new HashMap<String, String>();
		File file = new File(System.getProperty("user.dir"),
				"src/test/resources/testEntity.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				if (!line.startsWith("#")) {
					String[] split = line.split("\\|");
					showsTitle.put(split[0], split[1]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return showsTitle;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return
	 */
	private Map<String, String> loadWords() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("Allison & Lillia", "Allison &amp; Lillia");
		result
				.put("Avez-vous déjà vu... ?",
						"Avez-vous d\u00e9j\u00e0 vu... ?");
		result.put("Avengers: Earth’s Mightiest Heroes",
				"Avengers: Earth\u2019s Mightiest Heroes");
		result.put("Belle et Sébastien", "Belle et S\u00e9bastien");
		result.put("Blague à part", "Blague \u00e0 part");
		result.put("Caméra Café", "Cam\u00e9ra Caf\u00e9");
		result.put("Cam\u00e9ra Caf\u00e9", "Caméra Café");
		result.put("Caméra Café 2", "Cam\u00e9ra Caf\u00e9 2");
		result.put("DOGS Bullets & Carnage", "DOGS Bullets &amp; Carnage");
		result.put("Fais pas ci, fais pas ça", "Fais pas ci, fais pas \u00e7a");
		result.put("Física o Química", "F\u00edsica o Qu\u00edmica");
		result.put("Father & Son", "Father &amp; Son");
		result.put("Hélène et les garçons",
				"H\u00e9l\u00e8ne et les gar\u00e7ons");
		result.put("Hope & Faith", "Hope &amp; Faith");
		result.put("Il était une fois... la Vie",
				"Il \u00e9tait une fois... la Vie");
		result.put("Il était une fois... les Amériques",
				"Il \u00e9tait une fois... les Am\u00e9riques");
		result.put("Il était une fois... les Découvreurs",
				"Il \u00e9tait une fois... les D\u00e9couvreurs");
		result.put("Il était une fois... les Explorateurs",
				"Il \u00e9tait une fois... les Explorateurs");
		result.put("Kämpfer", "K\u00e4mpfer");
		result.put("Kath & Kim (US)", "Kath &amp; Kim (US)");
		result.put("Léa Parker", "L\u00e9a Parker");
		result.put("Law & Order: Los Angeles", "Law &amp; Order: Los Angeles");
		result.put("Law & Order", "Law &amp; Order");
		result.put("MÄR", "M\u00c4R");
		result.put("Melissa & Joey", "Melissa &amp; Joey");
		result.put("Pok\u00e9mon", "Pokémon");
		result.put("Ranma \u00bd", "Ranma ½");
		result.put("Romeo \u00d7 Juliet", "Romeo × Juliet");
		result.put("Saint Seiya: The Lost Canvas \u2013 Hades Mythology",
				"Saint Seiya: The Lost Canvas – Hades Mythology");
		result.put("T\u00fcrkisch f\u00fcr Anf\u00e4nger",
				"Türkisch für Anfänger");
		return result;
	}

	/**
	 * Entity convert
	 */
	public void testConvertEntity() {
		Map<String, String> showsTitle = new HashMap<String, String>();
		showsTitle = loadFile();

		for (Entry<String, String> entry : showsTitle.entrySet()) {
			String value = entry.getValue();
			List<String> list = new LinkedList<String>();
			add(EntityDecoder.charToHtml(value), list);
			add(EntityDecoder.entityToChar(value), list);
			add(EntityDecoder.entityToHtml(value), list);
			add(EntityDecoder.htmlToChar(value), list);
			for (String decodedValue : list) {
				if (entry.getKey().equals(decodedValue)) {
					assertTrue(true);
				}
			}
		}
	}

}
