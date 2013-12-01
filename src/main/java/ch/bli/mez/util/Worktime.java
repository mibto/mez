package ch.bli.mez.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Worktime {

  /**
   * Nimmt die Zeit im Format 1:30, 0:00 oder auch nur Minuten entgegen.
   * 
   * @param worktime
   *          Wortime als String
   * @return Worktime als Integer
   * @throws NumberFormatException
   *           wenn das Worktimeformat nicht im Format 00:00, oder 000 ist
   */
  public static Integer parseWorkTimeToMinutes(String worktime) throws NumberFormatException {
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
  public static String parseMinutesToWorkTime(Integer workminutes) {
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
  public static Calendar createDate(String date) throws NumberFormatException {
    if ("".equals(date)) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    try {
      String splittedDate[] = date.split("\\.");
      if (Integer.parseInt(splittedDate[2]) < 100) {
        calendar.set(Calendar.YEAR, Integer.parseInt(splittedDate[2]) + 2000);
      } else {
        calendar.set(Calendar.YEAR, Integer.parseInt(splittedDate[2]));
      }
      calendar.set(Calendar.MONTH, Integer.parseInt(splittedDate[1]) - 1);
      calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splittedDate[0]));
    } catch (Exception e) {
      throw new NumberFormatException("Datumformat ist nicht valid");
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
  public static String parseCalendar(Calendar calendar) {
    if (calendar == null) {
      return "";
    }
    Date date = calendar.getTime();
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    return format.format(date);
  }

}
