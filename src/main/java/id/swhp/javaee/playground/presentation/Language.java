package id.swhp.javaee.playground.presentation;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.stream.Collectors.toList;
import static org.omnifaces.util.Faces.addResponseCookie;
import static org.omnifaces.util.Faces.refreshWithQueryString;
import static org.omnifaces.util.Faces.removeResponseCookie;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.omnifaces.config.FacesConfigXml;
import org.omnifaces.cdi.Cookie;
import org.omnifaces.util.Faces;

/**
 * Support i18n Localization language.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Model
public class Language {

    private static final List<Locale> SUPPORTED_LOCALES = FacesConfigXml.INSTANCE
            .getSupportedLocales();
    private static final String COOKIE_NAME = "language";
    private static final String COOKIE_PATH = "/";
    private static final int COOKIE_MAX_AGE = (int) DAYS.toSeconds(30); // 30 days

    @Inject
    @Cookie
    private String language;
    private Locale locale;
    private List<String> others;

    @PostConstruct
    public void init() {

        if (this.language != null && SUPPORTED_LOCALES.stream().anyMatch(l -> l.getLanguage().equals(this.language))) {
            this.locale = new Locale(this.language);
        } else {
            this.locale = Faces.getLocale();

            if (this.language != null) {
                removeResponseCookie(COOKIE_NAME, COOKIE_PATH);
            }

            this.language = this.locale.getLanguage();
        }

        this.others = SUPPORTED_LOCALES.stream()
                .map(l -> l.getLanguage())
                .filter(l -> !l.equals(this.language))
                .collect(toList());
    }

    public void switchTo(String language) throws IOException {
        addResponseCookie(COOKIE_NAME, language, COOKIE_PATH, COOKIE_MAX_AGE);
        refreshWithQueryString();
    }

    public String getCode() {
        return language;
    }

    public Locale getLocale() {
        return locale;
    }

    public List<String> getOthres() {
        return others;
    }
}
