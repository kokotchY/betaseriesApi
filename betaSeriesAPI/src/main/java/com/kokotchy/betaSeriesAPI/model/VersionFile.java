package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class VersionFile {

	/**
	 * TODO Fill it
	 * 
	 * @param node
	 * @return
	 */
	public static VersionFile createVersionFile(Node node) {
		VersionFile versionFile = new VersionFile();
		versionFile.setLastChange(Utils.readInt(node, "last_change"));
		versionFile.setName(Utils.readString(node, "name"));
		return versionFile;
	}

	/**
	 * TODO Fill it
	 */
	private String name;

	/**
	 * TODO Fill it
	 */
	private int lastChange;

	/**
	 * TODO Fill it
	 * 
	 * @return the lastChange
	 */
	public int getLastChange() {
		return lastChange;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param lastChange
	 *            the lastChange to set
	 */
	public void setLastChange(int lastChange) {
		this.lastChange = lastChange;
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

	@Override
	public String toString() {
		String pattern = "[%d] %s";
		return String.format(pattern, lastChange, name);
	}
}
