package tk.mbondos.Entities;


import javax.persistence.*;

@Entity
@Table(name = "car", catalog = "rental_cars_db")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private long id;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private double price;
    @Column
    private int mileage;
    @Column(name = "production_year")
    private int productionYear;


    public Car() {
    }

    public Car(String make, String model, double price, int mileage, int productionYear) {
        this.make = make;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.productionYear = productionYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
