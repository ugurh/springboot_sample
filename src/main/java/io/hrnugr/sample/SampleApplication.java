package io.hrnugr.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * @author harun ugur
 */
@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SampleApplication.class);
        //creates a pid file at start
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}
