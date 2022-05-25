package at.commodussolutions.plentyentry.backendConfig.utils;

import at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotAllowedException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Order(1)
@Slf4j
@ConditionalOnClass(Aspect.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Component
@Aspect
public class PESecuredAspect {
    @Autowired
    private Environment env;
    @Autowired
    private UserService userService;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void isRestControllerMethod() {
    }

    @Pointcut("execution(* *(..))")
    public void isMethodExecution() {
    }

    @Before("isRestControllerMethod() && isMethodExecution()")
    public void annotationChecker(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PESecured peSecured = AnnotationUtils.findAnnotation(method, PESecured.class);
        if (peSecured == null) {
            throw new NotAllowedException("No Permissioncheck provided on this RestMethod, please define the " +
                    "anootation for our webapp security");
        }
        if (env.acceptsProfiles(Profiles.of("qa", "production")) && peSecured != null) {
            UserType[] requiredTypes = peSecured.value();
            var PEUser = userService.getUserByJWTToken();
            if (PEUser == null) {
                throw new NotAllowedException("User is not present!");
            }
            var checkIfUserHasRights =
                    Arrays.stream(requiredTypes).anyMatch(usertype -> PEUser.getUserType().equals(usertype));
            if (requiredTypes.length > 0 && !checkIfUserHasRights) {
                throw new NotAllowedException(SecurityConstant.ACCESS_DENIED_MESSAGE);
            }
        }
    }
}
