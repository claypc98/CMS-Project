package com.example.contractmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.contractmanagement.model.Types;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TypesRepositoryTest {
	@Autowired
	private TypesRepository typesrepo;
	
	@Test
	@Rollback(false)
	void createTypesTest() {
		Types type= new Types();
		type.setId(1);
		type.setType("type1");
		
		Types savedType = typesrepo.save(type);
		
		assertNotNull(savedType);
	}
}