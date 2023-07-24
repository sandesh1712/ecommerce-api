package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
    public List<Address> findAllByUserId(int id);
}
