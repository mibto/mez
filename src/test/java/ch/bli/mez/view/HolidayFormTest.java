package ch.bli.mez.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bli.mez.view.management.HolidayForm;

public class HolidayFormTest {

  static HolidayForm holidayForm;

  @Before
  public void setUp() throws Exception {
    holidayForm = new HolidayForm();
    holidayForm.setParentPanel(new DefaultPanel());
  }

  @After
  public void tearDown() throws Exception {
    holidayForm = null;
  }

  @Test
  public void validateFieldsTest() {

    // Case1: Required fields empty
    holidayForm.setYear("2000");
    assertFalse(holidayForm.validateFields());

    holidayForm.setPreWorkdays("55");
    assertFalse(holidayForm.validateFields());

    // Case2: Everything correct
    holidayForm.setPublicHolidays("55");
    assertTrue(holidayForm.validateFields());

    // Case3: PublicHolidays false, rest correct
    holidayForm.setPublicHolidays("ABC");
    assertFalse(holidayForm.validateFields());
    holidayForm.setPublicHolidays("55");

    // Case4: PreWorkdays false, rest correct
    holidayForm.setPreWorkdays("ABC");
    assertFalse(holidayForm.validateFields());
    holidayForm.setPreWorkdays("55");

    // Case5: PreWorkdays false, rest correct
    holidayForm.setYear("ABC");
    assertFalse(holidayForm.validateFields());
    holidayForm.setYear("50000");
    assertTrue(holidayForm.validateFields());
  }

}
