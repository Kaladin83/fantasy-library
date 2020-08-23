package com.fantasyLibrary.repos.clone;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.clone.CloneBook;

@Repository
public interface CloneBookRepository extends CrudRepository<CloneBook, Long>{
	@Query(value = "SELECT b.book_id FROM fantasy_library.book b WHERE b.list_of_authors like %:aId%", nativeQuery=true)
	List<Long> findAllByListOfAuthors(@Param("aId") String aId);
}
