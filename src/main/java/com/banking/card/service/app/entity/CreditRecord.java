package com.banking.card.service.app.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreditRecord {
	private String id;
	private Double totalLoan;
	private String note = "Credit creation";
	private LocalDate createAt;
}
