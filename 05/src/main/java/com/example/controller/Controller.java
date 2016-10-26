package com.example.controller;

import com.config.RecoEngineRibbonConfig;
import com.example.model.Artist;
import com.example.model.Meta;
import com.example.model.Recommendation;
import com.example.service.RecoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RibbonClient(name = "reco-engine", configuration = RecoEngineRibbonConfig.class)
public class Controller {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RecoService recoService;

    @RequestMapping("/my-recommendations")
    public Recommendation get() {
        return recoService.getRecommendations();
    }


}
