package solutions.fluidity.test.service;

import solutions.fluidity.test.model.dto.FixturesDto;

import java.io.IOException;
import java.util.List;

public interface FixturesService {
    List<FixturesDto> getFixturesCustom();

    String getFixtures() throws IOException;
}
