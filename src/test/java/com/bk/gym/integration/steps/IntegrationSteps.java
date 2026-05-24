package com.bk.gym.integration.steps;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
public class IntegrationSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private String trainerUsername = "Bidzina.Kevkhishvili";

    @Given("the gym-crm and trainer-workload-service are running")
    public void services_are_running() {
        assertEquals(HttpStatus.OK, restTemplate.getForEntity("http://localhost:8081/actuator/health", String.class).getStatusCode());
        assertEquals(HttpStatus.OK, restTemplate.getForEntity("http://localhost:8082/actuator/health", String.class).getStatusCode());
    }

    @When("I add a training session for trainer {string}")
    public void i_add_training_session(String username) {
        String json = String.format("{\"trainerUsername\":\"%s\",\"firstName\":\"Bidzina\",\"lastName\":\"Kevkhishvili\",\"isActive\":true,\"trainingDate\":\"2024-05-01\",\"trainingDuration\":60,\"actionType\":\"ADD\"}", username);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForEntity("http://localhost:8081/api/training", new HttpEntity<>(json, headers), String.class);
    }

    @Then("the trainer workload summary for {string} should be updated")
    public void trainer_workload_should_be_updated(String username) {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8082/api/workload/" + username + "/2024/5", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Bidzina"));
        assertTrue(response.getBody().contains("Kevkhishvili"));
        assertTrue(response.getBody().contains("60"));
    }
}
