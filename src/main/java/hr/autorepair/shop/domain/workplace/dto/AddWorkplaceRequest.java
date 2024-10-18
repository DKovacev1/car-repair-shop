package hr.autorepair.shop.domain.workplace.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddWorkplaceRequest {
    @NotEmpty(message = "Name {error.required}")
    private String name;
}
