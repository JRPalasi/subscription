package com.adidas.subscription;

import java.time.LocalDate;

import com.adidas.subscription.entity.Gender;
import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.service.repository.SubscriptionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Populates the database with mock data on the application startup
 */
@Configuration
public class DatabaseInitializer {
 
    private static final Logger LOGGER = LoggerFactory.getLogger( DatabaseInitializer.class);

    /**
     * Defines the commands for populating the database with mock data
     * @param repository SubscriptionRepository instance for populating the database
     * @return commadnds to be run
     */
    @Bean
    CommandLineRunner initDatabase( SubscriptionRepository repository){
        return args -> {
            LOGGER.info( "Initializing database");
            repository.save( new Subscription( "Jose Ramon", "jrpalasi@adidas.com", Gender.MALE, LocalDate.of(1983, 3, 5), true, 1L));
            repository.save( new Subscription( "Juan", "juan@adidas.com", Gender.MALE, LocalDate.of(1981, 9, 28), true, 1L));
            repository.save( new Subscription( "Esther", "esther@adidas.com", Gender.FEMALE, LocalDate.of(1973, 2, 13), true, 1L));
            repository.save( new Subscription( "Vanessa", "vanessa@adidas.com", Gender.FEMALE, LocalDate.of(1987, 1, 25), true, 1L));
            LOGGER.info( "Database initialized");
        };
    }

}
