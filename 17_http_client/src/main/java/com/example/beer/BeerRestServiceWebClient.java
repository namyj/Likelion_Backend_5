package com.example.beer;

import com.example.beer.dto.BeerGetDto;
import com.example.beer.dto.BeerPostDto;
import com.example.beer.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class BeerRestServiceWebClient {
    // Get 요청 > String 응답
    public String getBeerString() {
        WebClient webClient = WebClient.builder().build();
        String url = "https://random-data-api.com/api/v2/beers";

        String responseBody = webClient.get()
                .uri(url) // 요청 경로
                .header("x-test", "header") // 요청 헤더 추가
                .retrieve()  
                .bodyToMono(String.class) // 응답의 body 정의
                .block(); // 동기식 처리
        log.info(responseBody);

        return responseBody;
    }

    // Get 요청 > Dto 응답
    public BeerGetDto getBeerObject() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        BeerGetDto responseBody = restTemplate.getForObject(url, BeerGetDto.class);
        log.info(responseBody.toString());

        return responseBody;
    }

    // Get 요청 > Entity
    public ResponseEntity getBeerEntity() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // ResponseEntity 클래스 사용
        ResponseEntity<BeerGetDto> response = restTemplate.getForEntity(url, BeerGetDto.class);

        log.info(response.getStatusCode().toString());
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());
        return response;
    }

    // Post > String
    public void postBeerString() {
    }

    public MessageDto postBeer() {
        WebClient webClient = WebClient.builder().build();
        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto dto = new BeerPostDto();
        dto.setName("Stella Artois");
        dto.setCc(2000L);
        dto.setAlcohol(5.0);

        MessageDto responseBody = webClient.post()
                        .uri(url)
                        .bodyValue(dto) // requestBody 정의
                        .retrieve() // 응답 정의 시작
                        .bodyToMono(MessageDto.class) // 응답 타입 정의
                        .block(); // 동기식 처리

        log.info(responseBody.toString());

        return responseBody;
    }

    // Post > void
    public void postBeerVoid() {
        WebClient webClient = WebClient.builder().build();

        // 응답 Body 없이 응답하는 URL
        String url = "http://localhost:8081/give-me-beer-204";

        BeerPostDto dto = new BeerPostDto();
        // dto.setName("Stella Artois");
        // dto.setCc(2000L);
        // dto.setAlcohol(5.0);

        ResponseEntity<Void> reponse = webClient.post()
                .uri(url)
                .bodyValue(dto) 
                .retrieve() 
                .toBodilessEntity() // 응답 Body가 없을 경우 사용
                .block(); 

        log.info(reponse.getStatusCode().toString());
    }


}
