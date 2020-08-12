package com.fantasyLibrary.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.AuthorRoles;

@Repository
public interface AuthorRolesRepository extends CrudRepository<AuthorRoles, Long>{
	Optional<AuthorRoles> findByRole(String role);
}
