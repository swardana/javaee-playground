package id.swhp.javaee.playground.presentation.user;

import static org.omnifaces.util.Messages.addFlashGlobalInfo;

import id.swhp.javaee.playground.business.account.boundary.Registration;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

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

    @Inject
    Registration registration;

    public void process() {
        this.registration.createAccount(email, username, password);
        addFlashGlobalInfo("user.message.info.registered");
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
