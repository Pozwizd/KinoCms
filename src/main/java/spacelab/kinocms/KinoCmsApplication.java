package spacelab.kinocms;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootApplication
public class KinoCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinoCmsApplication.class, args);
    }

}
