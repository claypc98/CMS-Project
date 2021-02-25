package com.example.contractmanagement.service;

import java.util.List;

import com.example.contractmanagement.model.Contract;

public interface ContractService {

	public String createContract(String contractType, Integer duration, String tnc,Integer supplierId, String status, String amenities);
	public List<Contract> getCreatedContractsbyStatus(String status);
	public String modifyContract(Contract contract);
	public List<Contract> getContractsBySupplierId(Integer SupplierId);
}
