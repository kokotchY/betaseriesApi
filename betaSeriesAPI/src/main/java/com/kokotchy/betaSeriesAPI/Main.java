package com.kokotchy.betaSeriesAPI;

import java.io.File;
import java.util.Set;

import com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi;
import com.kokotchy.betaSeriesAPI.model.Subtitle;

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
		UtilsXml.setDebug(true);
		String[] loadCredentials = Utils.loadCredentials(new File(System
				.getProperty("user.dir")
				+ "/src/main/resources/credentials/", "dev042"));
		Set<Subtitle> subtitlesForFile = BetaSerieApi.getSubtitles().getSubtitlesForFile("Human.Target.S01E01.avi");
		System.out.println("There is " + subtitlesForFile.size() + " for this episode");
	}
}
