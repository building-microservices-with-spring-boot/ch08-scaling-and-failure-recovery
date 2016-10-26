package com.example.service;

import com.example.model.Artist;
import com.example.model.Meta;
import com.example.model.Recommendation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecoService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultRecommendations",
            commandKey = "recommendationCommand")
    public Recommendation getRecommendations() {
        return this.restTemplate.getForObject("http://reco-engine/recommendations", Recommendation.class);
    }


    Recommendation getDefaultRecommendations() {
        Recommendation recommendation = new Recommendation();
        recommendation.setMeta(new Meta("default"));
        recommendation.setRecommendations(getArtistRecommendations());
        return recommendation;
    }

    private List<Artist> getArtistRecommendations() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist("2f504a21", "Adele"));
        artistList.add(new Artist("8c1d906e", "Duman"));
        artistList.add(new Artist("5d164854", "The Beatles"));
        return artistList;
    }
}
