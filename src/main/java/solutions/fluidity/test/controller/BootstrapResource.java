package solutions.fluidity.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import solutions.fluidity.test.service.BootstrapService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Component
@Path("bootstrap")
public class BootstrapResource {

    private BootstrapService bootstrapService;
    private static Logger LOGGER = LoggerFactory.getLogger(BootstrapResource.class);

    public BootstrapResource(BootstrapService bootstrapService) {
        this.bootstrapService = bootstrapService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBootstrapStatic() throws IOException {
        String result = bootstrapService.getBootstrapData();
        LOGGER.info("Retrieved BootstrapStatic Data: {}", result);
        return Response.ok(result).build();
    }

    @GET
    @Path("/{gameWeekName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameWeekFixtures(@PathParam("gameWeekName") String gameWeekName) {
        String result = bootstrapService.getGameWeekFixtures(gameWeekName);
        LOGGER.info("Retrieved BootstrapStatic Data by Game Week: {}", result);
        return Response.ok(result).build();
    }
}