package com.mlorenzo.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlorenzo.users.entities.Role;

@Repository // Opcional
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
