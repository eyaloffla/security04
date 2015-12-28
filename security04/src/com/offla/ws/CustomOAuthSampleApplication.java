package com.offla.ws;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;



//@Path("/rest") 
public class CustomOAuthSampleApplication extends Application {
	
	@Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(TestWS.class);
        classes.add(CustomAuthResource.class);
        
        return classes;
    }

}
