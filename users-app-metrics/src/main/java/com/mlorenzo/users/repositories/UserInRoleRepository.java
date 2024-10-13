package com.mlorenzo.users.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mlorenzo.users.entities.User;
import com.mlorenzo.users.entities.UserInRole;

@Repository // Opcional
public interface UserInRoleRepository extends CrudRepository<UserInRole, Integer> {

	@Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
	List<User> findUsersByRoleName(String roleName);
	
	List<UserInRole> findByUser(User user);
	
	@Transactional
	void deleteAllByUser(User user);

}
