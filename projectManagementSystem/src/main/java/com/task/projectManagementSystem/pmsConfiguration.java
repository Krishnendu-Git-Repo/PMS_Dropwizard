package com.task.projectManagementSystem;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.projectManagementSystem.mail.SmtpConfiguration;

import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class pmsConfiguration extends Configuration {
	
	@JsonProperty("smtp")
    private SmtpConfiguration smtpConfiguration;

    public SmtpConfiguration getSmtpConfiguration() {
        return smtpConfiguration;
    }
    
	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
	
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		if (Objects.isNull(database))
			database = new DataSourceFactory();
		return database;
	}

	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.database = dataSourceFactory;
	}
}
