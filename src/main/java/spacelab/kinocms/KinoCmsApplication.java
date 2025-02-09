package spacelab.kinocms;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KinoCmsApplication {



    public static void main(String[] args) {
        SpringApplication.run(KinoCmsApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
