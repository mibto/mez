package ch.bli.mez.controller;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ch.bli.mez.model.Position;
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
    
    InOrder inOrder = inOrder(position, positionListEntry);
    
    inOrder.verify(positionListEntry).validateFields();
    inOrder.verify(positionListEntry).getPositionName();
    inOrder.verify(position).setPositionName("PositionName");
    inOrder.verify(positionListEntry).getComment();
    inOrder.verify(position).setComment("");
    inOrder.verify(positionListEntry).getCode();
    inOrder.verify(position).setCode("A4");
    inOrder.verify(positionModel).updatePosition(position);
  }
}
