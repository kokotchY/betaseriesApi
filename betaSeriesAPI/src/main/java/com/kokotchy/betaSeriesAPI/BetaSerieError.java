package com.kokotchy.betaSeriesAPI;

/**
 * Model of an error
 * 
 * @author kokotchy
 * 
 */
public class BetaSerieError {

	/**
	 * Code of the error
	 */
	private int code;

	/**
	 * Text of the error
	 */
	private String text;

	/**
	 * Create the error with the given code
	 * 
	 * @param code
	 *            Code of the error
	 */
	public BetaSerieError(int code) {
		this(code, null);
	}

	/**
	 * Create the error with the given code and text message
	 * 
	 * @param code
	 *            Code of the error
	 * @param text
	 *            Text of the error
	 */
	public BetaSerieError(int code, String text) {
		this.code = code;
		this.text = text;
	}

	/**
	 * Create the error with the given text message
	 * 
	 * @param text
	 *            Text of the error
	 */
	public BetaSerieError(String text) {
		this(-1, text);
	}

	/**
	 * Return the code of the error
	 * 
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Return the text of the error
	 * 
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Set the code of the error
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 *Set the text of the error
	 * 
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
