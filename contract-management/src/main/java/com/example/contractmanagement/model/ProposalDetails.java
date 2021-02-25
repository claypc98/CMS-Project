package com.example.contractmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProposalDetails {
	private Integer id;
	private String proposalDate;
	private Integer requirementId;
	private Integer supplierId;
	private Double quotation;
}
