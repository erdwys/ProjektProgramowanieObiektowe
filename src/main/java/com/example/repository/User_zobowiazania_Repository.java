package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Zobowiazania;
import org.springframework.data.repository.CrudRepository;

@Repository("User_zobowiazania_Repository")
public interface User_zobowiazania_Repository extends CrudRepository<Zobowiazania, Long>{



}
