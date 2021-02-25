package com.example.contractmanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.contractmanagement.model.Proposal;
import com.example.contractmanagement.util.DateUtil;
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
class ProposalRepositoryTest {

	
	@Autowired
	private ProposalRepository repo;
	@Test
	void testCreateProposal() {
		Proposal proposal =new Proposal();
		proposal.setId(1);
		proposal.setProposalDate(DateUtil.convertToDate("01/01/2022"));
		Proposal savedProposal = repo.save(proposal);
		assertNotNull(savedProposal);
	}
	@Test
	void testViewAllProposals() {
		Proposal proposal =new Proposal();
		proposal.setId(1);
		repo.save(proposal);
		List<Proposal> proposals = repo.findAll();
		assertThat(proposals).size().isGreaterThan(0);
	} 
	
	@Test
	void testViewProposalById() {
		Proposal proposal =new Proposal();
		proposal.setId(1);
		repo.save(proposal);
		Proposal savedProposal =repo.findById(1).orElse(null);
		assertNotNull(savedProposal);
	}
	
	@Test
	void testViewProposalByNotExistingId() {
		Proposal proposal =new Proposal();
		proposal.setId(1);
		repo.save(proposal);
		Proposal savedProposal =repo.findById(2).orElse(null);
		assertNull(savedProposal);
	}
	@Test
	void testViewProposalsToBeRevisited() {
		Proposal proposal =new Proposal();
		proposal.setId(1);
		proposal.setStatus("To Be Revisited");
		repo.save(proposal);
		List<Proposal> savedProposals= repo.findAllByStatus("To Be Revisited");
		assertThat(savedProposals).size().isGreaterThan(0);
	}
	
	@Test
	void testUpdateProposal() {
		String status1 ="To Be Revisted";
		String status2 ="Accepted";
		Proposal proposal =new Proposal();
		proposal.setId(1);
		proposal.setStatus(status1);
		repo.save(proposal);
		Proposal proposal1 =new Proposal();
		proposal1.setId(1);
		proposal1.setStatus(status2);
		repo.save(proposal1);
		Proposal updatedProposal1 =repo.findByStatus(status1);
		assertNull(updatedProposal1);
		Proposal updatedProposal2 =repo.findByStatus(status2);
		assertThat(updatedProposal2.getStatus()).isEqualTo(status2);
		
		}
	
	@Test
	void testDeleteProposal() {
		Proposal proposal =new Proposal();
		proposal.setId(1);
		repo.save(proposal);
		boolean existsBeforeDel =repo.findById(1).isPresent();
		repo.delete(proposal);
		boolean notExistAfterDel =repo.findById(1).isPresent();
		assertTrue(existsBeforeDel);
		assertFalse(notExistAfterDel);
	}

}
