package ch.bli.mez.view;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bli.mez.view.management.MissionPanel;

/**
 * Prüft ob die Klasse MissionPanel vorhanden ist
 * 
 * @author dave
 * @version 1.0
 */
public class MissionPanelTest {

  private MissionPanel instance;

  @Before
  public void setUp() throws Exception {
    this.instance = new MissionPanel();
  }

  @After
  public void tearDown() throws Exception {
    this.instance = null;
  }

  /*
   * Prüft ob die Instanz erstellt wurde
   */
  @Test
  public void checkInstance() {
    assertNotNull(instance);
  }

}
