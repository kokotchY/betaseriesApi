package com.kokotchy.betaSeriesAPI.api;

import com.kokotchy.betaSeriesAPI.api.xmlImpl.Comments;
import com.kokotchy.betaSeriesAPI.api.xmlImpl.Members;
import com.kokotchy.betaSeriesAPI.api.xmlImpl.Planning;
import com.kokotchy.betaSeriesAPI.api.xmlImpl.Shows;
import com.kokotchy.betaSeriesAPI.api.xmlImpl.Status;
import com.kokotchy.betaSeriesAPI.api.xmlImpl.Subtitles;
import com.kokotchy.betaSeriesAPI.api.xmlImpl.Timelines;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class BetaSerieApi {

	/**
	 * TODO Fill it
	 */
	private static String apiKey = "debfa5c405f5";

	/**
	 * TODO Fill it
	 */
	private static Members members;

	/**
	 * TODO Fill it
	 */
	private static Comments comments;

	/**
	 * TODO Fill it
	 */
	private static Planning planning;

	/**
	 * TODO Fill it
	 */
	private static Shows shows;

	/**
	 * TODO Fill it
	 */
	private static Status status;

	/**
	 * TODO Fill it
	 */
	private static Subtitles subtitles;

	/**
	 * TODO Fill it
	 */
	private static Timelines timeline;

	/**
	 * TODO Fill it
	 * 
	 * @return the comments
	 */
	public static Comments getComments() {
		if (comments == null) {
			comments = new Comments();
		}
		return comments;
	}

	/**
	 * TODO Fill it
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
	 * TODO Fill it
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
	 * TODO Fill it
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
	 * TODO Fill it
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
	 * TODO Fill it
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
	 * TODO Fill it
	 * 
	 * @return the timelines
	 */
	public static Timelines getTimelines() {
		if (timeline == null) {
			timeline = new Timelines(apiKey);
		}
		return timeline;
	}
}
