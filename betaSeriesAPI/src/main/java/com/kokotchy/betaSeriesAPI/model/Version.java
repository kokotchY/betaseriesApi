package com.kokotchy.betaSeriesAPI.model;

import java.util.HashMap;
import java.util.Map;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * A version of the api
 * 
 * @author kokotchy
 */
public class Version {

	/**
	 * Date
	 */
	private int date;

	/**
	 * List of changes
	 */
	private Map<String, Change> changes = new HashMap<String, Change>();

	/**
	 * Add a change
	 * 
	 * @param change
	 *            Change
	 */
	public void addChange(Change change) {
		changes.put(change.getValue(), change);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Version)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/**
	 * Return the changes
	 * 
	 * @return the changes
	 */
	public Map<String, Change> getChanges() {
		return changes;
	}

	/**
	 * Return the date
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, date);
		result = HashCodeUtil.hash(result, changes);
		return result;
	}

	/**
	 * Set the date
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}
}
