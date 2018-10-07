package com.wgcorp.mywithingsweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MyWithingsWeightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyWithingsWeightController.class);

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping("/")
    public ResponseEntity<String> facebookLogin(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        LOGGER.info("You are logged!");
        return ResponseEntity.ok("LOGGED");
    }
}
