package com.microServices.OrderService.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final WebClient webClient;

    public OrderController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    @GetMapping("/{id}")
    public String createOrder(@PathVariable int id) {
        String product = webClient.get()
                .uri("/products/" + id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if (product != null) {
            return "Order placed successfully for product: " + product;
        } else {
            return "Product not found!";
        }
    }
}
