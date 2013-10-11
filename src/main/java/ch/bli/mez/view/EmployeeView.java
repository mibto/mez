package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;

public class EmployeeView extends JPanel {

	/**
   * 
   */
	private static final long serialVersionUID = 8767516928379563985L;
	private JButton btnBlaa = new JButton("MA eintragen");
	private JButton btnBlubb = new JButton("Blubb");
	
	// @ Leandra: Hesch das so gmeint?
	// Map mit Employee-ID und (PANEL)EmployeePanel (Name, Aaresse) -> Diese Panel wird auf die PanelWithMap hinzugefügt
	private HashMap<Integer, EmployeePanel> employeePanels = new HashMap<Integer, EmployeePanel>();
	// Map mit Employee-ID und (PANEL)Verträge -> wird in die EmployeePanel hinzugefügt
	private HashMap<Integer, TimeTransferPanel> timeTransferPanels = new HashMap<Integer, TimeTransferPanel>();
	// Map mit Employee-ID und (PANEL)Ferienübertrag -> wird in die EmployeePanel hinzugefügt
	private HashMap<Integer, ContractPanel> contractPanels = new HashMap<Integer, ContractPanel>();
	
	// Tabelle mit Employee-ID und PanelWithMap
	private HashMap<Integer, PanelWithMap> employeePanelMaps = new HashMap<Integer, PanelWithMap>();
	
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

		PanelWithMap tabPanel = new PanelWithMap();
		employeePanelMaps.put(id, tabPanel);
		tabbedPane.addTab(name, null, tabPanel, null);
		
		// Panel Employee erstellen mit den zwei "Unterpanels" Contract und TimeTransfer
		EmployeePanel empPanel = new EmployeePanel();
		employeePanels.put(id, empPanel);
		
		TimeTransferPanel timetransPanel = new TimeTransferPanel();
		timeTransferPanels.put(id, timetransPanel);
		employeePanels.get(id).setExtraPanel(timeTransferPanels.get(id), "10, 4, 1, 15, fill, fill");

		ContractPanel contractPanel = new ContractPanel();
		contractPanels.put(id, contractPanel);
		employeePanels.get(id).setExtraPanel(contractPanels.get(id), "4, 22, 8, 2, fill, fill");
		
		
		//externe EmployeePanel setzen
		tabPanel.setPanel(employeePanels.get(id));

		// Alle TEXTFELDER als Componenten hinzufügen
		tabPanel.putComponent("lastname", employeePanels.get(id).getTextField_lastname());
		tabPanel.putComponent("fistname", employeePanels.get(id).getTextField_firstname());
		tabPanel.putComponent("street", employeePanels.get(id).getTextField_street());
		tabPanel.putComponent("city", employeePanels.get(id).getTextField_city());
		tabPanel.putComponent("plz", employeePanels.get(id).getTextField_plz());
		tabPanel.putComponent("homeNumber", employeePanels.get(id).getTextField_homeNumber());
		tabPanel.putComponent("mobileNumber", employeePanels.get(id).getTextField_mobileNumber());
		tabPanel.putComponent("email", employeePanels.get(id).getTextField_email());
		
		
	}

	private PanelWithMap getPanelById(Integer id) {
		return employeePanelMaps.get(id);
	}

	public JComponent getComponent(Integer id, String fieldname) {
		PanelWithMap employeePanel = getPanelById(id);
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
