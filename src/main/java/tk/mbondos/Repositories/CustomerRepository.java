package tk.mbondos.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.mbondos.Entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
