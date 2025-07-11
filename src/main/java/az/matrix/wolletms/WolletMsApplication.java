package az.matrix.wolletms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WolletMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WolletMsApplication.class, args);
    }

}
