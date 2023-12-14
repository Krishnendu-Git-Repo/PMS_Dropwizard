package com.task.projectManagementSystem.auth;

import java.util.List;
import java.util.Optional;

import com.task.projectManagementSystem.dao.UserDao;
import com.task.projectManagementSystem.db.User;
import com.task.projectManagementSystem.exception.CustomException;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class AuthenticatorConfig implements Authenticator<BasicCredentials, User> {
	
//	    @Override
//	    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
////	    	if ("secret".equals(credentials.getPassword())) {
////	            return Optional.of(new User(credentials.getUsername(),credentials.getPassword()));
////	        }
//	        return Optional.empty();
//	    }
// 
	private UserDao userDAO;
	
	public AuthenticatorConfig(UserDao userDAO) {
        this.userDAO = userDAO;
    }
	
	 @Override
	    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		 System.out.println("..................credentials.getUsername()"+ credentials.getUsername());
	        List<User> user = userDAO.findByUserName(credentials.getUsername());
            System.out.println(".................."+ user.get(0));
	        if (user.size() > 0 && isValidPassword(credentials.getPassword(), user.get(0).getPassword())) {
	            return Optional.ofNullable(user.get(0));
	        }
	        return Optional.empty();
	    }
	 
	 private boolean isValidPassword(String providedPassword, String storedPassword) {
		 if(providedPassword.equals(storedPassword)) {
	        return providedPassword.equals(storedPassword);
		 } else {
			 throw new CustomException("Password Not Matched");
		 }
	    }
	    
}
