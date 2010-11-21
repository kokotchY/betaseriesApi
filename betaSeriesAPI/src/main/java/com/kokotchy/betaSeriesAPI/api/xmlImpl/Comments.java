package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Node;

import com.kokotchy.betaSeriesAPI.UtilsXml;
import com.kokotchy.betaSeriesAPI.api.IComments;
import com.kokotchy.betaSeriesAPI.api.NotImplementedException;
import com.kokotchy.betaSeriesAPI.model.Comment;

/**
 * Comments api
 * 
 * @author kokotchy
 */
public class Comments implements IComments {

	/**
	 * TODO Fill it
	 */
	private String apiKey;

	/**
	 * TODO Fill it
	 * 
	 * @param apiKey
	 */
	public Comments(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public Set<Comment> getComments(String url) {
		// JSONObject jsonObject = UtilsJson.executeQuery("comments/shows/" +
		// url,
		// apiKey);
		// JSONObject comments = UtilsJson.getJSONObjectFromPath(jsonObject,
		// "/root/comments");
		// String[] names = JSONObject.getNames(comments);
		// Set<Comment> result = new HashSet<Comment>();
		// try {
		// for (String name : names) {
		// Comment comment = Comment.createComment(comments
		// .getJSONObject(name));
		// result.add(comment);
		// }
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
		// return result;

		Document document = UtilsXml.executeQuery("comments/shows/" + url,
				apiKey);
		Set<Comment> comments = new HashSet<Comment>();
		if (!UtilsXml.hasErrors(document)) {
			List<Node> nodes = document.selectNodes("/root/comments/comment");
			for (Node showNode : nodes) {
				Comment comment = Comment.createComment(showNode);
				comments.add(comment);
			}
		}
		return comments;
	}

	@Override
	public Set<Comment> getComments(String url, int season, int episode) {
		throw new NotImplementedException();
	}

	@Override
	public Set<Comment> getUserComments(String login) {
		throw new NotImplementedException();
	}

	@Override
	public void postComment(String url, String text) {
		throw new NotImplementedException();
	}

	@Override
	public void postComment(String url, String text, int responseTo) {
		throw new NotImplementedException();
	}

	@Override
	public void postComment(String url, String text, int season, int episode) {
		throw new NotImplementedException();
	}

	@Override
	public void postComment(String url, String text, int responseTo,
			int season, int episode) {
		throw new NotImplementedException();
	}

	@Override
	public void postUserComment(String login, String text) {
		throw new NotImplementedException();
	}

	@Override
	public void postUserComment(String login, String text, int responseTo) {
		throw new NotImplementedException();
	}

}
