package solutions.fluidity.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import solutions.fluidity.test.model.dto.FixturesDto;
import solutions.fluidity.test.service.FixturesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Component
@Path("fixtures")
public class FixturesResource {

    private FixturesService fixturesService;
    private static Logger LOGGER = LoggerFactory.getLogger(FixturesResource.class);

    public FixturesResource(FixturesService fixturesService) {
        this.fixturesService = fixturesService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixtures(){
        List<FixturesDto> fixturesDtoList = fixturesService.getFixturesCustom();
        LOGGER.info("Retrieved Fixtures: {}", fixturesDtoList);
        return Response.ok(fixturesDtoList).build();
    }
}