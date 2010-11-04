package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import org.dom4j.Document;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.api.IStatus;
import com.kokotchy.betaSeriesAPI.model.StatusInfo;

/**
 * Status of the website
 * 
 * @author kokotchy
 * 
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
	 *            Api key
	 */
	public Status(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public StatusInfo getStatus() {
		Document document = Utils.executeQuery("status.xml", apiKey);
		return StatusInfo.createStatusInfo(document);
	}

}