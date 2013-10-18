package ch.bli.mez.view;

import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollPanel extends JPanel {

	private static final long serialVersionUID = 3508706503558546692L;

	JScrollPane scrollPane;
	JPanel listContainer;
	
	/**
	 * Create the panel.
	 * Dieses Panel nimmt andere JPanels auf und listet diese untereinander an. Inkl. Scrollpanel
	 */
	public ScrollPanel() {
		setLayout(new CardLayout(0, 0));

		listContainer = new JPanel();
		listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS)); // Die Panels, welche hinzugefügt werden, werden untereinander angezeigt
		
		
		scrollPane = new JScrollPane(listContainer);
		add(scrollPane);


	}
	
	public void addPanelToList(JPanel newpanel){
		scrollPane.add(newpanel);
		scrollPane.revalidate();
	}

}
