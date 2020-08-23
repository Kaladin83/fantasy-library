package com.fantasyLibrary.repos.clone;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.clone.CloneEdition;

@Repository
public interface CloneEditionRepository extends CrudRepository<CloneEdition, Long>{
	public List<CloneEdition> findByBookId(long bookId);
}
