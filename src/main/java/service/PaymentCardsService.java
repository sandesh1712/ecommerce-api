package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.PaymentCard;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentCardsService implements ServiceInterface<PaymentCard>{
	
    
	@Autowired
	public PaymentCardsService() {
		super();
	}

	@Override
	public PaymentCard findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentCard> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentCard create(PaymentCard t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentCard update(PaymentCard t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
				
	}
}
