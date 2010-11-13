package com.kokotchy.betaSeriesAPI;

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
		showsTitle.put("Allison & Lillia", "Allison &amp; Lillia");
		showsTitle.put("Avez-vous déjà vu... ?",
				"Avez-vous d\u00e9j\u00e0 vu... ?");
		showsTitle.put("Avengers: Earth’s Mightiest Heroes",
				"Avengers: Earth\u2019s Mightiest Heroes");
		showsTitle.put("Belle et Sébastien", "Belle et S\u00e9bastien");
		showsTitle.put("Blague à part", "Blague \u00e0 part");
		showsTitle.put("Caméra Café", "Cam\u00e9ra Caf\u00e9");
		showsTitle.put("Cam\u00e9ra Caf\u00e9", "Caméra Café");
		showsTitle.put("Caméra Café 2", "Cam\u00e9ra Caf\u00e9 2");
		showsTitle.put("DOGS Bullets & Carnage", "DOGS Bullets &amp; Carnage");
		showsTitle.put("Fais pas ci, fais pas ça",
				"Fais pas ci, fais pas \u00e7a");
		showsTitle.put("Física o Química", "F\u00edsica o Qu\u00edmica");
		showsTitle.put("Father & Son", "Father &amp; Son");
		showsTitle.put("Hélène et les garçons",
				"H\u00e9l\u00e8ne et les gar\u00e7ons");
		showsTitle.put("Hope & Faith", "Hope &amp; Faith");
		showsTitle.put("Il était une fois... la Vie",
				"Il \u00e9tait une fois... la Vie");
		showsTitle.put("Il était une fois... les Amériques",
				"Il \u00e9tait une fois... les Am\u00e9riques");
		showsTitle.put("Il était une fois... les Découvreurs",
				"Il \u00e9tait une fois... les D\u00e9couvreurs");
		showsTitle.put("Il était une fois... les Explorateurs",
				"Il \u00e9tait une fois... les Explorateurs");
		showsTitle.put("Kämpfer", "K\u00e4mpfer");
		showsTitle.put("Kath & Kim (US)", "Kath &amp; Kim (US)");
		showsTitle.put("Léa Parker", "L\u00e9a Parker");
		showsTitle.put("Law & Order: Los Angeles",
				"Law &amp; Order: Los Angeles");
		showsTitle.put("Law & Order", "Law &amp; Order");
		showsTitle.put("MÄR", "M\u00c4R");
		showsTitle.put("Melissa & Joey", "Melissa &amp; Joey");
		showsTitle.put("Pok\u00e9mon", "Pokémon");
		showsTitle.put("Ranma \u00bd", "Ranma ½");
		showsTitle.put("Romeo \u00d7 Juliet", "Romeo × Juliet");
		showsTitle.put("Saint Seiya: The Lost Canvas \u2013 Hades Mythology",
				"Saint Seiya: The Lost Canvas – Hades Mythology");
		showsTitle.put("T\u00fcrkisch f\u00fcr Anf\u00e4nger",
				"Türkisch für Anfänger");
		for (Entry<String, String> entry : showsTitle.entrySet()) {
			assertEquals(entry.getKey(), EntityDecoder.htmlToChar(entry
					.getValue()));
		}
	}

}
