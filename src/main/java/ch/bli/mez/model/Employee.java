package ch.bli.mez.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
 
/**
 * The persistent class for the contact database table.
 *
 */
@Entity
@Table(name = "employee")
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String street;
    private Integer plz;
    private String city;
    private String mobileNumber;
    private String homeNumber;
    private String email;
    
    public Employee(){
      
    }
 
    public Employee(String firstName, String lastName, String street,
        Integer plz, String city) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.street = street;
      this.plz = plz;
      this.city = city;
    }

    @Id 
    @GeneratedValue(generator="increment") 
    @GenericGenerator(name="increment", strategy = "increment")   
    public Integer getId() {
        return this.id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public Integer getPlz() {
      return plz;
    }

    public void setPlz(Integer plz) {
      this.plz = plz;
    }

    public String getMobileNumber() {
      return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
    }

    public String getHomeNumber() {
      return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
      this.homeNumber = homeNumber;
    }
}
