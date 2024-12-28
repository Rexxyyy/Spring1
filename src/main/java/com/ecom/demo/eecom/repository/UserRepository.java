package com.ecom.demo.eecom.repository;
import com.ecom.demo.eecom.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Custom SQL query for login
    @Query(value = "SELECT * FROM user WHERE email = :emailId AND password = :pword", nativeQuery = true)
    Optional<User> dbLoginWithQuery(@Param("email") String email, @Param("password") String password);


@Transactional
    @Procedure(procedureName = "proc_login")
    Optional<User> dbLoginWithStoredProcedure(@Param("email") String email, @Param("password") String password);

}
