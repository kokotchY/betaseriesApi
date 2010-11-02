package com.kokotchy.betaSeriesAPI.model;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * Version of a file
 * 
 * @author kokotchy
 * 
 */
public class VersionFile {

	/**
	 * Create a version file from node
	 * 
	 * @param node
	 *            Node
	 * @return Version file
	 */
	public static VersionFile createVersionFile(Node node) {
		VersionFile versionFile = new VersionFile();
		versionFile.setLastChange(Utils.readInt(node, "last_change"));
		versionFile.setName(Utils.readString(node, "name"));
		return versionFile;
	}

	/**
	 * Name of the file
	 */
	private String name;

	/**
	 * Date of last change
	 */
	private int lastChange;

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
