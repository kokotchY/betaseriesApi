package com.kokotchy.betaSeriesAPI.api.jsonImpl;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.kokotchy.betaSeriesAPI.api.IComments;
import com.kokotchy.betaSeriesAPI.model.Comment;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class Comments implements IComments {

	@Override
	public List<Comment> getComments(String url) {
		throw new NotImplementedException();
	}

	@Override
	public List<Comment> getComments(String url, int season, int episode) {
		throw new NotImplementedException();
	}

	@Override
	public List<Comment> getUserComments(String login) {
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
