package com.thomasvitale.edgeservice;

import com.thomasvitale.edgeservice.WelcomeController.Welcome;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

@WebFluxTest(WelcomeController.class)
public class WelcomeControllerTests {

    @Autowired
    WebTestClient webClient;

    @MockBean
    ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Test
    void whenNotAuthenticatedThen401() {
        webClient
                .get().uri("/welcome")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void whenAuthenticatedThenReturnPrincipal() {
		var expectedWelcome = new Welcome("Welcome to Polar Bookshop!");
        webClient
                .mutateWith(mockOidcLogin())
                .get().uri("/welcome")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Welcome.class).isEqualTo(expectedWelcome);
    }

}
