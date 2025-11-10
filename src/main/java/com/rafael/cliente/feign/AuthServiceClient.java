package com.rafael.cliente.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "${auth-service.url}")
public interface AuthServiceClient {

    @GetMapping("/api/auth/user/{username}")
    ResponseEntity<Void> checkUserExists(@PathVariable("username") String username);

}
