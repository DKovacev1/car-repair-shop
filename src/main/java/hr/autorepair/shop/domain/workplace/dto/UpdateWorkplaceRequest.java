package hr.autorepair.shop.domain.workplace.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Objects;

@Data
public class UpdateWorkplaceRequest {
    @NotEmpty(message = "Name {error.required}")
    private String name;

    @JsonIgnore
    public boolean hasChanges(UpdateWorkplaceRequest other){
        return !Objects.equals(name, other.name);
    }
}
