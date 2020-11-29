package com.bamri.school.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

	private int errorCode;
	private String error;
	@JsonInclude(value = Include.NON_NULL)
	private String errorMessage;
	private List<String> fieldErrors = new ArrayList<>();

	public Error(HttpStatus status, List<String> fieldErrors) {
		this.errorCode = status.value();
		this.error = status.name();
		this.fieldErrors = fieldErrors;
	}

	public Error(HttpStatus status, String message, List<String> fieldErrors) {
		this.errorCode = status.value();
		this.error = status.name();
		this.errorMessage = message;
		this.fieldErrors = fieldErrors;
	}
}
