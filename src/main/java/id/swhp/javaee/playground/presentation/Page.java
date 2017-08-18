package id.swhp.javaee.playground.presentation;

import static org.omnifaces.util.Faces.getResource;
import static org.omnifaces.util.Faces.getViewId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

/**
 * Support dynamic title page.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Model
public class Page {

    private static final Map<String, Page> PAGES = new ConcurrentHashMap<>();

    private Page current;
    private String path;
    private String name;

    public Page() {
    }

    private Page(String path) {
        this.path = path;
        String uri = "/" + path;

        while (!uri.isEmpty()) {
            try {
                getResource(uri + ".xhtml").toString();
                break;
            } catch (Exception ignore) {
                uri = uri.substring(0, uri.lastIndexOf('/'));
            }
        }

        // remove WEB-INF folder, replace / with _
        this.name = path.replaceFirst("WEB\\-INF/", "").replaceAll("\\W+", "_");
        this.current = this;
    }

    @PostConstruct
    public void init() {
        String viewId = getViewId();
        this.current = get(viewId.substring(1, viewId.lastIndexOf('.')));
    }

    public Page get(String path) {
        return PAGES.computeIfAbsent(path, k -> new Page(path));
    }

    public boolean is(String path) {
        return path.equals(current.path);
    }

    public String getName() {
        return current.name;
    }

    public String getPath() {
        return current.path;
    }
}
