package com.simplon.api.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.simplon.api.model.Contest;



@Repository
public interface ContestRepository extends CrudRepository<Contest,Long>{
    
}