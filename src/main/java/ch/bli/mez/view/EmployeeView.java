package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class EmployeeView extends JPanel {

	/**
   * 
   */
	private static final long serialVersionUID = 8767516928379563985L;
	private JButton btnBlaa = new JButton("Blaa");
	private JButton btnBlubb = new JButton("Blubb");
	private HashMap<Integer, PanelWithMap> employeePanelMap = new HashMap<Integer, PanelWithMap>();
	private SearchPanel searchPanel;
	private JTabbedPane tabbedPane;

	public EmployeeView() {

		// @Zumbühl, ich habe ein SearchPanel kreiert, welches sowohl für diese
		// Klasse als auch für die Klasse ZeiterfassenView wiederverwendet
		// werden kann
		this.searchPanel = new SearchPanel();
		this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		
		this.setLayout(new BorderLayout());
		this.add(searchPanel, BorderLayout.NORTH);
		this.add(tabbedPane, BorderLayout.CENTER);
		

		// musterTab - muss weg, da die Tabs vom Controller mit der Methode
		// addEmployeeTab hinzugefügt werden sollten
		JPanel mitarbeiterTab = new JPanel();
		tabbedPane.addTab("New tab", null, mitarbeiterTab, null);
		mitarbeiterTab.add(btnBlubb);
		mitarbeiterTab.add(btnBlaa);

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
		employeePanelMap.put(id, tabPanel);
		tabbedPane.addTab(name, null, tabPanel, null);
	}

	private PanelWithMap getPanelById(Integer id) {
		return employeePanelMap.get(id);
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
