package com.banking.card.service.app.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.banking.card.service.app.entity.Account;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cards")
public class Card {
	
	@Id
	private String id;
	private Boolean debit = false;
	private String accountId;
	private List<Account> allAccounts;
	private String customerId;
	private Integer ccv;
	private Long cardNumber;
	private Double baseCreditLine;
	private Double remainingCreditLine = baseCreditLine;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiration;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createAt;
	
	
}
