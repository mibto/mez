package ch.bli.mez.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Prüft ob der Controller richtig erstellt wird, und ob die View instanziert wurde
 * 
 * @author dave
 * @version 1.0
 */
public class MissionControllerTest {
	
	private MissionController instance;

	@Before
	public void setUp(){
		this.instance = new MissionController();
	}

	@After
	public void tearDown() throws Exception {
		this.instance = null;
	}

	/*
	 * Prüft ob der MissionController korrekt erstellt wurde
	 */
	@Test
	public void checkInstance() {
		assertNotNull(instance);
		assertNotNull(instance.getView());
	}

}
