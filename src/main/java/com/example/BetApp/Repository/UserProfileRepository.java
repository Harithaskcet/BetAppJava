package com.example.BetApp.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.BetApp.Models.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer>{
    @Query(value = "SELECT * FROM USERINFO WHERE UPPER(NAME) LIKE %:name%", nativeQuery = true)
    List<UserProfile> searchByPlayerName( @Param("name") String name);
}
