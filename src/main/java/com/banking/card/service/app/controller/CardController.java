package com.banking.card.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.card.service.app.model.Card;
import com.banking.card.service.app.service.CardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	@GetMapping
	public Flux<Card> index(){
		return cardService.findAll();
	}
	@PostMapping("/new")
	public Mono<Card> save(@RequestBody Card card) {
		return cardService.save(card);
	}
	
	@GetMapping("/cid/{id}")
	public Mono<Card> findByCustomerId(@PathVariable String id ) {
		return cardService.findByCustomerId(id);
	}
	@GetMapping("/cn/{cardNumber}")
	public Mono<Card> findByCardNumber(@PathVariable Long cardNumber ) {
		return cardService.findByCardNumber(cardNumber);
	}
	@DeleteMapping("/delete")
	public Mono<Void> delete(@RequestBody Card card ) {
		return cardService.delete(card);
	}
}
