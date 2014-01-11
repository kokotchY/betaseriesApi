package com.kokotchy.betaSeriesAPI;

import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;

import com.kokotchy.betaSeriesAPI.model.Error;

/**
 * Test error handling
 * 
 * @author kokotchy
 */
public class TestErrors extends TestCase {

	/**
	 * Api key
	 */
	private String key;

	@Override
	@Before
	public void setUp() throws Exception {
		String userDir = System.getProperty("user.dir");
		key = Utils.getApiKey(userDir);
		UtilsJson.setDebug(true);
		UtilsJson.setDebugPath(userDir + "/src/test/resources/json/");
		UtilsXml.setDebug(true);
		UtilsXml.setDebugPath(userDir + "/src/test/resources/xml/");
	}

	/**
	 * Test double error
	 */
	public void testDoubleError() {
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		assertEquals(errorsXml, errorsJson);
	}

	/**
	 * Test double error equals json
	 */
	public void testDoubleErrorEqualsJson() {
		Set<Error> errorsJson2 = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		assertEquals(errorsJson, errorsJson2);
	}

	/**
	 * Test double error equals xml
	 */
	public void testDoubleErrorEqualsXml() {
		Set<Error> errorsXml2 = UtilsXml.getErrors(UtilsXml.executeQuery("errors/double", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/double", key));
		assertEquals(errorsXml, errorsXml2);
	}

	/**
	 * Test simple error
	 */
	public void testSimpleError() {
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		assertEquals(errorsXml, errorsJson);
	}

	/**
	 * Test a simple error equals json
	 */
	public void testSimpleErrorEqualsJson() {
		Set<Error> errorsJson2 = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		assertEquals(errorsJson, errorsJson2);
	}

	/**
	 * Test a simple error equals xml
	 */
	public void testSimpleErrorEqualsXml() {
		Set<Error> errorsXml2 = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		assertEquals(errorsXml, errorsXml2);
	}
}
