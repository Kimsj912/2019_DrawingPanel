package toolbarTop;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SpinnerUI;

import drawingPanel.GDrawingPanel;
import drawingPanel.GDrawingPanel.ELineFill;
import global.Gconstants.EColor;
import global.Gconstants.ETransformer;
import shape.GShape;
import toolbarLeft.GToolBar;

public class GHomePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GDrawingPanel drawingpanel;
	private GToolBar toolbar;
	private JButton lineBtn;
	private JButton fillBtn;
	private JPanel lineselectPane;
	private JPanel fillselectPane;
//	private ELineFill elineFill;
	private ActionEvent event;
	private int colorTransparency = 255;
	private JSlider ctScroll;

	private JTextField TransparcyNum;

	private JSpinner strokejps;

	private int strokeNum;
	private ImageIcon nullColorIcon;
	private Color nullcolor = new Color(255, 255, 255, 0);

//	public enum ELineFill {
//		line, fill;
//	}

	public GHomePanel() {
		// attribute
		ActionHandler actionHandler = new ActionHandler();
		ChangeHandler changehandler = new ChangeHandler();
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		this.setLayout(fl);

		// nullcolor시 사진
		ImageIcon nullColorIcon = new ImageIcon("image/nullColor.jpg");
		Image nullColorImg = nullColorIcon.getImage();
		Image nullColorImghangedNImg = nullColorImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		this.nullColorIcon = new ImageIcon(nullColorImghangedNImg);

		// 자식새끼들.
		JPanel ColorsPanel = new JPanel();
		JPanel lineFill = new JPanel();
//		lineFill.setLayout(new GridLayout(1, 2));

		this.lineselectPane = new JPanel();
		Border linesctborder = lineselectPane.getBorder();
		lineselectPane.setBorder(settingBorder(linesctborder, "선 색"));
		this.lineBtn = new JButton();
		this.lineBtn.setMargin(new Insets(20, 20, 20, 20));
		lineBtn.setActionCommand("linesct");
		lineBtn.addActionListener(actionHandler);
		lineselectPane.add(lineBtn);
		lineFill.add(lineselectPane);

		this.fillselectPane = new JPanel();
		Border fillsctborder = fillselectPane.getBorder();
		fillselectPane.setBorder(settingBorder(fillsctborder, "채우기 색"));
		this.fillBtn = new JButton();
		this.fillBtn.setMargin(new Insets(20, 20, 20, 20));
		fillBtn.setActionCommand("fillsct");
		fillBtn.addActionListener(actionHandler);
		fillselectPane.add(fillBtn);
		lineFill.add(fillselectPane);

		///////////////////////////////
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
		Border colorPanelborder = colorPanel.getBorder();
		colorPanel.setBorder(settingBorder(colorPanelborder, "색 선택"));

		JPanel uppercolorPanel = new JPanel();
		JPanel lowercolorPanel = new JPanel();

		int i = 0;
		for (EColor eColoritem : EColor.values()) {
			JButton colorbtn = new JButton();
			colorbtn.setMargin(new Insets(10, 10, 5, 5));
			colorbtn.setBackground(eColoritem.getColor());
			colorbtn.setActionCommand(eColoritem.getMethod());
			colorbtn.addActionListener(actionHandler);

			if (i < EColor.values().length / 2) {
				uppercolorPanel.add(colorbtn);
			} else {
				lowercolorPanel.add(colorbtn);
			}
			i++;
		}
		colorPanel.add(uppercolorPanel);
		colorPanel.add(lowercolorPanel);

		ColorsPanel.add(lineFill);
		ColorsPanel.add(colorPanel);

		this.add(ColorsPanel);

		//////////////////////////////////////////////////////////
		JPanel shapeOption = new JPanel();
		shapeOption.setLayout(new BoxLayout(shapeOption, BoxLayout.Y_AXIS));

		JPanel strokePanel = new JPanel();
		fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		strokePanel.setLayout(fl);

		JLabel strokethicklb = new JLabel("두께 조절      ");
		strokePanel.add(strokethicklb);

		SpinnerNumberModel SpinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
		this.strokejps = new JSpinner(SpinnerModel);
		this.strokejps.addChangeListener(changehandler);
		strokePanel.add(strokejps);

		shapeOption.add(strokePanel);
		//////////
		JPanel colorTransparcypane = new JPanel();
		JLabel ctscrolllb = new JLabel("투명도 조절");
		colorTransparcypane.add(ctscrolllb);

		this.ctScroll = new JSlider(JSlider.HORIZONTAL, 0, 255, 50);
		ctScroll.setPaintLabels(true);
		ctScroll.setPaintTicks(true);
		ctScroll.setPaintTrack(true);
		ctScroll.setMinorTickSpacing(50);
		ctScroll.setMinimum(0);
		ctScroll.setMaximum(255);
		ctScroll.addChangeListener(changehandler);
		colorTransparcypane.add(ctScroll);

		this.TransparcyNum = new JTextField(0);
		this.TransparcyNum.setMargin(new Insets(0, 0, 0, 0));
		this.TransparcyNum.addActionListener(actionHandler);
		this.TransparcyNum.setActionCommand("TransparcyNum");
		colorTransparcypane.add(this.TransparcyNum);
		shapeOption.add(colorTransparcypane);

		this.add(shapeOption);
	}

	public static Float convertToFloat(double doubleValue) {
		return (float) doubleValue;
	}

	public void associate(GDrawingPanel drawingpanel, GToolBar toolbar) {
		this.drawingpanel = drawingpanel;
		this.toolbar = toolbar;
	}

	public void initialize() {
		this.lineBtn.setBackground(this.drawingpanel.graphicLinecolor);
		this.fillBtn.setBackground(this.drawingpanel.graphicFillColor);
		ctScroll.setValue(255);
		this.TransparcyNum.setText("" + ctScroll.getValue());
		linesct();
	}
	/////////////

	public CompoundBorder settingBorder(Border border, String title) {
		MatteBorder b8 = new MatteBorder(0, 0, 0, 0, Color.white);
		Font font = new Font("돋움", Font.PLAIN, 10);
		TitledBorder b9 = new TitledBorder(border, title, TitledBorder.CENTER, TitledBorder.TOP, font, Color.black);
		CompoundBorder b10 = new CompoundBorder(b8, b9);
		return b10;
	}

	//////////////// 홈패널의 변경 관리
	public void colors() { // 색변경
		for (EColor ecolor : EColor.values()) {
			if (this.event.getActionCommand().equals(ecolor.getMethod())) {
				Color color = ecolor.getColor();
//				if (color == null) {
//					color = JColorChooser.showDialog(this, "색선정", Color.blue); // null=스크린 중앙에 화면 나옴
//					if (color == null) {
//						if (this.drawingpanel.elineFill.equals(ELineFill.line)) {
//							color = this.drawingpanel.graphicLinecolor;
//						} else {
//							color = this.drawingpanel.graphicFillColor;
//						}
//					}
//				}
				if (color == null) {
					if (this.drawingpanel.elineFill.equals(ELineFill.line)) {
						this.drawingpanel.setStrokeColor(new Color(this.drawingpanel.getstrokecolor().getRed(),
								this.drawingpanel.getstrokecolor().getGreen(),
								this.drawingpanel.getstrokecolor().getBlue(),0));
					} else {
						this.drawingpanel.setFillColor(new Color(this.drawingpanel.getfillcolor().getRed(),
								this.drawingpanel.getfillcolor().getGreen(),
								this.drawingpanel.getfillcolor().getBlue(),0));
					}
				} else {
					if (this.drawingpanel.elineFill.equals(ELineFill.line)) {
						this.drawingpanel.setStrokeColor(
								new Color(color.getRed(), color.getGreen(), color.getBlue(), this.colorTransparency));
					} else if (this.drawingpanel.elineFill.equals(ELineFill.fill)) {
						this.drawingpanel.setFillColor(
								new Color(color.getRed(), color.getGreen(), color.getBlue(), this.colorTransparency));
					}
				}
			}
		}
	}

	public void colortrans() { // 투명도 변경
		this.colorTransparency = this.ctScroll.getValue();
		this.TransparcyNum.setText("" + this.colorTransparency);
		if (this.drawingpanel.elineFill == ELineFill.line) {
			Color color = this.drawingpanel.getstrokecolor();
			this.drawingpanel.setStrokeColor(
					new Color(color.getRed(), color.getGreen(), color.getBlue(), this.colorTransparency));
		} else if (this.drawingpanel.elineFill == ELineFill.fill) {
			Color color = this.drawingpanel.getfillcolor();
			this.drawingpanel
					.setFillColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), this.colorTransparency));
		}
	}

	public void inputStrokeNum() { // 굵기 변경
		this.strokeNum = Integer.parseInt(this.strokejps.getValue().toString());
		this.drawingpanel.setStrokeThickness(strokeNum);
	}

	////////////////
	public void linesct() { // 라인 패널이 선택됨
		this.drawingpanel.elineFill = ELineFill.line;
		this.lineselectPane.setBackground(Color.LIGHT_GRAY);
		this.fillselectPane.setBackground(this.getBackground());
		this.ctScroll.setValue(this.drawingpanel.getstrokecolor().getAlpha());
		this.TransparcyNum.setText("" + this.drawingpanel.getstrokecolor().getAlpha());
	}

	public void fillsct() { // 필 패널이 선택됨.
		this.drawingpanel.elineFill = ELineFill.fill;
		this.fillselectPane.setBackground(Color.LIGHT_GRAY);
		this.lineselectPane.setBackground(this.getBackground());
		this.ctScroll.setValue(this.drawingpanel.getfillcolor().getAlpha());
		this.TransparcyNum.setText("" + this.drawingpanel.getfillcolor().getAlpha());

	}

	// 변경됨.
	public void setLineColorpane(Color graphicLineColor) { // 라인 컬러 색변경됨.
		if (graphicLineColor.getTransparency() == nullcolor.getTransparency()
				/*& graphicLineColor.getRGB() == nullcolor.getRGB()*/) {
			lineBtn.setIcon(nullColorIcon);
			lineBtn.setMargin(new Insets(0, 0, 0, 0));
			this.lineBtn.setBackground(Color.black);
		} else {
			lineBtn.setIcon(null);
			this.lineBtn.setMargin(new Insets(20, 20, 20, 20));
			lineBtn.setBackground(
					new Color(graphicLineColor.getRed(), graphicLineColor.getGreen(), graphicLineColor.getBlue()));
		}
		this.TransparcyNum.setText("" + graphicLineColor.getAlpha());
		this.ctScroll.setValue(graphicLineColor.getAlpha());

	}

	public void setFillColorpane(Color graphicFillColor) { // 필 컬러 색 변경됨.
		if (graphicFillColor.getTransparency() == nullcolor.getTransparency()
				/*& graphicFillColor.getRGB() == nullcolor.getRGB()*/) {
			fillBtn.setIcon(nullColorIcon);
			fillBtn.setMargin(new Insets(0, 0, 0, 0));
			this.fillBtn.setBackground(Color.black);
		} else {
			fillBtn.setIcon(null);
			this.fillBtn.setMargin(new Insets(20, 20, 20, 20));
			fillBtn.setBackground(
					new Color(graphicFillColor.getRed(), graphicFillColor.getGreen(), graphicFillColor.getBlue()));
		}
		this.TransparcyNum.setText("" + graphicFillColor.getAlpha());
		this.ctScroll.setValue(graphicFillColor.getAlpha());

	}

	public void setProperties(GShape currentShape) { // pane 변경
		this.strokeNum = (int) currentShape.getStrokeThickness();
		this.strokejps.setValue((int) currentShape.getStrokeThickness());
		if (this.drawingpanel.elineFill == ELineFill.fill) {
			this.ctScroll.setValue(currentShape.getGraphicFillColor().getAlpha());
			this.colorTransparency = currentShape.getGraphicFillColor().getAlpha();
			this.TransparcyNum.setText("" + currentShape.getGraphicFillColor().getAlpha());
		} else if (this.drawingpanel.elineFill == ELineFill.line) {
			this.ctScroll.setValue(currentShape.getGraphiccolor().getAlpha());
			this.colorTransparency = currentShape.getGraphiccolor().getAlpha();
			this.TransparcyNum.setText("" + currentShape.getGraphiccolor().getAlpha());
		}

	}

	private void invokeMethod(String name) {
		try {
			for (EColor ecolor : EColor.values()) {
				if (ecolor.getMethod().equals(name)) {
					this.colorTransparency = 255;
					name = "colors";
					break;
				}
			}
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
			event = e;
			invokeMethod(e.getActionCommand());

		}
	}

	private class ChangeHandler implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource().equals(ctScroll)) {
				colortrans();
			} else if (e.getSource().equals(strokejps)) {
				inputStrokeNum();
			}
		}

	}

}
