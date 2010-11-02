package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * A version of the api
 * 
 * @author kokotchy
 * 
 */
public class Version {

	/**
	 * Create a version from the node
	 * 
	 * @param node
	 *            Node
	 * @return Version
	 */
	@SuppressWarnings("unchecked")
	public static Version createVersion(Node node) {
		Version version = new Version();
		version.setDate(Utils.readInt(node, "date"));

		List<Node> changes = node.selectNodes("changes/change");
		for (Node changeNode : changes) {
			version.addChange(Change.createChange(changeNode));
		}

		return version;
	}

	/**
	 * Date
	 */
	private int date;

	/**
	 * List of changes
	 */
	private List<Change> changes = new LinkedList<Change>();

	/**
	 * Add a change
	 * 
	 * @param change
	 *            Change
	 */
	private void addChange(Change change) {
		changes.add(change);
	}

	/**
	 * Return the changes
	 * 
	 * @return the changes
	 */
	public List<Change> getChanges() {
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
