package com.example.project.dao;

import com.example.project.entity.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDTO, Integer> {
    Optional<RoleDTO> findByName(String name);
}
