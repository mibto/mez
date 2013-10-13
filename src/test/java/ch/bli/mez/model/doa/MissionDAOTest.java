package ch.bli.mez.model.doa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.dao.MissionDAO;

/**
 * Prüft ob die MissionDAO Klasse korrekt funktioniert
 * 
 * @author dave
 * @version draft
 */
public class MissionDAOTest {
	
	private MissionDAO instance;
	private Mission mission;

	@Before
	public void setUp() throws Exception {
		this.instance = new MissionDAO();
		this.mission = new Mission("Orgel1", "small comment", true);
	}

	@After
	public void tearDown() throws Exception {
		this.instance = null;
		this.mission = null;
	}

	/*
	 * Prüft ob die Instanz erstellt wurde
	 */
	@Test
	public void checkInstance() {
		assertNotNull(instance);
		assertNotNull(mission);
	}
	
	/*
	 * Prüft ob eine Mission im Model abgespeichert werden kann
	 */
	@Test
	public void addMission(){
		instance.addMission(mission);
	}
	
	@Test
	public void missionHasId(){
		instance.addMission(mission);
		List<Mission> myMissions = instance.findAll();
		assertNotNull(myMissions.get(0).getId());
	}
	
	/* 
	 * Exceptions werden abgefangen und ein rollback ausgeführt.
	 * Hier wäre ein mock test nützlich um zu testen, ob der rollback ausgeführt wird.
	 */
	@Test
	public void addNullMission(){
		instance.addMission(null);
	}

}
