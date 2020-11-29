package com.bamri.school.exception;

public class DiplomeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DiplomeNotFoundException(String exMessage) {
		super(exMessage);
	}
}
