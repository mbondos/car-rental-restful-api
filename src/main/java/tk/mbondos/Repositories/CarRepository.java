package tk.mbondos.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.mbondos.Entities.Car;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
