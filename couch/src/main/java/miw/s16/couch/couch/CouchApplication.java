package miw.s16.couch.couch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("miw.s16.couch.couch")
@EntityScan("miw.s16.couch.couch.model")
@SpringBootApplication

public class CouchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouchApplication.class, args);
    }

}
