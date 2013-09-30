package ch.bli.mez.model.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * The persistent class for the contact database table.
 *
 */
@Entity
@Table(name = "employe")
public class Employe {
    private Integer id;
    private String name;
    private String email;
 
    public Employe() {
 
    }
 
    public Employe(Integer id, String name, String email) {
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
