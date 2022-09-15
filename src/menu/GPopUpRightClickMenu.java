package menu;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.event.PopupMenuEvent;

import global.Gconstants.EEditMemu;

public class GPopUpRightClickMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	private JPopupMenu popupmenu;
	private Vector<JMenuItem> menuVector;

	public GPopUpRightClickMenu(MouseListener mousehandler) {

		this.menuVector = new Vector<JMenuItem>();
		this.setPopupSize(new Dimension(120, 240));

		for (EEditMemu eEditMenu : EEditMemu.values()) {
			if (!eEditMenu.equals(EEditMemu.selectall)) {
				JMenuItem menuitem = new JMenuItem(eEditMenu.getText());
				menuitem.addMouseListener(mousehandler);
				menuitem.setActionCommand(eEditMenu.getMethod());
				menuitem.setToolTipText(eEditMenu.getText());
				menuitem.setName(eEditMenu.getMethod());

				this.add(menuitem);
				this.menuVector.add(menuitem);
//				if(eEditMenu.getKeye()!=0) {
//					menuitem.setAccelerator(KeyStroke.getKeyStroke(eEditMenu.getKeye(), Event.CTRL_MASK));
//				}
				if (eEditMenu.equals(EEditMemu.udflipItem)/* || eEditMenu.equals(EEditMemu.redoItem) */
						|| eEditMenu.equals(EEditMemu.pasteItem) /* ||eEditMenu.equals(EEditMemu.ungroup) */) {
					this.addSeparator();
				}
			}

		}

	}

}
