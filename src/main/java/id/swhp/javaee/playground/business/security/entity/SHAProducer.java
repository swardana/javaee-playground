package id.swhp.javaee.playground.business.security.entity;

import id.swhp.javaee.playground.business.security.control.SecureHashAlgorithm;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Singleton
public class SHAProducer {

    @Produces
    @Sha
    public SecureHashAlgorithm produceSHAAlgorithm(InjectionPoint ip) {
        Annotated annotated = ip.getAnnotated();

        Sha sha = annotated.getAnnotation(Sha.class);

        SecureHashAlgorithm hashAlgorithm = new SecureHashAlgorithm(sha.algorithm().getName());

        return hashAlgorithm;
    }
}
