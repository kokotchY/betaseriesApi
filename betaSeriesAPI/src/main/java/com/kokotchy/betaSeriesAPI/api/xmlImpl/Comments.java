package com.kokotchy.betaSeriesAPI.api.xmlImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

	@SuppressWarnings("unchecked")
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

		Document document = UtilsXml.executeQuery("comments/show/" + url,
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

	@SuppressWarnings("unchecked")
	@Override
	public Set<Comment> getComments(String url, int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("season", "" + season);
		params.put("episode", "" + episode);
		Document document = UtilsXml.executeQuery("comments/episode/" + url,
				apiKey, params);
		List<Node> comments = document.selectNodes("/root/comments/comment");
		Set<Comment> result = new HashSet<Comment>();
		for (Node node : comments) {
			Comment comment = Comment.createComment(node);
			result.add(comment);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Comment> getUserComments(String login) {
		Document document = UtilsXml.executeQuery("comments/member/" + login,
				apiKey);
		List<Node> nodes = document.selectNodes("/root/comments/comment");
		Set<Comment> result = new HashSet<Comment>();
		for (Node node : nodes) {
			Comment comment = Comment.createComment(node);
			result.add(comment);
		}
		return result;
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
