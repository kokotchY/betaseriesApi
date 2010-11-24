package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.IComments;
import com.kokotchy.betaSeriesAPI.api.factories.CommentFactory;
import com.kokotchy.betaSeriesAPI.model.Comment;

/**
 * Comments api
 * 
 * @author kokotchy
 */
public class Comments implements IComments {

	/**
	 * Api key
	 */
	private String apiKey;

	/**
	 * Create new comments api with the given key
	 * 
	 * @param apiKey
	 *            API key
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
					Comment comment = CommentFactory.createComment(comments
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
					Comment comment = CommentFactory.createComment(comments
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
					Comment comment = CommentFactory.createComment(comments
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
		// TODO Add the token!
		Map<String, String> params = new HashMap<String, String>();
		params.put("show", url);
		params.put("text", text);
		UtilsJson.executeQuery("comments/post/show", apiKey, params);
	}

	@Override
	public void postComment(String url, String text, int responseTo) {
		// TODO Add the token!
		Map<String, String> params = new HashMap<String, String>();
		params.put("show", url);
		params.put("text", text);
		params.put("in_reply_to", "" + responseTo);
		UtilsJson.executeQuery("comments/post/show", apiKey, params);
	}

	@Override
	public void postComment(String url, String text, int season, int episode) {
		// TODO Add the token!
		Map<String, String> params = new HashMap<String, String>();
		params.put("show", url);
		params.put("text", text);
		params.put("season", "" + season);
		params.put("episode", "" + episode);
		UtilsJson.executeQuery("comments/post/episode", apiKey, params);
	}

	@Override
	public void postComment(String url, String text, int responseTo,
			int season, int episode) {
		// TODO Add the token!
		Map<String, String> params = new HashMap<String, String>();
		params.put("show", url);
		params.put("text", text);
		params.put("season", "" + season);
		params.put("episode", "" + episode);
		params.put("in_reply_to", "" + responseTo);
		UtilsJson.executeQuery("comments/post/episode", apiKey, params);
	}

	@Override
	public void postUserComment(String login, String text) {
		// TODO Add the token!
		Map<String, String> params = new HashMap<String, String>();
		params.put("member", login);
		params.put("text", text);
		UtilsJson.executeQuery("comments/post/member", apiKey, params);
	}

	@Override
	public void postUserComment(String login, String text, int responseTo) {
		// TODO Add the token!
		Map<String, String> params = new HashMap<String, String>();
		params.put("member", login);
		params.put("text", text);
		params.put("in_reply_to", "" + responseTo);
		UtilsJson.executeQuery("comments/post/member", apiKey, params);
	}
}
