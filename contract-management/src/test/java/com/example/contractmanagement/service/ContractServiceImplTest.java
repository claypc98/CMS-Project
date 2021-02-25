package com.example.contractmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.contractmanagement.exceptionhandling.ContractNotFoundException;
import com.example.contractmanagement.exceptionhandling.NoCreatedContractsException;
import com.example.contractmanagement.exceptionhandling.SupplierNotFoundException;
import com.example.contractmanagement.model.Contract;
import com.example.contractmanagement.model.Supplier;
import com.example.contractmanagement.repository.ContractRepository;
import com.example.contractmanagement.repository.SupplierRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ExtendWith(MockitoExtension.class)
class ContractServiceImplTest {
	
	private String contractType ="Some Text";
	private Integer duration = 1;
	String tnc = "Some Text";
	private Integer supplierId = 1;
	private String status="Submitted";
	private String amenities="Some Text";
	
	@InjectMocks
	ContractServiceImpl contractService;
	@Mock
	ContractRepository contractRepo;
	@Mock
	SupplierRepository supplierRepo;
	@Test
	void testCreateContract() {
	
		Contract contract = new Contract();
		contract.setId(1);
		Supplier supplier = new Supplier();
		supplier.setId(supplierId);
		when(contractRepo.save(Mockito.any(Contract.class))).thenReturn(contract);
		when(supplierRepo.findById(supplierId)).thenReturn(Optional.ofNullable(supplier));
		assertEquals(contractService.createContract(contractType,duration,tnc, supplierId, status,amenities), "Updated successfully. Contract Id is: "+supplierId.toString());
		
		when(supplierRepo.findById(supplierId)).thenReturn(Optional.ofNullable(null));
		assertThrows(SupplierNotFoundException.class, ()->contractService.createContract(contractType,duration,tnc, supplierId, status,amenities));
	}

	@Test
	void testGetCreatedContractsbyStatus() {
		Contract contract = new Contract();
		
		Supplier supplier = new Supplier();
		supplier.setId(supplierId);
		contract.setContractType(contractType);
		contract.setContractDuration(duration);
		contract.setTermsAndConditions(tnc);
		contract.setStatus(status);
		contract.setAmenities(amenities);
		List<Contract> contracts = new ArrayList<Contract>();
		contracts.add(contract);
		
		when(contractRepo.findByStatusContains(status)).thenReturn(contracts);
		assertEquals(contractService.getCreatedContractsbyStatus(status), contracts);
		
		when(contractRepo.findByStatusContains(status)).thenReturn(new ArrayList<Contract>());
		assertThrows(NoCreatedContractsException.class, ()->contractService.getCreatedContractsbyStatus(status));
	}

	@Test
	void testModifyContract() {
		Contract contract = new Contract();
		Supplier supplier = new Supplier();
		supplier.setId(supplierId);
		contract.setContractType(contractType);
		contract.setContractDuration(duration);
		contract.setTermsAndConditions(tnc);
		contract.setStatus(status);
		contract.setAmenities(amenities);
		when(contractRepo.save(Mockito.any(Contract.class))).thenReturn(contract);
		when(contractRepo.findById(contract.getId())).thenReturn(Optional.ofNullable(contract));
		assertEquals(contractService.modifyContract(contract),"Updated Successfully");
		
		when(contractRepo.findById(contract.getId())).thenReturn(Optional.ofNullable(null));
		assertThrows(ContractNotFoundException.class, ()->contractService.modifyContract(contract));
	}
	
	@Test
	void TestgetContractsBySupplierId(){
		Contract contract = new Contract();
		
		Supplier supplier = new Supplier();
		supplier.setId(supplierId);
		contract.setContractType(contractType);
		contract.setContractDuration(duration);
		contract.setTermsAndConditions(tnc);
		contract.setStatus(status);
		contract.setAmenities(amenities);
		List<Contract> contracts = new ArrayList<Contract>();
		contracts.add(contract);
		
		when(contractRepo.findBySupplierId(supplierId)).thenReturn(contracts);
		
		assertEquals(contractService.getContractsBySupplierId(supplierId), contracts);
		
		when(contractRepo.findBySupplierId(supplierId)).thenReturn(new ArrayList<Contract>());
		assertThrows(NoCreatedContractsException.class, ()->contractService.getContractsBySupplierId(supplierId));
		
	}

}
