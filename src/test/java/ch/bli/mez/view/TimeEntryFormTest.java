package ch.bli.mez.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bli.mez.util.Parser;
import ch.bli.mez.view.time.TimeEntryForm;
import ch.bli.mez.view.time.TimeEntryPanel;

public class TimeEntryFormTest {

  static TimeEntryForm timeEntryForm;

  @Before
  public void setUp() throws Exception {
    timeEntryForm = new TimeEntryForm();
    timeEntryForm.setParentPanel(new TimeEntryPanel(null));
  }

  @After
  public void tearDown() throws Exception {
    timeEntryForm = null;
  }

  @Test
  public void validateFieldsTest() {

    // Case1: Required fields empty
    timeEntryForm.setDate(Parser.parseDateStringToCalendar("01.01.2000"));
    assertFalse(timeEntryForm.validateFields());

    timeEntryForm.setMission("TestMission");
    assertFalse(timeEntryForm.validateFields());

    timeEntryForm.setPosition("TestPosition");
    assertFalse(timeEntryForm.validateFields());

    // Case2: Everything correct
    timeEntryForm.setWorktime(100);
    assertTrue(timeEntryForm.validateFields());

  }
}
