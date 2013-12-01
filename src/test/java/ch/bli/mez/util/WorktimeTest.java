package ch.bli.mez.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorktimeTest {
  static Worktime worktime;
  Calendar calendar;
  Integer workminutes1;
  Integer workminutes59;
  Integer workminutes60;
  Integer workminutes61;
  Integer workminutes71;

  @Before
  public void setUp() throws Exception {
    worktime = new Worktime();

    workminutes1 = 1;
    workminutes59 = 59;
    workminutes60 = 60;
    workminutes61 = 61;
    workminutes71 = 71;

    calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 2000);
    calendar.set(Calendar.MONTH, 0); // It's just part of the horrendous mess
                                     // which is the Java date/time API. Months
                                     // are numbered from 0 (January) to 11
                                     // (December)
    calendar.set(Calendar.DAY_OF_MONTH, 1);

  }

  @After
  public void tearDown() throws Exception {
    worktime = null;
    calendar = null;
  }

  @Test
  public void parseWorkTimeToMinutesTest() {
    // Case1: Test Parse Worktime 1 Minute
    assertEquals(workminutes1, Worktime.parseWorkTimeToMinutes("0:01"));
    assertEquals(workminutes1, Worktime.parseWorkTimeToMinutes("0.01"));

    // Case2: Test Parse Worktime 59 Minute
    assertEquals(workminutes59, Worktime.parseWorkTimeToMinutes("0:59"));
    assertEquals(workminutes59, Worktime.parseWorkTimeToMinutes("0.59"));

    // Case3: Test Parse Worktime 60 Minute
    assertEquals(workminutes60, Worktime.parseWorkTimeToMinutes("1:00"));
    assertEquals(workminutes60, Worktime.parseWorkTimeToMinutes("1.00"));

    // Case4: Test Parse Worktime 61 Minute
    assertEquals(workminutes61, Worktime.parseWorkTimeToMinutes("1:01"));
    assertEquals(workminutes61, Worktime.parseWorkTimeToMinutes("1.01"));

    // Case5: Test Parse Worktime 60 Minute
    assertEquals(workminutes71, Worktime.parseWorkTimeToMinutes("1:11"));
    assertEquals(workminutes71, Worktime.parseWorkTimeToMinutes("1.11"));

  }

  @Test
  public void parseMinutesToWorkTimeTest() {
    // Case1: Test Parse Worktime 1 Minute
    assertEquals("0:01", Worktime.parseMinutesToWorkTime(workminutes1));

    // Case2: Test Parse Worktime 59 Minute
    assertEquals("0:59", Worktime.parseMinutesToWorkTime(workminutes59));

    // Case3: Test Parse Worktime 60 Minute
    assertEquals("1:00", Worktime.parseMinutesToWorkTime(workminutes60));

    // Case4: Test Parse Worktime 61 Minute
    assertEquals("1:01", Worktime.parseMinutesToWorkTime(workminutes61));

    // Case5: Test Parse Worktime 71 Minute
    assertEquals("1:11", Worktime.parseMinutesToWorkTime(workminutes71));
  }

  @Test
  public void createDateTest() {
    // Case1: Test Date convert (test with 01.01.2000)
    Calendar calendartest = Worktime.createDate("01.01.2000");
    assertEquals(calendar, calendartest);

    // Case1: Test Date convert (test with 01.01.2000)
    calendartest = Worktime.createDate("01.01.00");
    assertEquals(calendar, calendartest);
  }

  @Test
  public void parseCalendarTest() {
    // Case1: Test Parse Date to String (test with 01.01.2000)
    assertEquals("01.01.2000", Worktime.parseCalendar(calendar));
  }

}
