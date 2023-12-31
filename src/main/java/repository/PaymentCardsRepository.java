package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.PaymentCard;

@Repository
public interface PaymentCardsRepository extends JpaRepository<PaymentCard, Integer> {

}
