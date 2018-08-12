package tk.mbondos.Entities.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;

@Embeddable
public class Address {


    @JsonProperty(value = "street_address")
    private String streetAddress;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "zipcode")
    private String zipcode;

    public Address() {
    }

    public Address(String streetAddress, String city, String zipcode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zipcode + '\'' +
                '}';
    }
}
