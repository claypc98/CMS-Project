/**
 * 
 */
package com.example.contractmanagement.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.contractmanagement.model.Contract;
import com.example.contractmanagement.model.Supplier;
import com.example.contractmanagement.model.Types;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Vennelakanti
 *
 */
@Slf4j
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
class ContractRepositoryTest {

	@Autowired
	private ContractRepository contractRepo;
	@Autowired
	private TypesRepository typesrepo;
	@Autowired
	private SupplierRepository supplierrepo;
	@Test
	void testCreateContract() {
		Types type= new Types();
		type.setId(1);
		type.setType("type1");
		Types savedType = typesrepo.save(type);
		
		Supplier supplier = new Supplier();
		supplier.setId(1);
		supplier.setName("supplier");
		supplier.setPassword("supplier");
		supplier.setContactNumber("0123456789");
		supplier.setAddress("address");
		supplier.setType(savedType);
		Supplier savedSupplier = supplierrepo.save(supplier);
		
		Contract contract = new Contract();
		contract.setId(1);
		contract.setContractType("contract type");
		contract.setContractDuration(1);
		contract.setTermsAndConditions("Termns and Conditions");
		contract.setStatus("Submitted");
		contract.setSupplier(savedSupplier);
		contract.setAmenities("Amenities");

		assertNotNull(contractRepo.save(contract));
	}

	@Test
	void testFindByStatusContains() {
		Types type= new Types();
		type.setId(1);
		type.setType("type1");
		Types savedType = typesrepo.save(type);
		
		Supplier supplier = new Supplier();
		supplier.setId(1);
		supplier.setName("supplier");
		supplier.setPassword("supplier");
		supplier.setContactNumber("0123456789");
		supplier.setAddress("address");
		supplier.setType(savedType);
		Supplier savedSupplier = supplierrepo.save(supplier);
		
		Contract contract = new Contract();
		contract.setId(1);
		contract.setContractType("contract type");
		contract.setContractDuration(1);
		contract.setTermsAndConditions("Termns and Conditions");
		contract.setStatus("Submitted");
		contract.setSupplier(savedSupplier);
		contract.setAmenities("Amenities");
		contractRepo.save(contract);
		
		List<Contract> contracts = contractRepo.findByStatusContains("Submitted");
		
		assertEquals(contracts.get(0).toString(), contract.toString());
	}
	
	@Test
	void testFindBySupplierId() {
		Types type= new Types();
		type.setId(1);
		type.setType("type1");
		Types savedType = typesrepo.save(type);
		
		Supplier supplier = new Supplier();
		supplier.setId(1);
		supplier.setName("supplier");
		supplier.setPassword("supplier");
		supplier.setContactNumber("0123456789");
		supplier.setAddress("address");
		supplier.setType(savedType);
		Supplier savedSupplier = supplierrepo.save(supplier);
		
		Contract contract = new Contract();
		contract.setId(1);
		contract.setContractType("contract type");
		contract.setContractDuration(1);
		contract.setTermsAndConditions("Termns and Conditions");
		contract.setStatus("Submitted");
		contract.setSupplier(savedSupplier);
		contract.setAmenities("Amenities");
		contractRepo.save(contract);
		
		List<Contract> contracts = contractRepo.findBySupplierId(1);
		log.info(contracts.get(0).toString());
		assertEquals(contracts.get(0).toString(), contract.toString());
	}
	
	
	@Test
	void testfindById() {
		Types type= new Types();
		type.setId(1);
		type.setType("type1");
		Types savedType = typesrepo.save(type);
		
		Supplier supplier = new Supplier();
		supplier.setId(1);
		supplier.setName("supplier");
		supplier.setPassword("supplier");
		supplier.setContactNumber("0123456789");
		supplier.setAddress("address");
		supplier.setType(savedType);
		Supplier savedSupplier = supplierrepo.save(supplier);
		
		Contract contract = new Contract();
		contract.setId(1);
		contract.setContractType("contract type");
		contract.setContractDuration(1);
		contract.setTermsAndConditions("Termns and Conditions");
		contract.setStatus("Submitted");
		contract.setSupplier(savedSupplier);
		contract.setAmenities("Amenities");
		contractRepo.save(contract);
		assertTrue(contractRepo.findById(1).isPresent());
		
		assertFalse(contractRepo.findById(2).isPresent());
	}

}
