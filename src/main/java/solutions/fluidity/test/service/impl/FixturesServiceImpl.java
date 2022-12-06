package solutions.fluidity.test.service.impl;

import org.springframework.stereotype.Service;
import solutions.fluidity.test.premiershipapi.Fixtures;
import solutions.fluidity.test.model.boostrapstatic.BootstrapStaticModel;
import solutions.fluidity.test.model.boostrapstatic.TeamModel;
import solutions.fluidity.test.model.fixtures.FixtureModel;
import solutions.fluidity.test.model.dto.FixturesDto;
import solutions.fluidity.test.service.BootstrapDataService;
import solutions.fluidity.test.service.FixturesService;
import solutions.fluidity.test.service.ObjectParseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FixturesServiceImpl implements FixturesService {
    private Fixtures fixtures;
    private ObjectParseService objectParseService;
    private BootstrapDataService bootstrapDataService;

    public FixturesServiceImpl(Fixtures fixtures, ObjectParseService objectParseService, BootstrapDataService bootstrapDataService) {
        this.fixtures = fixtures;
        this.objectParseService = objectParseService;
        this.bootstrapDataService = bootstrapDataService;
    }

    @Override
    public List<FixturesDto> getFixturesCustom() {
        String result = fixtures.fixturesLookup();
        List<FixtureModel> fixtureModel = objectParseService.parseResponse(result);
        BootstrapStaticModel bootstrapStaticModelData = bootstrapDataService.getBootstrapStaticModelData();
        List<TeamModel> teamModels = bootstrapStaticModelData.getTeams();
        List<FixturesDto> fixturesDtoList = fixtureModel.stream().map(fixtureModel1 -> new FixturesDto(
                fixtureModel1.getEvent(),
                fixtureModel1.getCode(),
                fixtureModel1.getId(),
                fixtureModel1.getTeam_h(),
                fixtureModel1.getTeam_a(),
                teamModels.stream().filter(teamModel -> teamModel.getId() == fixtureModel1.getTeam_h()).collect(Collectors.toList()).get(0).getName(),
                teamModels.stream().filter(teamModel -> teamModel.getId() == fixtureModel1.getTeam_a()).collect(Collectors.toList()).get(0).getName())
        ).collect(Collectors.toList());
        return fixturesDtoList;
    }

    @Override
    public String getFixtures(){
        String result = fixtures.fixturesLookup();
        return result;
    }
}