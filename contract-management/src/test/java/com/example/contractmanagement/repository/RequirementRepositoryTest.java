package com.example.contractmanagement.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.example.contractmanagement.model.Requirement;
import com.example.contractmanagement.model.Types;
import com.example.contractmanagement.util.DateUtil;




@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
class RequirementRepositoryTest {
	
	@Autowired
	private RequirementRepository repo;
	
	@Test
	void testCreateRequirement() {
		Requirement requirement =new Requirement();
		requirement.setId(1);
		requirement.setDescription("Some Text");
		requirement.setDeliveryDate(DateUtil.convertToDate("01/01/2022"));
		Requirement savedRequirement = repo.save(requirement);
		assertNotNull(savedRequirement);
	}
	
	@Test
	void testViewRequirement() {
		Requirement requirement =new Requirement();
		requirement.setId(1);
		repo.save(requirement);
		Requirement savedRequirement = repo.findById(1).orElse(null);
		assertNotNull(savedRequirement);
	}
	
	@Test
	void testViewRequirementsByType() {
		Requirement requirement =new Requirement();
		Types type=new Types();
		requirement.setType(type);
		repo.save(requirement);
		List<Requirement> savedRequirements = repo.findAllByType(type);
		assertNotNull(savedRequirements);
	}
	

	@Test
	void testDeleteRequirement() {
		Requirement requirement =new Requirement();
		requirement.setId(1);
		repo.save(requirement);
		boolean existsBeforeDel =repo.findById(1).isPresent();
		repo.delete(requirement);
		boolean notExistAfterDel =repo.findById(1).isPresent();
		assertTrue(existsBeforeDel);
		assertFalse(notExistAfterDel);
	}

}


