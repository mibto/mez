package ch.bli.mez.controller;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

@RunWith(MockitoJUnitRunner.class)
public class PositionControllerTest {
  private PositionController instance;

  @Mock
  static Position position;

  @Mock
  static PositionDAO positionModel;

  @Mock
  static PositionForm positionForm;

  @Before
  public void setUp() {
    instance = new PositionController();
    MockitoAnnotations.initMocks(this);
    instance.setModel(positionModel);
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
    //when(positionForm.getCode()).thenReturn("A4");

    instance.updatePosition(position, positionForm, false);

    InOrder inOrder = inOrder(position, positionForm, positionModel);

    inOrder.verify(positionForm).validateFields();
    inOrder.verify(positionForm).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionForm).getComment();
    inOrder.verify(position).setComment("");
    //inOrder.verify(positionForm).getCode();
    inOrder.verify(position).setCode("A4");
    inOrder.verify(positionModel).updatePosition(position);

    // Case 2: new Position which is the same for all organs, valid
    reset(position, positionForm, positionModel);

    MissionDAO missionModel = Mockito.mock(MissionDAO.class);
    PositionController mySpy = spy(instance);

    when(positionForm.validateFields()).thenReturn(true);
    when(positionForm.getPositionName()).thenReturn("PositionName");
    when(positionForm.getComment()).thenReturn("");
    //when(positionForm.getCode()).thenReturn("A4");
    //when(positionForm.getSelectedMission()).thenReturn("0");
    when(mySpy.makePosition()).thenReturn(position);

    mySpy.updatePosition(position, positionForm, true);

    inOrder = inOrder(position, positionForm, positionModel, mySpy);

    inOrder.verify(positionForm).validateFields();
    inOrder.verify(mySpy).makePosition();
    inOrder.verify(positionForm).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionForm).getComment();
    inOrder.verify(position).setComment("");
    //inOrder.verify(positionForm).getCode();
    inOrder.verify(position).setCode("A4");
    //inOrder.verify(positionForm).getSelectedMission();
    inOrder.verify(position).setOrganDefault(true);
    inOrder.verify(missionModel).getOrganMissions();
    inOrder.verify(position).addMissions(anyListOf(Mission.class));
    inOrder.verify(positionModel).updatePosition(position);

    // Case 3: new Position which is not on missionOrgan, valid
    reset(position, positionForm, positionModel, missionModel, mySpy);

    when(positionForm.validateFields()).thenReturn(true);
    when(positionForm.getPositionName()).thenReturn("PositionName");
    when(positionForm.getComment()).thenReturn("");
    //when(positionForm.getCode()).thenReturn("A4");
    //when(positionForm.getSelectedMission()).thenReturn(1);
    when(mySpy.makePosition()).thenReturn(position);

    mySpy.updatePosition(position, positionForm, true);

    inOrder = inOrder(position, positionForm, positionModel, mySpy);

    inOrder.verify(positionForm).validateFields();
    inOrder.verify(mySpy).makePosition();
    inOrder.verify(positionForm).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionForm).getComment();
    inOrder.verify(position).setComment("");
    //inOrder.verify(positionForm).getCode();
    inOrder.verify(position).setCode("A4");
    //inOrder.verify(positionForm).getSelectedMission();
    inOrder.verify(position).setOrganDefault(false);
    verify(missionModel, never()).getOrganMissions();
    inOrder.verify(missionModel).getMission(1);
    inOrder.verify(position).addMission(isA(Mission.class));
    inOrder.verify(positionModel).updatePosition(position);
  }
}
