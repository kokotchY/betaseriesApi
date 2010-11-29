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

	/**
	 * TODO Fill it
	 * 
	 * @param jsonObject
	 * @return
	 */
	private Set<Comment> getComments(JSONObject jsonObject) {
		JSONObject comments = UtilsJson.getJSONObjectFromPath(jsonObject,
				"/root/comments");
		String[] names = JSONObject.getNames(comments);
		Set<Comment> result = new HashSet<Comment>();
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
	public Set<Comment> getComments(String url) {
		JSONObject jsonObject = UtilsJson.executeQuery("comments/show/" + url,
				apiKey);
		return getComments(jsonObject);
	}

	@Override
	public Set<Comment> getComments(String url, int season, int episode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("season", "" + season);
		params.put("episode", "" + episode);
		JSONObject jsonObject = UtilsJson.executeQuery("comments/episode/"
				+ url, apiKey, params);
		return getComments(jsonObject);
	}

	@Override
	public Set<Comment> getUserComments(String login) {
		JSONObject jsonObject = UtilsJson.executeQuery("comments/member/"
				+ login, apiKey);
		return getComments(jsonObject);
	}

	/**
	 * TODO Fill it
	 * 
	 * @param token
	 * @param login
	 * @param text
	 * @param responseTo
	 */
	private void postAUserComment(String token, String login, String text,
			int responseTo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("member", login);
		params.put("text", text);
		if (responseTo >= 0) {
			params.put("in_reploy_to", "" + responseTo);
		}
		UtilsJson.executeQuery("comments/post/member", apiKey, params);
	}

	@Override
	public void postComment(String url, String text) {
		String token = "";
		postComment(token, url, text, -1, -1, -1);
	}

	@Override
	public void postComment(String url, String text, int responseTo) {
		String token = "";
		postComment(token, url, text, -1, -1, responseTo);
	}

	@Override
	public void postComment(String url, String text, int season, int episode) {
		String token = "";
		postComment(token, url, text, season, episode, -1);
	}

	@Override
	public void postComment(String url, String text, int responseTo,
			int season, int episode) {
		String token = "";
		postComment(token, url, text, season, episode, responseTo);
	}

	/**
	 * TODO Fill it
	 * 
	 * @param token
	 * @param url
	 * @param text
	 * @param season
	 * @param episode
	 * @param responseTo
	 */
	private void postComment(String token, String url, String text, int season,
			int episode, int responseTo) {
		Map<String, String> params = new HashMap<String, String>();
		String action = null;
		params.put("text", text);
		if (season < 0 && episode < 0) {
			action = "comments/post/show";
			params.put("show", url);
			if (responseTo >= 0) {
				params.put("in_reply_to", "" + responseTo);
			}
		} else if (season > 0 && episode > 0) {
			action = "comments/post/episode";
			params.put("season", "" + season);
			params.put("episode", "" + episode);
			if (responseTo >= 0) {
				params.put("in_reply_to", "" + responseTo);
			}
		}

		if (action != null) {
			UtilsJson.executeQuery(action, apiKey, params);
		}
	}

	@Override
	public void postUserComment(String login, String text) {
		String token = "";
		postAUserComment(token, login, text, -1);
	}

	@Override
	public void postUserComment(String login, String text, int responseTo) {
		String token = "";
		postAUserComment(token, login, text, responseTo);
	}
}
