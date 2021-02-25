package com.example.contractmanagement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.junit.jupiter.api.BeforeEach;

public class SupplierTest {
	private Validator validator;
	Supplier supplier = new Supplier();
	Types type = new Types();
	
	@Test
	public void testId() {
		supplier.setId(1);
		assertEquals(supplier.getId(), 1);
	}
	
	@Test
	public void testName() {
		supplier.setName("supplier");
		assertEquals(supplier.getName(), "supplier");
	}
	
	@Test
	public void testPassword() {
		supplier.setPassword("supplier");
		assertEquals(supplier.getPassword(), "supplier");
	}
	
	
	@Test
	public void testContactNumber() {
		supplier.setContactNumber("1983323");
		assertEquals(supplier.getContactNumber(), "1983323");
	}
	
	
	@Test
	public void testAddress() {
		supplier.setAddress("address");
		assertEquals(supplier.getAddress(), "address");
	}
	
	
	@Test
	public void testType() {
		type.setId(1);
		type.setType("type1");
		supplier.setType(type);
		assertEquals(supplier.getType(), type);
	}
	
	@Test
	public void testToString() {
		supplier.setId(1);
		supplier.setName("supplier");
		supplier.setPassword("supplier");
		supplier.setContactNumber("123");
		supplier.setAddress("address");
		type.setId(1);
		type.setType("type1");
		supplier.setType(type);
		assertTrue(supplier.toString().contains("supplier"));
	}
	
	@BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
		
	@Test
	void testPasswordValidations() {
		String ctype="";
		for (int i = 0; i<=60; i++)
			ctype = ctype+"a";
		supplier.setPassword(ctype);;
		Set<ConstraintViolation<Supplier>> violations = validator.validate(supplier);
		assertFalse(violations.isEmpty());	
		
	}
	
	@Test
	void anothertestPasswordValidations() {
		String ctype="";
		for (int i = 0; i<=8; i++)
			ctype = ctype+"a";
		supplier.setPassword(ctype);;
		Set<ConstraintViolation<Supplier>> violations = validator.validate(supplier);
		assertFalse(violations.isEmpty());	
		
	}
}
