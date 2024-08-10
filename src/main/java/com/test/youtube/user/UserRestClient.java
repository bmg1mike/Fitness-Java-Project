package com.test.youtube.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserRestClient {
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
    
    public List<User> getUsers() {
        return this.restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<List<User>>() {
                });
    }

    public User getUserById(Integer id) {
        return restClient.get()
        .uri("/users/{id}", id)
        .retrieve()
                .body(User.class);
    }
}
