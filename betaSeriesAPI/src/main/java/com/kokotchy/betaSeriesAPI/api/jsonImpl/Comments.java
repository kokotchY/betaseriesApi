package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
	 * Return the comments from the json object
	 * 
	 * @param jsonObject
	 *            Json object
	 * @return Comments
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
	 * Post a comment on a user profile
	 * 
	 * @param token
	 *            Token of the user
	 * @param login
	 *            Login of the member to post the message
	 * @param text
	 *            Content of the message
	 * @param responseTo
	 *            Id of the comment responded
	 * @return True if the comment is posted, false otherwise
	 */
	private boolean postAUserComment(String token, String login, String text,
			int responseTo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("member", login);
		String encode = text;
		try {
			encode = URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		params.put("text", encode);
		if (responseTo >= 0) {
			params.put("in_reply_to", "" + responseTo);
		}
		params.put("token", token);
		JSONObject jsonObject = UtilsJson.executeQuery("comments/post/member", apiKey, params);
		// TODO Check if there is error
		return true;
	}

	@Override
	public boolean postComment(String token, String url, String text) {
		return postGenericComment(token, url, text, -1, -1, -1);
	}

	@Override
	public boolean postComment(String token, String url, String text,
			int responseTo) {
		return postGenericComment(token, url, text, -1, -1, responseTo);
	}

	@Override
	public boolean postComment(String token, String url, String text,
			int season, int episode) {
		return postGenericComment(token, url, text, season, episode, -1);
	}

	@Override
	public boolean postComment(String token, String url, String text,
			int responseTo, int season, int episode) {
		return postGenericComment(token, url, text, season, episode, responseTo);
	}

	/**
	 * Post a comment
	 * TODO Found better name
	 * <ul>
	 * <li>season and episode >= 0 => post a comment on the episode page</li>
	 * <li>season and episode < 0 => post on the show page
	 * </ul>
	 * 
	 * @param token
	 *            Token of the user
	 * @param url
	 *            Url of the show
	 * @param text
	 *            Text of the message
	 * @param season
	 *            Numbebr of the season
	 * @param episode
	 *            Number of the episode
	 * @param responseTo
	 *            Id of the comment htat is responsed
	 */
	private boolean postGenericComment(String token, String url, String text,
			int season, int episode, int responseTo) {
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

		// TODO Check if there is error
		if (action != null) {
			UtilsJson.executeQuery(action, apiKey, params);
			return true;
		}
		return false;
	}

	@Override
	public boolean postUserComment(String token, String login, String text) {
		return postAUserComment(token, login, text, -1);
	}

	@Override
	public boolean postUserComment(String token, String login, String text,
			int responseTo) {
		return postAUserComment(token, login, text, responseTo);
	}
}
