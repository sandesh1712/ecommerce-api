package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
