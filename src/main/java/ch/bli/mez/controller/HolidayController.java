package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.HolidayDAO;
import ch.bli.mez.view.management.HolidayListEntry;
import ch.bli.mez.view.management.HolidayPanel;

/**
 * 
 * @author dave
 * @version 1.0
 */
public class HolidayController {

  private HolidayPanel view;
  private HolidayDAO model;

  public HolidayController() {
    this.view = new HolidayPanel();
    this.model = new HolidayDAO();

    addEntrys();
    setActionListeners();
  }

  public HolidayPanel getView() {
    return view;
  }

  private void addEntrys() {
    for (Holiday holiday : model.getGlobalHoliday()) {
      view.addHolidayListEntry(createHolidayListEntry(holiday));
    }
  }

  private HolidayListEntry createHolidayListEntry(final Holiday holiday) {
    final HolidayListEntry holidayListEntry = new HolidayListEntry();

    holidayListEntry.setYear(String.valueOf(holiday.getYear()));
    holidayListEntry.setPublicHolidays(String.valueOf(holiday
        .getPublicHolidays()));
    holidayListEntry.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));

    setHolidayListEntryActionListeners(holidayListEntry, holiday);

    return holidayListEntry;
  }

  private void setActionListeners() {
    view.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int year;
        int publicHolidays;
        int preWorkdays;
        if (view.getYear().equals("") || view.getPublicHolidays().equals("")
            || view.getPreWorkdays().equals("")) {
          view.showError("Die Eingabe ist nicht gültig, es darf kein Feld leer stehen");
          return;
        } else if (view.getYear().length() != 4) {
          view.showError("Das eingegebene Jahr ist nicht gültig");
          return;
        } else {
          try {
            year = Integer.parseInt(view.getYear());
            publicHolidays = Integer.parseInt(view.getPublicHolidays());
            preWorkdays = Integer.parseInt(view.getPreWorkdays());
          } catch (NumberFormatException exception) {
            view.showError("Die Eingabe ist nicht gültig, es dürfen nur Zahlen eingegeben werden");
            return;
          }
        }
        if (model.getGlobalHolidayByYear(year) != null) {
          view.showError("Das eingegebene Jahr existiert bereits");
          return;
        }
        Holiday holiday = new Holiday(year, publicHolidays, preWorkdays);
        model.addHoliday(holiday);
        view.addHolidayListEntry(createHolidayListEntry(holiday));
        view.showConfirmation("Eintrag für das Jahr " + holiday.getYear()
            + " eingefügt");
      }
    });
  }

  private void setHolidayListEntryActionListeners(
      final HolidayListEntry holidayListEntry, final Holiday holiday) {
    holidayListEntry.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (holidayListEntry.getPublicHolidays().equals("")
            || holidayListEntry.getPreWorkdays().equals("")) {
          holidayListEntry.showError();
          holidayListEntry.setPublicHolidays(String.valueOf(holiday
              .getPublicHolidays()));
          holidayListEntry.setPreWorkdays(String.valueOf(holiday
              .getPreworkdays()));
          return;
        } else {
          try {
            holiday.setPublicHolidays(Integer.parseInt(holidayListEntry
                .getPublicHolidays()));
            holiday.setPreworkdays(Integer.parseInt(holidayListEntry
                .getPreWorkdays()));
          } catch (NumberFormatException exception) {
            holidayListEntry.showError();
            holidayListEntry.setPublicHolidays(String.valueOf(holiday
                .getPublicHolidays()));
            holidayListEntry.setPreWorkdays(String.valueOf(holiday
                .getPreworkdays()));
            return;
          }
        }
        model.updateHoliday(holiday);
        holidayListEntry.showSuccess();
      }
    });
  }
}
