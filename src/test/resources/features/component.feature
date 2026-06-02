Feature: Trainee Registration

  Scenario: Register a new trainee
    Given the system is running
    When I register a trainee with first name "Bidzina" and last name "Kevkhishvili"
    Then the response should contain a username and password