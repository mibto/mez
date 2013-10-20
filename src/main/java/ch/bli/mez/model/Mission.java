package ch.bli.mez.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the mission database table.
 * 
 */
@Entity
@Table(name = "mission")
public class Mission {
  private Integer id;
  private String missionName;
  private String comment;
  private boolean isOrgan;
  private boolean isActive = true;

  public Mission() {

  }

  public Mission(String missionName, String comment, boolean isOrgan) {
    this.missionName = missionName;
    this.comment = comment;
    this.isOrgan = isOrgan;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMissionName() {
    return missionName;
  }

  public void setMissionName(String missionName) {
    this.missionName = missionName;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public boolean getIsOrgan() {
    return isOrgan;
  }

  public void setIsOrgan(boolean isOrgan) {
    this.isOrgan = isOrgan;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }
}
