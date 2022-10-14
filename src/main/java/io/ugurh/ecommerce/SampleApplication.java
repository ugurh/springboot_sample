package io.ugurh.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.PropertySource;

/**
 * @author harun ugur
 */
@SpringBootApplication
//@PropertySource("classpath:application-${spring.profiles.active}.yml")
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SampleApplication.class);
        //creates a pid file at start
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}
