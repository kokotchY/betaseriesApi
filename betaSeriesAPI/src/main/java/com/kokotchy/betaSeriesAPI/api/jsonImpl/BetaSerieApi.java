package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import com.kokotchy.betaSeriesAPI.api.IComments;
import com.kokotchy.betaSeriesAPI.api.IMembers;
import com.kokotchy.betaSeriesAPI.api.IPlanning;
import com.kokotchy.betaSeriesAPI.api.IShows;
import com.kokotchy.betaSeriesAPI.api.IStatus;
import com.kokotchy.betaSeriesAPI.api.ISubtitles;
import com.kokotchy.betaSeriesAPI.api.ITimelines;

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
	private static IMembers members;

	/**
	 * Comments
	 */
	private static IComments comments;

	/**
	 * Planning
	 */
	private static IPlanning planning;

	/**
	 * Shows
	 */
	private static IShows shows;

	/**
	 * Status
	 */
	private static IStatus status;

	/**
	 * Subtitles
	 */
	private static ISubtitles subtitles;

	/**
	 * Timeline
	 */
	private static ITimelines timeline;

	/**
	 * Return the comments api object
	 * 
	 * @return the comments
	 */
	public static IComments getComments() {
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
	public static IMembers getMembers() {
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
	public static IPlanning getPlanning() {
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
	public static IShows getShows() {
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
	public static IStatus getStatus() {
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
	public static ISubtitles getSubtitles() {
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
	public static ITimelines getTimelines() {
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
