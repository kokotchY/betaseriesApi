package com.kokotchy.betaSeriesAPI;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Member;

/**
 * Test members api
 * 
 * @author kokotchy
 */
public class TestMembers extends TestCase {

	/**
	 * Comments api for json
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Members membersJson;

	/**
	 * Comments api for xml
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Members membersXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		membersJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getMembers();
		membersXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getMembers();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/xml/");
	}

	/**
	 * Test authentication of the user
	 */
	public void testAuth() {
		String login = "Dev042";
		String password = Utils.getMD5("");
		assertEquals(membersXml.auth(login, password), membersJson.auth(login,
				password));
		System.out.println(membersJson.getToken());
	}

	/**
	 * Test informations with xml and json
	 */
	public void testInfos() {
		String token = "";
		Member infosJson = membersJson.infos(token);
		Member infosXml = membersXml.infos(token);
		assertEquals(infosXml, infosJson);
	}

	/**
	 * Test equals of two json for informations of the user
	 */
	public void testInfosEqualsJson() {
		String token = "";
		Member infosJson = membersJson.infos(token);
		Member infosJson2 = membersJson.infos(token);
		assertEquals(infosJson, infosJson2);
	}

	/**
	 * Test equals of two xml for informations of the user
	 */
	public void testInfosEqualsXml() {
		String token = "";
		Member infosXml = membersXml.infos(token);
		Member infosXml2 = membersXml.infos(token);
		assertEquals(infosXml, infosXml2);
	}

	/**
	 * Test if user still active with xml and json
	 */
	public void testIsActive() {
		String token = "";
		assertEquals(membersXml.isActive(token), membersJson.isActive(token));
	}

	/**
	 * TODO Fill it
	 */
	public void testMembersInfosOfUser() {
		Member memberXml = membersXml.infosOfUser("delphiki");
		Member memberJson = membersJson.infosOfUser("delphiki");
		assertEquals(memberXml, memberJson);
	}
}
