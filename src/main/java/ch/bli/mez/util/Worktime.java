package ch.bli.mez.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Worktime {
// rewrite as public static methods or as a Worktime Object that can return date as integer or string
  
  /*
   * Nimmt die Zeit im Format 1:30, 0:00 oder auch nur Minuten entgegen. Es wird
   * hier mit RegEx gearbeitet.
   * 
   * @param worktime , Eingabefeld des Formulars
   * 
   * @return Worktime in Minuten
   */
  public static Integer parseWorkTimeToMinutes(String worktime) {
    Integer workminutes = 0;
    if (worktime != null) {
      if (worktime.matches("[0-9]*")) {
        workminutes = Integer.parseInt(worktime);
      } else if (worktime.matches("[0-9]*[:,.]{1}[0-9]{2}")) {
        String workhours[] = worktime.split("[:,.]");
        workminutes = Integer.parseInt(workhours[0]) * 60 + Integer.parseInt(workhours[1]);
      }
    }
    return workminutes;
  }

  /*
   * Übersetzt Minuten ins stundenformat xx:xx
   */
   public static String parseMinutesToWorkTime(Integer workminutes) {
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

  /*
   * Aus String ein Calendar erstellen
   */
   public static Calendar createDate(String date) {
    if (date.equals("")){
      return null;
    }
    String splittedDate[] = date.split("\\.");
    Calendar calendar = Calendar.getInstance();
    if (Integer.parseInt(splittedDate[2]) < 100) {
      calendar.set(Calendar.YEAR, Integer.parseInt(splittedDate[2]) + 2000);
    } else {
      calendar.set(Calendar.YEAR, Integer.parseInt(splittedDate[2]));
    }
    calendar.set(Calendar.MONTH, Integer.parseInt(splittedDate[1]) - 1);
    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splittedDate[0]));
    return calendar;
  }

  /*
   * Format Calendar auf String umwandeln (anzeigetyp)
   */
  public static String parseCalendar(Calendar calendar) {
    if (calendar == null){
      return "";
    }
    Date date = calendar.getTime();
    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
    return format1.format(date);
  }
  
  
}
