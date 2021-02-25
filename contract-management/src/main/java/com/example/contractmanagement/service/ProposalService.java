package com.example.contractmanagement.service;

import java.util.List;

import com.example.contractmanagement.model.Proposal;
import com.example.contractmanagement.model.ProposalDetails;

public interface ProposalService {
	public boolean createProposal(Proposal proposal);
	public List<ProposalDetails> viewAllProposals();
	public ProposalDetails viewProposalById(Integer id);
	public boolean editProposalStatus(Integer id);
	public boolean editProposal(Proposal proposal);
	public List<ProposalDetails> viewToBeRevisitedProposals();
	
}
