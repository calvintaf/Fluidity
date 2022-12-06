package solutions.fluidity.test.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import solutions.fluidity.test.model.fixtures.FixtureModel;
import solutions.fluidity.test.service.ObjectParseService;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ObjectParseImpl implements ObjectParseService {

    @Override
    public List<FixtureModel> parseResponse(String responseBody)  {
        Gson gson = new GsonBuilder().create();
        Type listType  =  new TypeToken<List<FixtureModel>>() {}.getType();
        return gson.fromJson(responseBody, listType);
    }
}
