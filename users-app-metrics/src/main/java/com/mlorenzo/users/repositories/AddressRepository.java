package com.mlorenzo.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mlorenzo.users.entities.Address;

@Repository // Opcional
public interface AddressRepository extends CrudRepository<Address, Integer> {

	@Query("SELECT a FROM Address a where a.profile.user.id=?1 AND a.profile.id=?2")
	List<Address> findByProfileId(Integer userId, Integer profileId);
}
