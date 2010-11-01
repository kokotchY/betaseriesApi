package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Version {

	/**
	 * TODO Fill it
	 * 
	 * @param node
	 * @return
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
	 * TODO Fill it
	 */
	private int date;

	/**
	 * TODO Fill it
	 */
	private List<Change> changes = new LinkedList<Change>();

	/**
	 * TODO Fill it
	 * 
	 * @param change
	 */
	private void addChange(Change change) {
		changes.add(change);
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the changes
	 */
	public List<Change> getChanges() {
		return changes;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param changes
	 *            the changes to set
	 */
	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}
}
