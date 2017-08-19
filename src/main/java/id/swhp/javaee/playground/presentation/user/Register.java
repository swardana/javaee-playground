package id.swhp.javaee.playground.presentation.user;

import javax.enterprise.inject.Model;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Model
public class Register {

    private String email;
    private String username;
    private String password;

    public void process() {
        // TODO: Process registration user in here.
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
