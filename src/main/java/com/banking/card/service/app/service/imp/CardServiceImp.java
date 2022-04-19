package com.banking.card.service.app.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.card.service.app.model.Card;
import com.banking.card.service.app.repository.CardRepository;
import com.banking.card.service.app.service.CardService;
import com.banking.card.service.app.service.imp.CardServiceImp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CardServiceImp implements CardService{

	private static final Logger log = LoggerFactory.getLogger(CardServiceImp.class);
	
	@Autowired
	//dependency injection required for the class work
	private CardRepository cardRepository;

	@Override
	public Flux<Card> findAll() {
		return cardRepository.findAll();
	}

	@Override
	public Mono<Card> findById(String id) {
		return cardRepository.findById(id);
	}

	@Override
	public Mono<Card> save(Card card) {
		return cardRepository.save(card);
	}

	@Override
	public Mono<Void> delete(Card card) {
		return cardRepository.delete(card);
	}
	
}
