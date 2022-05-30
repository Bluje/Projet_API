package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.weather.OpenWeather;
import fr.ensim.interop.introrest.model.weather.Weather;
import fr.ensim.interop.introrest.model.weather.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

public class OpenWeatherRestController {

    @Value("${open.weather.api.token}")
    private String weatherApiKey;


    @GetMapping(value = "/meteo", params = {"lat", "long"})
    public ResponseEntity<OpenWeather> meteo(
            @RequestParam("lat") String lat,
            @RequestParam("long") String longitude) {

        RestTemplate restTemplate = new RestTemplate();
        OpenWeather openWeather = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?lat={lat}"
                        + "&lon={longitude}&appid=" + weatherApiKey,
                OpenWeather.class, lat, longitude);

        return ResponseEntity.ok().body(openWeather);
    }

    @GetMapping("/position")
    public ResponseEntity<City> getCoord(
            @RequestParam("ville") String nomVille) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<City[]> responseEntity = restTemplate.getForEntity("http://api.openweathermap.org/geo/1.0/direct?q={ville}&limit=3"
                        + "&appid=" + weatherApiKey,
                City[].class, nomVille);
        City[] cities = responseEntity.getBody();
        City city = cities[0];

        return ResponseEntity.ok().body(city);
    }

}
