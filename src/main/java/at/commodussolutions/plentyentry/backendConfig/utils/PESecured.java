package at.commodussolutions.plentyentry.backendConfig.utils;

import at.commodussolutions.plentyentry.user.userdata.enums.UserType;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PESecured {
    UserType[] value();
}
