package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import com.kokotchy.betaSeriesAPI.api.ISubtitles;
import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * Subtitles API
 * 
 * @author kokotchy
 */
public class Subtitles implements ISubtitles {

	/**
	 * Api key
	 */
	private String apiKey;

	/**
	 * Create new subtitle api with the given key
	 * 
	 * @param apiKey
	 *            Key
	 */
	public Subtitles(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Subtitle> getLastSubtitles(int nb,
			SubtitleLanguage subtitleLanguage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subtitle> getLastSubtitles(String url, int nb,
			SubtitleLanguage subtitleLanguage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subtitle> show(String url, SubtitleLanguage subtitleLanguage,
			int season, int episode) {
		// TODO Auto-generated method stub
		return null;
	}

}
