package miw.s16.couch.couch.model;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String userName;
    private String userPassword;

    @OneToOne(mappedBy="Retail")
    private Retail retail;


    public User() {
        this("", "", 100);
    }

    public User(String userName, String userPassword) {
        this(userName, userPassword, 0);
    }

    public User(String userName, String userPassword, int userId) {
        super();
        this.userName = userName;
        this.userPassword = userPassword;
        this.userId = userId;
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
