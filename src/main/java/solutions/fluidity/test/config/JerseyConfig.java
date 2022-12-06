package solutions.fluidity.test.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import solutions.fluidity.test.controller.BootstrapResource;
import solutions.fluidity.test.controller.EventResource;
import solutions.fluidity.test.controller.FixturesResource;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("api/v1")
class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(org.glassfish.jersey.server.ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(org.glassfish.jersey.server.ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);


        register(BootstrapResource.class);
        register(FixturesResource.class);
        register(EventResource.class);

    }
}
