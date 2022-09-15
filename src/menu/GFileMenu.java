package menu;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import drawingPanel.GDrawingPanel;
import global.Gconstants.EFileMemu;
import shape.GShape;

public class GFileMenu extends JMenu implements Printable {
//public class GFileMenu extends JPanel implements Printable {

	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingpanel;
	private File file;
	private File directory;

	public GFileMenu(String text) {
		super(text);
		this.directory = new File("C:\\Users\\YOUR\\Desktop");

		ActionHandler actionHandler = new ActionHandler();

		for (EFileMemu eMenuitem : EFileMemu.values()) {
			JMenuItem menuitem = new JMenuItem(eMenuitem.getText());
			menuitem.setActionCommand(eMenuitem.getMethod());
			menuitem.addActionListener(actionHandler);
			menuitem.setActionCommand(eMenuitem.getMethod());
			if (eMenuitem.getKeye() != 0) {
				menuitem.setAccelerator(KeyStroke.getKeyStroke(eMenuitem.getKeye(), Event.CTRL_MASK));
			}
			menuitem.setToolTipText(eMenuitem.getToolTipText());
			ToolTipManager m = ToolTipManager.sharedInstance();
			m.setInitialDelay(100);
			m.setDismissDelay(2000);
			this.add(menuitem);
			if (eMenuitem.equals(EFileMemu.openItem) || eMenuitem.equals(EFileMemu.saveAsItem)
					|| eMenuitem.equals(EFileMemu.print)) {
				this.addSeparator();
			}
		}

//		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//		FlowLayout fl = new FlowLayout();
//		fl.setAlignment(Label.LEFT);
//		fl.setHgap(0);
//		fl.setVgap(0);
//		this.setLayout(fl);
//		for (EFileMemu eMenuitem : EFileMemu.values()) {
//			JButton menuitem = new JButton(eMenuitem.getText());
//			menuitem.setActionCommand(eMenuitem.getMethod());
//			menuitem.addActionListener(actionHandler);
//			menuitem.setActionCommand(eMenuitem.getMethod());
//			menuitem.setFont(new Font("배달의민족 주아", Font.PLAIN, 8));
//			menuitem.setPreferredSize(new Dimension(60,80));
//			menuitem.setMargin(new Insets(0, 0, 0, 0));
////			if(eMenuitem.getKeye()!=0) {
////				menuitem.setAccelerator(KeyStroke.getKeyStroke(eMenuitem.getKeye(), Event.CTRL_MASK));
////			}
//			menuitem.setToolTipText(eMenuitem.getToolTipText());
//			ToolTipManager m = ToolTipManager.sharedInstance();
//			m.setInitialDelay(100);
//			m.setDismissDelay(2000);
//			this.add(menuitem);
//			if (eMenuitem.equals(EFileMemu.openItem) || eMenuitem.equals(EFileMemu.saveAsItem)
//					|| eMenuitem.equals(EFileMemu.print)) {
//				this.add(Box.createVerticalGlue());
//			}
//		}

	}

	public void associate(GDrawingPanel drawingpanel) {
		this.drawingpanel = drawingpanel;
	}

	public void initialize() {
		this.file = null;
	}

	private void clearDrawingPanel() {
		this.drawingpanel.clearall();
		this.drawingpanel.setUpdated(false);
	}

	private boolean isShapeExist() {
		Vector<GShape> shapeVector = (Vector<GShape>) this.drawingpanel.getShapeVector();
		if (shapeVector.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void nnew() {
		if (isShapeExist() & this.drawingpanel.isUpdated()) { // 도형이 있다면?
			int result = JOptionPane.showInternalConfirmDialog(null, "변경내용을 저장하시겠습니까?", "주의",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if (save()) {
					clearDrawingPanel();
					this.file = null;
				}
			} else if (result == JOptionPane.NO_OPTION) {
				clearDrawingPanel();
			}
		}
	}

	public void open() {

		JFileChooser fs = new JFileChooser(this.directory);
		fs.setDialogTitle("파일 열기");
		fs.setSelectedFile(this.file);
		fs.setFileFilter(new GFileTypeFilter(".xml", "xml File"));
		int reply = fs.showOpenDialog(this.drawingpanel);
		if (reply == JFileChooser.APPROVE_OPTION) {
			this.directory = fs.getCurrentDirectory();
			File minifile = fs.getSelectedFile();

			if (this.file == null) { //파일 자체가 없다.
				readfile(minifile);
			} else if (!(this.file.equals(minifile))) { // 파일자체가 없거나 다른파일을 열었따.
//				if (isShapeExist()) {
				if (this.drawingpanel.isUpdated()) {
					int result = JOptionPane.showConfirmDialog(null, "다른 파일을 열기 전에 저장하시겠습니까?", "파일 닫기",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION) { // 다른 파일 열기전에 저장할게요
						if (save()) {
							this.file = minifile;
							readfile(minifile);
						}
					} else if (result == JOptionPane.NO_OPTION) { // 다른 파일 열어도 저장안할거임. 그냥 날려요.
						this.file = minifile;
						readfile(minifile);
					}
				}
			} else if (this.file.equals(minifile)) { // 같은 파일을 열었다.
//				if( this.drawingpanel.isUpdated()) {
				int result = JOptionPane.showConfirmDialog(null, "원래 파일로 되돌릴까요?", "동일한 파일 오픈",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {// 같은 파일을 열었으니, 파일을 되돌려 주세요
					readfile(this.file);
//					}
				}
			}
		}

	}

	private void readfile(File file) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			Object object = objectInputStream.readObject();
			this.drawingpanel.setShapeVector(object);
			this.drawingpanel.repaint();
			objectInputStream.close();
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void pprint() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		PageFormat pf = pj.defaultPage();
		Paper paper = new Paper();
		double margin = 36; // half inch
		paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
		pf.setPaper(paper);
		try {
			pj.setPrintable(this, pf);
			if (pj.printDialog(printRequestAttributeSet)) {
				pj.print();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException { // paintable을 구현하기 위해 필요한 함수.여기에 뭘 넣을지
		if (pi != 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Serif", Font.PLAIN, 36));
		g2.setPaint(Color.black);

		for (GShape shape : (Vector<GShape>) this.drawingpanel.getShapeVector()) {
			shape.draw(g2);
		}
		Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf.getImageableWidth(),
				pf.getImageableHeight());
		g2.draw(outline);

		return PAGE_EXISTS;
	}

	public boolean save() {
		if (this.file != null) { // 저장할 파일 루트가 있어.
			if (this.drawingpanel.isUpdated()) {
				savefile();
			}
			return true;
		} else { // 저장할 파일 루트가 없어.
			saveAs();
			return true;
		}
	}

	public boolean saveAs() {
		JFileChooser fs = new JFileChooser(new File("C:\\Users\\YOUR\\Desktop"));
		fs.setDialogTitle("파일 저장");
		fs.setFileFilter(new GFileTypeFilter(".xml", "xml File"));
		int reply = fs.showSaveDialog(null);
		if (reply == JFileChooser.APPROVE_OPTION) {
			this.file = fs.getSelectedFile();
			return savefile();
		}
		return false;
	}

	private boolean savefile() { // 유저가 xml을 붙이면 xml이 안붙어서 나오도록 만들었다.
		try {
			String fs = file.toString();
			String f = "";
			for (int i = fs.length() - 1; i >= fs.length() - 4; i--) {
				f = fs.charAt(i) + f;
			}
			if (f.equals(".xml")) {
				BufferedOutputStream bots = new BufferedOutputStream(new FileOutputStream(file));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(bots);
				objectOutputStream.writeObject((this.drawingpanel.getShapeVector()));
				objectOutputStream.close();
			} else {
				BufferedOutputStream bots = new BufferedOutputStream(new FileOutputStream(file + ".xml"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(bots);
				objectOutputStream.writeObject((this.drawingpanel.getShapeVector()));
				objectOutputStream.close();
			}
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;

	}

	public void close() {
		int result = JOptionPane.showConfirmDialog(null, "파일을 닫기 전에 저장하시겠습니까?", "파일 닫기",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			save();
		} else if (result == JOptionPane.NO_OPTION) {
			System.exit(0);
		}

	}

	private void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e1) {
			// TODO Auto-generated catch block
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
