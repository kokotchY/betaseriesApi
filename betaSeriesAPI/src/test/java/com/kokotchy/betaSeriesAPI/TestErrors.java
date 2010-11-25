package com.kokotchy.betaSeriesAPI;

import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;

import com.kokotchy.betaSeriesAPI.model.Error;

/**
 * @author canas
 */
public class TestErrors extends TestCase {

	/**
	 *
	 */
	private String key;

	/**
	 * TODO Fill it
	 * 
	 * @throws java.lang.Exception
	 */
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
	 * TODO Fill it
	 */
	public void testDoubleError() {
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		assertEquals(errorsXml, errorsJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testDoubleErrorEqualsJson() {
		Set<Error> errorsJson2 = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		assertEquals(errorsJson, errorsJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testDoubleErrorEqualsXml() {
		Set<Error> errorsXml2 = UtilsXml.getErrors(UtilsXml.executeQuery("errors/double", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/double", key));
		assertEquals(errorsXml, errorsXml2);
	}

	/**
	 * TODO Fill it
	 */
	public void testSimpleError() {
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		assertEquals(errorsXml, errorsJson);
	}

	/**
	 * TODO Fill it
	 */
	public void testSimpleErrorEqualsJson() {
		Set<Error> errorsJson2 = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		Set<Error> errorsJson = UtilsJson.getErrors(UtilsJson.executeQuery("errors/simple", key));
		assertEquals(errorsJson, errorsJson2);
	}

	/**
	 * TODO Fill it
	 */
	public void testSimpleErrorEqualsXml() {
		Set<Error> errorsXml2 = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		Set<Error> errorsXml = UtilsXml.getErrors(UtilsXml.executeQuery("errors/simple", key));
		assertEquals(errorsXml, errorsXml2);
	}
}
