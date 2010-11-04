package com.kokotchy.betaSeriesAPI.model;

/**
 * Type of an event
 * 
 * @author kokotchy
 */
public enum EventType {
	FRIEND_ADD, // add of a friend
	FRIEND_DELETE, // delete of a friend
	MARKAS, // mark episode as seen
	ADD_SERIE, // add a serie
	DEL_SERIE, // delete a serie
	ARCHIVE, // archive a serie
	UNARCHIVE, // unarchive a serie
	RECOMMANDATION, // recommandation
	RECOMMANDATION_DECLINE, // decline a recommandation
	RECOMMANDATION_ACCEPT, // accept a recommandation
	INSCRIPTION, // inscription
	UPDATE, // update
	SUBTITLES, // subtitles
	COMMENT, // comment
}
