package com.task.projectManagementSystem.auth;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.task.projectManagementSystem.db.User;

import io.dropwizard.auth.Authorizer;
import jakarta.ws.rs.container.ContainerRequestContext;

public class AuthorizerConfig implements Authorizer<User> {

	@Override
	public boolean authorize(User user, String role, @Nullable ContainerRequestContext requestContext) {
		return user.getRoles().contains(role);
	}

//	@Override
//	public boolean authorize(User principal, String role, @Nullable ContainerRequestContext requestContext) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	 @Override
//	    public boolean authorize(User user, String role, @Nullable ContainerRequestContext requestContext) {
//	        return user.getRoles() != null && user.getRoles().contains(role);
//	    }
	

}
