package solutions.fluidity.test.premiershipapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import solutions.fluidity.test.controller.EventResource;
import solutions.fluidity.test.exception.BootStrapStaticException;
import solutions.fluidity.test.service.HttpRequestService;

import java.io.IOException;

@Component
public class Fixtures {

    @Value("${param.fixturesurl}")
    private String fixturesUrl;
    private HttpRequestService httpRequestService;
    private static Logger LOGGER = LoggerFactory.getLogger(Fixtures.class);

    public Fixtures(HttpRequestService httpRequestService) {
        this.httpRequestService = httpRequestService;
    }

    public String fixturesLookup() {

        try {
            Response response = httpRequestService.getExternalResponse(fixturesUrl);
            String responseBody = response.body().string();
            return responseBody;
        } catch (IOException e) {
            LOGGER.error("Failed to retrieve Fixtures Data", e);
            throw new BootStrapStaticException("Failed to retrieve Fixtures Data : " + e.getMessage());
        }


    }
}