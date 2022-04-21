package com.banking.card.service.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private String customerId;
	private Integer ccv;
	private Long cardNumber;
	private Date expiration;
	private Date createAt;
	
	
}
