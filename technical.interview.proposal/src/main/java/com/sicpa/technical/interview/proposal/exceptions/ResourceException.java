package com.sicpa.technical.interview.proposal.exceptions;

public class ResourceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8257530564202216688L;
	private String message;

	public ResourceException(String message, Throwable exception) {
		super(message, exception);
		this.message = message;
	}

	public ResourceException(String message) {
		super(message);
	}

}
