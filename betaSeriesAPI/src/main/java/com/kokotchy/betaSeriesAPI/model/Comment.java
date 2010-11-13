package com.kokotchy.betaSeriesAPI.model;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Model of a comment TODO Fill it
 * 
 * @author kokotchy
 */
public class Comment {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Comment)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
