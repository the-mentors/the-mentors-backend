package com.mentors.mentoring.recommend;


import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RecommendApiComponent {

    private final RestTemplate restTemplate;
    private final RecommendUrlBuilderService recommendUrlBuilderService;

    public void training(Long userId){
        URI uri = recommendUrlBuilderService.builderUriByTraining(userId);
        restTemplate.exchange(uri, HttpMethod.GET, null, Void.class);
    }

    public RecommendResponse recommend(Long userId){
        URI uri = recommendUrlBuilderService.builderUriByRecommend(userId);
        return restTemplate.exchange(uri, HttpMethod.GET, null, RecommendResponse.class).getBody();
    }
}
