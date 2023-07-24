package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.PaymentCard;

public interface PaymentCardsRepository extends JpaRepository<PaymentCard, Integer> {

}
