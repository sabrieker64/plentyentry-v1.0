package at.commodussolutions.plentyentry.user.userdata.enums;

import lombok.Getter;

/**
 * Author: @Eker
 */
@Getter
public enum UserType {
    GUEST("GUEST"),
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN"),
    //Creating Events (ist erlaubt)
    MAINTAINER("MAINTAINER"),
    SUPERADMIN("SUPERADMIN");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
