package com.example.contractmanagement.model;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name ="ContractType", length = 500)
	@Size(min = 1, max = 500, message = "Contract Type must not be empty and have more than 500 characters")
	private String contractType;
	
	@Column(name="ConractDuration")
	private Integer contractDuration; 
	
	@Column(name ="TermsAndConditions", length = 500)
	@Size(min = 1, max = 500, message = "Contract Type must not be empty and not have more than 500 characters")
	private String termsAndConditions;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="SuplierId")
	private Supplier supplier;

	@Column(name="Status", length=20)
	private String status;

	@Column(name="Amenities", length=500)
	@Size(max=500, message="Amenities must not have more than 500 characters")
	private String amenities;

	@Override
	public String toString() {
		return "Contract [id=" + id + ", contractType=" + contractType + ", contractDuration=" + contractDuration
				+ ", termsAndConditions=" + termsAndConditions + ", supplier=" + supplier.getId() + ", status=" + status
				+ ", amenities=" + amenities + "]";
	}
	
}
