package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.IStatus;
import com.kokotchy.betaSeriesAPI.api.factories.StatusInfoFactory;
import com.kokotchy.betaSeriesAPI.model.StatusInfo;

/**
 * Status of the website
 * 
 * @author kokotchy
 */
public class Status implements IStatus {

	/**
	 * Api key
	 */
	private String apiKey;

	/**
	 * Create the status with the apiKey
	 * 
	 * @param apiKey
	 */
	public Status(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public StatusInfo getStatus() {
		JSONObject jsonObject = UtilsJson.executeQuery("status", apiKey);
		return StatusInfoFactory.createStatusInfo(jsonObject);
	}

}
