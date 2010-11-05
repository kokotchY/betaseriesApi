package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.UtilsXml;

/**
 * Model of a Show
 * 
 * @author kokotchy
 */
public class Show {

	/**
	 * TODO Fill it
	 * 
	 * @param show
	 * @return
	 */
	public static Show createShow(JSONObject jsonObject) {
		Show show = new Show();
		try {
			show.setTitle(UtilsJson.getStringValue(jsonObject, "title"));
			show.setUrl(UtilsJson.getStringValue(jsonObject, "url"));
			show.setDescription(UtilsJson.getStringValue(jsonObject,
					"description"));
			show.setStatus(UtilsJson.getStringValue(jsonObject, "status"));
			show.setBanner(UtilsJson.getStringValue(jsonObject, "banner"));
			show.setIdTvdb(UtilsJson.getIntValue(jsonObject, "id_thetvdb"));
			JSONArray genresArray = UtilsJson
					.getJSONArray(jsonObject, "genres");
			for (int i = 0; i < genresArray.length(); i++) {
				JSONObject genre = genresArray.getJSONObject(i);
				show.addGenre(UtilsJson.getStringValue(genre, "genre"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return show;
	}

	/**
	 * Create a show from a node
	 * 
	 * @param node
	 *            Node
	 * @return Show
	 */
	@SuppressWarnings("unchecked")
	public static Show createShow(Node node) {
		Show show = new Show();
		show.setTitle(UtilsXml.readString(node, "title"));
		show.setUrl(UtilsXml.readString(node, "url"));
		show.setDescription(UtilsXml.readString(node, "description"));
		show.setStatus(UtilsXml.readString(node, "status"));
		show.setBanner(UtilsXml.readString(node, "banner"));
		show.setIdTvdb(UtilsXml.readInt(node, "id_thetvdb"));
		List<Node> genres = node.selectNodes("genres");
		for (Node nodeGenre : genres) {
			show.addGenre(UtilsXml.readString(nodeGenre, "genre"));
		}
		return show;
	}

	/**
	 * Id of the show on tvdb
	 */
	private int idTvdb;

	/**
	 * Url of the show
	 */
	private String url;

	/**
	 * Title of the show
	 */
	private String title;

	/**
	 * Description of the show
	 */
	private String description;

	/**
	 * Status of the show
	 */
	private ShowStatus status;

	/**
	 * Banner of the show
	 */
	private String banner;

	/**
	 * List of genres of the show
	 */
	private List<String> genres;

	/**
	 * Create a new show
	 */
	public Show() {
		this(null, null);
	}

	/**
	 * Create a new show
	 * 
	 * @param url
	 *            Url of the show
	 * @param title
	 *            Title of the show
	 */
	public Show(String url, String title) {
		this.url = url;
		this.title = title;
		genres = new LinkedList<String>();
	}

	/**
	 * Add the genre
	 * 
	 * @param genre
	 *            Genre of the show
	 */
	public void addGenre(String genre) {
		genres.add(genre);
	}

	/**
	 * Return the banner of the show
	 * 
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

	/**
	 * Return the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Return the genres of the show
	 * 
	 * @return the genres
	 */
	public List<String> getGenres() {
		return genres;
	}

	/**
	 * Return the id of the show on tv db
	 * 
	 * @return the idTvdb
	 */
	public int getIdTvdb() {
		return idTvdb;
	}

	/**
	 * Return the status of the show
	 * 
	 * @return the status
	 */
	public ShowStatus getStatus() {
		return status;
	}

	/**
	 * Set the title of the show
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Return the url of the show
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the banner of the show
	 * 
	 * @param banner
	 *            the banner to set
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}

	/**
	 * Set the description of the show
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the id of the show on tv db
	 * 
	 * @param idTvdb
	 *            the idTvdb to set
	 */
	public void setIdTvdb(int idTvdb) {
		this.idTvdb = idTvdb;
	}

	/**
	 * Set the status of the show
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ShowStatus status) {
		this.status = status;
	}

	/**
	 * Set the status of the show
	 * 
	 * @param status
	 *            Name of the status
	 */
	public void setStatus(String status) {
		if (status != null) {
			if (status.equals("Continuing")) {
				setStatus(ShowStatus.CONTINUING);
			} else if (status.equals("Ended")) {
				setStatus(ShowStatus.ENDED);
			} else if (status.equals("On Hiatus")) {
				setStatus(ShowStatus.ON_HIATUS);
			} else if (status.equals("Other")) {
				setStatus(ShowStatus.OTHER);
			}
		}
	}

	/**
	 * Set the title of the show
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set the url of the show
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		String format = "[%s] %s";
		return String.format(format, url, title);
	}

}
