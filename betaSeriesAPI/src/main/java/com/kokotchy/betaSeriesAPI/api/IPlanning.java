package com.kokotchy.betaSeriesAPI.api;

import java.util.List;

import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * Interface to the general of user planning
 * 
 * @author kokotchy
 */
public interface IPlanning {
	/**
	 * Return the general planning
	 * 
	 * @return List of episodes
	 */
	public List<Episode> getGeneralPlanning();

	/**
	 * Return the planning of the logged user
	 * 
	 * @param unseen
	 *            If only unseen has to be return
	 * @param token
	 *            Token of the user
	 * @return Planning of the user
	 */
	public List<Episode> getMemberPlanning(boolean unseen, String token);

	/**
	 * Return the planning of the user
	 * 
	 * @param login
	 *            Login of the user
	 * @param unseen
	 *            If only unseen has to be return
	 * @return Planning of the user
	 */
	public List<Episode> getMemberPlanning(String login, boolean unseen);
}
