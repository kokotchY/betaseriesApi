package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * Model of a Show
 * 
 * @author kokotchy
 */
public class Show {

	/**
	 * Status of a show
	 * TODO Translate in english
	 * 
	 * @author kokotchy
	 */
	public enum ShowStatus {
		EN_COURS,
		FINI,
	};

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
		show.setTitle(Utils.readString(node, "title"));
		show.setUrl(Utils.readString(node, "url"));
		show.setDescription(Utils.readString(node, "description"));
		show.setStatus(Utils.readString(node, "status"));
		show.setBanner(Utils.readString(node, "banner"));
		List<Node> genres = node.selectNodes("genres");
		for (Node nodeGenre : genres) {
			show.addGenre(Utils.readString(nodeGenre, "genre"));
		}
		return show;
	}

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
	private void setStatus(String status) {
		if (status != null) {
			if (status.equals("encours")) {
				setStatus(ShowStatus.EN_COURS);
			} else if (status.equals("fini")) {
				setStatus(ShowStatus.FINI);
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
