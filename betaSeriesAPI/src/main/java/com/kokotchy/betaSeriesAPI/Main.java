package com.kokotchy.betaSeriesAPI;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Season;

/**
 * Start application
 * 
 * @author kokotchy
 */
public class Main {

	/**
	 * Start the application with parameters
	 * 
	 * @param args
	 *            Parameters
	 */
	public static void main(String[] args) {
		new Main();
	}

	/**
	 * API Key
	 */
	private String apiKey = "";

	private String login = "Dev042";

	private String password = "";

	/**
	 * Start the application
	 */
	public Main() {
		// UtilsJson.setDebug(true);
		String[] loadCredentials = Utils.loadCredentials(new File(System
				.getProperty("user.dir")
				+ "/src/main/resources/credentials/", "dev042"));
		String token = BetaSerieApi.getMembers().auth(loadCredentials[0], loadCredentials[1]);
		if (token != null) {
			int nbSeen = 0;
			Set<Season> episodes = BetaSerieApi.getShows().getEpisodes(token, "dexter");
			for (Season season : episodes) {
				for (Entry<String, Episode> entry : season.getEpisodes().entrySet()) {
					if (entry.getValue().isSeen()) {
						nbSeen++;
					}
				}
			}

			System.out.println("There is " + nbSeen + " episodes seen");
		}
		// Set<Season> seasons = BetaSerieApi.getShows().getEpisodes(token, "dexter");
		// System.out.println("There is " + seasons.size() + " seasons");
		// String token = BetaSerieApi.getMembers().auth(loadCredentials[0],
		// loadCredentials[1]);
		// if (token != null) {
		// System.out.println(token);
		// if (BetaSerieApi.getMembers().isActive(token)) {
		// String message = "I'm am a robot and I'm here to kill you!";
		// try {
		// message = URLEncoder.encode(message, "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// // BetaSerieApi.getComments().postUserComment(token,
		// // "kokotchY", URLEncoder.encode(message, "UTF-8"));
		// BetaSerieApi.getComments().postComment(token, "dexter", message, 1, 1);
		// } else {
		// System.out.println("Not active");
		// }
		// } else {
		// System.out.println("Token null");
		// }
	}

	/**
	 * Display information about the season
	 * 
	 * @param season
	 *            Season
	 */
	private void displaySeason(Season season) {
		System.out.println("Season " + season.getNumber());
		Map<String, Episode> episodes = season.getEpisodes();
		for (Entry<String, Episode> episode : episodes.entrySet()) {
			System.out.println(episode.getValue());
		}
	}
}
