package com.tts.WeatherApp;

import com.tts.WeatherApp.domain.ZipCode;
import com.tts.WeatherApp.repository.ZipCodeRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {
    @Value("${api_key}")
    private String apiKey;

    @Autowired
    private ZipCodeRepository repo;

    public Response getForecast(String zipCode) {

        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
                    zipCode + "&units=imperial&appid=" + apiKey;
            RestTemplate restTemplate = new RestTemplate();
            Response myResp = restTemplate.getForObject(url, Response.class);
            ZipCode zippy = new ZipCode(zipCode, myResp.getName());
            repo.save(zippy);
            return myResp;
        }

        catch (HttpClientErrorException ex) {
            Response response = new Response();
            response.setName("error");
            return response;
        }

    }

}
