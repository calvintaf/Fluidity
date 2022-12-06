package solutions.fluidity.test.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import solutions.fluidity.test.exception.EventException;
import solutions.fluidity.test.model.boostrapstatic.BootstrapModel;
import solutions.fluidity.test.model.boostrapstatic.EventModel;
import solutions.fluidity.test.model.fixtures.FixtureModel;
import solutions.fluidity.test.premiershipapi.BootstrapStatic;
import solutions.fluidity.test.service.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventServiceImpl implements EventService {
    private BootstrapStatic bootstrapStatic;
    private static Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    public EventServiceImpl(BootstrapStatic bootstrapStatic) {
        this.bootstrapStatic = bootstrapStatic;
    }

    @Override
    public String getEvent(String eventCode) {
        try {
            BootstrapModel result = bootstrapStatic.getBootstrapModelData("");
            FixtureModel fixtureModel = null;
            EventModel event = null;

            for (EventModel eventModel : result.getEvents()) {
                fixtureModel = eventModel.getFixtures().stream().filter(fixtureModel1 -> fixtureModel1.getCode().equals(eventCode)).findFirst().orElse(null);

                if (fixtureModel != null) {
                    List<FixtureModel> fixturesList = new ArrayList<>();
                    fixturesList.add(fixtureModel);
                    event = eventModel;
                    event.setFixtures(fixturesList);
                    break;
                }
            }

            Gson gson = new GsonBuilder().create();
            return gson.toJson(event, EventModel.class);

        } catch (IOException e) {
            LOGGER.error("Failed to retrieve Event Data : ", e);
            throw new EventException("Failed to retrieve Event Data : " + e.getMessage());
        }
    }
}
