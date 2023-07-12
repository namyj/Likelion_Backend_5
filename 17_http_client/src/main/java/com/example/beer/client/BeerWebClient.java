package com.example.beer.client;

import com.example.beer.dto.BeerGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@Primary
@Qualifier("beerWebClient")
public class BeerWebClient implements BeerClient {
    public BeerGetDto getBeer() {
        log.info("BeerWebClient 실행");

        WebClient webClient = WebClient.builder().build();
        String url = "https://random-data-api.com/api/v2/beers";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(BeerGetDto.class)
                .block();
    }
}
