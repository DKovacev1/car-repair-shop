package hr.autorepair.shop.domain.joborderstatus.util;

import lombok.Getter;

public enum JobOrderStatusEnum {
    CREATED("Created"),
    IN_PROGRESS("In progress"),
    FINISHED("Finished");

    @Getter
    private final String name;

    JobOrderStatusEnum(String name) {
        this.name = name;
    }
}
