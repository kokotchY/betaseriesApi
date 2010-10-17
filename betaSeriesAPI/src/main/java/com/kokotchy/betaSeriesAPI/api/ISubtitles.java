/**
 * 
 */
package com.kokotchy.betaSeriesAPI.api;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 */
public interface ISubtitles {

	/**
	 * @param url
	 * @param nb
	 * @param subtitleLanguage
	 */
	public void getLastSubtitles(String url, int nb,
			SubtitleLanguage subtitleLanguage);

	/**
	 * @param url
	 * @param subtitleLanguage
	 * @param season
	 * @param episode
	 */
	public void show(String url, SubtitleLanguage subtitleLanguage, int season,
			int episode);
}
