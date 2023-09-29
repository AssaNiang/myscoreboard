package com.simplon.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplon.api.model.Player;


@Repository
public interface PlayerRepository extends CrudRepository<Player,Long> {

    
}