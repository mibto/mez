package ch.bli.mez.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
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
import ch.bli.mez.view.management.PositionListEntry;

@RunWith(MockitoJUnitRunner.class)
public class PositionControllerTest {
  static PositionController instance;
  
  @Mock
  static Position position;
  
  @Mock
  static PositionDAO positionModel;
  
  @Mock
  static PositionListEntry positionListEntry;
  
  @Before
  public void setUp(){
    instance = new PositionController();
    MockitoAnnotations.initMocks(this);
    instance.setModel(positionModel);
  }
  
  @After
  public void tearDown(){
    instance = null;
    position = null;
    positionModel = null;
  }
  
  public void updatePostitionTest(){
    // Case 1: existing Position changes, valid
    when(positionListEntry.validateFields()).thenReturn(true);
    when(positionListEntry.getPositionName()).thenReturn("PositionName");
    when(positionListEntry.getComment()).thenReturn("");
    when(positionListEntry.getCode()).thenReturn("A4");
    
    instance.updatePosition(position, positionListEntry, false);
    
    InOrder inOrder = inOrder(position, positionListEntry, positionModel);
    
    inOrder.verify(positionListEntry).validateFields();
    inOrder.verify(positionListEntry).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionListEntry).getComment();
    inOrder.verify(position).setComment("");
    inOrder.verify(positionListEntry).getCode();
    inOrder.verify(position).setCode("A4");
    inOrder.verify(positionModel).updatePosition(position);
    
    // Case 2: new Position which is the same for all organs, valid
    reset(position, positionListEntry, positionModel);
    
    MissionDAO missionModel = Mockito.mock(MissionDAO.class);
    PositionController mySpy = spy(instance);
    
    when(positionListEntry.validateFields()).thenReturn(true);
    when(positionListEntry.getPositionName()).thenReturn("PositionName");
    when(positionListEntry.getComment()).thenReturn("");
    when(positionListEntry.getCode()).thenReturn("A4");
    when(positionListEntry.getSelectedMission()).thenReturn("0");
    when(mySpy.makePosition()).thenReturn(position);
    
    mySpy.updatePosition(position, positionListEntry, true);
    
    inOrder = inOrder(position, positionListEntry, positionModel, mySpy);
    
    inOrder.verify(positionListEntry).validateFields();
    inOrder.verify(mySpy).makePosition();
    inOrder.verify(positionListEntry).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionListEntry).getComment();
    inOrder.verify(position).setComment("");
    inOrder.verify(positionListEntry).getCode();
    inOrder.verify(position).setCode("A4");
    inOrder.verify(positionListEntry).getSelectedMission();
    inOrder.verify(position).setOrganDefault(true);
    inOrder.verify(missionModel).getOrganMissions();
    inOrder.verify(position).addMissions(anyListOf(Mission.class));
    inOrder.verify(positionModel).updatePosition(position);
    
    // Case 3: new Position which is not on missionOrgan, valid
    reset(position, positionListEntry, positionModel, missionModel, mySpy);
    
    when(positionListEntry.validateFields()).thenReturn(true);
    when(positionListEntry.getPositionName()).thenReturn("PositionName");
    when(positionListEntry.getComment()).thenReturn("");
    when(positionListEntry.getCode()).thenReturn("A4");
    when(positionListEntry.getSelectedMission()).thenReturn(1);
    when(mySpy.makePosition()).thenReturn(position);
    
    mySpy.updatePosition(position, positionListEntry, true);
    
    inOrder = inOrder(position, positionListEntry, positionModel, mySpy);
    
    inOrder.verify(positionListEntry).validateFields();
    inOrder.verify(mySpy).makePosition();
    inOrder.verify(positionListEntry).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionListEntry).getComment();
    inOrder.verify(position).setComment("");
    inOrder.verify(positionListEntry).getCode();
    inOrder.verify(position).setCode("A4");
    inOrder.verify(positionListEntry).getSelectedMission();
    inOrder.verify(position).setOrganDefault(false);
    verify(missionModel, never()).getOrganMissions();
    inOrder.verify(missionModel).getMission(1);
    inOrder.verify(position).addMission(isA(Mission.class));
    inOrder.verify(positionModel).updatePosition(position);
  }
}
