package com.example.contractmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contractmanagement.model.Types;
@Repository
public interface TypesRepository extends JpaRepository<Types, Integer> {

}
