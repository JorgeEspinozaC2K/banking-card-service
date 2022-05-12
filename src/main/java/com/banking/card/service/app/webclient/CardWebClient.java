package com.banking.card.service.app.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.card.service.app.entity.Account;
import com.banking.card.service.app.entity.CreditRecord;
import com.banking.card.service.app.entity.DepositRecord;
import com.banking.card.service.app.entity.PayRecord;
import com.banking.card.service.app.entity.TransferRecord;
import com.banking.card.service.app.entity.WitdrawRecord;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardWebClient {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
	
	public Flux<Account> findAccounts(String accountId){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/account/{id}",accountId)
				.retrieve()
				.bodyToFlux(Account.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Flux.empty());
                });
	}
	
	public Mono<Double> amountConsult(String accountId){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/account/amount/{id}",accountId)
				.retrieve()
				.bodyToMono(Double.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Mono.empty());
                });
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<CreditRecord> lastTenCredit(Long cardNumber){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/credit/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(CreditRecord.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Flux.empty());
                });
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<PayRecord> lastTenPayment(Long cardNumber){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/payment/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(PayRecord.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Flux.empty());
                });
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<DepositRecord> lastTenDeposit(Long cardNumber){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/deposit/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(DepositRecord.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Flux.empty());
                });
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<TransferRecord> lastTenTransfer(Long cardNumber){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/transaction/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(TransferRecord.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Flux.empty());
                });
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<WitdrawRecord> lastTenWitdrawal(Long cardNumber){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/witdrawal/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(WitdrawRecord.class).transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Flux.empty());
                });
	}
}
