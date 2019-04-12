package com.itemsSharing.repository;

import com.itemsSharing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND password = ?2")
    Optional<User> validateUser(String username, String password);

    @Query("SELECT u FROM User u WHERE u.username=?1 AND u.active = 1")
    Optional<User> getUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username=?1")
    Optional<User> getUserByUsernameForSignUp(String username);

    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.active = 1")
    List<User> getAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.active = 0")
    List<User> getAllDeactivatedUsers();

    @Modifying
    @Query("update User u set u.refeshToken = :refeshToken WHERE u.username= :username")
    @Transactional
    int updateRefreshToken (@Param("username") String username, @Param("refeshToken") String refreshToken);

    @Query("SELECT u FROM User u WHERE u.refeshToken=?1")
    Optional<User> findByRefreshToken(String refreshToken);
}
