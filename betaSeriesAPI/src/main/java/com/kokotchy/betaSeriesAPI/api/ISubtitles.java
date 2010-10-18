package com.kokotchy.betaSeriesAPI.api;

import java.util.List;

import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Interface to the subtitles
 * 
 * @author kokotchy
 */
public interface ISubtitles {

	/**
	 * Return the last subtitles retrieved by BetaSeries, limited by 100.
	 * 
	 * @param nb
	 *            Number of subtitle to retrieve
	 * @param subtitleLanguage
	 *            Language needed for the subtitles
	 * @return List of subtitles
	 */
	public List<Subtitle> getLastSubtitles(int nb,
			SubtitleLanguage subtitleLanguage);

	/**
	 * Return the last subtitles retrieved by BetaSeries, limited by 100.
	 * 
	 * @param url
	 *            Url of the show
	 * @param nb
	 *            Number of subtitle to retrieve
	 * @param subtitleLanguage
	 *            Language needed for the subtitles
	 * @return List of subtitles
	 */
	public List<Subtitle> getLastSubtitles(String url, int nb,
			SubtitleLanguage subtitleLanguage);

	/**
	 * Return the subtitles retrieved by BetaSeries for the given show.
	 * 
	 * @param url
	 *            Url of the show
	 * @param subtitleLanguage
	 *            Language needed for the subtitles
	 * @param season
	 *            Season of the episode
	 * @param episode
	 *            Episode
	 * @return List of subtitles for the episode
	 */
	public List<Subtitle> show(String url, SubtitleLanguage subtitleLanguage,
			int season, int episode);
}
