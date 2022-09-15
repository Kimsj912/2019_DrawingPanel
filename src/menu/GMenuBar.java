package menu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import drawingPanel.GDrawingPanel;
import global.Gconstants.EMemu;
import toolbarLeft.GColorMenu;
import toolbarLeft.GToolBar;

public class GMenuBar extends JMenuBar {
//	public class GMenuBar extends JPanel {
//	JTabbedPane
	private static final long serialVersionUID = 1L;

	// components
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GColorMenu colorMenu;
	public GColorMenu getColorMenu() {
		return colorMenu;
	}

	public void setColorMenu(GColorMenu colorMenu) {
		this.colorMenu = colorMenu;
	}


	// Association
	private GDrawingPanel drawingpanel;

	private GToolBar gToolBar;

	public void associate(GDrawingPanel drawingpanel, GToolBar gToolBar ) {
		this.drawingpanel = drawingpanel;
		this.gToolBar = gToolBar;
	}

	public GMenuBar() {
		// initialize attirbutes
//		super(JTabbedPane.TOP);
		BorderLayout bLayout = new BorderLayout();
//		bLayout.
//		this.setLayout(new BorderLayout());
		// initialize components
		
		
//		JMenuBar fileMenuBar = new JMenuBar(); 
		this.fileMenu = new GFileMenu(EMemu.fileMenu.getText());
		this.fileMenu.setMnemonic(java.awt.event.KeyEvent.VK_F);
//		fileMenuBar.add(EMemu.fileMenu.getText(),this.fileMenu);
//		fileMenuBar.add(this.fileMenu);
		this.add(this.fileMenu);

//		JTabbedPane jtp = new JTabbedPane();
		this.editMenu = new GEditMenu(EMemu.editMenu.getText());
		this.editMenu.setMnemonic(java.awt.event.KeyEvent.VK_E);
//		jtp.addTab(EMemu.editMenu.getText(),this.editMenu);
		this.add(this.editMenu);
		
//		
//		this.shapeMenu = new GShapeMenu();
//		jtp.addTab(EMemu.shapeMenu.getText(), this.shapeMenu);
//		
//		this.add(fileMenuBar, BorderLayout.WEST);
//		this.add(jtp, BorderLayout.CENTER);
//	
	}

	public void initialize() {
		//associate
		this.fileMenu.associate(drawingpanel);
		this.editMenu.associate(drawingpanel);
//		this.shapeMenu.associate(drawingpanel);
		
		//initialize
		this.fileMenu.initialize();
		this.editMenu.initialize();
//		this.shapeMenu.initialize();

	}


}
