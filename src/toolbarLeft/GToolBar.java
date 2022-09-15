package toolbarLeft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import drawingPanel.GDrawingPanel;
import drawingPanel.GDrawingPanel.ELineFill;
import global.Gconstants.EToolBar;
import global.Gconstants.ETransformer;
import menu.GMenuBar;

public class GToolBar extends JToolBar {

	// Attribute
	private static final long serialVersionUID = 1L;

	// components
	private Vector<JButton> buttons;

	// Association
	private GDrawingPanel drawingpanel;

	private JButton fillbutton;
	private JButton linebutton;
	private GMenuBar gmenubar;

	private JButton changebutton;

	private JButton selectBtn;

	private int colorTransparency;

	private ImageIcon nullColorIcon;
	private Color nullcolor = new Color(255, 255, 255, 0);

	private ActionHandler actionHandler;

	public int getColorTransparency() {
		return colorTransparency;
	}

	public void setColorTransparency(int colorTransparency) {
		this.colorTransparency = colorTransparency;
	}

//	private JSlider transparcy;

//	private PropertyChangeListener ChangeHandler;

	public void associate(GDrawingPanel drawingpanel, GMenuBar gmenubar) {
		this.drawingpanel = drawingpanel;
		this.gmenubar = gmenubar;
	}

	public GToolBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ButtonGroup buttonGroup1 = new ButtonGroup();
		this.actionHandler = new ActionHandler();

		this.buttons = new Vector<JButton>();

		// select
		ImageIcon sNIcon = changeIcon(ETransformer.select.getImageAdrNor(), 20, 20);
		ImageIcon sPIcon = changeIcon(ETransformer.select.getImageAdrPrs(), 20, 20);
		this.selectBtn = new JButton(sNIcon);
		selectBtn.setRolloverIcon(sPIcon);
		selectBtn.setMargin(new Insets(0, 0, 0, 0));
		selectBtn.setToolTipText(ETransformer.select.getText());
		selectBtn = buttonsetting(selectBtn, null, "선택하기", 0, 0, 0, 0);
		buttons.add(selectBtn);
		this.add(selectBtn);

		for (EToolBar eToolBar : EToolBar.values()) {

			// normal
			ImageIcon NIcon = changeIcon(eToolBar.getImageAdrNor(), 20, 20);
			ImageIcon PIcon = changeIcon(eToolBar.getImageAdrPrs(), 20, 20);
			JButton button = new JButton(NIcon);
			button.setRolloverIcon(PIcon);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setToolTipText(eToolBar.getText());

			// event
			button = buttonsetting(button, null, eToolBar.name(), 0, 0, 0, 0);
			this.buttons.add(button);
			this.add(button);
			buttonGroup1.add(button);

		}

		this.addSeparator();

		// colorpanel
		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		// nullcolor시 사진
		this.nullColorIcon = changeIcon("image/nullColor.jpg", 20, 20);

		this.fillbutton = new JButton(); /////////////////////
		this.fillbutton = buttonsetting(this.fillbutton, Color.white, "채우기색", 10, 10, 20, 20);
		pnl.add(fillbutton);

		this.linebutton = new JButton();
		this.linebutton = buttonsetting(this.linebutton, Color.black, "선색", 0, 0, 20, 20);
		pnl.add(linebutton);

		ImageIcon NIcon = changeIcon("image/changecolor.jpg", 8, 8);
		this.changebutton = new JButton(NIcon);
		this.changebutton = buttonsetting(this.changebutton, Color.black, "색교환", 0, 22, 8, 8);
		changebutton.setMargin(new Insets(0, 0, 0, 0));
		pnl.add(changebutton);

		this.add(pnl);
	}

	public void initialize() {
		this.buttons.get(EToolBar.rectangle.ordinal()).doClick();
		this.setOrientation(VERTICAL);
	}

	public ImageIcon changeIcon(String adr, int width, int height) {
		ImageIcon adrIcon = new ImageIcon(adr);
		Image adrImg = adrIcon.getImage();
		Image adrImgchangedNImg = adrImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		return new ImageIcon(adrImgchangedNImg);
	}

	public JButton buttonsetting(JButton btn, Color backgroundcolor, String command, int bound1, int bound2, int bound3,
			int bound4) {
		btn.setBackground(backgroundcolor);
		btn.addActionListener(actionHandler);
		btn.setActionCommand(command);
		btn.setBounds(bound1, bound2, bound3, bound4);
		return btn;
	}

	public void setLineColorpane(Color graphicLineColor) {
		if (graphicLineColor.getTransparency() == nullcolor.getTransparency()
				/*& graphicLineColor.getRGB() == nullcolor.getRGB()*/) {
			linebutton.setIcon(nullColorIcon);
			this.linebutton.setBackground(Color.black);
		} else {
			linebutton.setIcon(null);
			linebutton.setBackground(
					new Color(graphicLineColor.getRed(), graphicLineColor.getGreen(), graphicLineColor.getBlue()));
		}
	}

	public void setFillColorpane(Color graphicFillColor) {
		if (graphicFillColor.getTransparency() == nullcolor.getTransparency()
				/*& graphicFillColor.getRGB() == nullcolor.getRGB()*/) {
			fillbutton.setIcon(nullColorIcon);
			fillbutton.setMargin(new Insets(0, 0, 0, 0));
			this.fillbutton.setBackground(Color.black);
		} else {
			fillbutton.setIcon(null);
			this.fillbutton.setMargin(new Insets(20, 20, 20, 20));
			fillbutton.setBackground(
					new Color(graphicFillColor.getRed(), graphicFillColor.getGreen(), graphicFillColor.getBlue()));
		}
	}

	public void linecolorpane() {
		Color selCr = JColorChooser.showDialog(null, "색선정", Color.blue); // null=스크린 중앙에 화면 나옴
		if (selCr != null) {
			this.drawingpanel.setStrokeColor(new Color(selCr.getRed(), selCr.getGreen(), selCr.getBlue()));
		}
		this.drawingpanel.elineFill = ELineFill.line;
	}

	public void fillcolorpane() {
		Color selCr = JColorChooser.showDialog(null, "색선정", Color.blue); // null=스크린 중앙에 화면 나옴
		if (selCr != null) {
			this.drawingpanel.setFillColor(new Color(selCr.getRed(), selCr.getGreen(), selCr.getBlue()));
		}
		this.drawingpanel.elineFill = ELineFill.fill;

	}

	public void changecolorpane() {
		Color fc = this.drawingpanel.graphicFillColor;
		Color lc = this.drawingpanel.graphicLinecolor;
		this.drawingpanel.setStrokeColor(fc);
		this.drawingpanel.setFillColor(lc);

	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("선색")) {
				linecolorpane();
			} else if (e.getActionCommand().equals("채우기색")) {
				fillcolorpane();
			} else if (e.getActionCommand().equals("색교환")) {
				changecolorpane();
			} else if (e.getActionCommand().equals("선택하기")) {
				drawingpanel.setTransformer(ETransformer.select.getMethod());
			} else {
				drawingpanel.setTransformer(ETransformer.drawer.getMethod());
				drawingpanel.setcurrentTool(EToolBar.valueOf(e.getActionCommand())); // 툴바가 쉐입을 아는건 안좋아.
			}

		}

	}

	public void doclickon(ETransformer eTransformer) {
		drawingpanel.setTransformer(eTransformer.getMethod());
		this.buttons.get(eTransformer.ordinal()).doClick();

	}
}
