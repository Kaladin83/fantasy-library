package com.fantasyLibrary.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	@Query(value = "SELECT b.book_id FROM fantasy_library.book b WHERE b.list_of_authors like %:aId%", nativeQuery=true)
	List<Long> findAllByListOfAuthors(@Param("aId") String aId);
}
