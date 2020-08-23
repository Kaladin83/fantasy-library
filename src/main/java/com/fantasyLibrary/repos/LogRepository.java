package com.fantasyLibrary.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long>{
}
