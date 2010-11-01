package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import org.dom4j.Document;

import com.kokotchy.betaSeriesAPI.Utils;
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
		Document document = Utils.executeQuery("status.xml", apiKey);
		return StatusInfo.createStatusInfo(document);
	}

}
