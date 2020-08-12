package com.fantasyLibrary.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
}
