package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Model of a friend
 * 
 * @author kokotchy
 */
public class Friend {

	/**
	 * Name of a friend
	 */
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Friend)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the name of the friend
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, name);
		return result;
	}

	/**
	 * Set the name of the friend
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
