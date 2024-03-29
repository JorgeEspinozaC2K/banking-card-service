package com.banking.card.service.app.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.card.service.app.model.Card;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardRepository extends ReactiveMongoRepository<Card, String> {

	/**
	 * EN: This method finds the card that is associated tath a credit card
	 * ES: este metodo encuentra la tarjeta que sta asociada a una cuenta de credito
	 * @param creditId String
	 * @return flux type card
	 */
	public Mono<Card> findByCustomerId(String customerId);
	
	/**
	 * EN:  This method returns an account associateed with a card
	 * ES: Este metodo retorna la cuenta asociada a una tarjeta
	 * @param cardNumber
	 * @return
	 */
	public Mono<Card> findByCardNumber(Long cardNumber);
	
	public Flux<Card> findByCreateAtAfter(LocalDate createAtF);
	
}
