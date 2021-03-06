package ch.bli.mez.model.doa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.dao.MissionDAO;

/**
 * Prüft ob eine Mission korrekt abgespeichert, updated und gelöscht werden kann
 * 
 * @author dave
 * @version 1.0
 */
public class MissionDAOTest {

  private static MissionDAO instance;
  private Mission mission;

  @BeforeClass
  public static void beforeEverything() {
    instance = new MissionDAO();
  }

  @AfterClass
  public static void cleanUp() {
    instance = null;
  }

  @Before
  public void setUp() {
    this.mission = new Mission("Orgelx", "comment", true);
  }

  @After
  public void tearDown() {
    this.mission = null;
  }

  /*
   * Prüft ob die Instanzen erstellt wurden
   */
  @Test
  public void checkInstance() {
    assertNotNull(instance);
    assertNotNull(mission);
  }

  /*
   * Prüft, dass eine Mission nicht doppelt gespeichert werden kann
   */
  @Test
  public void duplicateMission() {
    instance.addMission(mission);
    int begin = instance.findAll().size();
    instance.addMission(mission);
    assertEquals(begin, instance.findAll().size());
    instance.deleteMission(mission.getId());
  }

  /*
   * Exceptions werden abgefangen und ein rollback ausgeführt. Hier wäre ein
   * mock test nützlich um zu testen, ob der rollback ausgeführt wird.
   */
  @Test
  public void missionHasId() {
    instance.addMission(mission);
    List<Mission> myMissions = instance.findAll();
    assertNotNull(myMissions.get(0).getId());
  }

  /*
   * Prüft ob eine als null gespeicherte Mission nicht gespeichert wird
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNullMission() {
    instance.addMission(null);
  }

  /*
   * Prüft ob die Mission updated werden kann
   */
  @Test
  public void updateMission() {
    instance.addMission(mission);
    mission.setMissionName("KeineOrgel");
    mission.setIsOrgan(false);
    instance.updateMission(mission);
    assertFalse("Orgelx".equals(instance.getMission(mission.getId()).getMissionName()));
    assertEquals(mission, instance.getMission(mission.getId()));
    instance.deleteMission(mission.getId());
  }

  /*
   * Prüft ob die Mission ohne Änderungen korrekt updated wird
   */
  @Test
  public void updateWithoutModificationMission() {
    instance.addMission(mission);
    instance.updateMission(mission);
    assertTrue("Orgelx".equals(instance.getMission(mission.getId()).getMissionName()));
    assertEquals(mission, instance.getMission(mission.getId()));
    instance.deleteMission(mission.getId());
  }

}
