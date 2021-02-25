package com.example.contractmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contractmanagement.model.Requirement;
import com.example.contractmanagement.model.Types;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
	List<Requirement> findAllByType(Types type);
}
