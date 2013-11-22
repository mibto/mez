package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.HolidayDAO;
import ch.bli.mez.view.management.HolidayForm;
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
      view.addHolidayListEntry(createHolidayForm(holiday));
    }
  }

  private HolidayForm createHolidayForm(final Holiday holiday) {
    final HolidayForm holidayForm = new HolidayForm();

    holidayForm.setYear(String.valueOf(holiday.getYear()));
    holidayForm.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
    holidayForm.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));

    setHolidayFormActionListeners(holidayForm, holiday);

    return holidayForm;
  }

  private void setActionListeners() {
    view.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int year;
        int publicHolidays;
        int preWorkdays;
        if (view.getYear().equals("") || view.getPublicHolidays().equals("") || view.getPreWorkdays().equals("")) {
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
        view.addHolidayListEntry(createHolidayForm(holiday));
        view.showConfirmation("Eintrag für das Jahr " + holiday.getYear() + " eingefügt");
      }
    });
  }

  private void setHolidayFormActionListeners(final HolidayForm holidayForm, final Holiday holiday) {
    holidayForm.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (holidayForm.getPublicHolidays().equals("") || holidayForm.getPreWorkdays().equals("")) {
          holidayForm.showError();
          holidayForm.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
          holidayForm.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));
          return;
        } else {
          try {
            holiday.setPublicHolidays(Integer.parseInt(holidayForm.getPublicHolidays()));
            holiday.setPreworkdays(Integer.parseInt(holidayForm.getPreWorkdays()));
          } catch (NumberFormatException exception) {
            holidayForm.showError();
            holidayForm.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
            holidayForm.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));
            return;
          }
        }
        model.updateHoliday(holiday);
        holidayForm.showSuccess();
      }
    });
  }
}
