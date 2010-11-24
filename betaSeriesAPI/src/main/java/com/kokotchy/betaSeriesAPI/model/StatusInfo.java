package com.kokotchy.betaSeriesAPI.model;

import java.util.HashMap;
import java.util.Map;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;

/**
 * Informations about the site
 * 
 * @author kokotchy
 */
public class StatusInfo {

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
	private Map<Integer, Version> versions = new HashMap<Integer, Version>();

	/**
	 * List of files
	 */
	private Map<String, VersionFile> files = new HashMap<String, VersionFile>();

	/**
	 * Add the version
	 * 
	 * @param version
	 *            Version
	 */
	public void addVersion(Version version) {
		versions.put(version.getDate(), version);
	}

	/**
	 * Add the version file
	 * 
	 * @param file
	 *            File
	 */
	public void addVersionFile(VersionFile file) {
		files.put(file.getName(), file);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StatusInfo)) {
			return false;
		}
		return hashCode() == obj.hashCode();
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
	public Map<String, VersionFile> getFiles() {
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
	public Map<Integer, Version> getVersions() {
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

	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, databaseStatus);
		result = HashCodeUtil.hash(result, websiteStatus);
		result = HashCodeUtil.hash(result, version);
		result = HashCodeUtil.hash(result, versions);
		result = HashCodeUtil.hash(result, files);
		return result;
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
