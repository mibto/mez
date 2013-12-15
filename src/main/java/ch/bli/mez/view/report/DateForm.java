package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.bli.mez.util.Parser;
import ch.bli.mez.view.DefaultForm;

public class DateForm extends DefaultForm {
  private static final long serialVersionUID = -4898922035397551791L;

  private JTextField dateFrom;
  private JTextField dateUntil;

  public DateForm() {
    setLayout(new BorderLayout(0, 0));

    JPanel dateLabelpanel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) dateLabelpanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    add(dateLabelpanel, BorderLayout.NORTH);

    JLabel dateLabel = new JLabel("Datum");
    dateLabelpanel.add(dateLabel);

    JPanel inputPanel = new JPanel();
    add(inputPanel, BorderLayout.CENTER);
    inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    JLabel vonLabel = new JLabel("Von:");
    inputPanel.add(vonLabel);

    dateFrom = new JTextField();
    inputPanel.add(dateFrom);
    dateFrom.setColumns(10);

    Component horizontalStrut = Box.createHorizontalStrut(40);
    inputPanel.add(horizontalStrut);

    JLabel bisLabel = new JLabel("Bis:");
    inputPanel.add(bisLabel);

    dateUntil = new JTextField();
    inputPanel.add(dateUntil);
    dateUntil.setColumns(10);

  }

  public Calendar getDateFrom() {
    return Parser.parseDateStringToCalendar(dateFrom.getText());
  }

  public Calendar getDateUntil() {
    return Parser.parseDateStringToCalendar(dateUntil.getText());
  }

  @Override
  public Boolean validateFields() {
    try {
      Parser.parseDateStringToCalendar(dateFrom.getText());
      Parser.parseDateStringToCalendar(dateUntil.getText());
    } catch (NumberFormatException e) {
      getParentPanel().showError("Das Datumformat ist nicht gültig");
      return false;
    }
    return true;
  }

  @Override
  public void showAsCreateNew() {
    // not used
  }

  @Override
  public void cleanFields() {
    dateFrom.setText("");
    dateUntil.setText("");
  }

}
