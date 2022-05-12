package com.banking.card.service.app.service.imp;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.card.service.app.entity.Account;
import com.banking.card.service.app.entity.CreditRecord;
import com.banking.card.service.app.entity.DepositRecord;
import com.banking.card.service.app.entity.PayRecord;
import com.banking.card.service.app.entity.TransferRecord;
import com.banking.card.service.app.entity.WitdrawRecord;
import com.banking.card.service.app.model.Card;
import com.banking.card.service.app.repository.CardRepository;
import com.banking.card.service.app.service.CardService;
import com.banking.card.service.app.service.imp.CardServiceImp;
import com.banking.card.service.app.webclient.CardWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardServiceImp implements CardService{

	private static final Logger log = LoggerFactory.getLogger(CardServiceImp.class);
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private CardWebClient cardWebClient;	

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
		
		return cardWebClient.findAccounts(card.getCustomerId())
		.defaultIfEmpty(new Account())
		.collectList()
		.flatMap(accounts->{
			card.setAllAccounts(accounts);
			return cardRepository.findByCardNumber(card.getCardNumber())
					.defaultIfEmpty(new Card())
					.flatMap(_card ->{
						if (_card.getId() != null) {
							return Mono.error(new InterruptedException("Choose another card number"));
						}else {
							return cardRepository.save(card);
						}
					});
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

	@Override
	public Flux<Card> findByCreateAtBetween(LocalDate createAtF, LocalDate createAtL) {
		return cardRepository.findByCreateAtAfter(createAtF)
				.filter(c-> c.getCreateAt().isBefore(createAtL==null? LocalDate.now(): createAtL));
	}

	@Override
	public Mono<Double> amountConsult(Long cardNumber) {
		return cardRepository.findByCardNumber(cardNumber).defaultIfEmpty(new Card())
							.flatMap(c -> c.getId()==null ? Mono.error(new InterruptedException("Card does not exist")):
								cardWebClient.amountConsult(c.getAccountId()));
	}

	@Override
	public Flux<Object> creditCardTenLast(Long cardNumber) {
		Flux<CreditRecord> creditRecords = cardWebClient.lastTenCredit(cardNumber);
		Flux<PayRecord> paymentRecords= cardWebClient.lastTenPayment(cardNumber);
		
		return Flux.merge(creditRecords,paymentRecords).limitRate(10);
	}

	@Override
	public Flux<Object> debitCardTenLast(Long cardNumber) {
		Flux<DepositRecord> despositRecords = cardWebClient.lastTenDeposit(cardNumber);
		Flux<TransferRecord> transferRecords = cardWebClient.lastTenTransfer(cardNumber);
		Flux<WitdrawRecord> witdrawRecords = cardWebClient.lastTenWitdrawal(cardNumber);
		
		return Flux.merge(despositRecords,transferRecords,witdrawRecords).limitRate(10);
	}
	
}
