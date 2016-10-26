package com.example.controller;

import com.example.model.Artist;
import com.example.model.Meta;
import com.example.model.Recommendation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class Controller {

    @Value("${application.name:default}")
    private String applicationName;

    @RequestMapping(value = "/recommendations")
    Recommendation getRecommendations() throws Exception {
        Recommendation recommendation = new Recommendation();
        recommendation.setMeta(new Meta(applicationName));
        recommendation.setRecommendations(getArtistRecommendations());
        Thread.sleep(new Random().nextInt(1000));
        return recommendation;
    }

    List<Artist> getArtistRecommendations() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist("51c6188d", "Solomon Burke"));
        artistList.add(new Artist("095d08a9", "Yann Tiersen"));
        artistList.add(new Artist("592029de", "Lizz Wright"));
        artistList.add(new Artist("4f3c89e7", "Aynur"));
        artistList.add(new Artist("b61bbc1a", "Alexi Murdoch"));
        return artistList;
    }
}
