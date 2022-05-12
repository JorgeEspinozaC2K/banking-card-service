package com.banking.card.service.app.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DepositRecord {
	private String id;
	private Double amountDeposit;
	private Long accountNumber;
	private Boolean fromAnotherAccount;
	private Long accountNumberFrom;
	private String note = "Deposit creation";
	private LocalDate operationDate;
}
