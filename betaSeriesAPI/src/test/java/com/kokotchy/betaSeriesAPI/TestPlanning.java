package com.kokotchy.betaSeriesAPI;

import java.util.Set;

import junit.framework.TestCase;

import com.kokotchy.betaSeriesAPI.api.IPlanning;
import com.kokotchy.betaSeriesAPI.model.Episode;

/**
 * Test planning api
 * 
 * @author kokotchy
 */
public class TestPlanning extends TestCase {

	/**
	 * Comments api for json
	 */
	private IPlanning planningJson;

	/**
	 * Comments api for xml
	 */
	private IPlanning planningXml;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String userDir = System.getProperty("user.dir");
		String key = Utils.getApiKey(userDir);
		com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi.setApiKey(key);
		com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi.setApiKey(key);
		planningJson = com.kokotchy.betaSeriesAPI.api.jsonImpl.BetaSerieApi
				.getPlanning();
		planningXml = com.kokotchy.betaSeriesAPI.api.xmlImpl.BetaSerieApi
				.getPlanning();
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(userDir + "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(userDir + "/src/test/resources/xml/");
	}

	/**
	 * Test general planning is equals for xml and json
	 */
	public void testGeneralPlanning() {
		Set<Episode> generalPlanningJson = planningJson.getGeneralPlanning();
		Set<Episode> generalPlanningXml = planningXml.getGeneralPlanning();
		assertEquals(generalPlanningXml, generalPlanningJson);
	}

	/**
	 * Test general planning is equals for two json
	 */
	public void testGeneralPlanningEqualsJson() {
		Set<Episode> generalPlanningJson = planningJson.getGeneralPlanning();
		Set<Episode> generalPlanningJson2 = planningJson.getGeneralPlanning();
		assertEquals(generalPlanningJson, generalPlanningJson2);
	}

	/**
	 * Test general planning is equals for two xml
	 */
	public void testGeneralPlanningEqualsXml() {
		Set<Episode> generalPlanningXml = planningXml.getGeneralPlanning();
		Set<Episode> generalPlanningXml2 = planningXml.getGeneralPlanning();
		assertEquals(generalPlanningXml, generalPlanningXml2);
	}

	/**
	 * Test planning for logged user for xml and json
	 */
	public void testPlanningLoggedUser() {
		String token = "6668bb6944a5";
		Set<Episode> memberPlanningJson = planningJson.getMemberPlanning(true,
				token);
		Set<Episode> memberPlanningXml = planningXml.getMemberPlanning(true,
				token);
		assertEquals(memberPlanningXml, memberPlanningJson);
	}

	/**
	 * Test planning for logged user for two json
	 */
	public void testPlanningLoggedUserEqualsJson() {
		String token = "6668bb6944a5";
		Set<Episode> memberPlanningJson = planningJson.getMemberPlanning(true,
				token);
		Set<Episode> memberPlanningJson2 = planningJson.getMemberPlanning(true,
				token);
		assertEquals(memberPlanningJson, memberPlanningJson2);
	}

	/**
	 * Test planning for logged user for two xml
	 */
	public void testPlanningLoggedUserEqualsXml() {
		String token = "6668bb6944a5";
		Set<Episode> memberPlanningXml = planningXml.getMemberPlanning(true,
				token);
		Set<Episode> memberPlanningXml2 = planningXml.getMemberPlanning(true,
				token);
		assertEquals(memberPlanningXml, memberPlanningXml2);
	}

	/**
	 * Test the planning of a user for xml and json
	 */
	public void testPlanningUser() {
		String user = "delphiki";
		Set<Episode> memberPlanningJson = planningJson.getMemberPlanning(user,
				true);
		Set<Episode> memberPlanningXml = planningXml.getMemberPlanning(user,
				true);
		assertEquals(memberPlanningXml, memberPlanningJson);
	}

	/**
	 * Test the planning of a user for two json
	 */
	public void testPlanningUserEqualsJson() {
		String user = "delphiki";
		Set<Episode> memberPlanningJson = planningJson.getMemberPlanning(user,
				true);
		Set<Episode> memberPlanningJson2 = planningJson.getMemberPlanning(user,
				true);
		assertEquals(memberPlanningJson, memberPlanningJson2);
	}

	/**
	 * Test the planning of a user for two xml
	 */
	public void testPlanningUserEqualsXml() {
		String user = "delphiki";
		Set<Episode> memberPlanningXml = planningXml.getMemberPlanning(user,
				true);
		Set<Episode> memberPlanningXml2 = planningXml.getMemberPlanning(user,
				true);
		assertEquals(memberPlanningXml, memberPlanningXml2);
	}
}
