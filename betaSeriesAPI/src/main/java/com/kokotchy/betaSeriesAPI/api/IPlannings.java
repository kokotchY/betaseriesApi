package com.kokotchy.betaSeriesAPI.api;

import com.kokotchy.betaSeriesAPI.model.Planning;

/**
 * Interface to the general of user planning
 * 
 * @author kokotchy
 */
public interface IPlannings {
	/**
	 * Return the general planning
	 * 
	 * @return Planning
	 */
	public Planning getGeneralPlanning();

	/**
	 * Return the planning of the logged user
	 * 
	 * @param unseen
	 *            If only unseen has to be return
	 * @param token
	 *            Token of the user
	 * @return Planning of the user
	 */
	public Planning getMemberPlanning(boolean unseen, String token);

	/**
	 * Return the planning of the user
	 * 
	 * @param login
	 *            Login of the user
	 * @param unseen
	 *            If only unseen has to be return
	 * @return Planning of the user
	 */
	public Planning getMemberPlanning(String login, boolean unseen);
}
