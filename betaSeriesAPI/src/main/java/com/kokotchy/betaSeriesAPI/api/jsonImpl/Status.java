package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import com.kokotchy.betaSeriesAPI.api.IStatus;
import com.kokotchy.betaSeriesAPI.model.StatusInfo;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Status implements IStatus {

	/**
	 * TODO Fill it
	 */
	private String apiKey;

	/**
	 * TODO Fill it
	 * 
	 * @param apiKey
	 */
	public Status(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public StatusInfo getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
