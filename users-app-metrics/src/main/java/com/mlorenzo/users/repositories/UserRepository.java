package com.mlorenzo.users.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mlorenzo.users.entities.User;

@Repository // Opcional
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameAndPassword(String username, String password);
	/**
	 * 
	 * Esto NO es SQL se llama JPQL  
	 */
	@Query("SELECT u.username FROM User u")
	Page<String> findUsernames(Pageable pageable);

}
