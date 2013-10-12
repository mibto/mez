package ch.bli.mez.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	
	/*
	 * Prüft 
	 */
	@Test
	public void test(){
		assertTrue();
	}

}
