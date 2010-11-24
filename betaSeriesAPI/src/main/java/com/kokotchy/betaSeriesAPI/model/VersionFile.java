package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Version of a file
 * 
 * @author kokotchy
 */
public class VersionFile {
	/**
	 * Name of the file
	 */
	private String name;

	/**
	 * Date of last change
	 */
	private int lastChange;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VersionFile)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the last change date
	 * 
	 * @return the lastChange
	 */
	public int getLastChange() {
		return lastChange;
	}

	/**
	 * Return the name
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
		result = HashCodeUtil.hash(result, lastChange);
		return result;
	}

	/**
	 * Set the last change
	 * 
	 * @param lastChange
	 *            the lastChange to set
	 */
	public void setLastChange(int lastChange) {
		this.lastChange = lastChange;
	}

	/**
	 * Set the name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String pattern = "[%d] %s";
		return String.format(pattern, lastChange, name);
	}
}
