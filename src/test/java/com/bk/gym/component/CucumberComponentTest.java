package com.bk.gym.component;

import com.bk.gym.GymApp;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.bk.gym.component"
)
@SpringBootTest(classes = GymApp.class)
public class CucumberComponentTest {}
