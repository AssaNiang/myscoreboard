package com.simplon.api.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import com.simplon.api.model.Game;


@Repository
public interface GameRepository extends CrudRepository<Game,Long>{
    
}