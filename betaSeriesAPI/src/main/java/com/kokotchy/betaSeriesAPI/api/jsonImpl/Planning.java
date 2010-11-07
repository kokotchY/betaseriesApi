package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import com.kokotchy.betaSeriesAPI.api.IPlanning;
import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Planning implements IPlanning {

	/**
	 * TODO Fill it
	 */
	private String apiKey;

	/**
	 * TODO Fill it
	 * 
	 * @param apiKey
	 */
	public Planning(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public List<Episode> getGeneralPlanning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Episode> getMemberPlanning(boolean unseen, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Episode> getMemberPlanning(String login, boolean unseen) {
		// TODO Auto-generated method stub
		return null;
	}

}
