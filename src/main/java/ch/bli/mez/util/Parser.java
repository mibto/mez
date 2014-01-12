package ch.bli.mez.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Parser {

  /**
   * Nimmt die Zeit im Format 1:30, 0:00 oder auch nur Minuten entgegen.
   * 
   * @param worktime
   *          Wortime als String
   * @return Worktime als Integer
   * @throws NumberFormatException
   *           wenn das Worktimeformat nicht im Format 00:00, oder 000 ist
   */
  public static Integer parseMinuteStringToInteger(String worktime) throws NumberFormatException {
    if ("".equals(worktime) || worktime == null) {
      return 0;
    }
    Integer workminutes = 0;
    try {
      if (worktime.matches("[0-9]*")) {
        workminutes = Integer.parseInt(worktime);
      } else if (worktime.matches("[0-9]*[:,.]{1}[0-9]{2}")) {
        String workhours[] = worktime.split("[:,.]");
        workminutes = Integer.parseInt(workhours[0]) * 60 + Integer.parseInt(workhours[1]);
      }
    } catch (Exception e) {
      throw new NumberFormatException("WorkTimeformat ist nicht valid");
    }
    return workminutes;
  }

  /**
   * @param workminutes
   *          Minuten als Integer
   * @return Minuten als String im Format 00:00
   */
  public static String parseMinutesIntegerToString(Integer workminutes) {
    if (workminutes == null || workminutes == 0) {
      return "0";
    }
    String worktime = "";
    if ((workminutes / 60) > 0) {
      worktime = worktime + (workminutes / 60);
    } else {
      worktime = "0";
    }
    worktime = worktime + ":";
    if (workminutes % 60 < 10) {
      worktime = (workminutes / 60) + ":0" + workminutes % 60;
    } else {
      worktime = (workminutes / 60) + ":" + workminutes % 60;
    }
    return worktime;
  }

  /**
   * Aus String ein Calendarobjekt erstellen
   * 
   * @param date
   *          Datum als String
   * @return Datum als Calenderobjekt
   * @throws NumberFormatException
   *           wenn das Datumformat nicht dd.MM.yyyy entspricht
   */
  public static Calendar parseDateStringToCalendar(String date) throws NumberFormatException {
    if ("".equals(date)) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
      calendar.setTime(simpleDateFormat.parse(date));
    } catch (ParseException exception) {
      throw new NumberFormatException("Datumformat ist nicht gültig");
    }
    return calendar;
  }

  /**
   * Format Calendar auf String umwandeln (anzeigetyp)
   * 
   * @param calendar
   *          Calenderobjekt
   * @return Datum als String im Format dd.MM.yyyy
   */
  public static String parseDateCalendarToString(Calendar calendar) {
    if (calendar == null) {
      return "";
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    return simpleDateFormat.format(calendar.getTime());
  }

  public static Calendar getWeekBegin(Calendar calendar) {
    if (calendar == null){
      return null;
    }
    Calendar weekBegin = Calendar.getInstance();
    weekBegin.clear();
    int month = calendar.get(Calendar.MONTH);
    int week = calendar.get(Calendar.WEEK_OF_YEAR);
    int year = calendar.get(Calendar.YEAR);
    weekBegin.set(Calendar.YEAR, year);
    if (month == 0 && week == 52){
      weekBegin.add(Calendar.YEAR, -1);
    } else if (month == 11 && week == 1){
      weekBegin.add(Calendar.YEAR, 1);
    }
    weekBegin.set(Calendar.WEEK_OF_YEAR, week);
    weekBegin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return weekBegin;
  }
}
