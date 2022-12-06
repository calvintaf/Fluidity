package solutions.fluidity.test.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import solutions.fluidity.test.controller.FixturesResource;
import solutions.fluidity.test.exception.BootStrapStaticException;
import solutions.fluidity.test.model.boostrapstatic.BootstrapStaticModel;
import solutions.fluidity.test.service.BootstrapDataService;
import solutions.fluidity.test.service.HttpRequestService;

import java.io.IOException;

@Service
public class BootstrapDataServiceImpl implements BootstrapDataService {

    @Value("${param.bootstrapstaticurl}")
    private String bootstrapstaticUrl;
    private HttpRequestService httpRequestService;
    private static Logger LOGGER = LoggerFactory.getLogger(BootstrapDataServiceImpl.class);

    public BootstrapDataServiceImpl(HttpRequestService httpRequestService) {
        this.httpRequestService = httpRequestService;
    }

    @Override
    public BootstrapStaticModel getBootstrapStaticModelData() {

        try {
            Response response = httpRequestService.getExternalResponse(bootstrapstaticUrl);
            String responseBody = response.body().string();
            Gson gson = new GsonBuilder().create();
            BootstrapStaticModel bootstrapStaticModel = gson.fromJson(responseBody, BootstrapStaticModel.class);
            return bootstrapStaticModel;
        } catch (IOException e) {
            LOGGER.error("Failed to retrieve Bootstrap Data : ", e);
            throw new BootStrapStaticException("Failed to retrieve Bootstrap Data : " + e.getMessage());
        }

    }
}
