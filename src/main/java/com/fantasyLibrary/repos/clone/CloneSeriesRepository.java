package com.fantasyLibrary.repos.clone;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fantasyLibrary.models.db.clone.CloneSeries;

@Repository
public interface CloneSeriesRepository extends CrudRepository<CloneSeries, Long>{
}
