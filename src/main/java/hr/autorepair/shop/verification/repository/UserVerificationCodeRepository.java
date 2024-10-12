package hr.autorepair.shop.verification.repository;

import hr.autorepair.shop.verification.model.UserVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface UserVerificationCodeRepository extends JpaRepository<UserVerificationCode, Long> {
    UserVerificationCode save(UserVerificationCode userVerificationCode);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserVerificationCode u " +
            "WHERE u.appUser.idAppUser = :idAppUser " +
            "AND :timestamp BETWEEN u.createdAt AND u.expiresAt")
    boolean isCodeCreated(@Param("idAppUser") Long idAppUser, @Param("timestamp") Timestamp timestamp);
}
