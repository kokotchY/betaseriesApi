package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
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
		JSONObject jsonObject = UtilsJson.executeQuery("status.json", apiKey);
		return StatusInfo.createStatusInfo(jsonObject);
	}

}
