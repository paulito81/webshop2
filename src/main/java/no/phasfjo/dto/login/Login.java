package no.phasfjo.dto.login;

import no.phasfjo.constraint.Password;
import no.phasfjo.constraint.UserName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by paulhasfjord on 17.01.2017.
 */

@Entity
@SequenceGenerator(name = "SEQ_LOG", initialValue = 50)
public class Login {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOG")
    @Column(name = "FK_LOGIN")
    private int Id;
    @NotNull
    @UserName
    private String username;
    @NotNull
    @Password
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login() {

    }

    // ======================================
    // =            GET AND SET            =
    // ======================================


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // ======================================
    // =           TO STRING           =
    // ======================================

    public String toString2() {
        return "'{' id= " + Id +
                String.format("%" + username.length() + "s", "").replace(' ', '*')
                + String.format("%" + password.length() + "s", "").replace(' ', '*') + '\'' +
                '}';

    }

    @Override
    public String toString() {
        return "Login{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
