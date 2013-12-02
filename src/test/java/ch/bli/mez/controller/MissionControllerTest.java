package ch.bli.mez.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyListOf;
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
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.management.MissionForm;

/**
 * Prüft ob der Controller richtig erstellt wird, und ob die View instanziert
 * wurde
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
  static DefaultPanel missionPanel;

  @Mock
  static MissionForm missionForm;

  @Mock
  static MissionDAO missionModel;

  @Before
  public void setUp() {
    this.instance = new MissionController();
    MockitoAnnotations.initMocks(this);
    instance.setView(missionPanel);
    instance.setModel(missionModel);
  }

  @After
  public void tearDown() throws Exception {
    this.instance = null;
    missionForm = null;
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
  public void updateMissionTest() {
    // Case 1: valid, existing Organ, stays Organ
    when(missionForm.getMissionName()).thenReturn("Name");
    when(missionForm.getComment()).thenReturn("Kommentar");
    when(missionForm.validateFields()).thenReturn(true);
    when(missionForm.getIsOrgan()).thenReturn(true);
    when(mission.getIsOrgan()).thenReturn(true);
    when(mission.getMissionName()).thenReturn("Name");

    instance.updateMission(mission, missionForm);

    InOrder inOrder = inOrder(missionForm, mission, missionModel);

    inOrder.verify(missionForm).validateFields();
    inOrder.verify(missionForm).getMissionName();
    inOrder.verify(mission).setMissionName("Name");
    inOrder.verify(missionForm).getComment();
    inOrder.verify(mission).setComment("Kommentar");
    inOrder.verify(missionForm).getIsOrgan();
    inOrder.verify(mission).getIsOrgan();
    inOrder.verify(missionModel).updateMission(mission);

    // Case 2: valid, existing Organ turns to nonOrgan mission
    reset(mission, missionForm, missionModel);

    when(missionForm.getMissionName()).thenReturn("Name");
    when(missionForm.getComment()).thenReturn("Kommentar");
    when(missionForm.validateFields()).thenReturn(true);
    when(missionForm.getIsOrgan()).thenReturn(false);
    when(mission.getIsOrgan()).thenReturn(true);
    when(mission.getMissionName()).thenReturn("Name");

    instance.updateMission(mission, missionForm);
    inOrder = inOrder(missionForm, mission, missionModel);

    inOrder.verify(missionForm).validateFields();
    inOrder.verify(missionForm).getMissionName();
    inOrder.verify(mission).setMissionName("Name");
    inOrder.verify(missionForm).getComment();
    inOrder.verify(mission).setComment("Kommentar");
    inOrder.verify(missionForm).getIsOrgan();
    inOrder.verify(mission).getIsOrgan();
    inOrder.verify(mission).setIsOrgan(false);
    inOrder.verify(mission).clearPositions();
    inOrder.verify(missionModel).updateMission(mission);

    // Case 3: valid, save new Mission which is organ
    PositionDAO positionModel = Mockito.mock(PositionDAO.class);
    instance.setPositionModel(positionModel);

    reset(mission, missionForm, missionModel);

    when(missionForm.getIsOrgan()).thenReturn(true);
    when(mission.getIsOrgan()).thenReturn(false);
    when(missionForm.getMissionName()).thenReturn("Name");
    when(missionForm.getComment()).thenReturn("Kommentar");
    when(missionForm.validateFields()).thenReturn(true);
    when(mission.getMissionName()).thenReturn("");

    MissionController mySpy = spy(instance);
    when(mySpy.makeMission()).thenReturn(mission);

    inOrder = inOrder(missionForm, mission, missionModel, positionModel, mySpy);

    mySpy.updateMission(null, missionForm);

    inOrder.verify(missionForm).validateFields();
    inOrder.verify(mySpy).makeMission();
    inOrder.verify(missionForm).getMissionName();
    inOrder.verify(mission).setMissionName("Name");
    inOrder.verify(missionForm).getComment();
    inOrder.verify(mission).setComment("Kommentar");
    inOrder.verify(missionForm).getIsOrgan();
    inOrder.verify(mission).getIsOrgan();
    inOrder.verify(mission).setIsOrgan(true);
    inOrder.verify(mission).clearPositions();
    inOrder.verify(positionModel).getOrganPositions();
    inOrder.verify(mission).addPositions(anyListOf(Position.class));
    inOrder.verify(missionModel).addMission(mission);

    // Case 4: invalid
    reset(mission, missionForm, missionModel);
    when(missionForm.validateFields()).thenReturn(false);
    when(mission.getMissionName()).thenReturn("");

    instance.updateMission(mission, missionForm);

    verify(missionForm).validateFields();
    verify(missionForm, never()).getMissionName();
  }

  @Test
  public void updatePositionsTest() {
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
    verify(mission).addPositions(anyListOf(Position.class));
  }
}
