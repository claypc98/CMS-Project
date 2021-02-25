/**
 * 
 */
package com.example.contractmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.contractmanagement.model.Supplier;
import com.example.contractmanagement.model.Types;


@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class SupplierRepositoryTest {
	@Autowired
	private TypesRepository typesrepo;
	@Autowired
	private SupplierRepository supplierrepo;
	
	@Test
	@Rollback(false)
	void createSupplierTest() {
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
		
		assertNotNull(savedSupplier);
	}

}