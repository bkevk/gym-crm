<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Gym CRM Microservices Project</title>
</head>
<body>

<h1>Gym CRM Microservices Project</h1>

<h2>Overview</h2>
<p>
This project is a microservices-based Gym CRM system, designed to manage trainers, trainees, training sessions, and trainer workload analytics. It consists of multiple Spring Boot microservices, integrated via Eureka Discovery Service, secured with JWT, and instrumented with logging, metrics, and circuit breaker patterns.
</p>

<hr>

<h2>Modules</h2>

<h3>1. <b>Gym CRM Microservice (<code>gym-crm</code>)</b></h3>
<ul>
  <li><b>Purpose:</b> Manages core gym operations: trainer and trainee registration, authentication, profile management, training session scheduling, and deletion.</li>
  <li><b>Features:</b>
    <ul>
      <li>REST API for CRUD operations on trainers, trainees, and trainings.</li>
      <li>JWT-based authentication and authorization.</li>
      <li>Brute force protection for login.</li>
      <li>Passwords stored securely with salt and hashing (BCrypt).</li>
      <li>Calls the Trainer Workload Microservice via Feign client whenever a training is added or deleted.</li>
      <li>Transaction-level logging with transactionId.</li>
      <li>Circuit breaker integration for workload microservice calls.</li>
      <li>CORS policy configuration.</li>
      <li>Exposes metrics and health endpoints via Spring Boot Actuator.</li>
    </ul>
  </li>
  <li><b>Port:</b> <code>8081</code></li>
</ul>

<h3>2. <b>Trainer Workload Microservice (<code>trainer-workload-service</code>)</b></h3>
<ul>
  <li><b>Purpose:</b> Tracks and calculates the monthly working hours of each trainer based on training sessions.</li>
  <li><b>Features:</b>
    <ul>
      <li>REST API to accept workload updates (add/delete training session).</li>
      <li>Calculates and stores monthly workload summaries in an in-memory H2 database.</li>
      <li>Provides endpoints to query trainer workload by month.</li>
      <li>Secured with JWT Bearer token.</li>
      <li>Transaction-level logging with transactionId.</li>
      <li>Registered with Eureka for service discovery.</li>
    </ul>
  </li>
  <li><b>Port:</b> <code>8082</code></li>
</ul>

<h3>3. <b>Eureka Discovery Service (<code>eureka-server</code>)</b></h3>
<ul>
  <li><b>Purpose:</b> Enables service discovery for all microservices.</li>
  <li><b>Features:</b>
    <ul>
      <li>Central registry for microservices.</li>
      <li>Allows dynamic lookup and load balancing.</li>
    </ul>
  </li>
  <li><b>Port:</b> <code>8761</code></li>
</ul>

<hr>

<h2>How to Run</h2>

<h3>Prerequisites</h3>
<ul>
  <li>Java 11+ and Maven installed.</li>
  <li>Ports: 8761 (Eureka), 8081 (<code>gym-crm</code>), 8082 (<code>trainer-workload-service</code>) by default.</li>
</ul>

<h3>1. Start Eureka Server</h3>
<pre><code>cd eureka-server
mvn spring-boot:run
</code></pre>
<p>Eureka dashboard available at <a href="http://localhost:8761" target="_blank">http://localhost:8761</a></p>

<h3>2. Start Trainer Workload Microservice</h3>
<pre><code>cd trainer-workload-service
mvn spring-boot:run
</code></pre>

<h3>3. Start Gym CRM Microservice</h3>
<pre><code>cd gym-crm
mvn spring-boot:run
</code></pre>

<h3>4. Access APIs</h3>
<ul>
  <li><b>Gym CRM Microservice:</b> <a href="http://localhost:8081/api" target="_blank">http://localhost:8081/api</a></li>
  <li><b>Trainer Workload Microservice:</b> <a href="http://localhost:8082/api/workload" target="_blank">http://localhost:8082/api/workload</a></li>
  <li><b>Swagger UI:</b> <a href="http://localhost:8081/swagger-ui.html" target="_blank">http://localhost:8081/swagger-ui.html</a> (gym-crm), <a href="http://localhost:8082/swagger-ui.html" target="_blank">http://localhost:8082/swagger-ui.html</a> (workload)</li>
</ul>

<hr>

<h2>Authentication & Security</h2>
<ul>
  <li>All endpoints except registration and login require JWT Bearer token.</li>
  <li>Passwords are hashed with BCrypt.</li>
  <li>Brute force protection blocks users for 5 minutes after 3 failed logins.</li>
  <li>JWT and transactionId are passed between microservices for security and logging.</li>
</ul>

<hr>

<h2>Logging & Monitoring</h2>
<ul>
  <li>Transaction-level logging with transactionId for traceability.</li>
  <li>Metrics and health endpoints exposed via Spring Boot Actuator.</li>
  <li>Custom health indicators and Prometheus metrics available.</li>
</ul>

<hr>

<h2>Service Discovery & Resilience</h2>
<ul>
  <li>All microservices register with Eureka for discovery.</li>
  <li>Gym CRM microservice uses Feign client with circuit breaker (Resilience4j) to call workload microservice.</li>
</ul>

<hr>

<h2>Example API Calls</h2>
<ul>
  <li><b>Register Trainee:</b> <code>POST /api/trainee/register</code></li>
  <li><b>Register Trainer:</b> <code>POST /api/trainer/register</code></li>
  <li><b>Login:</b> <code>GET /api/login?username=...&password=...</code></li>
  <li><b>Add Training:</b> <code>POST /api/training</code></li>
  <li><b>Delete Training:</b> <code>DELETE /api/training/{id}</code></li>
  <li><b>Update Trainer Workload:</b> Called automatically by gym-crm microservice via Feign client.</li>
</ul>

<hr>

<h2>Development & Testing</h2>
<ul>
  <li>Use <code>application-local.properties</code>, <code>application-dev.properties</code>, etc. for environment-specific configuration.</li>
  <li>Unit and integration tests are provided in each module’s <code>src/test/java</code> directory.</li>
</ul>

<hr>

<h2>Contributing</h2>
<ol>
  <li>Fork the repository.</li>
  <li>Create a feature branch.</li>
  <li>Commit your changes.</li>
  <li>Submit a pull request.</li>
</ol>

<hr>

<h2>License</h2>
<p>This project is licensed under the MIT License.</p>

<hr>

<p><b>For more details, see the Swagger UI or consult the code documentation. If you have questions, open an issue or contact the maintainers.</b></p>

</body>
</html>