package com.example.project.dao;

import com.example.project.entity.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

    boolean existsByUsername(String userName);


    Page<UserDTO> findByFullNameContaining(String fullName, Pageable pageable);
    List<UserDTO> findByFullNameContaining(String fullName);

    Optional<UserDTO> findByUsername(String username);
}
