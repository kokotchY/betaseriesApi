package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import com.kokotchy.betaSeriesAPI.api.IComments;
import com.kokotchy.betaSeriesAPI.model.Comment;

/**
 * TODO Fill it
 * @author kokotchy
 *
 */
public class Comments implements IComments {

	@Override
	public List<Comment> getComments(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments(String url, int season, int episode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getUserComments(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postComment(String url, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postComment(String url, String text, int responseTo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postComment(String url, String text, int season, int episode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postComment(String url, String text, int responseTo,
			int season, int episode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postUserComment(String login, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postUserComment(String login, String text, int responseTo) {
		// TODO Auto-generated method stub

	}

}
