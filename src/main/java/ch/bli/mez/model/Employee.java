package ch.bli.mez.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
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
 
    public Employee(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
 
    @Id
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
