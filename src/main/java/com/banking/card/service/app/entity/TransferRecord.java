package com.banking.card.service.app.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TransferRecord {
	private String id;
	private Boolean bankDraft;
	private Double amount;
	private Long numberDestiantionAccount;
	private String note = "Account credit movement";
	private LocalDate operationDate;
}
