package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Change in the api
 * 
 * @author kokotchy
 */
public class Change {

	/**
	 * Type of change
	 */
	private String type;

	/**
	 * Value of change
	 */
	private String value;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Change)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the type of the change
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Return the value of the change
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, type);
		result = HashCodeUtil.hash(result, value);
		return result;
	}

	/**
	 * Set the type of the change
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Set the value of the change
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return type + " " + value;
	}
}
