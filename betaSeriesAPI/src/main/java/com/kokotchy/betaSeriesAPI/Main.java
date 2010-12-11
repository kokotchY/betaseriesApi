package com.kokotchy.betaSeriesAPI;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

import com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi;
import com.kokotchy.betaSeriesAPI.model.Comment;

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
		String token = BetaSerieApi.getMembers().auth(loadCredentials[0], loadCredentials[1]);
		if (token != null) {
			String message = "This is a test for a message with the api. I am a robot, so don't reponse to me!";
			int id = 5;
			try {
				message = URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			BetaSerieApi.getComments().postComment(token, "dexter", message, 1, 1);
		}
		Set<Comment> comments = BetaSerieApi.getComments().getComments("dexter", 1, 1);
		System.out.println("There is " + comments.size() + " comments");
		for (Comment comment : comments) {
			System.out.println("[" + comment.getInnerId() + "] Comment by " + comment.getLogin() + ": " + comment.getContent());
			if (comment.getReplyToId() > 0) {
				System.out.println("In response to comment " + comment.getReplyToId());
			}
		}
	}
}
