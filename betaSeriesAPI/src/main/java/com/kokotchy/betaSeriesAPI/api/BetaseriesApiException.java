package com.kokotchy.betaSeriesAPI.api;

import java.util.Set;

import com.kokotchy.betaSeriesAPI.Utils;
import com.kokotchy.betaSeriesAPI.model.Error;

/**
 * Exception throw when there is a problem
 * 
 * @author kokotchY
 */
public class BetaseriesApiException extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1719473867281748759L;

	/**
	 * Errors
	 */
	private Set<Error> errors;

	/**
	 * Create a new exception with the given errors
	 * 
	 * @param errors
	 *            Errors
	 */
	public BetaseriesApiException(Set<Error> errors) {
		super("Error with the api: " + Utils.getErrorsAsString(errors));
		this.errors = errors;
	}

	/**
	 * Return the errors
	 * 
	 * @return Return the errors to get
	 */
	public Set<Error> getErrors() {
		return errors;
	}

}
