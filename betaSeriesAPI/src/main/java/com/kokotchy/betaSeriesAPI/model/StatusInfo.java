package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class StatusInfo {

	/**
	 * TODO Fill it
	 * 
	 * @param document
	 * @return
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
	 * TODO Fill it
	 */
	private String databaseStatus;

	/**
	 * TODO Fill it
	 */
	private String websiteStatus;

	/**
	 * TODO Fill it
	 */
	private String version;

	/**
	 * TODO Fill it
	 */
	private List<Version> versions = new LinkedList<Version>();

	/**
	 * TODO Fill it
	 */
	private List<VersionFile> files = new LinkedList<VersionFile>();

	/**
	 * TODO Fill it
	 * 
	 * @param version
	 */
	public void addVersion(Version version) {
		versions.add(version);
	}

	/**
	 * TODO Fill it
	 * 
	 * @param file
	 */
	public void addVersionFile(VersionFile file) {
		files.add(file);
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the databaseStatus
	 */
	public String getDatabaseStatus() {
		return databaseStatus;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the files
	 */
	public List<VersionFile> getFiles() {
		return files;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the versions
	 */
	public List<Version> getVersions() {
		return versions;
	}

	/**
	 * TODO Fill it
	 * 
	 * @return the websiteStatus
	 */
	public String getWebsiteStatus() {
		return websiteStatus;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param databaseStatus
	 */
	private void setDatabaseStatus(String databaseStatus) {
		this.databaseStatus = databaseStatus;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param files
	 *            the files to set
	 */
	public void setFiles(List<VersionFile> files) {
		this.files = files;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param versions
	 *            the versions to set
	 */
	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	/**
	 * TODO Fill it
	 * 
	 * @param websiteStatus
	 */
	private void setWebsiteStatus(String websiteStatus) {
		this.websiteStatus = websiteStatus;
	}

}
