package ch.bli.mez.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "position")
public class Position {
	
	private Integer id;
	private String positionName;
    private String comment;
	private boolean isOrganDefault;
	private Set<Mission> missions = new HashSet<Mission>();

	public Position() {

	}

	@Id 
    @GeneratedValue(generator="increment") 
    @GenericGenerator(name="increment", strategy = "increment")  
	@Column(name="position_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isOrganDefault() {
		return isOrganDefault;
	}

	public void setOrganDefault(boolean isOrganDefault) {
		this.isOrganDefault = isOrganDefault;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "position_mission", joinColumns = { 
			@JoinColumn(name = "position_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "mission_id", 
					nullable = false, updatable = false) })
	public Set<Mission> getMissions() {
		return missions;
	}

	public void setMissions(Set<Mission> missions) {
		this.missions = missions;
	}
	
	public void addMission(Mission mission){
		missions.add(mission);
	}
	
	public void addMissions(Set<Mission> missions){
		this.missions.addAll(missions);
	}
}
