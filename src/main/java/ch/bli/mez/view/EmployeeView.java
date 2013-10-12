package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


import java.awt.event.ActionEvent;

public class EmployeeView extends JPanel {

	/**
   * 
   */
	private static final long serialVersionUID = 8767516928379563985L;
	private JButton btnBlaa = new JButton("MA eintragen");
	private JButton btnBlubb = new JButton("Blubb");
	
	private HashMap<Integer, EmployeePanel> employeePanels = new HashMap<Integer, EmployeePanel>();
	private HashMap<Integer, TimeTransferPanel> timeTransferPanels = new HashMap<Integer, TimeTransferPanel>();
	private HashMap<Integer, ContractPanel> contractPanels = new HashMap<Integer, ContractPanel>();
	
	private JTabbedPane tabbedPane;

	public EmployeeView(SearchPanel searchPanel) {

		// @Zumbühl, ich habe ein SearchPanel kreiert, welches sowohl für diese
		// Klasse als auch für die Klasse ZeiterfassenView wiederverwendet
		// werden kann
		this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		
		this.setLayout(new BorderLayout());
		this.add(searchPanel, BorderLayout.NORTH);
		this.add(tabbedPane, BorderLayout.CENTER);
		

		// musterTab - muss weg, da die Tabs vom Controller mit der Methode
		// addEmployeeTab hinzugefügt werden sollten
		JPanel mitarbeiterTab = new JPanel();
		tabbedPane.addTab("New tab", null, mitarbeiterTab, null);
		mitarbeiterTab.setLayout(new BorderLayout(0, 0));
		mitarbeiterTab.add(btnBlubb, BorderLayout.SOUTH);
		btnBlaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mitarbeiterTab.add(btnBlaa, BorderLayout.NORTH);

	}

	public void setName(String name) {
		this.btnBlaa.setText(name);
	}

	public void addEmployeeTab(String name, Integer id) {
		// Wird von Controller verwendet (internerKommentar)
		// Alle Felder müssen den Feldname zB.: "firstName" als name haben
		// newTextfield.setName(fieldname);
		// alle Buttons haben als Name die id des employees
		// newButton.setName(id);
		// zudem müssen die Felder der map des Panels hinzugefügt werden:
		// tabPanel.putComponent(fieldname, newTextfield)
		// so können die Felder eindeutig angesprochen und ausgelesen werden.

		JPanel tabPanel = new JPanel();
		tabbedPane.addTab(name, null, tabPanel, null);
		
		// Panel Employee erstellen mit den zwei "Unterpanels" Contract und TimeTransfer
		EmployeePanel empPanel = new EmployeePanel();
		employeePanels.put(id, empPanel);
		
		tabPanel.setLayout(new BorderLayout());
		tabPanel.add(empPanel, BorderLayout.CENTER);
		
		TimeTransferPanel timetransPanel = new TimeTransferPanel();
		timeTransferPanels.put(id, timetransPanel);
		empPanel.setExtraPanel(timeTransferPanels.get(id), new Rectangle(390, 20, 80, 260));

		ContractPanel contractPanel = new ContractPanel();
		contractPanels.put(id, contractPanel);
		empPanel.setExtraPanel(contractPanels.get(id), new Rectangle(25, 290, 540, 140));

		// Alle TEXTFELDER als Componenten hinzufügen
		empPanel.putComponent("lastname", employeePanels.get(id).getTextField_lastname());
		empPanel.putComponent("fistname", employeePanels.get(id).getTextField_firstname());
		empPanel.putComponent("street", employeePanels.get(id).getTextField_street());
		empPanel.putComponent("city", employeePanels.get(id).getTextField_city());
		empPanel.putComponent("plz", employeePanels.get(id).getTextField_plz());
		empPanel.putComponent("homeNumber", employeePanels.get(id).getTextField_homeNumber());
		empPanel.putComponent("mobileNumber", employeePanels.get(id).getTextField_mobileNumber());
		empPanel.putComponent("email", employeePanels.get(id).getTextField_email());

	}

	private PanelWithMap getEmployeePanelById(Integer id) {
		return employeePanels.get(id);
	}

	public JComponent getComponent(Integer id, String fieldname) {
		PanelWithMap employeePanel = getEmployeePanelById(id);
		return employeePanel.getComponentByName(fieldname);
	}

	
	public Object getFieldValue(Integer id, String fieldname) {
		// TODO: get Value by fieldtype
		return null;
	}

	public void setBlubbctionListener(ActionListener al) {
		btnBlubb.addActionListener(al);
	}

	public void setBlaaActionListener(ActionListener al) {
		btnBlaa.addActionListener(al);
	}

	public void setSaveNewEmployeeListener(ActionListener actionListener) {
		// Wird von Controller verwendet (internerKommentar)
	}

	public void setSaveChangedEmployeeListener(ActionListener actionListener) {
		// Wird von Controller verwendet (internerKommentar)
	}
}
