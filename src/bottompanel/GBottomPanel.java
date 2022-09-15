package bottompanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import drawingPanel.GDrawingPanel;
import transformer.GTransformer;

public class GBottomPanel extends JPanel{
	
	private GDrawingPanel drawingpanel;
	private JLabel jlmousePoint;
	private JLabel jltool;
	public void associate(GDrawingPanel drawingpanel) {
		this.drawingpanel = drawingpanel;
	}
	public GBottomPanel() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		fl.setHgap(5);
		this.setLayout(fl);
		this.setBackground(Color.white);
//		this.setBorder(new LineBorder(Color.BLACK));
		
	//마우스 좌표
		JPanel jlmousePointPane = new JPanel();
		//아이콘 넣기.
		ImageIcon normalIcon = new ImageIcon("image/MouseCoordinates.jpg");
		Image normalImg = normalIcon.getImage();
		Image changedNImg = normalImg.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		ImageIcon NIcon = new ImageIcon(changedNImg);
		JButton mouseCoordinatesICB = new JButton(NIcon);
		mouseCoordinatesICB.setMargin(new Insets(0, 0, 0, 0));
		jlmousePointPane.add(mouseCoordinatesICB);
		
		//좌표넣기.
		jlmousePointPane.setSize(new Dimension(10, 40));
		jlmousePointPane.setBackground(Color.white);
		this.jlmousePoint = new JLabel(0+" , "+0);
		jlmousePointPane.add(jlmousePoint);
	
	//툴 	
		JPanel jltoolPane = new JPanel();
		jltoolPane.setSize(new Dimension(10, 40));
		jltoolPane.setBackground(Color.white);
		this.jltool = new JLabel();
		jltoolPane.add(jltool);
		
		
		
		this.add(jltoolPane);
		this.add(jlmousePointPane);
		
	}
	public void initialize() {
		
	}
	public void viewMousePoint(int x, int y) {
		this.jlmousePoint.setText(x+ " , "+y);
	}
	public void viewTool(GTransformer transformer) {
		this.jltool.setText(transformer.getClass().getSimpleName());
		
	}
}
