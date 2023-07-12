package com.example.beer.client;

import com.example.beer.dto.BeerGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@Qualifier("beerRestClient")
public class BeerRestClient implements BeerClient {
    // BeerRestService -> getBeerObject() 메서드에서 return type만 BeerGetDto로 수정
    public BeerGetDto getBeer() {
        log.info("BeerRestClient 실행");

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";
        return restTemplate.getForObject(url, BeerGetDto.class);
    }
}
