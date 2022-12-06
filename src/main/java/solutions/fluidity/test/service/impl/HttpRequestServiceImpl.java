package solutions.fluidity.test.service.impl;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import solutions.fluidity.test.service.HttpRequestService;

import java.io.IOException;

@Service
public class HttpRequestServiceImpl implements HttpRequestService {
    @Value("${param.accept}")
    private String accept;
    @Value("${param.useragent}")
    private String useragent;

    @Override
    public Response getExternalResponse(String fixturesUri) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(fixturesUri)
                .header("Accept", accept)
                .header("User-Agent", useragent).build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
