package ch.bli.mez.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Prüft ob der Controller richtig erstellt wird und die Getter funktionieren
 * 
 * @author dave
 * @version 1.0
 */
public class ManagementControllerTest {

  private ManagementController instance;

  @Before
  public void setUp() throws Exception {
    this.instance = new ManagementController();
  }

  @After
  public void tearDown() throws Exception {
    this.instance = null;
  }

  /*
   * Prüft ob der ManagementController korrekt erstellt wurde
   */
  @Test
  public void test() {
    assertNotNull(instance);
    assertNotNull(instance.getView());
    assertNotNull(instance.getMissionController());
  }

}
