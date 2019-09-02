package miw.s16.couch.couch.model;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String userName;
    private String userPassword;

    // empty constructor
    public User() {
       super();
    }

    public User(String userName, String userPassword) {
        super();
        this.userName = userName;
        this.userPassword = userPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
