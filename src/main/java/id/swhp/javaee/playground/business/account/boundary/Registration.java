package id.swhp.javaee.playground.business.account.boundary;

import id.swhp.javaee.playground.business.account.entity.Account;
import id.swhp.javaee.playground.business.account.entity.Role;
import id.swhp.javaee.playground.business.account.entity.RoleName;
import id.swhp.javaee.playground.business.exception.boundary.SystemException;
import id.swhp.javaee.playground.business.security.boundary.HashGenerator;
import id.swhp.javaee.playground.business.security.entity.Sha;
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
public class Registration {

    @PersistenceContext
    EntityManager em;

    @Inject
    Logger logger;

    @Inject
    @Sha
    HashGenerator hash;

    public void createAccount(final String email, final String username, final String password) {

        String hashPassword = this.hash.getHashText(password);

        Account account = new Account(email, username, hashPassword);
        Role member = getMemberRole();

        member.addAccount(account);

        this.em.persist(account);

        // TODO: member shouldn't easily activate
        account.setActive(true);
    }

    private Role getMemberRole() {
        try {
            Role role = this.em.createNamedQuery(Role.FIND_BY_NAME, Role.class)
                    .setParameter("roleName", RoleName.MEMBER).getSingleResult();

            return role;
        } catch (NoResultException ignore) {
            this.logger.log(Level.INFO, "Role member not found", ignore);
            throw new SystemException();
        }
    }
}
