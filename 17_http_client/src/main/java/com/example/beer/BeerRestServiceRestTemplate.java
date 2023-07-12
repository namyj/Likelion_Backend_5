package com.example.beer;

import com.example.beer.dto.BeerGetDto;
import com.example.beer.dto.BeerPostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class BeerRestServiceRestTemplate {
    // Get 요청 > String 응답
    public String getBeerString() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        String responseBody = restTemplate.getForObject(url, String.class);
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
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto dto = new BeerPostDto();
        
        // post 요청을 보낼 때 requestBody를 같이 전달
        String reponseBody = restTemplate.postForObject(
                url, // 요청 url
                dto, // requestBody
                String.class // 응답 해석 타입
        );

        log.info(reponseBody);
    }

    // Post > void
    public void postBeerVoid() {
        RestTemplate restTemplate = new RestTemplate();

        // 응답 Body 없이 응답하는 URL
        String url = "http://localhost:8081/give-me-beer-204";

        BeerPostDto dto = new BeerPostDto();
        dto.setName("Stella Artois");
        dto.setCc(2000L);
        dto.setAlcohol(5.0);

        ResponseEntity<Void> reponse = restTemplate.postForEntity(
                url,
                dto,
                Void.class
        );

        log.info(reponse.getStatusCode().toString());
    }
}
