package com.task.projectManagementSystem.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomResponse {

	private final String message;

    @JsonCreator
    public CustomResponse(@JsonProperty("message") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
