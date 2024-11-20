package hr.autorepair.shop.domain.role.util;

import lombok.Getter;

public enum RoleEnum {

    USER("USER"),
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    @Getter
    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

}
