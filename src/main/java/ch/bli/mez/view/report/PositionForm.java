package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.bli.mez.view.DefaultForm;

public class PositionForm extends DefaultForm {
  private static final long serialVersionUID = 1L;

  private JTextField positions;

  public PositionForm() {
    setLayout(new BorderLayout(0, 0));

    JPanel positionsLabelPanel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) positionsLabelPanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    add(positionsLabelPanel, BorderLayout.NORTH);

    JLabel positionLabel = new JLabel("Positionen zusammenfassen im Report:");
    positionsLabelPanel.add(positionLabel);

    JPanel inputPanel = new JPanel();
    add(inputPanel, BorderLayout.CENTER);
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

    JPanel positionPanel = new JPanel();
    inputPanel.add(positionPanel);
    positionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    positions = new JTextField();
    positions.setToolTipText("Positionen müssen mit einem Komma getrennt werden. z.B 10,20,30");
    positionPanel.add(positions);
    positions.setColumns(40);

  }

  public List<String> getPositions() {
    String[] positions = this.positions.getText().split(",");
    List<String> positionsTrimed = new ArrayList<String>();
    for (String position : positions) {
      if (!"".equals(position)) {
        positionsTrimed.add(position.trim());
      }
    }
    return positionsTrimed;
  }

  @Override
  public Boolean validateFields() {
    return true;
  }

  @Override
  public void showAsCreateNew() {
    // not used

  }

  @Override
  public void cleanFields() {
    positions.setText("");
  }

}
