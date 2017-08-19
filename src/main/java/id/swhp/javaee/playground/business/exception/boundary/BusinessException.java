package id.swhp.javaee.playground.business.exception.boundary;

import javax.ejb.ApplicationException;

/**
 * RuntimeException will be wrapped by EJBException, it won't rollback the
 * transaction unless you specify {@link ApplicationException} with parameter
 * rollback = true.
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@ApplicationException(rollback = true)
public abstract class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
