package com.banking.card.service.app.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PayRecord {
	private String id;
	private Double paidAmount;
	private String note = "Credit payment";
	private LocalDate paymentDate;
}
