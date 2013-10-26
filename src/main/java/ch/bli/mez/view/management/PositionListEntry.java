package ch.bli.mez.view.management;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PositionListEntry extends JPanel {

	private static final long serialVersionUID = 7585160645657478969L;
	
	private JTextField numberTextField;
	private JTextField nameTextField;
	private JTextField commentTextField;
	private JTextField missionTextField;
	
	private JButton saveButton;
	private JButton statusButton;
	
	public PositionListEntry(boolean isActive){
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		numberTextField = new JTextField();
		numberTextField.setColumns(4);
		add(numberTextField);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		add(nameTextField);
		
		commentTextField = new JTextField();
		commentTextField.setColumns(25);
		add(commentTextField);
		
		missionTextField = new JTextField();
		missionTextField.setColumns(10);
		missionTextField.setEditable(false);
		add(missionTextField);
		
		saveButton = new JButton("Speichern");
		add(saveButton);
		
		statusButton = new JButton("Deaktivieren");
		add(statusButton);
		
		if(!isActive){
			setInactive();
		}
	}
	
	public void setInactive(){
		statusButton.setText("Aktivieren");
		// nicht fertig
	}
	
	public void setActive(){
		statusButton.setText("Deaktivieren");
		// nicht fertig
	}
	
	public void setSaveButtonListener(ActionListener actionListener){
		saveButton.addActionListener(actionListener);
	}
	
	public void setStatusButtonListener(ActionListener actionListener){
		statusButton.addActionListener(actionListener);
	}
	
	
	
	

}
