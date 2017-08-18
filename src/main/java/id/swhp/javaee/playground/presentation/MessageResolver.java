package id.swhp.javaee.playground.presentation;

import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static org.omnifaces.util.Faces.getLocale;

import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import org.omnifaces.cdi.Startup;
import org.omnifaces.util.Messages;

/**
 * Made JSF FacesMessage can read from Resource Bundle.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Startup
public class MessageResolver {

    @PostConstruct
    public void init() {
        configureMessageResolver();
    }

    private void configureMessageResolver() {
        Messages.setResolver(new Messages.Resolver() {

            private static final String BASE_NAME = "id.swhp.javaee.playground.i18n.messages";

            @Override
            public String getMessage(String message, Object... params) {

                ResourceBundle bundle = getBundle(BASE_NAME, getLocale());

                if (bundle.containsKey(message)) {
                    message = bundle.getString(message);
                }

                return isEmpty(params) ? message : format(message, params);
            }
        });
    }

    private boolean isEmpty(Object... param) {
        return param.length <= 0;
    }
}
