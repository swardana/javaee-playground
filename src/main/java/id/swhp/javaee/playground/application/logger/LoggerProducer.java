package id.swhp.javaee.playground.application.logger;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

/**
 * CDI producer for {@link Logger}.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Singleton
public class LoggerProducer {

    @Produces
    public Logger produceLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}
