package miw.s16.couch.couch;

import miw.s16.couch.couch.model.DBaccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CouchApplication {

    private DBaccess db = new DBaccess();
    public static void main(String[] args) {
        SpringApplication.run(CouchApplication.class, args);
    }

}
