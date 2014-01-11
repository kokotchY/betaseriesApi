package com.kokotchy.betaSeriesAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

/**
 * Test the MD5 function
 * 
 * @author kokotchy
 */
public class TestMd5 extends TestCase {

	/**
	 * Test the MD5 function
	 */
	public void testMd5() {
		Map<String, String> tests = new HashMap<String, String>();
		tests.put("1a1dc91c907325c69271ddf0c944bc72", "pass");
		tests.put("5d41402abc4b2a76b9719d911017c592", "hello");
		tests.put("9c553730ef5b6c8c542bfd31b5e25b69", "&&&");
		tests.put("2c58e2efe0b6224c7cd839163e214865", "this is a longer test");
		tests.put("827ccb0eea8a706c4c34a16891f84e7b", "12345");
		tests
				.put(
						"793e1a2d69ec82959839deb27d775c79",
						"In Cryptography, MD5 (Message-Digest algorithm 5) is a widely-used cryptographic hash function with a 128-bit hash value.");

		for (Entry<String, String> entry : tests.entrySet()) {
			String word = entry.getValue();
			assertEquals(entry.getKey(), Utils.getMD5(word));
		}
	}
}
