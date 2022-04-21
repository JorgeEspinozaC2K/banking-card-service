package com.banking.card.service.app.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.card.service.app.model.Card;
import com.banking.card.service.app.repository.CardRepository;
import com.banking.card.service.app.service.CardService;
import com.banking.card.service.app.service.imp.CardServiceImp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardServiceImp implements CardService{

	private static final Logger log = LoggerFactory.getLogger(CardServiceImp.class);
	
	@Autowired
	private CardRepository cardRepository;

	@Override
	public Flux<Card> findAll() {
		return cardRepository.findAll();
	}

	@Override
	public Mono<Card> findById(String id) {
		return cardRepository.findById(id)
				.defaultIfEmpty(new Card())
				.flatMap(_card ->{
					if (_card.getId() == null) {
						return Mono.error(new InterruptedException("Not found"));
					}else {
						return Mono.just(_card);
					}
				}).onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Card> save(Card card) {
		return cardRepository.findByCardNumber(card.getCardNumber())
		.defaultIfEmpty(new Card())
		.flatMap(_card ->{
			if (_card.getId() != null) {
				return Mono.error(new InterruptedException("Choose another card number"));
			}else {
				return cardRepository.save(card);
			}
		}).onErrorResume(_ex ->{
			log.error(_ex.getMessage());
			return Mono.empty();
		});
	}

	@Override
	public Mono<Void> delete(Card card) {
		return cardRepository.delete(card);
	}

	@Override
	public Mono<Card> findByCustomerId(String customerId) {
		return cardRepository.findByCustomerId(customerId)
				.defaultIfEmpty(new Card())
				.flatMap(_card ->{
					if (_card.getId() == null) {
						return Mono.error(new InterruptedException("Not found"));
					}else {
						return Mono.just(_card);
					}
				}).onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Card> findByCardNumber(Long cardNumber) {
		return cardRepository.findByCardNumber(cardNumber)
				.defaultIfEmpty(new Card())
				.flatMap(_card ->{
					if (_card.getId() == null) {
						return Mono.error(new InterruptedException("Not found"));
					}else {
						return Mono.just(_card);
					}
				}).onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}
	
}
