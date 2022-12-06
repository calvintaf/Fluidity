package solutions.fluidity.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import solutions.fluidity.test.service.EventService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("events")
public class EventResource {

    private EventService eventService;
    private static Logger LOGGER = LoggerFactory.getLogger(EventResource.class);

    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @GET
    @Path("/{eventCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvent(@PathParam("eventCode") String eventCode) {
        String result = eventService.getEvent(eventCode);
        LOGGER.info("Retrieved Events by Event Code: {}", result);
        return Response.ok(result).build();
    }
}
