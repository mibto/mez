package ch.bli.mez.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the holiday database table
 * 
 * @author dave
 * @version 1.0
 */

@Entity
@Table(name = "holiday")
public class Holiday {

	@Column(name = "holiday_id")
	private Integer id;
	private Integer year;
	private Integer publicHolidays;
	private Integer preWorkdays;
	private Employee employee;
	
	public Holiday(){
		
	}

	public Holiday(Integer year, Integer publicHolidays, Integer preworkdays) {
		this.year = year;
		this.publicHolidays = publicHolidays;
		this.preWorkdays = preworkdays;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "holiday_id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	@Column(unique = true, nullable = false)
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year){
		this.year = year;
	}

	@Column(nullable = false)
	public Integer getPublicHolidays() {
		return publicHolidays;
	}

	public void setPublicHolidays(Integer holidays) {
		this.publicHolidays = holidays;
	}

	@Column(nullable = false)
	public Integer getPreworkdays() {
		return preWorkdays;
	}

	public void setPreworkdays(Integer preworkdays) {
		this.preWorkdays = preworkdays;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Employee getEmployee(){
		return employee;
	}
	
	public void setEmployee(Employee employee){
		this.employee = employee;
	}
}
