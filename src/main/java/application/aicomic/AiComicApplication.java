package application.aicomic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "application.aicomic")
public class AiComicApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiComicApplication.class, args);
    }

}
