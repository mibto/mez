package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import ch.bli.mez.model.Contract;
import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.ContractDAO;
import ch.bli.mez.view.employee.ContractListEntry;
import ch.bli.mez.view.employee.ContractPanel;

/**
 * 
 * @author dave
 * @version 1.0
 */
public class ContractController {

  private ContractPanel view;
  private ContractDAO model;
  private Employee employee;

  private ActionListener holidayRefreshListener;

  public ContractController(Employee employee, ActionListener listener) {
    this.view = new ContractPanel();
    this.model = new ContractDAO();
    this.employee = employee;
    this.holidayRefreshListener = listener;
    addEntrys(employee);
    setActionListeners();
  }

  public ContractPanel getView() {
    return view;
  }

  public int getStartYear() {
    return model.getEmployeeContracts(employee).get(0).getStartDate().get(Calendar.YEAR);
  }

  public boolean contractsExists() {
    if (model.getEmployeeContracts(employee).size() == 0) {
      return false;
    } else {
      return true;
    }
  }

  // internal methods
  private void addEntrys(Employee employee) {
    for (Contract contract : model.getEmployeeContracts(employee)) {
      view.addContractListEntry(createContractListEntry(contract));
    }
  }

  private ContractListEntry createContractListEntry(final Contract contract) {
    final ContractListEntry contractListEntry = new ContractListEntry();
    contractListEntry.setWorkquota(String.valueOf(contract.getWorkquota()));
    contractListEntry.setStartDate(createStringDate(contract.getStartDate()));
    if (contract.getEndDate() != null) {
      contractListEntry.setEndDate(createStringDate(contract.getEndDate()));
    }
    setContractListEntryListeners(contractListEntry, contract);
    return contractListEntry;
  }

  private String createStringDate(Calendar calendar) {
    if (calendar == null) {
      return null;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    return simpleDateFormat.format(calendar.getTime());
  }

  private Calendar createCalendarDate(String dateString) throws NumberFormatException {
    if (dateString.equals("")) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
      calendar.setTime(simpleDateFormat.parse(dateString));
    } catch (ParseException exception) {
      throw new NumberFormatException("Datumformat ist nicht gültig");
    }
    return calendar;
  }

  private void setActionListeners() {
    view.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int workquota;
        Calendar startDate;
        Calendar endDate;
        if (view.getWorkquota().equals("") || view.getStartDate().equals("")) {
          view.showError("Die Felder Pensum und Start müssen ausgefüllt sein");
          return;
        }
        try {
          workquota = Integer.valueOf(view.getWorkquota());
          startDate = createCalendarDate(view.getStartDate());
          endDate = createCalendarDate(view.getEndDate());
        } catch (NumberFormatException exception) {
          view.showError("Die Eingegebene Werte sind nicht gültig");
          return;
        }
        if (workquota < 0 || workquota > 100) {
          view.showError("Das Pensum muss eine Zahl zwischen 0 und 100 sein");
          return;
        }
        Contract contract = new Contract(employee, startDate, workquota);
        if (endDate != null) {
          contract.setEndDate(endDate);
        }
        model.addContract(contract);
        view.addContractListEntry(createContractListEntry(contract));
        view.showConfirmation("Der Vertrag wurde erfolgreich erstellt");
        holidayRefreshListener.actionPerformed(arg0);
      }
    });
  }

  private void setContractListEntryListeners(final ContractListEntry contractListEntry, final Contract contract) {
    contractListEntry.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int workquota;
        Calendar startDate;
        Calendar endDate;
        if (contractListEntry.getWorkquota().equals("") || contractListEntry.getStartDate().equals("")) {
          showErrorContractListEntry(contractListEntry, contract);
          return;
        }
        try {
          workquota = Integer.valueOf(contractListEntry.getWorkquota());
          startDate = createCalendarDate(contractListEntry.getStartDate());
          endDate = createCalendarDate(contractListEntry.getEndDate());
        } catch (NumberFormatException exception) {
          showErrorContractListEntry(contractListEntry, contract);
          return;
        }
        if (workquota < 0 || workquota > 100) {
          showErrorContractListEntry(contractListEntry, contract);
          return;
        }
        contract.setWorkquota(workquota);
        contract.setStartDate(startDate);
        if (endDate != null) {
          contract.setEndDate(endDate);
        }
        model.updateContract(contract);
        contractListEntry.showSuccess();
        holidayRefreshListener.actionPerformed(e);
      }
    });
    contractListEntry.setDeleteListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(null, "Soll dieser Vertrag wirklich gelöscht werden?", "Vertrag löschen",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != 0) {
          return;
        }
        model.deleteContract(contract.getId());
        contractListEntry.getParent().remove(contractListEntry);
        holidayRefreshListener.actionPerformed(e);
      }
    });
  }

  private void showErrorContractListEntry(final ContractListEntry contractListEntry, final Contract contract) {
    contractListEntry.showError();
    contractListEntry.setWorkquota(String.valueOf(contract.getWorkquota()));
    contractListEntry.setStartDate(createStringDate(contract.getStartDate()));
    if (contract.getEndDate() != null) {
      contractListEntry.setEndDate(createStringDate(contract.getEndDate()));
    }
  }
}
