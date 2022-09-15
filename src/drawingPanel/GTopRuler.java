package drawingPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.geom.Line2D;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GTopRuler extends JPanel {

	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingpanel;

	public GTopRuler() {
		this.setBackground(Color.LIGHT_GRAY);
		BoxLayout layout =new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(layout);
	}
	public void initialize() {
		double widthNum= this.drawingpanel.getWidth();
		for(double i =0; i<=widthNum;i=i+0.5) {
			java.awt.geom.Line2D.Double line = new java.awt.geom.Line2D.Double();
			line.setLine(i, 0, i, i);
		}
		
	}

	public void associate(GDrawingPanel drawingpanel) {
		this.drawingpanel = drawingpanel;
		
	}

}
