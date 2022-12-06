package solutions.fluidity.test.service;

import okhttp3.Response;

import java.io.IOException;

public interface HttpRequestService {
    Response getExternalResponse(String fixturesUri) throws IOException;
}
