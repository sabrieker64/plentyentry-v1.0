package at.commodussolutions.plentyentry.backendConfig.utils;

import at.commodussolutions.plentyentry.user.authentication.constant.SecurityConstant;
import at.commodussolutions.plentyentry.user.authentication.jwt.JwtTokenUtil;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.NotAllowedException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class PESecuredValidator implements ConstraintValidator<PESecured, List<UserType>> {

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    public void initialize(PESecured constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<UserType> values, ConstraintValidatorContext context) throws NotAllowedException {
        var jwtToken = servletRequest.getHeader(AUTHORIZATION);
        var username = jwtTokenUtil.getUsernameFromToken(jwtToken.replace("Bearer ", ""));
        var user = userService.findUserByUsername(username);
        boolean anyFindings =
                values.stream().anyMatch(v -> v.equals(user.getUserType()));
        if (anyFindings) {
            return true;
        } else {
            throw new NotAllowedException(SecurityConstant.ACCESS_DENIED_MESSAGE);

        }
    }
}
