package com.kokotchy.betaSeriesAPI.api.factories;

import com.kokotchy.betaSeriesAPI.model.Friend;

/**
 * Friend factory
 * 
 * @author kokotchy
 */
public class FriendFactory {

	/**
	 * Create a friend from the login
	 * 
	 * @param name
	 *            Name of the friend
	 * @return Friend
	 */
	public static Friend createFriend(String name) {
		Friend friend = new Friend();
		friend.setName(name);
		return friend;
	}

}
