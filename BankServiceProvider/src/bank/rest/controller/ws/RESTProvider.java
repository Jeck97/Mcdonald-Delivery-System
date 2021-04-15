/**
 * 
 */
package bank.rest.controller.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


/**
 * This class creates a subclass of javax.ws.rs.core.Application
 * It tells the framework when the URI is -> http://localhost:8085/RESTProvider/services/
 * It is referring to all the services in bank.rest.controller.service
 * @author Tiang King Jeck
 *
 */
@ApplicationPath("service")
public class RESTProvider extends ResourceConfig {

	public RESTProvider() {
		
		packages("bank.rest.controller.service");
		
	}

}