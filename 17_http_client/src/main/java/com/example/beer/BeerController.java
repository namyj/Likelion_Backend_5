package com.example.beer;

import com.example.beer.dto.BeerDto;
import com.example.beer.dto.BeerGetDto;
import com.example.beer.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeerController {
    // RestTemplate 사용
    // private final BeerRestServiceRestTemplate service;
    // WebClient
    private final BeerRestServiceWebClient service;

    // interface DI
    private final BeerService DIService;

    @GetMapping("/get")
    public void getBeer() {
        DIService.drinkBeer();
    }

    @GetMapping("/get-beer-string")
    public String getBeerString() {
        return service.getBeerString();
    }

    @GetMapping("/get-beer-dto")
    public BeerGetDto getBeerObject() {
        return service.getBeerObject();
    }

    @GetMapping("/get-beer-entity")
    public ResponseEntity getBeerEntity() {
        return service.getBeerEntity();
    }

    @PostMapping("/give-string")
    public void give() {
        service.postBeerString();
    }

    @PostMapping("/give-entity")
    public MessageDto giveEntity() {
        return service.postBeer();
    }

    @PostMapping("/give-204")
    public void give204() {
        service.postBeerVoid();
    }

    @PostMapping("/give-me-beer")
    public MessageDto giveMeBeer(@RequestBody BeerDto beerDto) {
        log.info(beerDto.toString());
        log.info("꺼어어억");
        return new MessageDto("꺼어어억");
    }

    @PostMapping("/give-me-beer-204")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void giveMeBeer204(@RequestBody BeerDto beerDto) {
        log.info(beerDto.toString());
        log.info("꺼어어억");
    }
}
