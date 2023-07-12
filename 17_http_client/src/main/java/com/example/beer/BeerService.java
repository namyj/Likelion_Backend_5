package com.example.beer;

import com.example.beer.client.BeerClient;
import com.example.beer.dto.BeerGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeerService {
    private final BeerClient client;

    public BeerService(@Qualifier("beerWebClient") BeerClient client) {
        this.client = client;
    }

    public void drinkBeer() {
        log.info("order beer");
        BeerGetDto result = client.getBeer();
        log.info("{}는 맛있다", result.getName());
    }
}
