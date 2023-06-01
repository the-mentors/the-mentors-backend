package com.mentors.mentoring.recommend;

import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RecommendUrlBuilderService {

    private static final String RECOMMEND_URL = " http://localhost:5000/api/v1/recommends";
    private static final String TRAINING_URL = " http://localhost:5000/api/v1/training";


    public URI builderUriByRecommend(Long userId){
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(RECOMMEND_URL);
        uriBuilder.queryParam("user_id", userId);
        return uriBuilder.build().encode().toUri();
    }

    public URI builderUriByTraining(Long userId){
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(TRAINING_URL);
        uriBuilder.queryParam("user_id", userId);
        return uriBuilder.build().encode().toUri();
    }
}
