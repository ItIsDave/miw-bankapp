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
    private int roleId;

    // empty constructor
    public User() {
       super();
    }

    //roleId is 1, 2 oe 3 depending if user is a RetailUser, SMEUser or BankUser respectively
    public User(String userName, String userPassword, int roleId) {
        super();
        this.userName = userName;
        this.userPassword = userPassword;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
