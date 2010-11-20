package com.kokotchy.betaSeriesAPI.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.HashCodeUtil;
import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

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
		statusInfo.setWebsiteStatus(UtilsXml.readString(websiteNode, "status"));
		statusInfo.setDatabaseStatus(UtilsXml.readString(websiteNode,
				"database"));

		Node apiNode = document.selectSingleNode("/root/api");
		statusInfo.setVersion(UtilsXml.readString(apiNode, "version"));
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
	 * Create a status informations from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Status informations
	 */
	public static StatusInfo createStatusInfo(JSONObject jsonObject) {
		StatusInfo statusInfo = new StatusInfo();
		JSONObject websiteObject = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/website");
		statusInfo.setWebsiteStatus(UtilsJson.getStringValue(websiteObject,
				"status"));
		statusInfo.setDatabaseStatus(UtilsJson.getStringValue(websiteObject,
				"database"));

		JSONObject apiObject = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/api");
		statusInfo.setVersion(UtilsJson.getStringValue(apiObject, "version"));
		try {
			JSONObject versions = UtilsJson
					.getJSONObject(apiObject, "versions");
			String[] versionsName = JSONObject.getNames(versions);
			for (String name : versionsName) {
				JSONObject versionObject = versions.getJSONObject(name);
				Version version = Version.createVersion(versionObject);
				statusInfo.addVersion(version);
			}

			JSONObject files = UtilsJson.getJSONObject(apiObject, "files");
			String[] filesName = JSONObject.getNames(files);
			for (String name : filesName) {
				JSONObject fileObject = files.getJSONObject(name);
				VersionFile file = VersionFile.createVersionFile(fileObject);
				statusInfo.addVersionFile(file);
			}
		} catch (JSONException e) {
			e.printStackTrace();
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
