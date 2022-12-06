package solutions.fluidity.test.premiershipapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import solutions.fluidity.test.controller.EventResource;
import solutions.fluidity.test.exception.BootStrapStaticException;
import solutions.fluidity.test.model.boostrapstatic.*;
import solutions.fluidity.test.model.fixtures.FixtureModel;
import solutions.fluidity.test.service.BootstrapDataService;
import solutions.fluidity.test.service.FixturesService;
import solutions.fluidity.test.service.ObjectParseService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static solutions.fluidity.test.domain.enums.Position.*;

@Component
public class BootstrapStatic {

    private FixturesService fixturesService;
    private ObjectParseService objectParseService;
    private BootstrapDataService bootstrapDataService;
    private static Logger LOGGER = LoggerFactory.getLogger(BootstrapStatic.class);

    public BootstrapStatic(FixturesService fixturesService, ObjectParseService objectParseService, BootstrapDataService bootstrapDataService) {
        this.fixturesService = fixturesService;
        this.objectParseService = objectParseService;
        this.bootstrapDataService = bootstrapDataService;
    }

    public BootstrapModel getBootstrapModelData(String gameWeekName) throws IOException {

        BootstrapStaticModel bootstrapStaticModel = bootstrapDataService.getBootstrapStaticModelData();

        String result = fixturesService.getFixtures();
        List<FixtureModel> fixtures = objectParseService.parseResponse(result);
        var bootstrapModel = new BootstrapModel();

        List<EventModel> filteredEvents = null;
        if (gameWeekName.isEmpty()) {
            filteredEvents = bootstrapStaticModel.getEvents();
        } else {
            filteredEvents = bootstrapStaticModel.getEvents()
                    .stream().filter(eventModel -> Objects.equals(eventModel.getName().toLowerCase(Locale.ROOT).replaceAll("\\s", ""),
                            gameWeekName.toLowerCase(Locale.ROOT).replaceAll("\\s", "")))
                            .collect(Collectors.toList());
        }

        if (filteredEvents.isEmpty()) {
            LOGGER.warn("Invalid Game week parameter supplied: ", gameWeekName );
            throw new BootStrapStaticException("Invalid Game week parameter supplied.");
        }

        getEvents(bootstrapStaticModel, filteredEvents, fixtures);
        setEventPlayerTypes(bootstrapStaticModel, filteredEvents.get(0));
        bootstrapModel.setEvents(filteredEvents);
        return bootstrapModel;
    }

    public String lookup() throws IOException {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(getBootstrapModelData(""));
    }

    public String lookup(String gameWeekName) throws IOException {

        Gson gson = new GsonBuilder().create();
        return gson.toJson(getBootstrapModelData(gameWeekName));
    }

    private void setEventPlayerTypes(
            BootstrapStaticModel bootstrapStaticModel,
            EventModel filteredEvents
    ) {
        bootstrapStaticModel.getElement_types().forEach(et ->
                filteredEvents.setGoalKeepers(bootstrapStaticModel.getElements().stream().filter(el -> el.getElement_type() == GOAL_KEEPER.getValue()).collect(Collectors.toList())));
        filteredEvents.setForwards(bootstrapStaticModel.getElements().stream().filter(el -> el.getElement_type() == DEFENDER.getValue()).collect(Collectors.toList()));
        filteredEvents.setMidfielders(bootstrapStaticModel.getElements().stream().filter(el -> el.getElement_type() == MIDFIELDER.getValue()).collect(Collectors.toList()));
        filteredEvents.setDefenders(bootstrapStaticModel.getElements().stream().filter(el -> el.getElement_type() == FORWARD.getValue()).collect(Collectors.toList()));
    }


    private void getEvents(BootstrapStaticModel bootstrapStaticModel,
                           List<EventModel> filteredEvents,
                           List<FixtureModel> fixtures
    ) {
        filteredEvents.stream().forEach(evt -> getEventFixtures(bootstrapStaticModel, fixtures, evt));
    }

    private void getEventFixtures(
            BootstrapStaticModel bootstrapStaticModel,
            List<FixtureModel> fixtures,
            EventModel evt
    ) {
        List<FixtureModel> eventFixtures =
                fixtures.stream().filter(fix -> fix.getEvent() == evt.getId()).collect(Collectors.toList());

        eventFixtures.stream().forEach(evtFix ->
                getTeams(bootstrapStaticModel, evtFix)
        );
        evt.setFixtures(eventFixtures);
    }

    private void getTeams(BootstrapStaticModel bootstrapStaticModel, FixtureModel evtFix) {
        List<TeamModel> homeTeam =
                bootstrapStaticModel.getTeams().stream().filter(t -> t.getId() == evtFix.getTeam_h()).collect(Collectors.toList());
        List<TeamModel> awayTeam =
                bootstrapStaticModel.getTeams().stream().filter(t -> t.getId() == evtFix.getTeam_a()).collect(Collectors.toList());

        var teams = new TeamsModel();
        teams.setHomeTeam(homeTeam.get(0));
        teams.setAwayTeam(awayTeam.get(0));

        getPlayers(bootstrapStaticModel, teams);
        evtFix.setTeams(teams);
    }

    private TeamsModel getPlayers(
            BootstrapStaticModel bootstrapStaticModel,
            TeamsModel teams
    ) {
        List<ElementModel> homeTeamPlayers =
                bootstrapStaticModel.getElements().stream().filter(elm -> elm.getTeam() == teams.getHomeTeam().getId()).collect(Collectors.toList());
        List<ElementModel> awayTeamPlayers =
                bootstrapStaticModel.getElements().stream().filter(elm -> elm.getTeam() == teams.getAwayTeam().getId()).collect(Collectors.toList());

        teams.getHomeTeam().setElements(homeTeamPlayers);
        teams.getAwayTeam().setElements(awayTeamPlayers);

        return teams;
    }


}