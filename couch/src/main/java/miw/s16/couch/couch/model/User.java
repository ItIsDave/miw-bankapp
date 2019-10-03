package miw.s16.couch.couch.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import miw.s16.couch.couch.model.constraints.UsernameDoesNotExistConstraint;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAutoConfiguration

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User  {
   @Id
    @GeneratedValue
    private int userId;
    @Column(name = "userName", unique = true)
    @NotEmpty
    @UsernameDoesNotExistConstraint(message = "Gebruikersnaam is al in gebruik.")
    private String userName;
    @NotEmpty
    private String userPassword;

    // empty constructor
    public User() {
        this("","");
    }

    public User(@NotEmpty String userName, @NotEmpty String userPassword) {
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

