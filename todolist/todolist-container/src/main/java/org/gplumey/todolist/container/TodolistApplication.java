package org.gplumey.todolist.container;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.gplumey.todolist.container.observability.AbstractObserveAroundMethodHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication(scanBasePackages = "org.gplumey.todolist")
@EnableWebSecurity
@EnableMethodSecurity
@EnableScheduling

public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Your API Title")
                                .description("Your API Description")
                                .version("1.0"));
    }

    @Bean
    SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) ->
                authorize.
                        requestMatchers("/**").permitAll()
                        .anyRequest().authenticated());
        //http.cors().disable();
        http.csrf().disable();
        http.headers().httpStrictTransportSecurity().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.httpBasic().disable();

        return http.build();
    }


    @Bean
    ObservationRegistry observationRegistry() {
        CompositeMeterRegistry compositeRegistry = new CompositeMeterRegistry();
        MeterRegistry simpleMeterRegistry = new SimpleMeterRegistry();

        MeterRegistry loggingMeterRegistry = new LoggingMeterRegistry();
        compositeRegistry.add(simpleMeterRegistry);
        compositeRegistry.add(loggingMeterRegistry);
        ObservationRegistry registry = ObservationRegistry.create();
        registry.observationConfig().observationHandler(new DefaultMeterObservationHandler(compositeRegistry));
        registry.observationConfig().observationHandler(new AbstractObserveAroundMethodHandler());
        return registry;
    }

    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }
}
