package com.kokotchy.betaSeriesAPI;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for all tests
 * 
 * @author kokotchy
 */
public class AllTests {

	/**
	 * Suite case
	 * 
	 * @return Suite Case
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.kokotchy.betaSeriesAPI");
		// $JUnit-BEGIN$
		suite.addTestSuite(TestMembers.class);
		suite.addTestSuite(TestStatus.class);
		suite.addTestSuite(TestSubtitles.class);
		suite.addTestSuite(TestComments.class);
		suite.addTestSuite(TestEntityDecoder.class);
		suite.addTestSuite(TestShows.class);
		suite.addTestSuite(TestPlanning.class);
		suite.addTestSuite(TestMd5.class);
		suite.addTestSuite(TestTimeline.class);
		// $JUnit-END$
		return suite;
	}

}
