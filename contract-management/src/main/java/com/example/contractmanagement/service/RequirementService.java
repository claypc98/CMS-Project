package com.example.contractmanagement.service;

import java.util.List;

import com.example.contractmanagement.model.Requirement;

public interface RequirementService {
	public boolean createRequirement(Requirement requirement);
	public List<Integer> viewRequirementByType();
	public Requirement viewRequirementById(Integer id);
}
