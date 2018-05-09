package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Informacja;
import org.springframework.data.repository.CrudRepository;

@Repository("Admin_zarz_powiadomienia_Repository")
public interface Admin_zarz_powiadomienia_Repository extends CrudRepository<Informacja, Long>{

}
