package com.task.projectManagementSystem.mail;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmtpConfiguration {
	@JsonProperty
    private String host;

    @JsonProperty
    private int port;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private boolean tls;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTls() {
		return tls;
	}

	public void setTls(boolean tls) {
		this.tls = tls;
	}
}
