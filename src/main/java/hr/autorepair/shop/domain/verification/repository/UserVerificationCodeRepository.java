package hr.autorepair.shop.domain.verification.repository;

import hr.autorepair.shop.domain.verification.model.UserVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Optional;

public interface UserVerificationCodeRepository extends JpaRepository<UserVerificationCode, Long> {
    UserVerificationCode save(UserVerificationCode userVerificationCode);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserVerificationCode u " +
            "WHERE u.appUser.idAppUser = :idAppUser " +
            "AND :timestamp BETWEEN u.createdAt AND u.expiresAt")
    boolean isCodeCreated(@Param("idAppUser") Long idAppUser, @Param("timestamp") Timestamp timestamp);

    @Query("SELECT u FROM UserVerificationCode u " +
            "WHERE u.appUser.idAppUser = :idAppUser " +
            "AND :timestamp BETWEEN u.createdAt AND u.expiresAt")
    Optional<UserVerificationCode> getUserVerificationCodeForUserAtTime(@Param("idAppUser") Long idAppUser, @Param("timestamp") Timestamp timestamp);
}
