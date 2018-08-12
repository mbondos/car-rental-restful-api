package tk.mbondos.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rental", catalog = "rental_cars_db")

public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rental_id")
    private long id;


    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "pickup_date")
    private LocalDate pickupDate = LocalDate.now();

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "return_date")
    private LocalDate returnDate;


    @ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Car car;

    public Rental() {
    }

    public Rental(LocalDate pickupDate, LocalDate returnDate, Car car, Customer customer) {
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.car = car;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
            this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
