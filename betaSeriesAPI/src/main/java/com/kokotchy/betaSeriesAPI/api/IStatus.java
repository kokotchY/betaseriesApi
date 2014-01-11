package com.kokotchy.betaSeriesAPI.api;

import com.kokotchy.betaSeriesAPI.model.StatusInfo;

/**
 * Status of the site
 * 
 * @author kokotchy
 * 
 */
public interface IStatus {

	/**
	 * Return the status of the site
	 * 
	 * @return Status information
	 */
	public StatusInfo getStatus();
}
