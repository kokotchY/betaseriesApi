/**
 * 
 */
package com.kokotchy.betaSeriesAPI;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author kokotchy
 * 
 */
public class BetaSerieHandler extends DefaultHandler {

	/**
	 * 
	 */
	private StringBuffer buffer;

	/**
	 * 
	 */
	private boolean inErrors;

	/**
	 * 
	 */
	private boolean error = false;

	/**
	 * 
	 */
	private List<BetaSerieError> errors = new LinkedList<BetaSerieError>();

	/**
	 * 
	 */
	private BetaSerieError currentError;

	/**
	 * 
	 */
	private String token;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String content = new String(ch, start, length);
		if (buffer != null) {
			buffer.append(content);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("code")) {
			error = readBoolean();
		} else if (qName.equals("error")) {
			currentError.setText(readString());
			errors.add(currentError);
			currentError = null;
		} else if (qName.equals("token")) {
			token = readString();
		} else {
			System.out.println("Unknown readed txt: " + readString());
		}
	}

	/**
	 * @return the errors
	 */
	public List<BetaSerieError> getErrors() {
		return errors;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	public boolean isError() {
		return errors.size() > 0 || error;
	}

	/**
	 * @return
	 */
	private boolean readBoolean() {
		return readInt() == 1;
	}

	/**
	 * @return
	 */
	private int readInt() {
		return Integer.parseInt(readString());
	}

	/**
	 * @return
	 */
	private String readString() {
		if (buffer != null) {
			String string = buffer.toString();
			buffer = null;
			return string;
		}
		return null;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("code")) {
			buffer = new StringBuffer();
		} else if (qName.equals("errors")) {
			inErrors = true;
		} else if (qName.equals("error")) {
			int code = Integer.parseInt(attributes.getValue(attributes
					.getIndex("code")));
			currentError = new BetaSerieError(code);
			buffer = new StringBuffer();
		} else if (qName.equals("token")) {
			buffer = new StringBuffer();
		} else {
			System.out.println("Unknown element: " + qName);
		}
	}
}
