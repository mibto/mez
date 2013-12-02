package ch.bli.mez.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {
  static Parser parser;
  Calendar calendar;
  Integer workminutes1;
  Integer workminutes59;
  Integer workminutes60;
  Integer workminutes61;
  Integer workminutes71;

  @Before
  public void setUp() throws Exception {
    parser = new Parser();

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
    parser = null;
    calendar = null;
  }

  @Test
  public void parseWorkTimeToMinutesTest() {
    // Case1: Test Parse Worktime 1 Minute
    assertEquals(workminutes1, Parser.parseMinuteStringToInteger("0:01"));
    assertEquals(workminutes1, Parser.parseMinuteStringToInteger("0.01"));

    // Case2: Test Parse Worktime 59 Minute
    assertEquals(workminutes59, Parser.parseMinuteStringToInteger("0:59"));
    assertEquals(workminutes59, Parser.parseMinuteStringToInteger("0.59"));

    // Case3: Test Parse Worktime 60 Minute
    assertEquals(workminutes60, Parser.parseMinuteStringToInteger("1:00"));
    assertEquals(workminutes60, Parser.parseMinuteStringToInteger("1.00"));

    // Case4: Test Parse Worktime 61 Minute
    assertEquals(workminutes61, Parser.parseMinuteStringToInteger("1:01"));
    assertEquals(workminutes61, Parser.parseMinuteStringToInteger("1.01"));

    // Case5: Test Parse Worktime 60 Minute
    assertEquals(workminutes71, Parser.parseMinuteStringToInteger("1:11"));
    assertEquals(workminutes71, Parser.parseMinuteStringToInteger("1.11"));

  }

  @Test
  public void parseMinutesToWorkTimeTest() {
    // Case1: Test Parse Worktime 1 Minute
    assertEquals("0:01", Parser.parseMinutesIntegerToString(workminutes1));

    // Case2: Test Parse Worktime 59 Minute
    assertEquals("0:59", Parser.parseMinutesIntegerToString(workminutes59));

    // Case3: Test Parse Worktime 60 Minute
    assertEquals("1:00", Parser.parseMinutesIntegerToString(workminutes60));

    // Case4: Test Parse Worktime 61 Minute
    assertEquals("1:01", Parser.parseMinutesIntegerToString(workminutes61));

    // Case5: Test Parse Worktime 71 Minute
    assertEquals("1:11", Parser.parseMinutesIntegerToString(workminutes71));
  }

  @Test
  public void createDateTest() {
    // Case1: Test Date convert (test with 01.01.2000)
    Calendar calendartest = Parser.parseDateStringToCalendar("01.01.2000");
    assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendartest.get(Calendar.DAY_OF_MONTH));
    assertEquals(calendar.get(Calendar.MONTH), calendartest.get(Calendar.MONTH));
    assertEquals(calendar.get(Calendar.YEAR), calendartest.get(Calendar.YEAR));
  }

  @Test
  public void parseCalendarTest() {
    // Case1: Test Parse Date to String (test with 01.01.2000)
    assertEquals("01.01.2000", Parser.parseDateCalendarToString(calendar));
  }

}
