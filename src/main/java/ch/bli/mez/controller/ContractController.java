package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import ch.bli.mez.model.Contract;
import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.ContractDAO;
import ch.bli.mez.util.Parser;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.employee.ContractForm;

/**
 * @author dave
 * @version 1.0
 */
public class ContractController {

  private DefaultPanel view;
  private ContractDAO model;
  private Employee employee;

  private ActionListener holidayRefreshListener;

  public ContractController(Employee employee, ActionListener listener) {
    this.view = new DefaultPanel();
    this.model = new ContractDAO();
    this.employee = employee;
    this.holidayRefreshListener = listener;
    addEntrys(employee);
    setActionListeners();
  }

  public DefaultPanel getView() {
    return view;
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
      view.addForm(createContractListEntry(contract));
    }
  }

  private ContractForm createContractListEntry(final Contract contract) {
    final ContractForm contractForm = new ContractForm();
    contractForm.setWorkquota(String.valueOf(contract.getWorkquota()));
    contractForm.setStartDate(Parser.parseDateCalendarToString(contract.getStartDate()));
    if (contract.getEndDate() != null) {
      contractForm.setEndDate(Parser.parseDateCalendarToString(contract.getEndDate()));
    }
    setContractListEntryListeners(contractForm, contract);
    return contractForm;
  }

  private void setActionListeners() {
//    view.setSaveListener(new ActionListener() {
//      public void actionPerformed(ActionEvent arg0) {
//        int workquota;
//        Calendar startDate;
//        Calendar endDate;
//        if (view.getWorkquota().equals("") || view.getStartDate().equals("")) {
//          view.showError("Die Felder Pensum und Start müssen ausgefüllt sein");
//          return;
//        }
//        try {
//          workquota = Integer.valueOf(view.getWorkquota());
//          startDate = Parser.parseDateStringToCalendar(view.getStartDate());
//          endDate = Parser.parseDateStringToCalendar(view.getEndDate());
//        } catch (NumberFormatException exception) {
//          view.showError("Die Eingegebene Werte sind nicht gültig");
//          return;
//        }
//        if (workquota < 0 || workquota > 100) {
//          view.showError("Das Pensum muss eine Zahl zwischen 0 und 100 sein");
//          return;
//        }
//        Contract contract = new Contract(employee, startDate, workquota);
//        if (endDate != null) {
//          contract.setEndDate(endDate);
//        }
//        model.addContract(contract);
//        view.addContractListEntry(createContractListEntry(contract));
//        view.showConfirmation("Der Vertrag wurde erfolgreich erstellt");
//        holidayRefreshListener.actionPerformed(arg0);
//      }
//    });
  }

  private void setContractListEntryListeners(final ContractForm contractForm, final Contract contract) {
    contractForm.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int workquota;
        Calendar startDate;
        Calendar endDate;
        if (contractForm.getWorkquota().equals("") || contractForm.getStartDate().equals("")) {
          showErrorContractListEntry(contractForm, contract);
          return;
        }
        try {
          workquota = Integer.valueOf(contractForm.getWorkquota());
          startDate = Parser.parseDateStringToCalendar(contractForm.getStartDate());
          endDate = Parser.parseDateStringToCalendar(contractForm.getEndDate());
        } catch (NumberFormatException exception) {
          showErrorContractListEntry(contractForm, contract);
          return;
        }
        if (workquota < 0 || workquota > 100) {
          showErrorContractListEntry(contractForm, contract);
          return;
        }
        contract.setWorkquota(workquota);
        contract.setStartDate(startDate);
        if (endDate != null) {
          contract.setEndDate(endDate);
        }
        model.updateContract(contract);
//        contractForm.showSuccess();
        holidayRefreshListener.actionPerformed(e);
      }
    });
//    contractForm.setDeleteListener(new ActionListener() {
//      public void actionPerformed(ActionEvent e) {
//        if (JOptionPane.showConfirmDialog(null, "Soll dieser Vertrag wirklich gelöscht werden?", "Vertrag löschen",
//            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != 0) {
//          return;
//        }
//        model.deleteContract(contract.getId());
//        contractForm.getParent().remove(contractForm);
//        holidayRefreshListener.actionPerformed(e);
//      }
//    });
  }

  private void showErrorContractListEntry(final ContractForm contractForm, final Contract contract) {
//    contractForm.showError("");
    contractForm.setWorkquota(String.valueOf(contract.getWorkquota()));
    contractForm.setStartDate(Parser.parseDateCalendarToString(contract.getStartDate()));
    if (contract.getEndDate() != null) {
      contractForm.setEndDate(Parser.parseDateCalendarToString(contract.getEndDate()));
    }
  }
}
