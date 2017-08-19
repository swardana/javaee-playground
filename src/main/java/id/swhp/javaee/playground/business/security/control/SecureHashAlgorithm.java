package id.swhp.javaee.playground.business.security.control;

import id.swhp.javaee.playground.business.exception.boundary.SystemException;
import id.swhp.javaee.playground.business.security.boundary.HashGenerator;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Stateless
public class SecureHashAlgorithm implements HashGenerator {

    private String algorithm;

    @Inject
    Logger logger;

    public SecureHashAlgorithm() {
        // EJB need this.
    }

    public SecureHashAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getHashText(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(this.algorithm);
            byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);

            return encoded;
        } catch (NoSuchAlgorithmException ex) {
            this.logger.log(Level.SEVERE, "Can't generate hash text", ex);
            throw new SystemException();
        }
    }

}
