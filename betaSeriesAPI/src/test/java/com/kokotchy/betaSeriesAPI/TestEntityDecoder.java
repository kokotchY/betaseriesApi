package com.kokotchy.betaSeriesAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Test the entity decoder
 * 
 * @author kokotchy
 */
public class TestEntityDecoder extends TestCase {

	/**
	 * Load the string to test from a file
	 * 
	 * @return List of string to test
	 */
	private Map<String, String> loadFile() {
		Map<String, String> showsTitle = new HashMap<String, String>();
		File file = new File(System.getProperty("user.dir"),
				"src/test/resources/testEntity.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
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
	 * Load the string to test
	 * 
	 * @return List of string to test
	 */
	private Map<String, String> loadWords() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("Allison & Lillia", "Allison &amp; Lillia");
		result.put("Avez-vous déjà vu... ?",
				"Avez-vous d&eacute;j&agrave; vu... ?");
		result.put("Avengers: Earth’s Mightiest Heroes",
				"Avengers: Earth&rsquo;s Mightiest Heroes");
		result.put("Belle et Sébastien", "Belle et S&eacute;bastien");
		result.put("Blague à part", "Blague &agrave; part");
		result.put("DOGS Bullets & Carnage", "DOGS Bullets &amp; Carnage");
		result.put("Fais pas ci, fais pas ça",
				"Fais pas ci, fais pas &ccedil;a");
		result.put("Física o Química", "F&iacute;sica o Qu&iacute;mica");
		result.put("Father & Son", "Father &amp; Son");
		result.put("Hélène et les garçons",
				"H&eacute;l&egrave;ne et les gar&ccedil;ons");
		result.put("Hope & Faith", "Hope &amp; Faith");
		result.put("Il était une fois... la Vie",
				"Il &eacute;tait une fois... la Vie");
		result.put("Il était une fois... les Amériques",
				"Il &eacute;tait une fois... les Am&eacute;riques");
		result.put("Il était une fois... les Découvreurs",
				"Il &eacute;tait une fois... les D&eacute;couvreurs");
		result.put("Il était une fois... les Explorateurs",
				"Il &eacute;tait une fois... les Explorateurs");
		result.put("Kämpfer", "K&auml;mpfer");
		result.put("Kath & Kim (US)", "Kath &amp; Kim (US)");
		result.put("Léa Parker", "L&eacute;a Parker");
		result.put("Law & Order: Los Angeles", "Law &amp; Order: Los Angeles");
		result.put("Law & Order", "Law &amp; Order");
		result.put("MÄR", "M&Auml;R");
		result.put("Melissa & Joey", "Melissa &amp; Joey");
		result.put("Pokémon", "Pok&eacute;mon");
		result.put("Ranma ½", "Ranma &frac12;");
		result.put("Romeo × Juliet", "Romeo &times; Juliet");
		result.put("Saint Seiya: The Lost Canvas – Hades Mythology",
				"Saint Seiya: The Lost Canvas &ndash; Hades Mythology");
		result.put("Türkisch für Anfänger",
				"T&uuml;rkisch f&uuml;r Anf&auml;nger");
		return result;
	}

	/**
	 * Entity convert from file
	 */
	public void testConvertEntityFromFile() {
		Map<String, String> showsTitle = loadFile();

		for (Entry<String, String> entry : showsTitle.entrySet()) {
			String beforeParsing = entry.getKey();
			String afterParsing = entry.getValue();
			String parsed = StringEscapeUtils.escapeHtml(beforeParsing);
			assertEquals(afterParsing, parsed);
		}
	}

	/**
	 * Entity convert from source
	 */
	public void testConvertEntityFromSourceCode() {
		Map<String, String> showsTitle = loadWords();

		for (Entry<String, String> entry : showsTitle.entrySet()) {
			String beforeParsing = entry.getKey();
			String afterParsing = entry.getValue();
			String parsed = StringEscapeUtils.escapeHtml(beforeParsing);
			assertEquals(afterParsing, parsed);
		}
	}

	/**
	 * Simple test of escaping/unescaping
	 */
	public void testEntityDecoding() {
		String jsonString = StringEscapeUtils
				.escapeHtml("Nouvel &eacute;pisode : Spartacus S01E08 - Mark of the Brotherhood");
		String xmlString = "Nouvel &amp;eacute;pisode : Spartacus S01E08 - Mark of the Brotherhood";
		assertEquals(xmlString, jsonString);
	}
}
