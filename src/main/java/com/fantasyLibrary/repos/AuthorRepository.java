package com.fantasyLibrary.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{
	public List<Author> findAllByOrderByLastNameAsc();
}
