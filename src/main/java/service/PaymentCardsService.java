package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentCardsService {
	private PaymentCardsService paymentService;
    
	@Autowired
	public PaymentCardsService(PaymentCardsService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	

}
