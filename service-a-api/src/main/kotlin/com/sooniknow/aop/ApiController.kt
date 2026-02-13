package com.sooniknow.aop

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api")
class ApiController {
    private val restTemplate = RestTemplate()

    @GetMapping("/hello")
    fun hello(): String {
        // Optionally call service-b-lol here
        // val response = restTemplate.getForObject("http://service-b-lol:8080/api/lol", String::class.java)
        // return "Hello from service-a-api! Got from service-b-lol: $response"
        return "Hello from service-a-api!"
    }
}
