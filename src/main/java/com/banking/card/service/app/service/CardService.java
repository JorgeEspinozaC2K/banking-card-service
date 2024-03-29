package com.banking.card.service.app.service;

import java.time.LocalDate;

import com.banking.card.service.app.model.Card;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardService {

	/**
	 * EN: This method returns all the information about the card database
	 * ES: Este metodo retorna la información de la base de datos Card
	 * @return Flux type Card
	 */
	public  Flux<Card> findAll();
	
	/**
	 * EN: This method returns a record from database Card
	 * ES: Este metodo retorna un registro de la base de datos Card
	 * @param id String
	 * @return Mono type Card
	 */
	public Mono<Card> findById(String id);
	
	/**
	 * EN: This method save or update a record from database Card
	 * ES: Este metodo guarda o modifica un registro de la base de datos
	 * @param card String
	 * @return Mono type Card
	 */
	public Mono<Card> save(Card card);
	
	/**
	 * EN: This method delete a record
	 * ES: Este metodo elimina un registro
	 * @param card
	 * @return
	 */
	public Mono<Void> delete(Card card);
	
	/**
	 * EN: This method delete a record
	 * ES: Este metodo elimina un registro
	 * @param card
	 * @return
	 */
	public Mono<Card> findByCustomerId(String customerId);
	
	public Mono<Card> findByCardNumber(Long cardNumber);
	
	public Flux<Card> findByCreateAtBetween(LocalDate createAtF,LocalDate createAtL);
	
	public Mono<Double> amountConsult(Long cardNumber);
	
	public Flux<Object> creditCardTenLast(Long cardNumber);
	
	public Flux<Object> debitCardTenLast(Long cardNumber);
	
}
