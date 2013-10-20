package ch.bli.mez.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Prüft ob eine Mission erstellt, geändert, inaktiviert werden kann
 * 
 * @author dave
 * @version 1.1
 */
public class MissionTest {

	private Mission instance;

	@Before
	public void setUp() throws Exception {
		// Mission braucht einen Konstruktor mit den Paramter "Name","Kommentar"
		// und "IstOrgel"
		// Datenfeld "isOrgan" gibt an ob der Auftrag eine Orgel ist, oder nicht
		// (für die 4 statische Auftragsarten, "Lager", ...)
		this.instance = new Mission("Orgel1", "small comment", true);
	}

	@After
	public void tearDown() throws Exception {
		this.instance = null;
	}

	/*
	 * Prüft ob die Instanz korrekt erstellt wurde
	 */
	@Test
	public void checkInstance() {
		assertNotNull(instance);
		assertTrue(instance.getMissionName() == "Orgel1");
		assertTrue(instance.getComment() == "small comment");
		assertTrue(instance.getIsOrgan());
		assertNull(instance.getId());  // Not set before written to Database.
		assertTrue(instance.getIsActive());
	}

	/*
	 * Prüft ob eine Mission verändert werden kann
	 */
	@Test
	public void changeMission() {
		instance.setMissionName("Orgel2");
		instance.setComment("comment changed");
		assertTrue(instance.getMissionName() == "Orgel2");
		assertTrue(instance.getComment() == "comment changed");
	}

	/*
	 * Prüft den Status einer Mission und ob dieser auf Inaktiv gesetzt werden
	 * kann
	 */
	@Test
	public void changeStatusMission() {
		instance.setIsActive(false);
		assertTrue(instance.getIsActive() == false);
	}

	/*
	 * Prüft ob "IstOrgel" auf false geändert werden kann
	 */
	@Test
	public void changeIsOrgan() {
		instance.setIsOrgan(false);
		assertFalse(instance.getIsOrgan());
	}
}