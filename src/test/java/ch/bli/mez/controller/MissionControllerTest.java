package ch.bli.mez.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.management.MissionListEntry;
import ch.bli.mez.view.management.MissionPanel;

/**
 * Prüft ob der Controller richtig erstellt wird, und ob die View instanziert wurde
 * 
 * @author dave, leandra
 * @version 1.0
 */

@RunWith(MockitoJUnitRunner.class)
public class MissionControllerTest {
	
	private MissionController instance;
	
	@Mock
  static Mission mission;
  
  @Mock
  static MissionPanel missionPanel;
  
  @Mock
  static MissionListEntry missionListEntry;
  
  @Mock
  static MissionDAO missionModel;

	@Before
	public void setUp(){
		this.instance = new MissionController();
		MockitoAnnotations.initMocks(this);
    instance.setView(missionPanel);
    instance.setModel(missionModel);
	}

	@After
	public void tearDown() throws Exception {
		this.instance = null;
		missionListEntry = null;
		missionPanel = null;
		mission = null;
	}
	
	/*
	 * Prüft ob der MissionController korrekt erstellt wurde
	 */
	@Test
	public void checkInstance() {
		assertNotNull(instance);
		assertNotNull(instance.getView());
	}
	
	@Test
	public void updateMissionTest(){
	  // Case 1: valid, existing Organ, stays Organ
	  when(missionListEntry.getMissionName()).thenReturn("Name");
	  when(missionListEntry.getComment()).thenReturn("Kommentar");
	  when(missionListEntry.validateFields("Name")).thenReturn(true);
	  when(missionListEntry.getIsOrgan()).thenReturn(true);
	  when(mission.getIsOrgan()).thenReturn(true);
	  when(mission.getMissionName()).thenReturn("Name");
	  
	  
	  instance.updateMission(mission, missionListEntry, false);
	  
	  InOrder inOrder = inOrder(missionListEntry, mission, missionModel);
	  
	  inOrder.verify(missionListEntry).validateFields("Name");
	  inOrder.verify(missionListEntry).getMissionName();
	  inOrder.verify(mission).setMissionName("Name");
	  inOrder.verify(missionListEntry).getComment();
	  inOrder.verify(mission).setComment("Kommentar");
	  inOrder.verify(missionListEntry).getIsOrgan();
	  inOrder.verify(mission).getIsOrgan();
	  inOrder.verify(missionModel).updateMission(mission);
	  
	  // Case 2: valid, existing Organ turns to nonOrgan mission
	  reset(mission, missionListEntry, missionModel);
	  
	  when(missionListEntry.getMissionName()).thenReturn("Name");
    when(missionListEntry.getComment()).thenReturn("Kommentar");
    when(missionListEntry.validateFields("Name")).thenReturn(true);
    when(missionListEntry.getIsOrgan()).thenReturn(false);
    when(mission.getIsOrgan()).thenReturn(true);
    when(mission.getMissionName()).thenReturn("Name");
	  
	  
	  instance.updateMission(mission, missionListEntry, false);
	  inOrder = inOrder(missionListEntry, mission, missionModel);
	  
	  inOrder.verify(missionListEntry).validateFields("Name");
    inOrder.verify(missionListEntry).getMissionName();
    inOrder.verify(mission).setMissionName("Name");
    inOrder.verify(missionListEntry).getComment();
    inOrder.verify(mission).setComment("Kommentar");
    inOrder.verify(missionListEntry).getIsOrgan();
    inOrder.verify(mission).getIsOrgan();
    inOrder.verify(mission).setIsOrgan(false);
    inOrder.verify(mission).clearPositions();
    inOrder.verify(missionModel).updateMission(mission);
    
    // Case 3: valid, save new Mission which is organ
    PositionDAO positionModel = Mockito.mock(PositionDAO.class);
    instance.setPositionModel(positionModel);
    
    reset(mission, missionListEntry, missionModel);
    
    when(missionListEntry.getIsOrgan()).thenReturn(true);
    when(mission.getIsOrgan()).thenReturn(false);
    when(missionListEntry.getMissionName()).thenReturn("Name");
    when(missionListEntry.getComment()).thenReturn("Kommentar");
    when(missionListEntry.validateFields("")).thenReturn(true);
    when(mission.getMissionName()).thenReturn("");

    MissionController mySpy = spy(instance);
    when(mySpy.makeMission()).thenReturn(mission);
    
    inOrder = inOrder(missionListEntry, mission, missionModel, positionModel, mySpy);
    
    mySpy.updateMission(mission, missionListEntry, true);
    
    inOrder.verify(missionListEntry).validateFields("");
    inOrder.verify(mySpy).makeMission();
    inOrder.verify(missionListEntry).getMissionName();
    inOrder.verify(mission).setMissionName("Name");
    inOrder.verify(missionListEntry).getComment();
    inOrder.verify(mission).setComment("Kommentar");
    inOrder.verify(missionListEntry).getIsOrgan();
    inOrder.verify(mission).getIsOrgan();
    inOrder.verify(mission).setIsOrgan(true);
    inOrder.verify(mission).clearPositions();
    inOrder.verify(positionModel).getOrganPositions();
    inOrder.verify(mission).addPositions(new ArrayList<Position>());
    inOrder.verify(missionModel).addMission(mission);

    //Case 4: invalid
    reset(mission, missionListEntry, missionModel);
    when(missionListEntry.validateFields("")).thenReturn(false);
    when(mission.getMissionName()).thenReturn("");
    
    instance.updateMission(mission, missionListEntry, true);
    
    verify(missionListEntry).validateFields("");
    verify(missionListEntry, never()).getMissionName();
	}
	
	@Test
  public void updatePositionsTest(){
    // Case1: Mission is not organ
	  PositionDAO positionModel = Mockito.mock(PositionDAO.class);
    instance.setPositionModel(positionModel);
	  
    instance.updatePositions(mission, false);
    
    verify(mission).clearPositions();
    verify(positionModel, never()).getOrganPositions();
    
    reset(mission, positionModel, missionModel);
    
    // Case2: Mission is organ
    instance.updatePositions(mission, true);
    verify(mission).clearPositions();
    verify(positionModel).getOrganPositions();
    verify(mission).addPositions(new ArrayList<Position>());
	}
}
