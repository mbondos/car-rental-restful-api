package tk.mbondos.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.mbondos.Entities.Rental;

import java.util.Collection;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    Rental findAllByCustomerId(Long customerId);
    Collection<Rental> findByCarId(Long carId);
}
