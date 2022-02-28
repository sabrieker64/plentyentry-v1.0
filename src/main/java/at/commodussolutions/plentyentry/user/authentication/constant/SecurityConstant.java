package at.commodussolutions.plentyentry.user.authentication.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 DAYS in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String COMMODUS_SOLUTIONS_PLENTYENTRY = "PlentyEntry by Commodus Solutions GmbH";
    public static final String PLENTYENTRY_ADMINISTRATION = "Plententry Administration";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You dont have the permission to access this page";
    public static final String TOKEN_EXPIRED = "Your Token is Expired please login again";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/api/backend/user/authenticate", "/api/backend/user/register", "/api/backend/user/resetpassword/**"};

}
