package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import com.kokotchy.betaSeriesAPI.api.ISubtitles;
import com.kokotchy.betaSeriesAPI.model.Subtitle;
import com.kokotchy.betaSeriesAPI.model.SubtitleLanguage;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Subtitles implements ISubtitles {

	/**
	 * TODO Fill it
	 */
	private String apiKey;

	/**
	 * TODO Fill it
	 * 
	 * @param apiKey
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
