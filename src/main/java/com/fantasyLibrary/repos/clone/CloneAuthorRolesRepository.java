package com.fantasyLibrary.repos.clone;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.clone.CloneAuthorRoles;

@Repository
public interface CloneAuthorRolesRepository extends CrudRepository<CloneAuthorRoles, Long>{
	Optional<CloneAuthorRoles> findByRole(String role);
}
