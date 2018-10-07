package com.wgcorp.mywithingsweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.TimeZone;

@RestController
public class MyWithingsWeightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyWithingsWeightController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OAuth2AuthorizedClientService clientService;

    @GetMapping("/")
    public ResponseEntity<String> checklogin(OAuth2AuthenticationToken authentication) {
        LOGGER.info("You are logged!");

        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        long startDate = LocalDateTime.of(2018, Month.JANUARY, 1, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();
        long endDate = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();

        URI getMeasureUri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("wbsapi.withings.net")
                .path("measure")
                .query("action=getmeas&access_token={access_token}&meastype=1&category=1&startdate={startdate}&enddate={enddate}")
                .buildAndExpand(client.getAccessToken().getTokenValue(), startDate, endDate)
                .toUri();

        return restTemplate.getForEntity(getMeasureUri, String.class);
    }
}
