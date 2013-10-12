package ch.bli.mez.view;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class PanelWithMap extends JPanel {
	private static final long serialVersionUID = -8776121615582171474L;
	private HashMap<String, JComponent> componentMap;
	
	
	public PanelWithMap(){
		this.componentMap = new HashMap<String, JComponent>();
		setLayout(new BorderLayout(0, 0));
	}
	
	// externes Panel hinzufügen
	public void setPanel(JPanel panel){
		this.add(panel, BorderLayout.CENTER);
	}
	
	//
	public void putComponent(String name, JComponent component){
		componentMap.put(name, component);
	}
	
	public JComponent getComponentByName(String name){
		return componentMap.get(name);
	}
}
