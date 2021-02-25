package com.example.contractmanagement.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.example.contractmanagement.util.DateUtil;

class ProposalTest {
	
	Proposal proposal = new Proposal();
	
	@Test
	void testId() {
		proposal.setId(1);
		assertEquals(proposal.getId(),1);
	}
	
	@Test
	void testProposalDate() {
		Date date= DateUtil.convertToDate("01/01/2022");
		proposal.setProposalDate(date);
		assertEquals(proposal.getProposalDate(),date);
	}
	
	@Test
	void testQuotation() {
		BigDecimal bd =  new BigDecimal("124567890.0987654321"); 
		proposal.setQuotation(bd);
		assertEquals(proposal.getQuotation(),bd);
	}
	
	@Test
	void testStatus() {
		proposal.setStatus("dummy status");
		assertEquals(proposal.getStatus(), "dummy status");
	}
	
	@Test
	void testRequirement() {
		Requirement requirement =new Requirement();
		proposal.setRequirement(requirement);
		assertEquals(proposal.getRequirement(),requirement);
	}
	
	@Test
	void testSupplier() {
		Supplier supplier = new Supplier();
		proposal.setSupplier(supplier);
		assertEquals(proposal.getSupplier(),supplier);
	}

	
	
	
	
	
	
	
	
	

}

