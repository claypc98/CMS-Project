package com.example.contractmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.contractmanagement.exceptionhandling.ContractNotFoundException;
import com.example.contractmanagement.exceptionhandling.NoCreatedContractsException;
import com.example.contractmanagement.exceptionhandling.SupplierNotFoundException;
import com.example.contractmanagement.model.Contract;
import com.example.contractmanagement.model.Supplier;
import com.example.contractmanagement.repository.ContractRepository;
import com.example.contractmanagement.repository.SupplierRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractRepository contractRepo;
    @Autowired
	private SupplierRepository supplierRepo;
	@Override
	public String createContract(String contractType, Integer duration, String tnc,Integer supplierId, String status, String amenities) {
		log.info("START");
		Contract contract = new Contract();
		contract.setContractType(contractType);
		contract.setContractDuration(duration);
		contract.setTermsAndConditions(tnc);
		if(amenities.length()==0 || amenities==null)
			contract.setAmenities(null);
		else
			contract.setAmenities(amenities);
		
		Supplier supplier = supplierRepo.findById(supplierId).orElse(null);
		if (supplier == null) {
			log.error("Supplier Not Found. Throwing SupplierNotFound Exception");
			throw new SupplierNotFoundException();
		}
		contract.setSupplier(supplier);
		contract.setStatus(status);
		Integer id = contractRepo.save(contract).getId();
		log.info("END");
		return "Updated successfully. Contract Id is: "+ id.toString();
	}
	@Override
	public List<Contract> getCreatedContractsbyStatus(String status) {
		log.info("START");
		List<Contract> contracts = contractRepo.findByStatusContains(status);
		if(contracts.isEmpty()) {
			log.error("Found no contractors. Throwing NoContractorsException");
			throw new NoCreatedContractsException();
		}
		log.info("END");
		return contracts;
	}

	@Override
	public String modifyContract(Contract contract) {
		log.info("START");
		Contract originalContract = contractRepo.findById(contract.getId()).orElse(null);
		if (originalContract==null) {
			log.error("Cannot find the contractor with the givern ID, Throwing ContractorNotFound Exception");
			throw new ContractNotFoundException();
		}
		if (contract.getContractType() != null) {
			log.info("Modifying contract type");
			originalContract.setContractType(contract.getContractType());
		}
		if(contract.getContractDuration() != null) {
			log.info("Modifying contract duration");
			originalContract.setContractDuration(contract.getContractDuration());
		}
		if (contract.getStatus() != null) {
			log.info("Modifying Contract status");
			originalContract.setStatus(contract.getStatus());
		}
		if(contract.getAmenities() != null) {
			log.info("Modifying Amenities");
			originalContract.setAmenities(contract.getAmenities());
		}
		contractRepo.save(originalContract);
		log.info("END");
		return "Updated Successfully";
	}
	@Override
	public List<Contract> getContractsBySupplierId(Integer SupplierId) {
		log.info("START");
		List<Contract> contracts = contractRepo.findBySupplierId(SupplierId);
		if(contracts.isEmpty()) {
			log.error("No Contracts with the given SpplierId. Throwing ");
			throw new NoCreatedContractsException();
		}
		log.info("END");
		return contracts;
	}
	
	
	

	
}
