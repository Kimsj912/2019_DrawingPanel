package menu;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;

import drawingPanel.GDrawingPanel;
import global.Gconstants.EEditMemu;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingpanel;

	public void associate(GDrawingPanel drawingpanel) {
		this.drawingpanel = drawingpanel;
	}

	public GEditMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();

		for (EEditMemu eEdititem : EEditMemu.values()) {
			JMenuItem edititem = new JMenuItem(eEdititem.getText());
			edititem.setActionCommand(eEdititem.getMethod());
			edititem.addActionListener(actionHandler);
			edititem.setActionCommand(eEdititem.getMethod());
			edititem.setToolTipText(eEdititem.getToolTipText());
			this.add(edititem);
			if (eEdititem.getKeye() != 0) {
				edititem.setAccelerator(KeyStroke.getKeyStroke(eEdititem.getKeye(), Event.CTRL_MASK));
			}
			if (eEdititem.equals(EEditMemu.udflipItem)  || eEdititem.equals(EEditMemu.pasteItem)) {
				this.addSeparator();
			}

		}
	}

	public void initialize() {

	}

	public void undo() {
		this.drawingpanel.undo();

	}

	public void redo() {
		this.drawingpanel.redo();

	}

	public void lrflip() {
		this.drawingpanel.lrflip();
	}
	public void udflip() {
		this.drawingpanel.udflip();
	}

	public void delete() {
		this.drawingpanel.delete();
	}

	public void cut() {
		this.drawingpanel.cut();
	}

	public void copy() {
		this.drawingpanel.copy();
	}

	public void paste() {
		this.drawingpanel.paste();
	}

	public void selectall() {
		this.drawingpanel.selectall();

	}

	public void group() {
		this.drawingpanel.group();

	}

	public void ungroup() {
		this.drawingpanel.ungroup();
	}

	public void toFront() {
		this.drawingpanel.toFront();

	}

	public void toBack() {
		this.drawingpanel.toBack();
	}

	private void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e1) {
			e1.printStackTrace();
		}
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			invokeMethod(e.getActionCommand());
		}
	}
}
