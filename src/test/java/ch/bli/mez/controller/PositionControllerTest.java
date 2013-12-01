package ch.bli.mez.controller;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

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
import ch.bli.mez.view.management.PositionForm;
import ch.bli.mez.view.management.PositionPanel;

@RunWith(MockitoJUnitRunner.class)
public class PositionControllerTest {
  private PositionController instance;

  @Mock
  static Position position;

  @Mock
  static PositionDAO positionModel;

  @Mock
  static PositionForm positionForm;
  
  @Mock
  static PositionPanel view;

  @Before
  public void setUp() {
    instance = new PositionController();
    MockitoAnnotations.initMocks(this);
    instance.setModel(positionModel);
    instance.setView(view);
  }

  @After
  public void tearDown() {
    instance = null;
    position = null;
    positionModel = null;
  }

  @Test
  public void updatePostitionTest() {
    // Case 1: existing Position changes, valid
    when(positionForm.validateFields()).thenReturn(true);
    when(positionForm.getPositionName()).thenReturn("PositionName");
    when(positionForm.getComment()).thenReturn("");
    when(positionForm.getPositionCode()).thenReturn("A4");

    instance.updatePosition(position, positionForm);

    InOrder inOrder = inOrder(position, positionForm, positionModel);

    inOrder.verify(positionForm).validateFields();
    inOrder.verify(positionForm).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionForm).getComment();
    inOrder.verify(position).setComment("");
    inOrder.verify(positionForm).getPositionCode();
    inOrder.verify(position).setCode("A4");
    inOrder.verify(positionModel).updatePosition(position);

    // Case 2: new Position which is the same for all organs, valid
    reset(position, positionForm, positionModel);
    
    MissionDAO missionModel = Mockito.mock(MissionDAO.class);
    instance.setMissionModel(missionModel);
    
    PositionController mySpy = spy(instance);

    when(positionForm.validateFields()).thenReturn(true);
    when(positionForm.getPositionName()).thenReturn("PositionName");
    when(positionForm.getComment()).thenReturn("");
    when(positionForm.getPositionCode()).thenReturn("A4");
    when(view.getSelectedMission()).thenReturn(0);
    Position newPosition = Mockito.mock(Position.class);
    Mission mission = Mockito.mock(Mission.class);
    when(newPosition.firstMission()).thenReturn(mission);
    doReturn(Mockito.mock(PositionForm.class)).when(mySpy).createPositionForm(newPosition);
    when(mission.getMissionName()).thenReturn("sdfa");
    when(mySpy.makePosition()).thenReturn(newPosition);

    position = null;
    
    mySpy.updatePosition(position, positionForm);

    inOrder = inOrder(missionModel, view, newPosition, positionForm, positionModel, mySpy);
    
    inOrder.verify(positionForm).validateFields();
    inOrder.verify(mySpy).makePosition();
    inOrder.verify(view).getSelectedMission();
    inOrder.verify(newPosition).setOrganDefault(true);
    inOrder.verify(missionModel).getOrganMissions();
    inOrder.verify(newPosition).addMissions(anyListOf(Mission.class));
    inOrder.verify(positionForm).getPositionName();
    inOrder.verify(newPosition).setPositionName("PositionName");
    inOrder.verify(positionForm).getComment();
    inOrder.verify(newPosition).setComment("");
    inOrder.verify(positionForm).getPositionCode();
    inOrder.verify(newPosition).setCode("A4");
    inOrder.verify(positionModel).addPosition(newPosition);

    // Case 3: new Position which is not on missionOrgan, valid
    reset(view, mission, newPosition, positionForm, positionModel, missionModel, mySpy);

    when(positionForm.validateFields()).thenReturn(true);
    when(positionForm.getPositionName()).thenReturn("PositionName");
    when(positionForm.getComment()).thenReturn("");
    when(positionForm.getPositionCode()).thenReturn("A4");
    when(view.getSelectedMission()).thenReturn(1);
    mission = Mockito.mock(Mission.class);
    when(newPosition.firstMission()).thenReturn(mission);
    doReturn(Mockito.mock(PositionForm.class)).when(mySpy).createPositionForm(newPosition);
    when(mission.getMissionName()).thenReturn("sdfa");
    when(missionModel.getMission(1)).thenReturn(mission);
    when(mySpy.makePosition()).thenReturn(newPosition);

    position = null;

    mySpy.updatePosition(position, positionForm);

    inOrder = inOrder(view, newPosition, positionForm, missionModel, positionModel, mySpy);

    position = null;
    
    inOrder.verify(positionForm).validateFields();
    inOrder.verify(mySpy).makePosition();
    inOrder.verify(view).getSelectedMission();
    inOrder.verify(newPosition).setOrganDefault(false);
    verify(missionModel, never()).getOrganMissions();
    inOrder.verify(missionModel).getMission(1);
    inOrder.verify(newPosition).addMission(isA(Mission.class));
    inOrder.verify(positionForm).getPositionName();
    inOrder.verify(newPosition).setPositionName("PositionName");
    inOrder.verify(positionForm).getComment();
    inOrder.verify(newPosition).setComment("");
    inOrder.verify(positionForm).getPositionCode();
    inOrder.verify(newPosition).setCode("A4");
    inOrder.verify(positionModel).addPosition(newPosition);
  }
}
