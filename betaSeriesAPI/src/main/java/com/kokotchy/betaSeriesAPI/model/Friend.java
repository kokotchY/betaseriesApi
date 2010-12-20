package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 */
public class Friend {

	/**
	 * TODO Fill it
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
	 * TODO Fill it
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
	 * TODO Fill it
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
