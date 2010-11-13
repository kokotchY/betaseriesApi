package com.kokotchy.betaSeriesAPI;

import java.util.List;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * TODO Fill it
 * 
 * @author kokotchy
 * 
 */
public class TestPlanning extends TestCase {

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.jsonImpl.Planning planningJson;

	/**
	 * TODO Fill it
	 */
	private com.kokotchy.betaSeriesAPI.api.xmlImpl.Planning planningXml;

	/**
	 *
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		planningJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getPlanning();
		planningXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getPlanning();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriejson/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(System.getProperty("user.dir")
				+ "/src/test/resources/betaseriexml/");
	}

	/**
	 * TODO Fill it
	 */
	public void testGeneralPlanning() {
		List<Episode> generalPlanningJson = planningJson.getGeneralPlanning();
		List<Episode> generalPlanningXml = planningXml.getGeneralPlanning();
		assertEquals(generalPlanningXml, generalPlanningJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testGeneralPlanningEqualsJson() {
		List<Episode> generalPlanningJson = planningJson.getGeneralPlanning();
		List<Episode> generalPlanningJson2 = planningJson.getGeneralPlanning();
		assertEquals(generalPlanningJson, generalPlanningJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testGeneralPlanningEqualsXml() {
		List<Episode> generalPlanningXml = planningXml.getGeneralPlanning();
		List<Episode> generalPlanningXml2 = planningXml.getGeneralPlanning();
		assertEquals(generalPlanningXml, generalPlanningXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testPlanningLoggedUser() {
		String token = "6668bb6944a5";
		List<Episode> memberPlanningJson = planningJson.getMemberPlanning(true,
				token);
		List<Episode> memberPlanningXml = planningXml.getMemberPlanning(true,
				token);
		assertEquals(memberPlanningXml, memberPlanningJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testPlanningLoggedUserEqualsJson() {
		String token = "6668bb6944a5";
		List<Episode> memberPlanningJson = planningJson.getMemberPlanning(true,
				token);
		List<Episode> memberPlanningJson2 = planningJson.getMemberPlanning(
				true, token);
		assertEquals(memberPlanningJson, memberPlanningJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testPlanningLoggedUserEqualsXml() {
		String token = "6668bb6944a5";
		List<Episode> memberPlanningXml = planningXml.getMemberPlanning(true,
				token);
		List<Episode> memberPlanningXml2 = planningXml.getMemberPlanning(true,
				token);
		assertEquals(memberPlanningXml, memberPlanningXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testPlanningUser() {
		String user = "delphiki";
		List<Episode> memberPlanningJson = planningJson.getMemberPlanning(user,
				true);
		List<Episode> memberPlanningXml = planningXml.getMemberPlanning(user,
				true);
		assertEquals(memberPlanningXml, memberPlanningJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testPlanningUserEqualsJson() {
		String user = "delphiki";
		List<Episode> memberPlanningJson = planningJson.getMemberPlanning(user,
				true);
		List<Episode> memberPlanningJson2 = planningJson.getMemberPlanning(
				user, true);
		assertEquals(memberPlanningJson, memberPlanningJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testPlanningUserEqualsXml() {
		String user = "delphiki";
		List<Episode> memberPlanningXml = planningXml.getMemberPlanning(user,
				true);
		List<Episode> memberPlanningXml2 = planningXml.getMemberPlanning(user,
				true);
		assertEquals(memberPlanningXml, memberPlanningXml2);
	}
}
