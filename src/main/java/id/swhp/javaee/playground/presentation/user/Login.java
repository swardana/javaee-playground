package id.swhp.javaee.playground.presentation.user;

import javax.enterprise.inject.Model;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Model
public class Login {

    private String username;
    private String password;
    private boolean remember;
    
    public void submit() {
        // TODO: process login here
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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
