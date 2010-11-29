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
	 *
	 */
	private Set<Error> errors;

	/**
	 * TODO Fill it
	 * 
	 * @param errors
	 */
	public BetaseriesApiException(Set<Error> errors) {
		super("Error with the api: " + Utils.getErrorsAsString(errors));
		this.errors = errors;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return Return the errors to get
	 */
	public Set<Error> getErrors() {
		return errors;
	}

	/**
	 * TODO Fill it
	 * Set the field with the given value
	 * errors
	 */
	public void setErrors(Set<Error> errors) {
		this.errors = errors;
	}
}
