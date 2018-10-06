package com.wgcorp.mywithingsweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MyWithingsWeightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyWithingsWeightController.class);

    @GetMapping("/login")
    public ResponseEntity<String> facebookLogin(@RequestParam("code") String code, @RequestParam("state") String state,
                                        HttpServletResponse httpServletResponse) {
        LOGGER.info("You are logged!");
        return ResponseEntity.ok("LOGGED");
    }
}
