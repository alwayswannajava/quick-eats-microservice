package com.gatewayserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class FallbackController {
    private static final String FALLBACK_MESSAGE = "An error occurred. Please try again later or contact our support team";

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        log.debug("Fallback is invoked...");
        return Mono.just(FALLBACK_MESSAGE);
    }
}
