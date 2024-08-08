package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Classroom;
import com.example.fai_edunext.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IUserRepository extends JpaRepository<User , Long> {
    @Query("SELECT u FROM User u WHERE u.isEnable = true")
    List<User> findAllStudentApprove();

    @Query("SELECT u FROM User u WHERE u.isEnable = false")
    List<User> findAllStudentWaitingApprove ();

//    List<User> findAllByEnable(boolean);

    @Query("SELECT u FROM User u WHERE " +
            "u.lastName LIKE CONCAT('%',:query, '%')" +
            "OR u.lastName LIKE CONCAT('%',:query, '%')" +
            "OR u.address LIKE CONCAT('%',:query, '%')" +
            "OR u.phone LIKE CONCAT('%',:query, '%')"
    )
    List<User> searchUsers(String query);

    boolean existsByPhone(String phone);
}
