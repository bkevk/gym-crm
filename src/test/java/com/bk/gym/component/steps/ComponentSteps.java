package com.bk.gym.component.steps;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration
public class ComponentSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("the system is running")
    public void the_system_is_running() {
        ResponseEntity<String> health = restTemplate.getForEntity("/actuator/health", String.class);
        assertEquals(HttpStatus.OK, health.getStatusCode());
    }

    @When("I register a trainee with first name {string} and last name {string}")
    public void i_register_a_trainee(String firstName, String lastName) {
        String json = String.format("{\"firstName\":\"%s\",\"lastName\":\"%s\"}", firstName, lastName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response = restTemplate.postForEntity("/api/trainee/register", new HttpEntity<>(json, headers), String.class);
    }

    @Then("the response should contain a username and password")
    public void the_response_should_contain_username_and_password() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("username"));
        assertTrue(response.getBody().contains("password"));
    }
}
