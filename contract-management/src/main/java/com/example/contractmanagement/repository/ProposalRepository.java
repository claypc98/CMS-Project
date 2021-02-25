package com.example.contractmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contractmanagement.model.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {
	List<Proposal> findAllByStatus(String status);
	Proposal findByStatus(String status);
}
