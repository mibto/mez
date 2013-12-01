package ch.bli.mez.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "position")
public class Position {

  @Column(name = "position_id")
  private Integer id;
  private String code;
  private String positionName;
  private String comment;
  private boolean isOrganDefault;
  private Boolean isActive;
  private Set<Mission> missions = new HashSet<Mission>();

  public Position() {
  }

  public Position(String code, String positionName, String comment, boolean isOrganDefault) {
    this.positionName = positionName;
    this.comment = comment;
    this.isOrganDefault = isOrganDefault;
    this.setCode(code);
    this.isActive = true;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "position_id", unique = true, nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(unique = true, nullable = false)
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

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "position_mission", joinColumns = { @JoinColumn(name = "position_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "mission_id", nullable = false, updatable = false) })
  public Set<Mission> getMissions() {
    return missions;
  }

  public void setMissions(Set<Mission> missions) {
    this.missions = missions;
  }

  public void addMission(Mission mission) {
    missions.add(mission);
  }

  public void addMissions(List<Mission> missions) {
    this.missions.addAll(missions);
  }

  public void removeMission(Mission mission) {
    this.missions.remove(mission);
  }

  @Column(unique = true)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(nullable = false, columnDefinition = "boolean default true")
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public void setOrganDefault(boolean isOrganDefault) {
    this.isOrganDefault = isOrganDefault;
  }

  public Mission firstMission() {
    if (!missions.isEmpty()){
      return (Mission) missions.toArray()[0];      
    }
    else{
      return null;
    }
  }
}
