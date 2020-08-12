package com.fantasyLibrary.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.Edition;

@Repository
public interface EditionRepository extends CrudRepository<Edition, Long>{
	public List<Edition> findByBookId(long bookId);
}
