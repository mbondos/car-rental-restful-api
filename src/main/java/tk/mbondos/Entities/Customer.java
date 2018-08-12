package tk.mbondos.Entities;

import tk.mbondos.Entities.Embeddable.Address;

import javax.persistence.*;

@Entity
@Table(name = "customer", catalog = "rental_cars_db")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "streetAddress", column = @Column(name = "street_address", length = 50)),
            @AttributeOverride(name = "zipcode", column = @Column(length = 10)),
            @AttributeOverride(name = "city", column = @Column(nullable = false))
    })
    private Address address;


    public Customer() {
    }

    public Customer(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
