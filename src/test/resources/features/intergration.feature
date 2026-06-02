Feature: Training Event Integration

  Scenario: Add training and update trainer workload
    Given the gym-crm and trainer-workload-service are running
    When I add a training session for trainer "Bidzina.Kevkhishvili"
    Then the trainer workload summary for "Bidzina.Kevkhishvili" should be updated