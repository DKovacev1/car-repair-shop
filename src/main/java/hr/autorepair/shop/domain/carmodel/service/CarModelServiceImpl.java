package hr.autorepair.shop.domain.carmodel.service;

import hr.autorepair.shop.domain.carmodel.dto.CarModelResponse;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.util.AppProperties;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarModelServiceImpl implements CarModelService{

    private final HttpClient httpClient;
    private final AppProperties appProperties;

    @Override
    public List<CarModelResponse> getCarModels(BigDecimal idCarMaker) {
        String url = "https://car-api2.p.rapidapi.com/api/models?verbose=yes&sort=id&year=2020&direction=asc";
        if(idCarMaker != null)
            url += "&make_id=" + idCarMaker;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-ua", "RapidAPI-Playground")
                .header("x-rapidapi-key", appProperties.getProperty("rapidapi.key"))
                .header("x-rapidapi-host", "car-api2.p.rapidapi.com")
                .GET()
                .build();

        List<CarModelResponse> carModels = new ArrayList<>();

        try{
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (httpResponse.statusCode() == 200) {
                JSONObject responseJson = new JSONObject(httpResponse.body());
                JSONArray dataArray = responseJson.getJSONArray("data");

                for(int i = 0; i < dataArray.length(); i++){
                    JSONObject data = dataArray.getJSONObject(i);
                    CarModelResponse response = new CarModelResponse();
                    response.setIdCarModel(data.getBigDecimal("id"));
                    response.setName(data.getString("name"));
                    carModels.add(response);
                }
            }
            else
                throw new BadRequestException("Unexpected error occurred while contacting rapid api");
        } catch (IOException | InterruptedException e) {
            System.err.println("Unexpected error occurred while contacting rapid api");
            throw new RuntimeException(e);
        }

        return carModels;
    }

}
