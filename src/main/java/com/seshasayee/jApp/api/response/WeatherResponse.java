package com.seshasayee.jApp.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class   WeatherResponse{

    @JsonProperty("current")
    private Current current;
    @JsonProperty("location")
    private Location location;

    @Getter
    @Setter
    public class Current {
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("wind_dir")
        private String windDir;
        private int pressure;
        private int precip;
        private int humidity;
        @JsonProperty("cloudcover")
        private int cloudCover;
        @JsonProperty("feelslike")
        private int feelsLike;
        @JsonProperty("uv_index")
        private int uvIndex;
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;

        @Override
        public String toString() {
            return
                    "\nWeather :\n{" + "observationTime='" + observationTime + '\'' +
                    ", temperature=" + temperature +
                    ", weatherCode=" + weatherCode +
                    ", weatherDescriptions=" + weatherDescriptions +
                    ", windSpeed=" + windSpeed +
                    ", windDegree=" + windDegree +
                    ", windDirection='" + windDir + '\'' +
                    ", pressure=" + pressure +
                    ", precipitation=" + precip +
                    ", humidity=" + humidity +
                    ", cloudCover=" + cloudCover +
                    ", feelsLike=" + feelsLike +
                    ", uvIndex=" + uvIndex +
                    ", visibility=" + visibility +
                    ", isDay='" + isDay + '\'' +
                    '}';
        }
    }
    @Getter
    @Setter
    public class Location{
        public String name;
        public String country;
        public String region;

        @Override
        public String toString() {
            return "\nLocation :\n{" +
                    "name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    ", region='" + region + '\'' +
                    '}';
        }
    }


}





