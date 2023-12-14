package com.task.projectManagementSystem.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import com.task.projectManagementSystem.db.User;

import io.dropwizard.hibernate.AbstractDAO;

public class UserDao extends AbstractDAO<User> {

	public UserDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public User createUser(User User) throws Exception {
		System.out.println("Working");
        return persist(User);
	}
	
	public List<User> findByUserName(String userName){
		System.out.println(">>>>>>>>>>>>>"+userName);
		List<User> list = list(namedTypedQuery("com.task.projectManagementSystem.db.User.findByUserName")
				.setParameter("userName", userName));
		System.out.println(">>>>>>>>>>>>>!!!!!!!!!!!!!!!!!!!!!"+list);
		return list(namedTypedQuery("com.task.projectManagementSystem.db.User.findByUserName")
				.setParameter("userName", userName));
	}

}
