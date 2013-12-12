package ch.bli.mez.view.report;

import java.awt.BorderLayout;

import ch.bli.mez.view.DefaultForm;
import ch.bli.mez.view.DefaultPanel;

public class ProjectPanel extends DefaultPanel {
  private static final long serialVersionUID = 9218067562902931609L;

  public ProjectPanel() {
    createPanel();
  }

  public void createPanel() {
  }

  @Override
  public void addForm(DefaultForm form) {
    getTopPanel().add(form, BorderLayout.CENTER);
    form.setParentPanel(this);
  }

}
