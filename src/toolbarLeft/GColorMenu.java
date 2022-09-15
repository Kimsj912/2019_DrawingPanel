package toolbarLeft;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.lang.reflect.InvocationTargetException;
//
//import javax.swing.JColorChooser;
import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//
//import drawingPanel.GDrawingPanel;
//import global.Gconstants.EColor;
//import global.Gconstants.EFileMemu;
//
public class GColorMenu extends JMenu {
//	private static final long serialVersionUID = 1L;
//	
//	private GDrawingPanel drawingpanel;
//	private GToolBar gt;
//	private Object gtoolBar;
//
//	public GColorMenu(String text) {
//		super(text);
//		ActionHandler actionHandler = new ActionHandler();
//
//		for (EColor eMenuitem : EColor.values()) {
//			JMenuItem menuitem = new JMenuItem(eMenuitem.getText());
//			menuitem.setActionCommand(eMenuitem.getMethod());
//			menuitem.addActionListener(actionHandler);
//			menuitem.setActionCommand(eMenuitem.getMethod());
//			this.add(menuitem);
//
//		}
//
//	}
//
//	public void initialize() {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void associate(GDrawingPanel drawingpanel, GToolBar gtoolbar) {
//		this.drawingpanel = drawingpanel;
//		this.gtoolBar = gtoolbar;
//	}
//
//	public void red() {
////		this.drawingpanel.getGraphics().setColor(Color.red);
//		this.drawingpanel.setStrokeColor(Color.red);
//	}
//
//	public void orange() {
//		this.drawingpanel.setStrokeColor(Color.orange);
//
//	}
//
//	public void yellow() {
//		this.drawingpanel.setStrokeColor(Color.yellow);
//
//	}
//
//	public void green() {
//		this.drawingpanel.setStrokeColor(Color.green);
//
//	}
//
//	public void blue() {
//		this.drawingpanel.setStrokeColor(Color.blue);
//
//	}
//
//	public void puple() {
//		this.drawingpanel.setStrokeColor(Color.magenta);
//
//	}
//
//	public void white() {
//		this.drawingpanel.setStrokeColor(Color.white);
//
//	}
//
//	public void black() {
//		this.drawingpanel.setStrokeColor(Color.black);
//
//	}
//
//	public void chooseC() {
//		Color selCr = JColorChooser.showDialog(null, "색선정", Color.blue); // null=스크린 중앙에 화면 나옴
//		this.drawingpanel.setStrokeColor(selCr);
//
//	}
//	public void fillred() {
//		this.drawingpanel.setFillColor(Color.red);
//	}
//
//	public void fillorange() {
//		this.drawingpanel.setFillColor(Color.orange);
//
//	}
//
//	public void fillyellow() {
//		this.drawingpanel.setFillColor(Color.yellow);
//
//	}
//
//	public void fillgreen() {
//		this.drawingpanel.setFillColor(Color.green);
//
//	}
//
//	public void fillblue() {
//		this.drawingpanel.setFillColor(Color.blue);
//
//	}
//
//	public void fillpuple() {
//		this.drawingpanel.setFillColor(Color.magenta);
//
//	}
//
//	public void fillwhite() {
//		this.drawingpanel.setFillColor(Color.white);
//
//	}
//
//	public void fillblack() {
//		this.drawingpanel.setFillColor(Color.black);
//
//	}
//
//	public void fillchooseC() {
//		Color selCr = JColorChooser.showDialog(null, "색선정", Color.blue); // null=스크린 중앙에 화면 나옴
//		this.drawingpanel.setFillColor(selCr);
//
//	}
//	private void invokeMethod(String name) {
//		try {
//			this.getClass().getMethod(name).invoke(this);
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
//				| SecurityException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//
//	private class ActionHandler implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			invokeMethod(e.getActionCommand());
//		}
//	}
//
}
