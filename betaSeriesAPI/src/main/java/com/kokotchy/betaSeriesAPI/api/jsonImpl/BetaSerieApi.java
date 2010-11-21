package com.kokotchy.betaSeriesAPI.api.jsonImpl;

/**
 * Json api for Beta Serie
 * 
 * @author kokotchy
 */
public class BetaSerieApi {

	/**
	 * API key
	 */
	private static String apiKey = "debfa5c405f5";

	/**
	 * Members
	 */
	private static Members members;

	/**
	 * Comments
	 */
	private static Comments comments;

	/**
	 * Planning
	 */
	private static Planning planning;

	/**
	 * Shows
	 */
	private static Shows shows;

	/**
	 * Status
	 */
	private static Status status;

	/**
	 * Subtitles
	 */
	private static Subtitles subtitles;

	/**
	 * Timeline
	 */
	private static Timelines timeline;

	/**
	 * Return the comments api object
	 * 
	 * @return the comments
	 */
	public static Comments getComments() {
		if (comments == null) {
			comments = new Comments(apiKey);
		}
		return comments;
	}

	/**
	 * Return the members api object
	 * 
	 * @return the members
	 */
	public static Members getMembers() {
		if (members == null) {
			members = new Members(apiKey);
		}
		return members;
	}

	/**
	 * Return the planning api object
	 * 
	 * @return the planning
	 */
	public static Planning getPlanning() {
		if (planning == null) {
			planning = new Planning(apiKey);
		}
		return planning;
	}

	/**
	 * Return the shows api object
	 * 
	 * @return the shows
	 */
	public static Shows getShows() {
		if (shows == null) {
			shows = new Shows(apiKey);
		}
		return shows;
	}

	/**
	 * Return the status api object
	 * 
	 * @return the status
	 */
	public static Status getStatus() {
		if (status == null) {
			status = new Status(apiKey);
		}
		return status;
	}

	/**
	 * Return the subtitles api object
	 * 
	 * @return the subtitles
	 */
	public static Subtitles getSubtitles() {
		if (subtitles == null) {
			subtitles = new Subtitles(apiKey);
		}
		return subtitles;
	}

	/**
	 * Return the timelines api object
	 * 
	 * @return the timelines
	 */
	public static Timelines getTimelines() {
		if (timeline == null) {
			timeline = new Timelines(apiKey);
		}
		return timeline;
	}

	/**
	 * Set the api key to use
	 * 
	 * @param apiKey
	 *            Api key
	 */
	public static void setApiKey(String apiKey) {
		BetaSerieApi.apiKey = apiKey;
	}
}
