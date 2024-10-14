package hr.autorepair.shop.role.util;

import lombok.Getter;

public enum RoleEnum {

    ADMIN("ADMIN"),
    USER("USER");

    @Getter
    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

}
