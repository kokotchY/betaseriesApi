package com.kokotchy.betaSeriesAPI.model;

import java.util.LinkedList;
import java.util.List;

import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.Utils;

/**
 * @author kokotchy
 * 
 */
public class Show {

	/**
	 * @author kokotchy
	 * 
	 */
	public enum ShowStatus {
		EN_COURS, FINI
	};

	/**
	 * @param node
	 * @return
	 */
	public static Show createShow(Node node) {
		Show show = new Show();
		show.setTitle(Utils.readNode(node, "title"));
		show.setUrl(Utils.readNode(node, "url"));
		show.setDescription(Utils.readNode(node, "description"));
		show.setStatus(Utils.readNode(node, "status"));
		show.setBanner(Utils.readNode(node, "banner"));
		List<Node> genres = node.selectNodes("genres");
		for (Node nodeGenre : genres) {
			show.addGenre(Utils.readNode(nodeGenre, "genre"));
		}
		return show;
	}

	/**
	 * 
	 */
	private String url;

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	private ShowStatus status;

	/**
	 * 
	 */
	private String banner;

	/**
	 * 
	 */
	private List<String> genres;

	/**
	 * 
	 */
	public Show() {
		this(null, null);
	}

	/**
	 * @param url
	 * @param title
	 */
	public Show(String url, String title) {
		this.url = url;
		this.title = title;
		this.genres = new LinkedList<String>();
	}

	/**
	 * @param genre
	 */
	public void addGenre(String genre) {
		genres.add(genre);
	}

	/**
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the genres
	 */
	public List<String> getGenres() {
		return genres;
	}

	/**
	 * @return the status
	 */
	public ShowStatus getStatus() {
		return status;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param banner
	 *            the banner to set
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ShowStatus status) {
		this.status = status;
	}

	/**
	 * @param status
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
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
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
