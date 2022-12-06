package solutions.fluidity.test.service;

import solutions.fluidity.test.model.fixtures.FixtureModel;

import java.util.List;

public interface ObjectParseService {
    List<FixtureModel> parseResponse(String responseBody);
}
