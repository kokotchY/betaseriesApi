package com.kokotchy.betaSeriesAPI.api;

import java.util.Set;

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
	public Set<Subtitle> getLastSubtitles(int nb,
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
	public Set<Subtitle> getLastSubtitles(String url, int nb,
			SubtitleLanguage subtitleLanguage);

	/**
	 * Return the subtitles for the given file
	 * 
	 * @param file
	 *            File
	 * @return Subtitles
	 */
	public Set<Subtitle> getSubtitlesForFile(String file);

	/**
	 * Return the subtitle for the given file and with the specified language
	 * 
	 * @param file
	 *            Name of the file
	 * @param subtitleLanguage
	 *            Language of the subtitle
	 * @return Subtitles
	 */
	public Set<Subtitle> getSubtitlesForFile(String file, SubtitleLanguage subtitleLanguage);

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
	public Set<Subtitle> show(String url, SubtitleLanguage subtitleLanguage,
			int season, int episode);
}
