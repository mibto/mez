package ch.bli.mez.view;

import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollPanel extends JPanel {

	private static final long serialVersionUID = 3508706503558546692L;

	private JPanel listContainer;
	
	/**
	 * Create the panel.
	 * Dieses Panel nimmt andere JPanels auf und listet diese untereinander an. Inkl. Scrollpanel
	 */
	public ScrollPanel() {
		setLayout(new CardLayout(0, 0));

		listContainer = new JPanel();
		listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS)); // Die Panels, welche hinzugefügt werden, werden untereinander angezeigt
		
		JScrollPane scrollPane = new JScrollPane(listContainer);
		add(scrollPane);

	}
	
	public void addPanelToList(JPanel panel){
		
		System.out.println("Eintrag hinzuuu");
		listContainer.add(panel);
		listContainer.revalidate();
	}
	
	public void removePanelFromList(JPanel panel){
		listContainer.remove(panel);
		listContainer.revalidate();
	}

}
