package com.banking.card.service.app.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.banking.card.service.app.entity.Account;
import com.banking.card.service.app.entity.CreditRecord;
import com.banking.card.service.app.entity.DepositRecord;
import com.banking.card.service.app.entity.PayRecord;
import com.banking.card.service.app.entity.TransferRecord;
import com.banking.card.service.app.entity.WitdrawRecord;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class CardWebClient {
	
	private Builder cardWebClient = WebClient.builder();
	
	public Flux<Account> findAccounts(String accountId){
		return cardWebClient.build()
				.get()
				.uri("http://localhost:8080/account/{id}",accountId)
				.retrieve()
				.bodyToFlux(Account.class);
	}
	
	public Mono<Double> amountConsult(String accountId){
		return cardWebClient.build()
				.get()
				.uri("http://localhost:8080/account/amount/{id}",accountId)
				.retrieve()
				.bodyToMono(Double.class);
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<CreditRecord> lastTenCredit(Long cardNumber){
		return cardWebClient.build()
				.get()
				.uri("http://localhost:8080/credit/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(CreditRecord.class);
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<PayRecord> lastTenPayment(Long cardNumber){
		return cardWebClient.build()
				.get()
				.uri("http://localhost:8080/payment/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(PayRecord.class);
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<DepositRecord> lastTenDeposit(Long cardNumber){
		return cardWebClient.build()
				.get()
				.uri("http://localhost:8080/deposit/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(DepositRecord.class);
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<TransferRecord> lastTenTransfer(Long cardNumber){
		return cardWebClient.build()
				.get()
				.uri("http://localhost:8080/transaction/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(TransferRecord.class);
	}
	
	/**
	 * 
	 * @param cardNumber
	 * @return
	 */
	public Flux<WitdrawRecord> lastTenWitdrawal(Long cardNumber){
		return cardWebClient.build()
				.get()
				.uri("http://localhost:8080/witdrawal/lastTen/{cardNumber}",cardNumber)
				.retrieve()
				.bodyToFlux(WitdrawRecord.class);
	}
}
