package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.HolidayDAO;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.management.HolidayForm;
import ch.bli.mez.view.management.HolidayTitlePanel;

/**
 * 
 * @author dave
 * @version 1.0
 */
public class HolidayController {

  private DefaultPanel view;
  private HolidayDAO model;

  public HolidayController() {
    this.view = new DefaultPanel();
    this.model = new HolidayDAO();
    addHolidayForms();
  }

  public DefaultPanel getView() {
    return view;
  }

  private void addHolidayForms() {
    view.setCreateNewForm(createHolidayForm(null));
    view.setListTitlePanel(new HolidayTitlePanel());
    for (Holiday holiday : model.getGlobalHoliday()) {
      view.addForm(createHolidayForm(holiday));
    }
  }

  private HolidayForm createHolidayForm(Holiday holiday) {
    final HolidayForm form = new HolidayForm();
    if (holiday == null) {
      form.showAsCreateNew();
    } else {
      form.setYear(String.valueOf(holiday.getYear()));
      form.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
      form.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));
    }
    setHolidayFormActionListeners(form, holiday);
    return form;
  }

  private void setHolidayFormActionListeners(final HolidayForm form, final Holiday holiday) {
    form.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateHoliday(holiday, form);
      }
    });
  }

  public boolean validateFields(HolidayForm form, Holiday holiday) {
    if (!form.validateFields()) {
      return false;
    }
    if (form.getYear().length() != 4) {
      form.getParentPanel().showError("Das Jahr muss vier Stellen umfassen z.B 2014");
      return false;
    }
    if (holiday.getId() == null && model.getGlobalHolidayByYear(Integer.parseInt(form.getYear())) != null) {
      form.getParentPanel().showError("Das eingegebene Jahr existiert bereits");
      return false;
    }
    return true;
  }

  private void updateHoliday(Holiday holiday, HolidayForm form) {
    boolean newholiday = false;
    if (holiday == null) {
      newholiday = true;
      holiday = new Holiday();
    }
    if (validateFields(form, holiday)) {
      holiday.setYear(Integer.parseInt(form.getYear()));
      holiday.setPublicHolidays(Integer.parseInt(form.getPublicHolidays()));
      holiday.setPreworkdays(Integer.parseInt(form.getPreWorkdays()));
      if (newholiday) {
        model.addHoliday(holiday);
        view.addForm(createHolidayForm(holiday));
        form.cleanFields();
      } else {
        model.updateHoliday(holiday);
      }
      form.getParentPanel().showConfirmation("Der Eintrag wurde gespeichert.");
    }
  }
}
