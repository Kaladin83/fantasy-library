package com.fantasyLibrary.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.Series;

@Repository
public interface SeriesRepository extends CrudRepository<Series, Long>{
}
