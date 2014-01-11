/**
 * 
 */
package com.kokotchy.betaSeriesAPI.api;

import java.util.Set;

import com.kokotchy.betaSeriesAPI.model.Comment;

/**
 * Interface for comments on shows, users, ...
 * 
 * @author kokotchy
 */
public interface IComments {

	/**
	 * Return the comments for the given show
	 * 
	 * @param url
	 *            Url of the show
	 * @return Comments for the given show
	 */
	public Set<Comment> getComments(String url);

	/**
	 * Return the comments for the episode in the season of the show
	 * 
	 * @param url
	 *            Url of the show
	 * @param season
	 *            Season
	 * @param episode
	 *            Episode
	 * @return Comments for the given episode
	 */
	public Set<Comment> getComments(String url, int season, int episode);

	/**
	 * Return the comments of the specified member
	 * 
	 * @param login
	 *            Login of the member
	 * @return Comments for the member
	 */
	public Set<Comment> getUserComments(String login);

	/**
	 * Post a comment on the given show
	 * 
	 * @param url
	 *            Url of the show
	 * @param text
	 *            Comment to post
	 * @return Comment posted
	 */
	public boolean postComment(String token, String url, String text);

	/**
	 * Post a comment on the given show as a response to another comment
	 * 
	 * @param url
	 *            Url of the show
	 * @param text
	 *            Text to post
	 * @param responseTo
	 *            Id of the comment to response to
	 * @return Comment posted
	 */
	public boolean postComment(String token, String url, String text,
			int responseTo);

	/**
	 * Post a comment to the episode of the season of the show
	 * 
	 * @param url
	 *            Url of the show
	 * @param text
	 *            Text to post
	 * @param season
	 *            Season
	 * @param episode
	 *            Episode
	 * @return Comment posted
	 */
	public boolean postComment(String token, String url, String text,
			int season, int episode);

	/**
	 * Post a comment to the episode of the season of the show in response to
	 * another comment
	 * 
	 * @param url
	 *            Url of the show
	 * @param text
	 *            Text to post
	 * @param responseTo
	 *            Id of the comment to respond to
	 * @param season
	 *            Season
	 * @param episode
	 *            Episode
	 * @param Comment
	 *            posted
	 */
	public boolean postComment(String token, String url, String text,
			int responseTo, int season, int episode);

	/**
	 * Post a comment to the user profile
	 * 
	 * @param token
	 * @param login
	 *            Login of the user
	 * @param text
	 *            Comment
	 * @return Comment posted
	 */
	public boolean postUserComment(String token, String login, String text);

	/**
	 * Post a comment to the user profile in response to another comment
	 * 
	 * @param login
	 *            Login of the user
	 * @param text
	 *            Comment
	 * @param responseTo
	 *            Id of the responsed comment
	 * @return Comment posted
	 */
	public boolean postUserComment(String token, String login, String text,
			int responseTo);
}
