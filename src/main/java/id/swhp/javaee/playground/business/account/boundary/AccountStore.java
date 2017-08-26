package id.swhp.javaee.playground.business.account.boundary;

import id.swhp.javaee.playground.business.account.entity.Account;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Stateless
public class AccountStore {

    @PersistenceContext
    EntityManager em;

    @Inject
    Logger logger;

    public Optional<Account> getByEmail(final String email) {
        try {
            return Optional.of(
                    this.em.createNamedQuery(Account.FIND_BY_EMAIL, Account.class)
                            .setParameter("email", email).getSingleResult()
            );
        } catch (NoResultException e) {
            this.logger.log(Level.INFO, "Can't find Account by email ", e);
            return Optional.empty();
        }
    }

    public Optional<Account> getByUsername(final String username) {
        try {
            return Optional.of(
                    this.em.createNamedQuery(Account.FIND_BY_USERNAME, Account.class)
                            .setParameter("username", username).getSingleResult()
            );
        } catch (NoResultException e) {
            this.logger.log(Level.INFO, "Can't find Account by email ", e);
            return Optional.empty();
        }
    }
}
