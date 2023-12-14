package com.task.projectManagementSystem;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.task.projectManagementSystem.auth.AuthenticatorConfig;
import com.task.projectManagementSystem.auth.AuthorizerConfig;
import com.task.projectManagementSystem.core.GetUser;
import com.task.projectManagementSystem.dao.EmployeeDao;
import com.task.projectManagementSystem.dao.ProjectAssigneeDao;
import com.task.projectManagementSystem.dao.ProjectDao;
import com.task.projectManagementSystem.dao.UserDao;
import com.task.projectManagementSystem.db.Employee;
import com.task.projectManagementSystem.db.Project;
import com.task.projectManagementSystem.db.ProjectAssignee;
import com.task.projectManagementSystem.db.User;
import com.task.projectManagementSystem.mail.SmtpConfiguration;
import com.task.projectManagementSystem.resources.EmployeeResource;
import com.task.projectManagementSystem.resources.ProjectResource;
import com.task.projectManagementSystem.resources.UserResource;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class pmsApplication extends Application<pmsConfiguration> {
	
	public static void main(final String[] args) throws Exception {
        new pmsApplication().run(new String[] { "server", "config.yml" });
    }
	
	private final HibernateBundle<pmsConfiguration> hibernateBundle =
            new HibernateBundle<pmsConfiguration>(Employee.class, Project.class, ProjectAssignee.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(pmsConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "pms";
    }

    @Override
    public void initialize(final Bootstrap<pmsConfiguration> bootstrap) {
    	 bootstrap.addBundle(hibernateBundle);
    	 
    }

    @Override
    public void run(final pmsConfiguration configuration, final Environment environment) {
    	
    	SmtpConfiguration smtpConfiguration = configuration.getSmtpConfiguration();
        EmployeeDao employeeDao = new EmployeeDao(hibernateBundle.getSessionFactory());    
        
		/* For Authentication */
        UserDao userDaO = new UserDao(hibernateBundle.getSessionFactory());
        final AuthenticatorConfig authenticator = new AuthenticatorConfig(userDaO);
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(authenticator)
                        .setAuthorizer(new AuthorizerConfig())
                        .setRealm("your-realm")
                        .buildAuthFilter()));
        
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        
//        GetUser getUser= new GetUser(employeeDao);
//        environment.jersey().register(getUser);
//        EmployeeDao employeeDao2 = getUser.getEmployeeDao();
//        employeeDao2.findAllEmployee();
        
        ProjectDao projectDao = new ProjectDao(hibernateBundle.getSessionFactory());
        ProjectAssigneeDao projectAssigneeDao = new ProjectAssigneeDao(hibernateBundle.getSessionFactory());
        
    	environment.jersey().register(new EmployeeResource(employeeDao));
        environment.jersey().register(new ProjectResource(projectDao, projectAssigneeDao, smtpConfiguration, employeeDao));        
//        environment.jersey().register(new UserResource(userDaO));
        

        
    }
    

}
