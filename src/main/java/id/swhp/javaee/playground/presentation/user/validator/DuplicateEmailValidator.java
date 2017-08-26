package id.swhp.javaee.playground.presentation.user.validator;

import static org.omnifaces.util.Messages.createError;

import id.swhp.javaee.playground.business.account.boundary.AccountStore;
import id.swhp.javaee.playground.business.account.entity.Account;
import java.util.Optional;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@FacesValidator("duplicateEmailValidator")
public class DuplicateEmailValidator implements Validator {

    @Inject
    AccountStore accountStore;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        Optional<Account> account = this.accountStore.getByEmail((String) value);

        if (account.isPresent()) {
            throw new ValidatorException(createError("duplicateEmailValidator"));
        }
    }

}
