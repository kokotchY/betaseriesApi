package com.kokotchy.betaSeriesAPI;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kokotchy.betaSeriesAPI.api.jsonImpl.Status;
import com.kokotchy.betaSeriesAPI.model.Episode;
import com.kokotchy.betaSeriesAPI.model.Season;
import com.kokotchy.betaSeriesAPI.model.StatusInfo;
import com.kokotchy.betaSeriesAPI.model.Version;
import com.kokotchy.betaSeriesAPI.model.VersionFile;

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
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath("/home/kokotchy/Desktop/betaseriejson/");
		Status status = new Status(apiKey);
		StatusInfo statusInfo = status.getStatus();
		System.out
				.println("Database status: " + statusInfo.getDatabaseStatus());
		System.out.println("Website status: " + statusInfo.getWebsiteStatus());
		List<Version> versions = statusInfo.getVersions();
		System.out.println("Version:");
		for (Version version : versions) {
			System.out.println("\t" + version);
		}
		List<VersionFile> files = statusInfo.getFiles();
		System.out.println("Files:");
		for (VersionFile file : files) {
			System.out.println("\t" + file);
		}
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
