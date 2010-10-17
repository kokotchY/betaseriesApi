/**
 * 
 */
package com.kokotchy.betaSeriesAPI;

/**
 * @author kokotchy
 * 
 */
public class BetaSerieError {

	/**
	 * 
	 */
	private int code;

	/**
	 * 
	 */
	private String text;

	/**
	 * @param code
	 */
	public BetaSerieError(int code) {
		this(code, null);
	}

	/**
	 * @param code
	 * @param text
	 */
	public BetaSerieError(int code, String text) {
		this.code = code;
		this.text = text;
	}

	/**
	 * @param text
	 */
	public BetaSerieError(String text) {
		this(-1, text);
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		String format = "[#%d] %s";
		return String.format(format, code, text);
	}

}
