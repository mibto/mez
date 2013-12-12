package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import ch.bli.mez.model.Contract;
import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.ContractDAO;
import ch.bli.mez.util.Parser;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.employee.ContractForm;
import ch.bli.mez.view.employee.ContractTitlePanel;

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
    this.employee = employee;
    this.holidayRefreshListener = listener;
    this.model = new ContractDAO();
    createView();
  }

  public DefaultPanel getView() {
    return view;
  }

  private void createView() {
    this.view = new DefaultPanel();
    view.setListTitlePanel(new ContractTitlePanel());
    view.setCreateNewForm(createContractForm(null));
    for (Contract contract : model.getEmployeeContracts(employee)) {
      view.addForm(createContractForm(contract));
    }
  }

  private ContractForm createContractForm(Contract contract) {
    ContractForm form = new ContractForm();
    setContractFormActionListener(contract, form);
    if (contract == null) {
      form.showAsCreateNew();
    } else {
      form.setWorkquota(String.valueOf(contract.getWorkquota()));
      form.setStartDate(Parser.parseDateCalendarToString(contract.getStartDate()));
      if (contract.getEndDate() != null) {
        form.setEndDate(Parser.parseDateCalendarToString(contract.getEndDate()));
      }
    }
    return form;
  }

  private void updateContract(Contract contract, ContractForm form) {
    if (!validateFields(contract, form)) {
      return;
    }
    boolean isNewContract = false;
    if (contract == null) {
      isNewContract = true;
    }
    if (isNewContract) {
      contract = new Contract();
      contract.setEmployee(employee);
    }
    contract.setWorkquota(Integer.valueOf(form.getWorkquota()));
    contract.setStartDate(Parser.parseDateStringToCalendar(form.getStartDate()));
    Calendar endDate = null;
    if (!"".equals(form.getEndDate())) {
      endDate = Parser.parseDateStringToCalendar(form.getEndDate());
    }
    contract.setEndDate(endDate);
    if (isNewContract) {
      model.addContract(contract);
      view.addForm(createContractForm(contract));
      form.cleanFields();
    } else {
      model.updateContract(contract);
    }
    form.getParentPanel().showConfirmation(
        "Der Vertrag mit Startdatum " + Parser.parseDateCalendarToString(contract.getStartDate())
            + " wurde gespeichert");
  }

  protected Boolean validateFields(Contract contract, ContractForm form) {
    if (!form.validateFields()) {
      return false;
    }
    int workQuota = Integer.valueOf(form.getWorkquota());
    Calendar startDate = Parser.parseDateStringToCalendar(form.getStartDate());
    if (workQuota < 0 || workQuota > 100) {
      form.getParentPanel().showError("Das Pensum muss zwischen 0 und 100 sein");
      return false;
    }
    if (validateList(contract, model.getEmployeeContracts(employee, startDate))) {
      form.getParentPanel().showError("Das Startdatum überschneidet sich mit einem existierenden Vertrag");
      return false;
    }
    if ("".equals(form.getEndDate())) {
      if (validateList(contract, model.getEmployeeContractsWithoutEndDate(employee))) {
        form.getParentPanel().showError("Es darf höchstens ein Vertrag ohne Enddatum existieren");
        return false;
      }
    } else {
      Calendar endDate = Parser.parseDateStringToCalendar(form.getEndDate());
      if (startDate.after(endDate)) {
        form.getParentPanel().showError("Das Startdatum darf nicht vor dem Enddatum sein");
        return false;
      }
      if (validateList(contract, model.getEmployeeContracts(employee, endDate))) {
        form.getParentPanel().showError("Das Enddatum überschneidet sich mit einem existierenden Vertrag");
        return false;
      }
      if (validateList(contract, model.getEmployeeContracts(employee, startDate, endDate))) {
        form.getParentPanel().showError("Dieser Vertrag umhüllt einen bestehenden Vertrag, dies ist nicht erlaubt");
        return false;
      }
    }
    return true;
  }

  private Boolean validateList(Contract contract, List<Contract> list) {
    if (list.size() > 0 && (list.size() > 1 || (contract == null || list.get(0).getId() != contract.getId()))) {
      return true;
    }
    return false;
  }

  private void deleteContract(Contract contract, ContractForm form) {
    model.deleteContract(contract.getId());
    form.getParentPanel().removeForm(form);
    form.getParentPanel().showConfirmation("Der Vertrag wurde erfolgreich gelöscht");
  }

  private void setContractFormActionListener(final Contract contract, final ContractForm form) {
    form.setSaveListener(createContractFormSaveListener(contract, form));
    form.setDeleteListener(createContractFormDeleteListener(contract, form));
  }

  private ActionListener createContractFormSaveListener(final Contract contract, final ContractForm form) {
    return new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateContract(contract, form);
        holidayRefreshListener.actionPerformed(e);
      }
    };
  }

  private ActionListener createContractFormDeleteListener(final Contract contract, final ContractForm form) {
    return new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showOptionDialog(null, "Soll dieser Vertrag wirklich gelöscht werden?", "Vertrag löschen",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] { "Ja", "Nein" }, "Nein") == JOptionPane.YES_OPTION) {
          deleteContract(contract, form);
        } else {
          form.getParentPanel().showError("Der Vertrag wurde NICHT gelöscht");
        }
        holidayRefreshListener.actionPerformed(e);
      }
    };
  }
}