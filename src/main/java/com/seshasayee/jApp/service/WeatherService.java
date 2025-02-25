package com.seshasayee.jApp.service;

import com.seshasayee.jApp.api.response.WeatherResponse;
import com.seshasayee.jApp.cache.AppCache;
import com.seshasayee.jApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;
    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null){
            return weatherResponse;
        }
        else{
            String finalAPI = appCache.appCACHE.get(AppCache.Keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apiKey);
            ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalAPI , HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse weatherResponseEntityBody = responseEntity.getBody();
            if (weatherResponseEntityBody != null){
                  redisService.set("weather_of_" + city,weatherResponseEntityBody,300l);
            }
            return weatherResponseEntityBody;
        }


    }

}
