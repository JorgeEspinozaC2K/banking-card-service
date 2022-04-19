package com.banking.card.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.card.service.app.model.Card;
import com.banking.card.service.app.service.CardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/card")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	//This method displays all the information from the Card database
	@GetMapping
	public Flux<Card> index(){
		return cardService.findAll();
	}
	@PostMapping("/save")
	public Mono<Card> save(@RequestBody Card card) {
		return cardService.save(card);
	}
}
