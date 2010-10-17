package com.kokotchy.betaSeriesAPI.api;

/**
 * @author kokotchy
 * 
 */
public interface IPlanning {
	/**
	 * 
	 */
	public void getGeneralPlanning();

	/**
	 * @param unseen
	 * @param token
	 */
	public void getMemberPlanning(boolean unseen, String token);

	/**
	 * @param login
	 * @param unseen
	 */
	public void getMemberPlanning(String login, boolean unseen);
}
