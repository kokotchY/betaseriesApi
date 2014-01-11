package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.kokotchy.betaSeriesAPI.UtilsJson;
import com.kokotchy.betaSeriesAPI.api.Constants;
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
		if (!UtilsJson.hasErrors(jsonObject)) {
			JSONObject comments = UtilsJson.getJSONObjectFromPath(jsonObject,
					"/root/comments");
			JSONObject[] array = UtilsJson.getArray(comments);
			Set<Comment> result = new HashSet<Comment>();
			if (array.length > 0) {
				for (JSONObject commentObject : array) {
					Comment comment = CommentFactory.createComment(commentObject);
					result.add(comment);
				}
			}
			return result;
		}
		return null;
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
		params.put(Constants.SEASON, "" + season);
		params.put(Constants.EPISODE, "" + episode);
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
		params.put(Constants.COMMENT_MEMBER, login);
		String encode = text;
		try {
			encode = URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		params.put(Constants.COMMENT_TEXT, encode);
		if (responseTo >= 0) {
			params.put(Constants.COMMENT_IN_REPLY_TO, "" + responseTo);
		}
		params.put(Constants.TOKEN, token);
		JSONObject jsonObject = UtilsJson.executeQuery("comments/post/member", apiKey, params);
		return !UtilsJson.hasErrors(jsonObject);
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
		String encoded = text;
		try {
			encoded = URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		params.put(Constants.COMMENT_TEXT, encoded);
		params.put(Constants.SHOW, url);
		if (season < 0 && episode < 0) {
			action = "comments/post/show";
		} else if (season > 0 && episode > 0) {
			action = "comments/post/episode";
			params.put(Constants.SEASON, "" + season);
			params.put(Constants.EPISODE, "" + episode);
		}

		if (responseTo >= 0) {
			params.put(Constants.COMMENT_IN_REPLY_TO, "" + responseTo);
		}

		if (token != null) {
			params.put(Constants.TOKEN, token);
		}

		if (action != null) {
			JSONObject jsonObject = UtilsJson.executeQuery(action, apiKey, params);
			return !UtilsJson.hasErrors(jsonObject);
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
