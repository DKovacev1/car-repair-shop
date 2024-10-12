package hr.autorepair.shop.verification.model;

import hr.autorepair.shop.appuser.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class UserVerificationCode {
    @Id
    @GeneratedValue
    private Long idUserVerificationCode;
    @ManyToOne
    private AppUser appUser;
    private String verificationCode;
    private Timestamp createdAt;
    private Timestamp expiresAt;
}
