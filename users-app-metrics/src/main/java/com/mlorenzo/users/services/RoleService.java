package com.mlorenzo.users.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mlorenzo.users.entities.Role;
import com.mlorenzo.users.entities.User;
import com.mlorenzo.users.repositories.RoleRepository;
import com.mlorenzo.users.repositories.UserInRoleRepository;

@Service
public class RoleService {
	private static final Logger log = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private RoleRepository repository;

	@Autowired
	private UserInRoleRepository inRoleRepository;

	public List<User> getUsersByRole(String roleName) {
		log.info("Getting roles by name {}", roleName);
		return inRoleRepository.findUsersByRoleName(roleName);
	}

	public List<Role> getRoles() {
		log.info("Getting roles");
		return repository.findAll();
	}

	public Role createRole(Role role) {
		Role roleCreated = repository.save(role);
		return roleCreated;
	}

	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> result = repository.findById(roleId);
		if (result.isPresent()) {
			return repository.save(role);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
		}
	}

	public void deleteRole(Integer roleId) {
		Optional<Role> result = repository.findById(roleId);
		if (result.isPresent()) {
			repository.delete(result.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
		}
	}
}
