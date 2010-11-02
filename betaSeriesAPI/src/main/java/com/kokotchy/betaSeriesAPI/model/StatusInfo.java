package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * Informations about the site
 * 
 * @author kokotchy
 * 
 */
public class StatusInfo {

	/**
	 * Create the status informations from the document
	 * 
	 * @param document
	 *            Document
	 * @return Status informations
	 */
	@SuppressWarnings("unchecked")
	public static StatusInfo createStatusInfo(Document document) {
		StatusInfo statusInfo = new StatusInfo();
		Node websiteNode = document.selectSingleNode("/root/website");
		statusInfo.setWebsiteStatus(Utils.readString(websiteNode, "status"));
		statusInfo.setDatabaseStatus(Utils.readString(websiteNode, "database"));

		Node apiNode = document.selectSingleNode("/root/api");
		statusInfo.setVersion(Utils.readString(apiNode, "version"));
		List<Node> versions = apiNode.selectNodes("versions/version");
		for (Node node : versions) {
			Version version = Version.createVersion(node);
			statusInfo.addVersion(version);
		}

		List<Node> files = apiNode.selectNodes("files/file");
		for (Node node : files) {
			VersionFile file = VersionFile.createVersionFile(node);
			statusInfo.addVersionFile(file);
		}
		return statusInfo;
	}

	/**
	 * Status of the database
	 */
	private String databaseStatus;

	/**
	 * Status of the website
	 */
	private String websiteStatus;

	/**
	 * Version of the api
	 */
	private String version;

	/**
	 * List of versions
	 */
	private List<Version> versions = new LinkedList<Version>();

	/**
	 * List of files
	 */
	private List<VersionFile> files = new LinkedList<VersionFile>();

	/**
	 * Add the version
	 * 
	 * @param version
	 *            Version
	 */
	public void addVersion(Version version) {
		versions.add(version);
	}

	/**
	 * Add the version file
	 * 
	 * @param file
	 *            File
	 */
	public void addVersionFile(VersionFile file) {
		files.add(file);
	}

	/**
	 * Return the status of the database
	 * 
	 * @return the databaseStatus
	 */
	public String getDatabaseStatus() {
		return databaseStatus;
	}

	/**
	 * Return the files
	 * 
	 * @return the files
	 */
	public List<VersionFile> getFiles() {
		return files;
	}

	/**
	 * Return the version
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Return the versions
	 * 
	 * @return the versions
	 */
	public List<Version> getVersions() {
		return versions;
	}

	/**
	 * Return the status of the website
	 * 
	 * @return the websiteStatus
	 */
	public String getWebsiteStatus() {
		return websiteStatus;
	}

	/**
	 * Set the database status
	 * 
	 * @param databaseStatus
	 */
	private void setDatabaseStatus(String databaseStatus) {
		this.databaseStatus = databaseStatus;
	}

	/**
	 * Set the version
	 * 
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Set the status of the website
	 * 
	 * @param websiteStatus
	 */
	private void setWebsiteStatus(String websiteStatus) {
		this.websiteStatus = websiteStatus;
	}

}
