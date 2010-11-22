package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
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
	 */
	public Comments(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public Set<Comment> getComments(String url) {
		JSONObject jsonObject = UtilsJson.executeQuery("comments/show/" + url,
				apiKey);
		JSONObject comments = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/comments");
		String[] names = JSONObject.getNames(comments);
		Set<Comment> result = new HashSet<Comment>();
		if (names != null) {
			try {
				for (String name : names) {
					Comment comment = Comment.createComment(comments
							.getJSONObject(name));
					result.add(comment);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Set<Comment> getComments(String url, int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("season", "" + season);
		params.put("episode", "" + episode);
		JSONObject jsonObject = UtilsJson.executeQuery("comments/episode/"
				+ url, apiKey, params);
		JSONObject comments = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/comments");
		Set<Comment> result = new HashSet<Comment>();
		String[] names = JSONObject.getNames(comments);
		if ((names != null) && (names.length > 0)) {
			try {
				for (String name : names) {
					Comment comment = Comment.createComment(comments
							.getJSONObject(name));
					result.add(comment);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Set<Comment> getUserComments(String login) {
		JSONObject jsonObject = UtilsJson.executeQuery("comments/member/"
				+ login, apiKey);
		JSONObject comments = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/comments");
		String[] names = JSONObject.getNames(comments);
		Set<Comment> result = new HashSet<Comment>();
		if (names != null) {
			try {
				for (String name : names) {
					Comment comment = Comment.createComment(comments
							.getJSONObject(name));
					result.add(comment);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
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
