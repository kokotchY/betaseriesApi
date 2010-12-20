package com.kokotchy.betaSeriesAPI.api.factories;

import com.kokotchy.betaSeriesAPI.model.Friend;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class FriendFactory {

	/**
	 * TODO Fill it
	 * 
	 * @param name
	 * @return
	 */
	public static Friend createFriend(String name) {
		Friend friend = new Friend();
		friend.setName(name);
		return friend;
	}

}
