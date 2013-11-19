package ch.bli.mez.view.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class PositionPanel extends JPanel {

  private static final long serialVersionUID = -8686804124961218430L;

  private JComboBox<missionComboBoxItem> missionComboBox;

  private JTextField codeTextField;
  private JTextField nameTextField;
  private JTextField commentTextField;

  private JButton saveButton;

  private JLabel messageLabel;

  private JPanel listPanel;

  public PositionPanel() {

    setLayout(new BorderLayout());

    // EntryPanel (north)
    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    JPanel missionChooserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(missionChooserPanel, BorderLayout.NORTH);

    JLabel missionLabel = new JLabel("Auftrag");
    missionChooserPanel.add(missionLabel);

    missionComboBox = new JComboBox<missionComboBoxItem>();
    missionChooserPanel.add(missionComboBox);

    JPanel fieldsPanel = new JPanel();
    topPanel.add(fieldsPanel, BorderLayout.CENTER);

    JLabel numberLabel = new JLabel("Position");
    fieldsPanel.add(numberLabel);

    codeTextField = new JTextField();
    codeTextField.setColumns(4);
    fieldsPanel.add(codeTextField);

    JLabel nameLabel = new JLabel("Name");
    fieldsPanel.add(nameLabel);

    nameTextField = new JTextField();
    nameTextField.setColumns(10);
    fieldsPanel.add(nameTextField);

    JLabel commentLabel = new JLabel("Kommentar");
    fieldsPanel.add(commentLabel);

    commentTextField = new JTextField();
    commentTextField.setColumns(25);
    fieldsPanel.add(commentTextField);

    saveButton = new JButton("Speichern");
    fieldsPanel.add(saveButton);

    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    // ListPanel (center)
    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));

    addGuiFeatureListener();
  }

  public void showConfirmation(String name) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(name + " wurde zur Liste hinzugefügt!");
    hideMessage();
  }

  public void showNameError() {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText("Name darf nicht leer sein");
    hideMessage();
  }

  private void hideMessage() {
    Timer timer = new Timer(1800, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        messageLabel.setText(" ");
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public void setSaveButtonListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  public String getCode() {
    return codeTextField.getText();
  }

  public String getPositionName() {
    return nameTextField.getText();
  }

  public String getComment() {
    return commentTextField.getText();
  }

  public void setComboBoxItems(HashMap<Integer, String> missionList) {
    missionComboBox.removeAllItems();
    for (Entry<Integer, String> entry : missionList.entrySet()) {

      missionComboBox.addItem(new missionComboBoxItem(entry.getKey(), entry
          .getValue()));
    }
    missionComboBox.setSelectedIndex(0);
  }

  public Integer getSelectedMission() {
    return ((missionComboBoxItem) missionComboBox.getSelectedItem()).getId();
  }

  public void addPositionListEntry(PositionListEntry positionListEntry) {
    listPanel.add(positionListEntry);
    listPanel.revalidate();
  }

  public void removePositionListEntry(PositionListEntry positionListEntry) {
    listPanel.remove(positionListEntry);
    listPanel.revalidate();
  }

  private void cleanFields() {
    codeTextField.setText("");
    nameTextField.setText("");
    commentTextField.setText("");
  }

  private void addGuiFeatureListener() {
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cleanFields();
      }
    });
    KeyListener enterKeyListener = new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          saveButton.doClick();
        }
      }
    };
    codeTextField.addKeyListener(enterKeyListener);
    nameTextField.addKeyListener(enterKeyListener);
    commentTextField.addKeyListener(enterKeyListener);
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