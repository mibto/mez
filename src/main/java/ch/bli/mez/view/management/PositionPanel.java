package ch.bli.mez.view.management;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import ch.bli.mez.view.DefaultPanel;

public class PositionPanel extends DefaultPanel {

  private static final long serialVersionUID = -8686804124961218430L;

  private JComboBox<missionComboBoxItem> missionComboBox;

  public PositionPanel() {
    setChooserPanel(createChooserPanel());
  }

  private JPanel createChooserPanel() {
    JPanel missionChooserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    missionComboBox = new JComboBox<missionComboBoxItem>();
    missionChooserPanel.add(missionComboBox);

    return missionChooserPanel;
  }

  public void setComboBoxItems(HashMap<Integer, String> missionList) {
    missionComboBox.removeAllItems();
    for (Entry<Integer, String> entry : missionList.entrySet()) {
      missionComboBox.addItem(new missionComboBoxItem(entry.getKey(), entry.getValue()));
    }
    missionComboBox.setSelectedIndex(0);
  }

  public Integer getSelectedMission() {
    return ((missionComboBoxItem) missionComboBox.getSelectedItem()).getId();
  }

  public class missionComboBoxItem {
    private Integer id;
    private String name;

    public missionComboBoxItem(Integer id, String name) {
      this.id = id;
      this.name = name;
    }

    public Integer getId() {
      return id;
    }

    @Override
    public String toString() {
      return name;
    }
  }
}