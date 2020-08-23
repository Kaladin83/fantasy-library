package com.fantasyLibrary.repos.clone;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.clone.CloneAuthor;

@Repository
public interface CloneAuthorRepository extends CrudRepository<CloneAuthor, Long>{
	public List<CloneAuthor> findAllByOrderByLastNameAsc();
}
