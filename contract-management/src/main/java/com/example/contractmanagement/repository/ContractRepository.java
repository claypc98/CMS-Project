package com.example.contractmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.contractmanagement.model.Contract;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

	List<Contract> findByStatusContains(String status);
	
	@Query("FROM Contract c WHERE c.supplier.id = :id")
	List<Contract> findBySupplierId(@Param("id") Integer id);
	
}
