package ch.bli.mez.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "timeEntry")
public class TimeEntry {

  @Column(name = "timeEntry_id")
  private Integer id;
  private Calendar date;
  private Integer worktime; // in minutes
  private Mission mission;
  private Position position;
  private Employee employee;
  private Boolean isActive = true;

  public TimeEntry() {

  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(nullable = false)
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Calendar getDate() {
    return date;
  }

  public void setDate(Calendar cal) {
    this.date = cal;
  }

  public Integer getWorktime() {
    return worktime;
  }

  public void setWorktime(Integer worktime) {
    this.worktime = worktime;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Mission getMission() {
    return mission;
  }

  public void setMission(Mission mission) {
    this.mission = mission;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

}
