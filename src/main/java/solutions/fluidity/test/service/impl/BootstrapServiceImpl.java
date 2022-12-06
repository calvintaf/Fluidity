package solutions.fluidity.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import solutions.fluidity.test.exception.BootStrapStaticException;
import solutions.fluidity.test.exception.FixturesException;
import solutions.fluidity.test.premiershipapi.BootstrapStatic;
import solutions.fluidity.test.service.BootstrapService;

import java.io.IOException;

@Service
public class BootstrapServiceImpl implements BootstrapService {

    private BootstrapStatic bootstrapStatic;
    private static Logger LOGGER = LoggerFactory.getLogger(BootstrapServiceImpl.class);

    public BootstrapServiceImpl(BootstrapStatic bootstrapStatic) {
        this.bootstrapStatic = bootstrapStatic;
    }

    @Override
    public String getBootstrapData() {

        try {
            String result = bootstrapStatic.lookup();
            return result;
        } catch (IOException e) {
            LOGGER.error("Failed to retrieve Bootstrap Static Data : " , e);
            throw new BootStrapStaticException("Failed to retrieve Bootstrap Static Data : " + e.getMessage());
        }
    }


    @Override
    public String getGameWeekFixtures(String gameWeekName) {

        try {
            String result = bootstrapStatic.lookup(gameWeekName);
            return result;
        } catch (IOException e) {
            LOGGER.error("Failed to retrieve Fixtures Data : " , e);
            throw new FixturesException("Failed to retrieve Fixtures Data : " + e.getMessage());
        }
    }
}