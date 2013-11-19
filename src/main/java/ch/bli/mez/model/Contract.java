package ch.bli.mez.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the contract database table
 * 
 * @author dave
 * @version 1.0
 */

@Entity
@Table(name = "contract")
public class Contract {

  @Column(name = "contract_id")
  private Integer id;
  private Calendar startDate;
  private Calendar endDate;
  private Integer workquota;
  private Employee employee;

  public Contract() {
  }

  public Contract(Employee employee, Calendar startDate, Integer workquota) {
    this.employee = employee;
    this.startDate = startDate;
    this.workquota = workquota;
  }

  public Contract(Employee employee, Calendar startDate, Calendar endDate, Integer workquota) {
    this.employee = employee;
    this.startDate = startDate;
    this.endDate = endDate;
    this.workquota = workquota;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "contract_id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(nullable = false)
  public Calendar getStartDate() {
    return startDate;
  }

  public void setStartDate(Calendar startDate) {
    this.startDate = startDate;
  }

  public Calendar getEndDate() {
    return endDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  @Column(nullable = false)
  public Integer getWorkquota() {
    return workquota;
  }

  public void setWorkquota(Integer workquota) {
    this.workquota = workquota;
  }

  @ManyToOne(cascade = CascadeType.DETACH)
  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
