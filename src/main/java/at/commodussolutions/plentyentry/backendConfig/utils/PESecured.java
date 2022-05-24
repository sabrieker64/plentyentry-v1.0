package at.commodussolutions.plentyentry.backendConfig.utils;

import at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, ANNOTATION_TYPE, TYPE_USE, METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PESecuredValidator.class)
public @interface PESecured {

    String message() default SecurityConstant.ACCESS_DENIED_MESSAGE;

    Class<? extends Payload>[] payload() default {};

    UserType[] value();


}
