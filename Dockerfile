FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY target/gym-*.jar app.jar
EXPOSE 8081
ENV SPRING_PROFILES_ACTIVE=docker
ENV EUREKA_CLIENT_ENABLED=false
ENV SPRING_ACTIVEMQ_BROKER-URL=disabled
ENV SPRING_DATA_MONGODB_URI=disabled
ENTRYPOINT ["java", "-jar", "app.jar"]