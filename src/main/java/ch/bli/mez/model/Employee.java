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
    private String name;
    private String email;
 
    public Employee() {
 
    }
 
    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
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
 
    public String getName() {
        return this.name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
}
