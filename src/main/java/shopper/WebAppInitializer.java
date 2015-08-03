package shopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shivendra on 8/2/15.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class WebAppInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebAppInitializer.class, args);
    }
}

