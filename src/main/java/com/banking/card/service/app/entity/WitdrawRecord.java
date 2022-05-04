package com.banking.card.service.app.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class WitdrawRecord {
	private String id;
	private Double amountWithdraw;
	private String note = "Witdrawal";
	private LocalDate operationDate;
}
